/**   
* Copyright (c) 2012 jrj. All rights reserved.
* @filename QueueConsumerTask.java 
* @project JrjAggregate
* @package com.jrj.android.pad.common 
* @date 2012-10-12 
* @author 	xinxuan.wang
*/
package com.jrj.android.pad.common;

import java.util.concurrent.PriorityBlockingQueue;

import android.os.AsyncTask;

import com.jrj.android.pad.model.BodyList;
import com.jrj.android.pad.model.ISendReq;
import com.jrj.android.pad.model.req.CommonReq;
/**
 * @classname QueueConsumerTask 
 * @author xinxuan.wang
 * @date 2012-10-12
 * @remark 
 * @description 数据消费者AsyncTask，将队列中的数据包组织成数据包列表发送，暂不使用
 */
public class QueueConsumerTask extends AsyncTask<Object, Integer, BodyList> {

	private final String className = this.getClass().getSimpleName();

	private PriorityBlockingQueue<CommonReq> queue;

	private ISendReq sendReq;

	public QueueConsumerTask(ISendReq sendReq,
			PriorityBlockingQueue<CommonReq> q) {
		this.sendReq = sendReq;
		this.queue = q;
	}

	@Override
	protected BodyList doInBackground(Object... params) {
//		Debug.d(className, "doInBackground:");
		// 生成数据列表
		BodyList reqBodyList = new BodyList();
		reqBodyList.encrypt = Constants.ENCRYPT_NONE;
		try {
//			Debug.d(className, "reqQueue size=" + queue.size());
			while (reqBodyList.getBodyNum() < Constants.LIST_MAX_BODY_NUM) {// 如果还未够上限，则继续添加
				// 如果队列中有数据则取数据，没有数据则挂起
				CommonReq reqBody = queue.take();
				//TODO 入队之前需调用接口中的refreshReceiveResp方法，使用缓存数据刷新

				//入BodyList
				reqBodyList.addBody(reqBody);
				if (queue.peek() == null) {// 如果队列空了，则直接发送
//					Debug.d(className,
//							"reqList size=" + reqBodyList.getBodyNum());
					return sendReq.processBodyList(reqBodyList);
				}
			}
//			Debug.d(className, "reqList size=" + reqBodyList.getBodyNum());
		} catch (InterruptedException e) {
			// TODO 如果入队出错，则应通知View层
			e.printStackTrace();
		}

		// 测试界面是否会无响应
		// try {
		// Thread.sleep(6000);
		// } catch (InterruptedException e) {
		// e.printStackTrace();
		// }

		return sendReq.processBodyList(reqBodyList);
	}

	@Override
	protected void onCancelled() {
		// TODO Auto-generated method stub
//		Debug.d(className, "onCancelled:");
		super.onCancelled();
	}

	@Override
	protected void onPostExecute(BodyList respList) {
//		Debug.d(className, "onPostExecute:");
		super.onPostExecute(respList);

		sendReq.refreshReceiveResp(respList);
		//启动另一个消费线程
		new QueueConsumerTask(sendReq, queue).execute();
	}

	@Override
	protected void onPreExecute() {
		super.onPreExecute();
//		Debug.d(className, "onPreExecute:");
	}

	@Override
	protected void onProgressUpdate(Integer... values) {
		// TODO Auto-generated method stub
//		Debug.d(className, "onProgressUpd/ate:" + values);
		super.onProgressUpdate(values);
	}
}