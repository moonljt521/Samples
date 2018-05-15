/**   
* Copyright (c) 2012 jrj. All rights reserved.
* @filename NetConnection.java 
* @project JrjAggregate
* @package com.jrj.android.pad.common 
* @date 2012-10-12 
* @author 	xinxuan.wang
*/
package com.jrj.android.pad.common;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.Proxy.Type;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.net.URL;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.jrj.android.pad.model.BodyList;
/**
 * @classname NetConnection 
 * @author xinxuan.wang
 * @date 2012-10-12
 * @remark 
 * @description 网络连接工具类
 */
public class NetConnection {
	/** CMNET连接 */
	public static final byte CONNECT_DIRECT = 0;
	/** CMWAP连接 */
	public static final byte CONNECT_PROXY = 1;
	/**连接类型*/
	private byte connectType;

	/** 服务器IP */
	private String server;
	/** 服务器端口 */
	private short port;

	Context context;

	// private String connectionUrl;

	/**
	 * 发送缓存
	 */
	private byte[] sendBuffer = null;

	/**
	 * 接收头部信息缓存
	 */
	private byte[] recvHeadBuffer = new byte[Constants.MIN_HEAD_LEN];

	//	private byte recvHSHead[] = new byte[6];

	/**
	 * 是否连接标志，true为已经连接
	 */
	private boolean connected = false;

	/**
	 * 直连方式使用的Socket连接实例
	 */
	public Socket socket = null;
	/**
	 * 代理方式使用的HttpURLConnection连接实例
	 */
	private HttpURLConnection proxyConnection = null;

	/**
	 * 输入流
	 */
	private InputStream is = null;

	/**
	 * 输出流
	 */
	private OutputStream os = null;

	/**
	 * 接收包体缓存
	 */
	private byte[] recvBodyBuffer = new byte[Utility.RECV_BUFFER_SIZE];
	/** 是否是用户直接取消连接 */
	protected boolean isCancelByUser;

	/** 遇到错误重连次数 */
	final int i_errTime = 1;

	/** 没有可用网络 */
	public static final int NO_NET = -1;
	/** 网络错误 */
	public static final int NET_ERR = -2;
	/** 超时 */
	public static final int TIME_OUT = -3;

	/**
	 * 构造方法
	 * @param server
	 * @param port
	 */
	public NetConnection(String server, short port) {
		this.server = server;
		this.port = port;
	}

	/**
	 * 是否通过wifi直连，true为直接连接
	 */
	private boolean isWifi = true;

	public boolean wifiEnable() {
		return isWifi;
	}

	/**
	 * 断开连接
	 */
	public void disconnect() {
		try {
			if (is != null)
				is.close();
			if (os != null)
				os.close();
			if (socket != null)
				socket.close();
			if (proxyConnection != null)
				proxyConnection.disconnect();
		} catch (Exception e) {
		} finally {
			is = null;
			os = null;
			socket = null;
			proxyConnection = null;
		}

		connected = false;
//		Debug.d("Network_NetConnection", "disconnected!");
	}

