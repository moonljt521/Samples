/**   
* Copyright (c) 2012 jrj. All rights reserved.
* @filename InQueueTask.java 
* @project JrjAggregate
* @package com.jrj.android.pad.common 
* @date 2012-10-12 
* @author 	xinxuan.wang
*/
package com.jrj.android.pad.common;

import java.util.concurrent.PriorityBlockingQueue;

import com.jrj.android.pad.model.req.CommonReq;
import com.jrj.android.pad.model.req.JsonCommonReq;
/**
 * @classname InQueueTask 
 * @author xinxuan.wang
 * @date 2012-10-12
 * @remark 
 * @description 数据生产者线程，暂未用
 */
public class InQueueTask implements Runnable {

	private final String className = this.getClass().getSimpleName();

	private PriorityBlockingQueue<CommonReq> queue;

	private int reqClientObjId;

	private CommonReq[] params;

	public InQueueTask(PriorityBlockingQueue<CommonReq> q, int reqClientObjId,
			CommonReq... params) {
		this.queue = q;
		this.reqClientObjId = reqClientObjId;
		this.params = params;
	}

	@Override
	public void run() {
//		Debug.d(className, "doInBackground:");
//		Debug.d(className, "reqQueue pre size=" + queue.size());
		// 一次性添加所有的
		// ArrayList<CommonReq> reqs = new
		// ArrayList<CommonReq>(Arrays.asList(params));
		// queue.addAll(reqs);
		// 逐个添加
		for (CommonReq req : params) {
			if (req != null) {
				while (queue.size() == Constants.QUEUE_MAX_BODY_NUM) {// 如果队列已满，则休眠后再继续放置
					try {
//						Debug.d(className, "reqQueue full, sleep:"
//								+ Constants.QUEUE_SLEEP_TIME);
						Thread.sleep(Constants.QUEUE_SLEEP_TIME);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				req.setClientObjId(reqClientObjId);
				if (req instanceof JsonCommonReq) {
					((JsonCommonReq) req).initReqLength();
				}
				req.inQueueTime = System.currentTimeMillis();
				queue.put(req);
			}
		}
//		Debug.d(className, "reqQueue after size=" + queue.size());
	}
}