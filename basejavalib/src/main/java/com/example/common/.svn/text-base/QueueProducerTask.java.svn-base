/**   
* Copyright (c) 2012 jrj. All rights reserved.
* @filename QueueProducerTask.java 
* @project JrjAggregate
* @package com.jrj.android.pad.common 
* @date 2012-10-12 
* @author 	xinxuan.wang
*/
package com.jrj.android.pad.common;

import java.util.Iterator;
import java.util.concurrent.PriorityBlockingQueue;

import android.os.AsyncTask;

import com.jrj.android.pad.model.req.CommonReq;
import com.jrj.android.pad.model.req.JsonCommonReq;
import com.jrj.android.pad.model.req.RtReq;
/**
 * @classname QueueProducerTask 
 * @author xinxuan.wang
 * @date 2012-10-12
 * @remark 
 * @description  数据生产者AsyncTask，将数据包依据优先级放入队列
 */
public class QueueProducerTask extends AsyncTask<CommonReq, Integer, Integer> {

	private final String className = this.getClass().getSimpleName();

	private PriorityBlockingQueue<CommonReq> queue;

	private int reqClientObjId;

	public QueueProducerTask(PriorityBlockingQueue<CommonReq> q,
			int reqClientObjId) {
		this.queue = q;
		this.reqClientObjId = reqClientObjId;
	}

	@Override
	protected Integer doInBackground(CommonReq... params) {
//		Debug.d(className, "doInBackground:");
//		Debug.d(className, "reqQueue pre size=" + queue.size());
		// 一次性添加所有的
		// ArrayList<CommonReq> reqs = new
		// ArrayList<CommonReq>(Arrays.asList(params));
		// queue.addAll(reqs);
		// 逐个添加
		boolean isExist = false;
		for (CommonReq req : params) {
			if (req != null) {
				// 检查队列中是否有重复请求，如果没有才入队
				if (!queue.isEmpty() && req instanceof RtReq) {
					Iterator<CommonReq> it = queue.iterator();
					while (it.hasNext()) {
						CommonReq temp = it.next();
						if (temp instanceof RtReq
								&& ((RtReq) temp).reqStockCode
										.equals(((RtReq) req).reqStockCode)) {
//							Debug.d(className, "find RtReq exist, CommonReq="
//									+ req);
							isExist = true;
						}
					}
				}
				if (!isExist) {
					while (queue.size() == Constants.QUEUE_MAX_BODY_NUM) {// 如果队列已满，则休眠后再继续放置
						try {
//							Debug.d(className, "reqQueue full, sleep:"
//									+ Constants.QUEUE_SLEEP_TIME);
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
		}
//		Debug.d(className, "reqQueue after size=" + queue.size());
		return queue.size();
	}
}