	/**
	 * 连接网络
	 * @param context
	 * @return boolean
	 */
	public synchronized boolean connect(Context context) {
		// 断开网络连接
		disconnect();
		isCancelByUser = false;
		try {

			ConnectivityManager connectivityManager = (ConnectivityManager) context
					.getSystemService(Context.CONNECTIVITY_SERVICE);
			NetworkInfo ni = connectivityManager.getActiveNetworkInfo();
			if (ni == null) {
//				Debug.d("Network_NetConnection", "no activity network!");
				return connected;
			}
//			Debug.d("Network_NetConnection", "getActiveNetworkInfo:" + ni);

			if (ni.getType() == ConnectivityManager.TYPE_WIFI) {
				// 当前连接到WIFI
				// Debug.d("Network_NetConnection",
				// "ActiveNetworkType: TYPE_WIFI!");
				connectType = CONNECT_DIRECT;
				isWifi = true;
			} else if (ni.getType() == ConnectivityManager.TYPE_MOBILE) {
				// 当前连接到手机网络
				// Debug.d("Network_NetConnection",
				// "ActiveNetworkType: TYPE_MOBILE!");
				// Debug.d("Network_NetConnection",
				// "Host:"+android.net.Proxy.getDefaultHost()+", Port:"+android.net.Proxy.getDefaultPort());
				connectType = (android.net.Proxy.getDefaultHost() == null) ? CONNECT_DIRECT
						: CONNECT_PROXY;
				isWifi = false;
			} else {
				// 无可用连接
				// Debug.d("Network_NetConnection",
				// "ActiveNetworkType: unknown!");
				isWifi = false;
				return connected;
			}

			if (connectType == CONNECT_PROXY) {

//				Debug.d("Network_NetConnection", "connectType: CONNECT_PROXY");
				// 使用代理方式联网CMWAP
				URL url = new URL("http://" + server + ":" + port + "/");
				// 创建一个 URL 连接，如果有代理的话可以指定一个代理。
				proxyConnection = (HttpURLConnection) url
						.openConnection(new Proxy(Type.HTTP,
								new InetSocketAddress(android.net.Proxy
										.getDefaultHost(), android.net.Proxy
										.getDefaultPort())));
				// 在开始和服务器连接之前，可能需要设置一些网络参数
				proxyConnection.setConnectTimeout(10000);
				// Debug.d("Network_NetConnection",
				// "setConnectTimeout:30 seconds");
				proxyConnection.setRequestMethod("POST");
				// Debug.d("Network_NetConnection", "setRequestMethod:POST");
				proxyConnection.setDoOutput(true);
				// proxyConnection.addRequestProperty("User-Agent",
				// "J2me/MIDP2.0");
				// Debug.d("Network_NetConnection",
				// "addRequestProperty:User-Agent=J2me/MIDP2.0");
				// proxyConnection.addRequestProperty("X-Online-Host",server+":"+port);
				// proxyConnection.addRequestProperty("Connection", "close");
				// 连接到服务器
				proxyConnection.connect();

				// Debug.d("Network_NetConnection", "connect!");
				// is = proxyConnection.getInputStream();
				// Debug.d("Network_NetConnection", "getInputStream!");
				os = proxyConnection.getOutputStream();
				// Debug.d("Network_NetConnection", "getOutputStream!");
				if (os == null)
					throw new java.io.IOException("connect error!");
			} else {
//				Debug.d("Network_NetConnection", "connectType: CONNECT_DIRECT");
				// 直连方式联网CMNET
				socket = new Socket(server, port);
				socket.setSoTimeout(10000);// 10秒超时
				is = socket.getInputStream();
				os = socket.getOutputStream();
				if (is == null || os == null)
					throw new java.io.IOException("connect error!");
			}
//			Debug.d("Network_NetConnection", "connected!");
			connected = true;
		} catch (Exception e) {
//			Debug.d("Network_NetConnection", "connected failed:" + e);
			disconnect();
		}
		return connected;
	}

