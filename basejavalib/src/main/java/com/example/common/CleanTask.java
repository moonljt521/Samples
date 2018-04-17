/**   
* Copyright (c) 2012 jrj. All rights reserved.
* @filename CleanTask.java 
* @project JrjAggregate
* @package com.jrj.android.pad.common 
* @date 2012-10-12 
* @author 	xinxuan.wang
*/
package com.jrj.android.pad.common;

import java.util.Iterator;
import java.util.concurrent.PriorityBlockingQueue;

import android.os.Message;

import com.jrj.android.pad.model.BodyFactory;
import com.jrj.android.pad.model.BodyList;
import com.jrj.android.pad.model.ISendReq;
import com.jrj.android.pad.model.req.CommonReq;
import com.jrj.android.pad.model.req.QueryStockReq;
import com.jrj.android.pad.model.req.RtReq;
import com.jrj.android.pad.model.resp.RtResp;
/**
 * @classname CleanTask 
 * @author xinxuan.wang
 * @date 2012-10-12
 * @remark 
 * @description 清理队列线程
 */
public class CleanTask extends AbstrackTask implements Runnable {

	/**
	 * 队列实例
	 */
	PriorityBlockingQueue<CommonReq> queue;
	// private static Handler childHandler;

	/**
	 * 发送线程实例
	 */
	private SendTask sendTask;

	/**
	 * 接收线程实例
	 */
	private ReceiveTask receiveTask;

	/**
	 * 构造方法
	 * @param sendReq
	 * @param q
	 * @param sendTask
	 * @param receiveTask
	 */
	public CleanTask(ISendReq sendReq, PriorityBlockingQueue<CommonReq> q,
			SendTask sendTask, ReceiveTask receiveTask) {
		super(sendReq);
		this.queue = q;
		this.sendTask = sendTask;
		this.receiveTask = receiveTask;
	}

	// public static Handler getChildHandler() {
	// return childHandler;
	// }

	@Override
	public void run() {
		try {
//			Debug.d(className, "socket is connect=" + sendReq.isConnected());
			// 初始化消息循环队列，需要在Handler创建之前
			/*
			 * Looper.prepare(); childHandler = new Handler() {
			 * 
			 * @Override public void handleMessage(Message msg) {
			 * cleanQueue(msg.arg1); } }; // 启动子线程消息循环队列 Looper.loop();
			 */
			isRunning = true;

			// while (true) {
			while (isRunning) {
				//				Debug.d(className, "into cleanQueue");
				// 监测发送时间，如果在SEND_CONN_TIMEOUT分钟没有数据包发送，则发送维护连接数据包
				if (System.currentTimeMillis() - Constants.lastSend > Constants.SEND_CONN_TIMEOUT) {
					BodyList reqBodyList = new BodyList();
					reqBodyList.encrypt = Constants.ENCRYPT_NONE;
//					CommonReq body = BodyFactory
//							.createBodyById(Constants.REQID_JSON_NEWS_TOP_COLUMN_LIST);

					QueryStockReq body = (QueryStockReq) BodyFactory
							.createBodyById(Constants.REQID_KJAVA_QUERY_STOCK);
					body.byt_ReqMarket = (byte) 0;
					body.str_ReqUserInput ="000001";
					body.setClientObjId(-1);
					reqBodyList.addBody(body);
					sendReq.sendBodyList(reqBodyList);
					
					Constants.lastSend = System.currentTimeMillis();
//					Debug.d(className, "send REQID_JSON_NEWS_TOP_COLUMN_LIST ="
//							+ reqBodyList);
				}
				cleanQueue();
				Thread.sleep(Constants.QUEUE_CLEAN_SLEEP_TIME);
			}
			// }
		} catch (Exception t) {
			t.printStackTrace();
		}
	}

	/**
	 * 清理队列中超过Constants.QUEUE_BODY_MAX_SURVIVAL毫秒依然没有发送的数据包
	 */
	private void cleanQueue() {
		if (!queue.isEmpty()) {
//			Debug.d(className, "cleanQueue start, pause sendTask & receiveTask");
			pause();
			long now = System.currentTimeMillis();
			Iterator<CommonReq> it = queue.iterator();
			while (it.hasNext()) {
				CommonReq req = it.next();
				if (now - req.inQueueTime > Constants.QUEUE_BODY_MAX_SURVIVAL) {
//					Debug.d("jrjtest", "Clean Timeout req");
					sendTimeoutReq(req);
					it.remove();
				}
			}
			resume();
//			Debug.d(className, "cleanQueue end, resume sendTask & receiveTask");
		}
	}

	/**
	 * 清理队列中clientObjId为参数的数据包
	 * @param clientObjId
	 */
	public void cleanQueueByClientObjId(int clientObjId) {
		if (!queue.isEmpty()) {
			isRunning = false;
//			Debug.d(className,
//					"cleanQueue start, pause sendTask & receiveTask, clientObjId="
//							+ clientObjId);
			pause();
			Iterator<CommonReq> it = queue.iterator();
			while (it.hasNext()) {
				CommonReq req = it.next();
				if (req.getClientObjId() == clientObjId) {
//					Debug.d("jrjtest",
//							"Clean DestroyView req="
//									+ String.valueOf(clientObjId));
					sendTimeoutReq(req);
					it.remove();
				}
			}
			resume();
//			Debug.d(className,
//					"cleanQueue end, resume sendTask & receiveTask, clientObjId="
//							+ clientObjId);
			isRunning = true;
		}
	}

	/**
	 * 清理队列中与返回数据包同样请求数据的请求数据包
	 * @param resp
	 */
	public void cleanQueueByCommonResp(RtResp resp) {
		if (!queue.isEmpty()) {
			isRunning = false;
//			Debug.d(className,
//					"cleanQueue start, pause sendTask & receiveTask, CommonResp="
//							+ resp);
			pause();
			Iterator<CommonReq> it = queue.iterator();
			while (it.hasNext()) {
				CommonReq req = it.next();
				if (req instanceof RtReq
						&& ((RtReq) req).reqStockCode
								.equals(resp.retStock.stockCode)) {
					it.remove();
				}
			}
			resume();
//			Debug.d(className,
//					"cleanQueue end, resume sendTask & receiveTask, CommonResp="
//							+ resp);
			isRunning = true;
		}
	}

	/**
	 * 发送超时数据包消息给主线程，由主线程分发到来源UI
	 * @param req
	 */
	private void sendTimeoutReq(CommonReq req) {
//		Debug.d(className, "cleanQueue, clientObjId=" + req.getClientObjId()
//				+ ", clean=" + req);
		Message toMain = mainHandler.obtainMessage();
		toMain.obj = req;
		mainHandler.sendMessage(toMain);
	}

	/**
	 * 暂停发送及接收线程
	 */
	private void pause() {
		sendTask.isRunning = false;
		receiveTask.isRunning = false;
	}

	/**
	 * 恢复发送及接收线程
	 */
	private void resume() {
		sendTask.isRunning = true;
		receiveTask.isRunning = true;
	}
}