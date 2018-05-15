/**   
* Copyright (c) 2012 jrj. All rights reserved.
* @filename AbstrackTask.java 
* @project JrjAggregate
* @package com.jrj.android.pad.common 
* @date 2012-10-12 
* @author 	xinxuan.wang
*/
package com.jrj.android.pad.common;

import android.os.Handler;

import com.jrj.android.pad.model.ISendReq;
/**
 * @classname AbstrackTask 
 * @author xinxuan.wang
 * @date 2012-10-12
 * @remark 
 * @description 线程抽象类，所有自定义的线程类均应继承此类并实现Runnable接口
 */
public abstract class AbstrackTask {

	/**类名，用于在LogCat创建注释过滤器*/
	protected final String className = this.getClass().getSimpleName();
	/**ISendReq接口的实例*/
	protected ISendReq sendReq;
	/**是否已经进入run方法*/
	public boolean isRunning = false;
	//	/**是否可以发送请求包*/
	//	public boolean canSend = false;
	/**向主线程发送消息Handler，目前主要用于刷新UI*/
	protected static Handler mainHandler;

	/**
	 * 主线程Handler
	 * @param handler
	 */
	public static void setMainHandler(Handler handler) {
		mainHandler = handler;
	}

	/**
	 * 构造方法
	 * @param sendReq
	 */
	public AbstrackTask(ISendReq sendReq) {
		super();
		this.sendReq = sendReq;

	}
}