	// void createConnetionUrl()
	// {
	// connectionUrl = "socket://" + server + ":" + String.valueOf(port);
	// }
	/**
	 * 从某个 InputStream 接收指定字节数目的数据，返回 true ,false ,不抛出异常 is InputStream buffer
	 * 缓冲区 offset 存储偏移位置 recvBytesCount 接收字节数目
	 */
	private boolean recvBytes(InputStream is, byte[] buffer, int offset,
			int recvBytesCount) {
		// 缓冲区不够
		if (buffer.length - offset < recvBytesCount) {
//			Debug.d("Network_NetConnection", "recvBytes error: buffer.length("
//					+ buffer.length + ") - offset(" + offset
//					+ ") < recvBytesCount(" + recvBytesCount + ")");
			return false;
		}
		int recvBytes = 0;
		try {
			// 读取输入流
			while (recvBytes < recvBytesCount) {
				// 还有数据可读
				int recvBytesime = is.read(buffer, offset + recvBytes,
						recvBytesCount - recvBytes);
				if (recvBytesime <= 0) {
//					Debug.d("Network_NetConnection", "socket read return "
//							+ recvBytesime);
					return false;
				}
				recvBytes += recvBytesime;
			}
		} catch (SocketTimeoutException soe) {
//			Debug.d("Network_NetConnection", "recvBytes error: " + soe);
			return false;
		} catch (IOException ioe) {
//			Debug.d("Network_NetConnection", "recvBytes error: " + ioe);
			return false;
		} catch (Exception e) {
//			Debug.d("Network_NetConnection", "recvBytes error: " + e);
			return false;
		}

		// canvas.debugString1 = "Recv" + String.valueOf(recvBytes) + "total" +
		// String.valueOf(recvBytesCount);
//		if (recvBytes != recvBytesCount)
//			Debug.d("Network_NetConnection", "recvBytes error: recvBytes("
//					+ recvBytes + ") != recvBytesCount(" + recvBytesCount + ")");
		return recvBytes == recvBytesCount;
	}

	/**
	 * 发送数据包列表
	 * @param bodyList
	 * @throws IOException 
	 */
	private void sendData(BodyList bodyList) throws IOException {
		// currentErrorId = ErrorBody.ERROR_ID_PRE_PROCESS_PACKAGE;
		if (!bodyList.prepareReqData())
			return;

		// currentErrorId = ErrorBody.ERROR_ID_COPY_PACKAGE;
		if (sendBuffer == null || sendBuffer.length < bodyList.length)
			sendBuffer = new byte[bodyList.length];
		else {
			// 数据清0
			for (int ii = 0; ii < bodyList.length; ii++)
				sendBuffer[ii] = 0;
		}
		bodyList.copyBytes(sendBuffer, 0);

		// currentErrorId = ErrorBody.ERROR_ID_SEND_PACKAGE;
		os.write(sendBuffer, 0, bodyList.length);
		os.flush();
	}

	/**
	 * 接收数据包列表
	 * @return BodyList
	 * @throws IOException
	 */
	private BodyList recvData() throws IOException {
		// currentErrorId = ErrorBody.ERROR_ID_RECV_HEAD_BUFFER;
		if (connectType == CONNECT_PROXY) {
			int code = proxyConnection.getResponseCode();
			// canvas.debugString4 = "HttpCon RespCode " + String.valueOf( code
			// );
			if (code != HttpURLConnection.HTTP_OK) {
				throw new IOException("http getResponseCode!=HTTP_OK");
			}
			is = proxyConnection.getInputStream();
			if (is == null)
				throw new IOException("getInputStream error!");
		}

		byte[] packagesBuffer = null;

		int recvHeadBytes = 0;

//		Debug.d("Network_NetConnection", "recv data head");

		if (!recvBytes(is, recvHeadBuffer, recvHeadBytes,
				Constants.MIN_HEAD_LEN))
			throw new IOException("head recvBytes error!");

		// currentErrorId = ErrorBody.ERROR_ID_PARSE_HEAD;
		BodyList retPackageList = new BodyList();
		if (!retPackageList.parseHead(recvHeadBuffer, 0))
			throw new IOException("parseHead error!");

		if (retPackageList.headTag != Constants.PACKAGE_HEADTAG)
			throw new IOException("PACKAGE_HEADTAG(" + retPackageList.headTag
					+ ") error!");

		// currentErrorId = ErrorBody.ERROR_ID_RECV_BODY_BUFFER;
		int bodyLength = retPackageList.length - Constants.MIN_HEAD_LEN;

		if (bodyLength <= Utility.RECV_BUFFER_SIZE)
			packagesBuffer = recvBodyBuffer;
		else
			packagesBuffer = new byte[bodyLength];

		int recvBodyBytes = 0;
		if (!recvBytes(is, packagesBuffer, recvBodyBytes, bodyLength
				- recvBodyBytes))
			throw new IOException("body recvBytes error!");

		// currentErrorId = ErrorBody.ERROR_ID_PARSE_BODY;
		byte parseRet = retPackageList.parseBody(packagesBuffer, 0, bodyLength);
		if (parseRet != Constants.PACKAGE_LIST_OP_SUCC) {
			// if (parseRet == PackageList.PACKAGE_LIST_OP_SUCC)
			// // 解密错误
			// currentErrorId = ErrorBody.ERROR_ID_UNENCRYPT;
			throw new IOException("parseRet(" + parseRet + ") error!");
		}

		if (connectType == CONNECT_PROXY) {
			// 如果使用代理，则接收成功后断开连接
			disconnect();
		}

		return retPackageList;

	}

