/**   
* Copyright (c) 2012 jrj. All rights reserved.
* @filename ReceiveTask.java 
* @project JrjAggregate
* @package com.jrj.android.pad.common 
* @date 2012-10-12 
* @author 	xinxuan.wang
*/
package com.jrj.android.pad.common;

import android.os.Message;

import com.jrj.android.pad.model.BodyList;
import com.jrj.android.pad.model.CommonBody;
import com.jrj.android.pad.model.ISendReq;
/**
 * @classname ReceiveTask 
 * @author xinxuan.wang
 * @date 2012-10-12
 * @remark 
 * @description 接收任务线程，只要Socket数据缓冲区有数据就读取，实例化为BodyList
 */
public class ReceiveTask extends AbstrackTask implements Runnable {

	public ReceiveTask(ISendReq sendReq) {
		super(sendReq);
	}

	@Override
	public void run() {
		isRunning = true;
		Message toMain = null;

//		Debug.d(className, "socket is connect=" + sendReq.isConnected());
		while (sendReq.isConnected()) {
			while (isRunning) {
				try {
					if (sendReq.isAvailable()) {
						BodyList respList = sendReq.receiveBodyList();
						if (respList != null) {

							// 更新缓存数据
							for (CommonBody resp : respList.getBodyVector()) {
//								Debug.d(className, "["
//										+ resp.getClass().toString() + "]"
//										+ resp.toString());

							}
							if (mainHandler != null) {
								toMain = mainHandler.obtainMessage();
								toMain.obj = respList;
								mainHandler.sendMessage(toMain);
							}
						}
					}
					Thread.sleep(Constants.QUEUE_SE_SLEEP_TIME);
				} catch (Exception e) {
					e.printStackTrace();
//					Debug.e(className, "ReceiveTask Error:" + e);
					continue;
				}
			}
		}
	}
}