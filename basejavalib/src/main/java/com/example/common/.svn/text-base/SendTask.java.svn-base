/**   
* Copyright (c) 2012 jrj. All rights reserved.
* @filename SendTask.java 
* @project JrjAggregate
* @package com.jrj.android.pad.common 
* @date 2012-10-12 
* @author 	xinxuan.wang
*/
package com.jrj.android.pad.common;

import java.util.concurrent.PriorityBlockingQueue;

import android.os.Message;

import com.jrj.android.pad.model.BodyList;
import com.jrj.android.pad.model.ISendReq;
import com.jrj.android.pad.model.req.CommonReq;
import com.jrj.android.pad.model.resp.CommonResp;
/**
 * @classname SendTask 
 * @author xinxuan.wang
 * @date 2012-10-12
 * @remark 
 * @description 发送任务线程，只要队列中有数据就组织成BodyList并通过Socket发送
 */
public class SendTask extends AbstrackTask implements Runnable {

	PriorityBlockingQueue<CommonReq> queue;

	public SendTask(ISendReq sendReq, PriorityBlockingQueue<CommonReq> q) {
		super(sendReq);
		this.queue = q;
	}

	@Override
	public void run() {
		isRunning = true;
		//		canSend = true;
		BodyList reqBodyList = null;
		CommonReq reqBody = null;
//		Debug.d(className, "socket is connect=" + sendReq.isConnected());
		while (sendReq.isConnected()) {
			while (isRunning) {
				// 生成数据列表
				reqBodyList = new BodyList();
				// 发送标志
				boolean isSended = false;
				reqBodyList.encrypt = Constants.ENCRYPT_NONE;
//				Debug.d(className, "reqQueue size=" + queue.size());
				try {
					while (reqBodyList.getBodyNum() < Constants.LIST_MAX_BODY_NUM) {// 如果还未够上限，则继续添加
						// 如果队列中有数据则取数据，没有数据则挂起
						reqBody = queue.take();

//						Debug.d(className, reqBody.getClass().getName());

						if (reqBody.needCache) {// 入BodyList之前使用缓存数据刷新
							sendCache(reqBody);
						}

						if (reqBody.needRealTime) {// 如果需要请求实时数据
							// 分时数据需要设置请求数据索引
							//							if (reqBody instanceof RtReq) {
							//								StockCode stockCode = ((RtReq) reqBody).reqStockCode;
							//								short reqIndex = DataMgr.getReqRtIndex(stockCode.code, stockCode.market);
							//								((RtReq) reqBody).reqIndex = reqIndex;
							//							}

							// 入BodyList
							reqBodyList.addBody(reqBody);
							if (queue.peek() == null) {// 如果队列空了，则直接发送
//								Debug.d("Network_NetConnection",
//										"reqList size="
//												+ reqBodyList.getBodyNum());
//								Debug.d(className, "reqList size="
//										+ reqBodyList.getBodyNum());
								sendReq.sendBodyList(reqBodyList);
								isSended = true;
								Constants.lastSend = System.currentTimeMillis();
								break;
							}
						}
					}
					if (!isSended) {
//						Debug.d("Network_NetConnection", "reqList size="
//								+ reqBodyList.getBodyNum());
//						Debug.d(className,
//								"reqList size=" + reqBodyList.getBodyNum());
						sendReq.sendBodyList(reqBodyList);
						Constants.lastSend = System.currentTimeMillis();
					}
					Thread.sleep(Constants.QUEUE_SEND_SLEEP_TIME);
				} catch (Exception e) {
					e.printStackTrace();
//					Debug.e(className, "SendTask Error:" + e);
					continue;
				}
			}
		}
	}

	/**
	 * 发送缓存数据
	 * 
	 * @param reqBody
	 */
	private void sendCache(CommonReq reqBody) {
		CommonResp resp = null;

		if (resp != null) {
			resp.setClientObjId(reqBody.getClientObjId());

			BodyList respList = new BodyList();
			respList.addBody(resp);
			Message toMain = mainHandler.obtainMessage();
			toMain.obj = respList;
			mainHandler.sendMessage(toMain);
		}
	}
}