	/**
	 * 发送并接收数据包列表，为同步阻塞式通信
	 * @param bodyList
	 * @param context
	 * @return BodyList
	 */
	public synchronized BodyList processPackageList(BodyList bodyList,
			Context context) {
		// canvas.debugString3 = "";
		// int currentErrorId = ErrorBody.ERROR_ID_CONNECT;
		BodyList pList = null;
		for (int i = 0; i < i_errTime; i++) {
			try {
				if (!connected)
					connect(context);
				if (!connected)
					throw new IOException("connect error!");
//				Debug.d("Network_NetConnection", "Send data...");
				sendData(bodyList);
//				Debug.d("Network_NetConnection",
//						"Send data finished, waiting for response...");
				pList = recvData();
				break;
			} catch (Exception e) {
//				Debug.d("Network_NetConnection", "processPackageList error: "
//						+ e);
				disconnect();
			}
			//注释掉休眠时间及Retry，必要时再打开
			//			if (!packageList.canRetry)
			//				break;
			//			try {
			//				Thread.sleep(200);
			//			} catch (Exception e) {
			//			}
		}
		// Utility.LAST_ERROR_ID = currentErrorId;
		return pList;
	}

	/**
	 * 发送请求数据包列表
	 * @param bodyList
	 * @param context
	 */
	public void sendBodyList(BodyList bodyList, Context context) {
		this.context = context;
		for (int i = 0; i < i_errTime; i++) {
			try {
				if (!connected) {
					connect(context);
				}
				if (!connected) {
					throw new IOException("connect error!");
				}
//				Debug.d("Network_NetConnection", "Send data...");
				sendData(bodyList);
//				Debug.d("Network_NetConnection",
//						"Send data finished, waiting for response...");
				break;
			} catch (IOException e) {
				e.printStackTrace();
//				Debug.d("Network_NetConnection", "sendBodyList error: " + e);
				connect(context);
			}
			//之后可能启用bodyList.canRetry
			//			if (!bodyList.canRetry) {
			//				break;
			//			}
		}
	}

	/**
	 * 接收请求数据包列表
	 * @return BodyList
	 */
	public BodyList receiveBodyList() {
		BodyList bodyList = null;
		for (int i = 0; i < i_errTime; i++) {
			try {
//				Debug.d("Network_NetConnection", "begin receive response...");
				bodyList = recvData();
//				Debug.d("Network_NetConnection", "end receive response...");
				break;
			} catch (IOException e) {
				e.printStackTrace();
//				Debug.d("Network_NetConnection", "receiveBodyList error: " + e);
				disconnect();
				connect(context);
			}
		}
		return bodyList;
	}

	/**
	 * 用户主动断开连接
	 */
	public void disConByUser() {
		isCancelByUser = true;
		disconnect();
	}

	/**
	 * 获取连接方式
	 * @return byte
	 */
	public byte getConnectType() {
		return connectType;
	}

	/**
	 * 是否已经连接
	 * @return boolean
	 */
	public boolean isConnected() {
		if (socket == null) {
			return false;
		} else {
			return socket.isConnected();
		}
	}

	/**
	 * 缓存区是否有数据可以读取
	 * @return boolean
	 */
	public boolean isAvailable() {
		try {
			if (is != null) {
				return is.available() > 0;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}
}