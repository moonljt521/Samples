/**   
* Copyright (c) 2012 jrj. All rights reserved.
* @filename Utility.java 
* @project JrjAggregate
* @package com.jrj.android.pad.common 
* @date 2012-10-12 
* @author 	xinxuan.wang
*/
package com.jrj.android.pad.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Vector;

import android.graphics.Color;
/**
 * @classname Utility 
 * @author xinxuan.wang
 * @date 2012-10-12
 * @remark 
 * @description Utility 是工具类，集成静态工具函数和静态数据定义 可以保存唯一一份全局数据，单件实例通过 GetInstance 静态方法取得
 */
public class Utility {
	// 单件实例
	static Utility utilityInstance = null;

	/**
	 * 取得单件实例
	 */
	public static Utility GetInstance() {
		if (utilityInstance == null) {
			utilityInstance = new Utility();
		}

		return utilityInstance;
	}

	/**
	 * protect 构造函数，防止外部创建实例
	 */
	protected Utility() {

	}

	// victor added
	/**
	 * 将整数转化为小数形式的字符串 data, 将要转化的整数 floatNum, 小数位数 keepFloatNum, 保留的小数位数
	 */
	public static String getFloatStr(long data, int floatNum, int keepFloatNum) {
		if (keepFloatNum > floatNum) {
			return null;
		}
		long intNum = 1;
		long intDiffNum = 1;
		StringBuffer floatStr = new StringBuffer();
		StringBuffer temp = null;
		int floatStart = 0;
		boolean isNegativeNumber = false;

		for (int i = 0; i < floatNum; i++) {
			intNum *= 10;
		}

		for (int i = 0; i < floatNum - keepFloatNum; i++) {
			intDiffNum *= 10;
		}
		if (data < 0) {
			isNegativeNumber = true;
			data = -data;
		}

		if (floatNum - keepFloatNum > 0
				&& data % intDiffNum >= 5 * intDiffNum / 10) { // 四舍五入
			data = (data / intDiffNum + 1) * intDiffNum;
		}

		temp = new StringBuffer(String.valueOf(data));
		if ((data >= 0 && data < intNum)) {

			floatStr.append("0.");
			while (temp.toString().length() < floatNum) {
				temp.insert(0, "0");
			}
			floatStr.append(temp.toString().substring(0, keepFloatNum));

		} else {

			floatStart = temp.toString().length() - floatNum;

			floatStr.append(temp.toString().substring(0, floatStart))
					.append(".")
					.append(temp.toString().substring(floatStart,
							floatStart + keepFloatNum));
		}
		if (isNegativeNumber) {
			floatStr.insert(0, "-");
		}

		return floatStr.toString();

	}

	// victor added
	/*
	 * public static int utilFuncGetPriceColor(int currentPrice, int
	 * yesterdayClosePrice){ int diff = currentPrice - yesterdayClosePrice;
	 * 
	 * return utilFuncGetPriceColor(diff); }
	 * 
	 * public static int utilFuncGetPriceColor(int value) { if (value > 0) {
	 * return colorPositive; } else if (value < 0) { return colorNegative; }
	 * else { return colorZero; } }
	 */
	public static int utilFuncGetPriceColor(int value) {
		if (value > 0) {
			return colorPositive2;
			// return DataMgr.COLOR_STOCK_RED;
		} else if (value < 0) {
			return colorNegative2;
			// return DataMgr.COLOR_STOCK_GREEN;
		} else {
			return colorZero2;
			// return DataMgr.COLOR_STOCK_SAME;
		}
	}

	// 系统参数块长度
	public static final int LENGTH = 12 + 4 + 33 + 1 + 1 + 1 + 1 + 1 + 4 + 1
			+ 4 + 1;

	// public static final int OPTION_PRODUCT_ID =2000100;

	// ----- OPTION OPTION_XXXXX -----------------------------------------------
	/** Option ProductId */
	public static final boolean DEBUG_MODE = false;

	/** 股票查询最大数目 */
	public static final int OPTION_QUERY_STOCK_NUM = 20;
	/** CMNET连接 */
	public static final byte OPTION_NET_CMNET = 0;
	/** CMWAP连接 */
	public static final byte OPTION_NET_CMWAP = 1;
	/** CMWAP代理连接 */
	public static final byte OPTION_NET_CMWAP_PROXY = 2;

	public static final byte OPTION_ENCRYPT = Constants.ENCRYPT_DES;
	// public static final byte OPTION_ENCRYPT = PackageList.ENCRYPT_NONE;
	public static final int OPTION_FORM_OBJ_INTERVAL = 10;
	private String systemOptionLogonSession = "";
	public boolean desKeyValid = false;
	public byte[] systemOptionDesKey = new byte[8];
	public static int LAST_ERROR_ID = 0;
	public static int RECV_BUFFER_SIZE = 10 * 1024;

	// ----- resXXXXX -----------------------------------------------
	// ---- 资源文件 ----
	// 多个地方同事使用的资源文件,放在Utility中可以避免多次创建
	/** 通讯状态 故障 */
	public static byte RES_IMG_COMMUNICATION_ERROR = 0;
	/** 通讯状态 联机 */
	public static byte RES_IMG_COMMUNICATION_LOGOUT = 1;
	/** 通讯状态 掉线 */
	public static byte RES_IMG_COMMUNICATION_LOGON = 2;
	/** 一共有多少种通讯状态 */
	public static byte RES_IMG_COMMUNICATION_STATUS_COUNT = 3;
	// /** 多行文本左滚动箭头 */
	// public Image resImgPageLeft;
	// /** 多行文本左滚动箭头 灰色 */
	// public Image resImgPageLeftGrey;
	// /** 通讯状态灯 */
	// public Image resImgLight;
	// /** 键盘 */
	// public Image resImgKeyboard;

	// ----- COLOR定义 COLOR_XXXX -----------------------------------------------
	// 真实调用后打开注释
	/** 红色 */
	public static final int COLOR_Red = 0x00ff0000;
	/** 绿色 */
	public static final int COLOR_Green = 0x0000ff00;
	/** 蓝色 */
	public static final int COLOR_Blue = 0x000000ff;
	/** 黑色 */
	public static final int COLOR_Black = 0x00000000;
	/** 白色 */
	public static final int COLOR_White = 0x00ffffff;
	/** 黄色 */
	public static final int COLOR_Yellow = 0x00ffff00;
	/** 紫色 */
	public static final int COLOR_Purple = 0x00ff00ff;
	/** 青色 */
	public static final int COLOR_Cyan = 0x0000ffff;
	/** 棕色 */
	public static final int COLOR_Brown = 0x00B00000;
	/** 灰色 */
	public static final int COLOR_Grey = 0x00c0c0c0;
	/** 亮黄 */
	public static final int COLOR_LightYellow = 0x00ffffc0;
	/** 亮红 */
	public static final int COLOR_LightRed = 0x00DE0000;
	/** 暗红 */
	public static final int COLOR_DarkRed = 0x00D00000;
	/** 粉红 */
	public static final int COLOR_Pink = 0x00ffC0C0;
	/** 砖红 */
	public static final int COLOR_BrickRed = 0x00ff8000;
	/** 深绿 */
	public static final int COLOR_DarkGreen = 0x0000C000;
	/** 暗绿 */
	public static final int COLOR_BlackGreen = 0x00008000;
	/** 亮绿 */
	public static final int COLOR_LightGreen = 0x0080ff80;
	/** 亮蓝 */
	public static final int COLOR_LightBlue = 0x008080ff;
	/** 深蓝 */
	public static final int COLOR_DarkBlue = 0x0000008c;
	/** 深灰 */
	public static final int COLOR_DarkGrey = 0x008c8c8c;
	/** 黑灰 */
	public static final int COLOR_VeryDarkGrey = 0x00606060;
	/** 亮灰 */
	public static final int COLOR_LightGrey = 0x00e1e1e1;
	/** 超亮灰 */
	public static final int COLOR_Light2Grey = 0x00F6F6F6;
	/** 暗青 */
	public static final int COLOR_BlackCyan = 0x00008c8c;
	/** 深青 */
	public static final int COLOR_DarkCyan = 0x0000c0c0;
	/** 亮紫 */
	public static final int COLOR_LightPurple = 0x00ff66ff;
	/** 阳线色 */
	public static int colorPositive = Color.RED;
	/** 阴线色 */
	public static int colorNegative = Color.GREEN;
	/** 平线色 */
	public static int colorZero = Color.WHITE;
	/** 阳线色 */
	public static int colorPositive2;
	/** 阴线色 */
	public static int colorNegative2;
	/** 平线色 */
	public static int colorZero2;
	/** 背景色 */
	public int colorBackground;
	/** 高亮背景色 */
	public int colorActiveBackground;
	/** 高亮背景色的边框色 */
	public int colorActiveBackgroundFocus;
	/** 水线色 */
	public int colorWaterLine;
	/** 索引线颜色 */
	public int colorIndex;
	/** 分时走势图色 */
	public int colorRt;
	/** 成交量色 */
	public int colorVolume;
	/** 普通文字色 */
	public int colorFontText;
	/** 标题字体色 */
	public int colorFontTitleText;
	/** 阳线文字色 */
	public int colorFontPositive;
	/** 阴线文字色 */
	public int colorFontNegative;
	/** 第一根线颜色 */
	public int colorLine1;
	/** 第二根线颜色 */
	public int colorLine2;
	/** 第三根线颜色 */
	public int colorLine3;
	/** 第四根线颜色 */
	public int colorLine4;
	/** 第五根线颜色 */
	public int colorLine5;
	/** 高亮色 */
	public int colorHighLight;
	/** 阴影色 */
	public int colorShadow;
	/** K线界面背景色 */
	public int colorKLineBackground;
	/** K线界面边界色 */
	public int colorKLineBorder;
	/** K线界面水线色 */
	public int colorKLineWaterLine;
	/** K线界面文字标签色 */
	public int colorKLineTextLabel;
	/** 报价页面文字标签色 */
	public int colorReportTextLabel;
	/** 九宫背景色 */
	public int colorJiuGongBackground;
	/** 九宫高亮背景色 */
	public int colorJiuGongBackgroundActive;
	/** 九宫高亮背景边界色 */
	public int colorJiuGongBackgroundActiveBorder;

	// ----- UTILITY_FUNCTION utilFuncXXXX -------------------------------------
	/** byte 数组转换成 16进制可显示的字符串 */
	public static String utilFuncByteToString(byte[] bBase) {
		int len = Math.min(bBase.length, 20);

		StringBuffer sb = new StringBuffer();
		for (int ii = 0; ii < len; ii++) {

			int intValue = bBase[ii];
			if (intValue < 0) {
				intValue += 0x100;
			}
			int d1 = intValue / 16;
			sb.append(d1 < 10 ? (char) ('0' + d1) : (char) ('A' + d1 - 10));
			int d2 = intValue % 16;
			sb.append(d2 < 10 ? (char) ('0' + d2) : (char) ('A' + d2 - 10));

			sb.append(' ');
		}

		return sb.toString();
	}

	/** 字符串是否是纯数字 */
	public static boolean utilFuncIsDigitString(String str) {
		for (int ii = 0; ii < str.length(); ii++) {
			if (str.charAt(ii) < '0' || str.charAt(ii) > '9') {
				return false;
			}
		}
		return true;
	}

	// 真实调用后打开注释
	/**
	 * 判断一个点是否在Rect区域内
	 * 
	 * @param x
	 *            触点x坐标
	 * @param y
	 *            触点y坐标
	 * @param l
	 *            区域原点(左上)x坐标
	 * @param t
	 *            区域原点(左上)y坐标
	 * @param w
	 *            区域宽
	 * @param h
	 *            区域高
	 * @return <li>true 触点在区域内</li> <li>false 触点在区域外</li>
	 * 
	 */
	public static boolean utilFuncPointInRect(int x, int y, int l, int t,
			int w, int h) {
		return x >= l && x <= l + w && y >= t && y <= t + h;
	}

	/**
	 *从一块数据缓冲区复制数据到另外一块缓冲区，可指定每块缓冲区的开始位置和复制数据长度
	 * 
	 * @param bDest
	 *            目的数据缓冲区
	 * @param offsetDest
	 *            目的数据缓冲区偏移量
	 * @param bSrc
	 *            原始数据缓冲区
	 * @param offsetSrc
	 *            原始数据缓冲区偏移量
	 * @param nLen
	 *            复制数据的长度
	 * @return <li>true 复制成功</li> <li>false 失败</li>
	 */
	public static boolean utilFuncByteCopyByte(byte[] bDest, int offsetDest,
			byte[] bSrc, int offsetSrc, int nLen) {
		if ((bDest == null) || (bSrc == null) || (nLen < 0)) {
			return false;
		}

		for (int n = 0; n < nLen; n++) {
			bDest[offsetDest + n] = bSrc[offsetSrc + n];
		}

		return true;
	}

	/**
	 * 取得ASCII字符串的缓冲区
	 * 
	 */
	public static byte[] utilFuncGetBytes(String str) {
		return str.getBytes();
	}

	/**
	 * 将Utf8字符串转换成正常字符串
	 */
	// public static String utilFuncUTF8toString(String source) throws
	// IOException
	// {
	// if ( SYSTEM_OPTION_FOR_NETBEAN )
	// return source;
	//
	// byte[] srcBytes = source.getBytes();
	//
	// int nLen = srcBytes.length;
	// StringBuffer stringbuffer = new StringBuffer("");
	// for(int j = 0; j < nLen; )
	// {
	// byte a = 0;
	// byte b = 0;
	// byte c = 0;
	// char temp = ' ';
	//
	// a = srcBytes[j++];
	// if(a > 0)
	// {
	// temp = (char)a;
	// }
	// else if ((a & 0xE0) == 0xC0)
	// {
	// if (j >= nLen)
	// throw new IOException("bad UTF format");
	//
	// b = srcBytes[j++];
	// if ((b & 0xC0) != 0x80)
	// throw new IOException("bad UTF format");
	//
	// temp = (char)(((a& 0x1F) << 6) | (b & 0x3F));
	// }
	// else if ((a & 0xF0) == 0xE0)
	// {
	// if (j >= nLen)
	// throw new IOException("bad UTF format");
	//
	// b = srcBytes[j++];
	// if ((b & 0xC0) != 0x80)
	// throw new IOException("bad UTF format");
	//
	// if (j >= nLen)
	// throw new IOException("bad UTF format");
	//
	// c = srcBytes[j++];
	// if ((c & 0xC0) != 0x80)
	// throw new IOException("bad UTF format");
	//
	// temp = (char)(((a & 0x0F) << 12) | ((b & 0x3F) << 6) | (c & 0x3F));
	// }
	// else
	// {
	// throw new IOException("bad UTF format");
	// }
	//
	// stringbuffer.append(temp);
	// }
	// return stringbuffer.toString();
	// }
	/**
	 * Unicode数据流 转 字符串 常常用来解析中文 len = 数据流长度，而不是字符串长度
	 * 
	 */
	public static String utilFuncUnicodeByte2String(byte[] bBase, int offset,
			int len) {
		StringBuffer strBuffer = new StringBuffer(len / 2);
		for (int ii = 0; ii < len / 2; ii++) {
			int intValue = utilFuncByte2short(bBase, offset + ii * 2);
			if (intValue == 0) {
				break;
			}
			strBuffer.append((char) (intValue));
		}

		return strBuffer.toString();
	}

	/**
	 * 字符串 转 Unicode数据流 常常用来保存中文 maxStringBufferLength =
	 * 可用来保存字符串的数据流长度，如果小于字符串长度的2倍，会返回失败
	 */
	public static boolean utilFuncString2UnicodeByte(byte[] bDest, int offset,
			String strSrc, int maxStringBufferLength) {
		if (bDest.length - offset < strSrc.length() * 2
				|| strSrc.length() * 2 > maxStringBufferLength) {
			return false;
		}

		for (int ii = 0; ii < strSrc.length(); ii++) {
			utilFuncInt2byte(bDest, offset, (int) strSrc.charAt(ii));
			offset += 2;
		}

		for (int ii = strSrc.length(); ii < maxStringBufferLength; ii++) {
			bDest[offset] = 0;
		}

		return true;
	}

	/**
	 * 将ASCII字符串复制到缓冲区中
	 * 
	 * @param bDest
	 *            目的缓冲区
	 * @param offset
	 *            缓冲区起始位置
	 * @param strSrc
	 *            原始字符串
	 * 
	 */
	public static boolean utilFuncAsciiString2Bytes(byte[] bDest, int offset,
			String strSrc) {
		return utilFuncByteCopyByte(bDest, offset, strSrc.getBytes(), 0,
				strSrc.length());
	}

	/**
	 * 从缓冲区中解析字符串
	 * 
	 * @param bBase
	 *            缓冲区
	 * @param offset
	 *            缓冲区起始位置
	 * @param stringLength
	 *            期望的字符串长度 有些手机空字符会显示乱码，所以用trim函数过滤掉
	 */
	public static String utilFuncBytes2AsciiString(byte[] bBase, int offset,
			int stringLength) {
		return new String(bBase, offset, stringLength).trim();
	}

	/**
	 * 将保存浮点数的整形数四舍五入 value 105432 && numOfDecimalFraction 3 => 原浮点数 105.432 结果
	 * = 105，返回结果是105000
	 * 
	 * @param value
	 *            整形数
	 * @param numOfDecimalFraction
	 *            整形数后面多少位认为是小数
	 */
	public static long utilFuncRound(long value, int numOfDecimalFraction)
			throws Exception {
		if (value == 0) {
			return 0;
		}

		if (numOfDecimalFraction <= 0) {
			return value;
		}

		if (numOfDecimalFraction >= 8) {
			throw new Exception(
					"parameter [numOfDecimalFraction] in func [Utility.round] is too large.");
		}
		int fixValue = 1;
		for (int ii = 0; ii < numOfDecimalFraction; ii++) {
			fixValue *= 10;
		}

		/* 正数四舍五入等于 + .5 后取整数，负数四舍五入等于 -.5后取整 */
		long tempValue;
		long retValue;
		if (value > 0) {
			tempValue = value + fixValue / 2;
			retValue = tempValue - tempValue % fixValue;
		} else {
			tempValue = value - fixValue / 2;
			retValue = tempValue - tempValue % fixValue;
		}

		return retValue;
	}

	public static boolean utilFuncString2UnicodeByte(byte[] bDest, int offset,
			String strSrc) {
		if (bDest.length - offset < strSrc.length() * 2) {
			return false;
		}

		for (int ii = 0; ii < strSrc.length(); ii++) {
			utilFuncShort2byte(bDest, offset, (short) strSrc.charAt(ii));
			offset += 2;
		}
		return true;
	}

	/**
	 * 将保存浮点数的整形数转换成字符串格式, 整型数后三位是小数
	 * 
	 * @param intValue
	 *            整型数
	 * @param numOfdecimalFraction
	 *            转换过程中需要保留小数的位数
	 */
	/*
	 * public static String utilFuncIntToPrice(int intValue,int
	 * numOfdecimalFraction) { int roundValue; try { if ( numOfdecimalFraction >
	 * 3) numOfdecimalFraction = 3; if ( numOfdecimalFraction < 0)
	 * numOfdecimalFraction = 0; roundValue = Utility.utilFuncRound(intValue, 3
	 * - numOfdecimalFraction); } catch ( Exception e ) { roundValue = intValue;
	 * } StringBuffer part1 = new StringBuffer(); if ( roundValue >= 0 ) {
	 * part1.append(roundValue / 1000).append("."); } else { roundValue =
	 * -roundValue; part1.append("-").append(roundValue / 1000).append("."); }
	 * StringBuffer part2 = new StringBuffer(); // 大于100的肯定保留两位小数（一般是指数） if (
	 * numOfdecimalFraction == 2 ) { // 两位小数 part2.append(roundValue % 1000 /
	 * 10); while (part2.length() < 2) part2.insert(0,'0'); return
	 * part1.toString() + part2.toString(); } else { // 三位小数
	 * part2.append(roundValue % 1000); while (part2.length() < 3)
	 * part2.insert(0,'0'); return part1.toString() + part2.toString(); } }
	 */
	/**
	 * 将保存浮点数的整形数转换成字符串格式, 整型数后三位是小数
	 * 
	 * @param intValue
	 *            整型数
	 * @param numOfdecimalFraction
	 *            转换过程中需要保留小数的位数
	 */
	public static String utilFuncIntToPrice(long intValue,
			int numOfdecimalFraction) {
		long roundValue;
		try {
			if (numOfdecimalFraction > 3) {
				numOfdecimalFraction = 3;
			}
			if (numOfdecimalFraction < 0) {
				numOfdecimalFraction = 0;
			}

			roundValue = Utility.utilFuncRound(intValue,
					3 - numOfdecimalFraction);

		} catch (Exception e) {
			roundValue = intValue;
		}

		StringBuffer part1 = new StringBuffer();
		if (roundValue >= 0) {
			part1.append(roundValue / 1000).append(".");
		} else {
			roundValue = -roundValue;
			part1.append("-").append(roundValue / 1000).append(".");
		}

		StringBuffer part2 = new StringBuffer();
		// 大于100的肯定保留两位小数（一般是指数）
		if (numOfdecimalFraction == 2) {
			// 两位小数

			part2.append(roundValue % 1000 / 10);
			while (part2.length() < 2) {
				part2.insert(0, '0');
			}

			return part1.toString() + part2.toString();
		} else {
			// 三位小数
			part2.append(roundValue % 1000);
			while (part2.length() < 3) {
				part2.insert(0, '0');
			}

			return (part1.toString() + part2.toString()).trim();
		}
	}

	public static String utilFuncIntToPriceDif(long intValue,
			int numOfdecimalFraction) {
		if (intValue > 0)
			return "+" + utilFuncIntToPrice(intValue, numOfdecimalFraction);
		return utilFuncIntToPrice(intValue, numOfdecimalFraction);
	}

	/**
	 * 将保存百分数的整形数转换成百分比字符串 12345 => 12.345％
	 * 
	 * @param intValue
	 *            整型数
	 * @param numOfdecimalFraction
	 *            转换过程中需要保留小数的位数
	 */
	public static String utilFuncIntToPercenet(int intValue,
			int numOfdecimalFraction) {
		// 强制保留2位
		// numOfdecimalFraction = 2;
		StringBuffer buffer = new StringBuffer();
		return buffer
				.append(utilFuncIntToPrice(intValue, numOfdecimalFraction))
				.append("%").toString();
		// ％
	}

	/**
	 * 将保存百分数的整形数转换成百分比字符串 12345 => 12.345％
	 * 
	 * @param intValue
	 *            整型数
	 * @param numOfdecimalFraction
	 *            转换过程中需要保留小数的位数
	 */
	public static String utilFuncIntToPercenet(long intValue,
			int numOfdecimalFraction) {
		// 强制保留2位
		// numOfdecimalFraction = 2;
		StringBuffer buffer = new StringBuffer();
		return buffer
				.append(utilFuncIntToPrice(intValue, numOfdecimalFraction))
				.append("％").toString();
	}

	/**
	 * int 转 成交量 超过1万单位用万 超过1亿单元用亿
	 */
	public static String utilFuncIntToVol(int intValue) {
		return utilFuncLongToVol((long) intValue);
	}

	/**
	 * long 转 成交量 超过1万单位用万 超过1亿单元用亿
	 */
	public static String utilFuncLongToVol(long intValue) {
		int intWAN = 10000;
		int intYI = 100000000;
		long intPart;
		long decimalPart;
		char chUnit = ' ';

		if (intValue >= intYI) {
			intPart = intValue / intYI;
			decimalPart = intValue / (intYI / 100) % 100;
			chUnit = '亿';
		} else if (intValue >= intWAN) {
			intPart = intValue / intWAN;
			decimalPart = intValue / (intWAN / 100) % 100;
			chUnit = '万';
		} else {
			intPart = intValue;
			decimalPart = 0;
		}

		StringBuffer sb = new StringBuffer(32);
		sb.append(intPart);
		if (intPart < 100 && decimalPart > 0) {
			// sb.append('.').append(decimalPart); // victor removed

			// victor added begin to fix bug 小数位显示错误。比如23.06显示成了23.6

			sb.append('.');
			// 因为此函数默认设置的小数位数就是2位的，所以只要判断小数位的数是否小于10，就可以确定是否需要添加0
			// 如果将来小数位数改变，整个函数需要另外修改
			if (decimalPart < 10) {
				sb.append(0);
			}
			sb.append(decimalPart);
			// victor added end.
		}
		if (chUnit != ' ') {
			sb.append(chUnit);
		}

		return sb.toString();
	}

	public static int utilFuncLongToZPB(long intValue) {
		long temp = intValue / 1000;
		int intWAN = 10000;
		int intYI = 100000000;
		if (temp > intYI)
			return 5;
		else if (temp > intWAN)
			return 9;
		else
			return 0;
	}

	public static String utilFuncIntToNum(int intValue) {
		StringBuffer sb = new StringBuffer();
		if (intValue >= 10000) {
			sb.append(intValue / 10000);
			sb.append('万');
		} else
			sb.append(intValue);
		sb.append('股');
		return sb.toString();
	}

	/**
	 * 将整形时间转换成 HH:MM:SS 格式的时间字符串
	 */
	public static String utilFuncIntToTime(int intTime, boolean showSecond) {
		int hour = intTime % 1000000 / 10000;
		int minute = intTime % 10000 / 100;
		int second = intTime % 100;
		StringBuffer stringBuffer = new StringBuffer();
		if (hour < 10) {
			stringBuffer.append("0");
		}
		stringBuffer.append(hour);
		if (minute < 10) {
			stringBuffer.append(":0");
		} else {
			stringBuffer.append(":");
		}
		stringBuffer.append(minute);
		if (showSecond) {
			if (second < 10) {
				stringBuffer.append(":0");
			} else {
				stringBuffer.append(":");
			}
			stringBuffer.append(second);
		}

		return stringBuffer.toString();
	}

	/**
	 * 将整形日期转换成 YYYY/MM/DD 格式的日期字符串
	 */
	public static String utilFuncIntToDate(int intDate) {
		int year = intDate / 10000;
		int month = intDate % 10000 / 100;
		int day = intDate % 100;
		StringBuffer stringBuffer = new StringBuffer();
		if (year < 10) {
			stringBuffer.append("0");
		}
		stringBuffer.append(year);
		if (month < 10) {
			stringBuffer.append("-0");
		} else {
			stringBuffer.append("-");
		}
		stringBuffer.append(month);
		if (day < 10) {
			stringBuffer.append("-0");
		} else {
			stringBuffer.append("-");
		}
		stringBuffer.append(day);

		return stringBuffer.toString();
	}

	public static String utilFuncIntToMonth(int intDate) {
		int year = intDate / 10000;
		int month = intDate % 10000 / 100;
		@SuppressWarnings("unused")
		int day = intDate % 100;
		StringBuffer stringBuffer = new StringBuffer();
		if (year < 10) {
			stringBuffer.append("0");
		}
		stringBuffer.append(year);
		// if (month < 10) {
		// stringBuffer.append("/0");
		// } else {
		// stringBuffer.append("/");
		// }
		stringBuffer.append("/");
		stringBuffer.append(month);

		return stringBuffer.toString();
	}

	/**
	 * 将整形日期转换成 YYYY-MM-DD 格式的日期字符串
	 */
	public static String utilFuncIntToChineseDate(int intDate) {
		int year = intDate / 10000;
		int month = intDate % 10000 / 100;
		int day = intDate % 100;
		StringBuffer stringBuffer = new StringBuffer();
		// stringBuffer.append("20");
		if (year < 10) {
			stringBuffer.append("200");
		}
		stringBuffer.append(year);
		if (month < 10) {
			stringBuffer.append("-0");
		} else {
			stringBuffer.append("-");
		}
		stringBuffer.append(month);
		if (day < 10) {
			stringBuffer.append("-0");
		} else {
			stringBuffer.append("-");
		}
		stringBuffer.append(day);

		return stringBuffer.toString();
	}

	/**
	 *2011-06-30T00:00:00+08:00 转换成2011-06-30
	 * @param s
	 * @return
	 */
	public static String getDate(String s) {
		if (s.length() > 10)
			return s.substring(0, 10);
		else
			return s;
	}

	// /**
	// * 取得当前日期，整形格式
	// */
	// public static int utilFuncGetCurrentDateInt() {
	// Calendar calendar = Calendar.getInstance();
	// int year = calendar.get(Calendar.YEAR);
	// int month = calendar.get(Calendar.MONTH) + 1;
	// int day = calendar.get(Calendar.DAY_OF_MONTH);
	// return year * 10000 + month * 100 + day;
	// }

	/**
	 * 取得当前日期,字符串格式
	 */
	// public static String utilFuncGetCurrentDateString() {
	// return utilFuncIntToDate(utilFuncGetCurrentDateInt());
	// }

	/**
	 * 分时242根走势索引Id转换成小时
	 */
	/*
	 * public static int utilFuncIndex2Hour(int nIndex) { if(nIndex < 121)
	 * return (nIndex + 570) / 60; else return (nIndex - 61) / 60 + 12; }
	 */
	/**
	 * 分时242根走势索引Id转换成分钟
	 */
	/*
	 * public static int utilFuncIndex2Minute(int nIndex) { if(nIndex < 121)
	 * return (nIndex + 570) % 60; else return (nIndex - 61) % 60; }
	 */
	/**
	 * 将一个字符串风格成多个子字符串，分割字符 delimiter
	 */
	public static String[] utilFuncSplit(String value, char delimiter) {
		char[] valueChars = value.toCharArray();
		int lastIndex = 0;
		Vector<String> strings = null;
		for (int i = 0; i < valueChars.length; i++) {
			char c = valueChars[i];
			if (c == delimiter) {
				if (strings == null) {
					strings = new Vector<String>();
				}
				strings.addElement(new String(valueChars, lastIndex, i
						- lastIndex));
				lastIndex = i + 1;
			}
		}
		if (strings == null) {
			return new String[] { value };
		}
		strings.addElement(new String(valueChars, lastIndex, valueChars.length
				- lastIndex));

		String[] strArry = new String[strings.size()];
		for (int i = 0; i < strings.size(); i++) {
			strArry[i] = strings.elementAt(i);
		}

		return strArry;
	}

	/**
	 * 删除字符串后面的空白字符（空格、'\u0000'）
	 */
	public static String utilFuncDeleteStringNullTail(String str) {
		String strRet = str.replace('\u0000', '\u0020');
		strRet = strRet.trim();
		return strRet;
	}

	/**
	 * 从字节流解析一个short
	 */
	public static short utilFuncByte2short(byte[] bBase, int offset) {
		try {
			short j = (short) (((bBase[offset + 1] & 0xff) << 8) + (bBase[offset] & 0xff));
			if (j == 0x7fff) {
				j = 0;
			}
			return j;
		} catch (NullPointerException ex) {
			return -1;
		}
	}

	/**
	 * 从字节流解析一个int
	 */
	public static int utilFuncByte2int(byte[] bBase, int offset) {
		try {
			int j = ((bBase[offset + 3] & 0xff) << 24)
					+ ((bBase[offset + 2] & 0xff) << 16)
					+ ((bBase[offset + 1] & 0xff) << 8)
					+ (bBase[offset] & 0xff);

			if (j == 0x7fffffff) {
				j = 0;
			}
			return j;
		} catch (NullPointerException _ex) {
			return -1;
		}
	}

	/**
	 * 从字节流解析一个long
	 */
	public static long utilFuncByte2long(byte[] bBase, int offset) {
		try {
			long j = ((long) (bBase[offset + 7] & 0xff) << 56)
					+ ((long) (bBase[offset + 6] & 0xff) << 48)
					+ ((long) (bBase[offset + 5] & 0xff) << 40)
					+ ((long) (bBase[offset + 4] & 0xff) << 32)
					+ ((long) (bBase[offset + 3] & 0xff) << 24)
					+ ((bBase[offset + 2] & 0xff) << 16)
					+ ((bBase[offset + 1] & 0xff) << 8)
					+ (bBase[offset] & 0xff);

			if (j == 0x7fffffffffffffffl) {
				j = 0;
			}
			return j;
		} catch (NullPointerException _ex) {
			return -1;
		}
	}

	/**
	 * 将short 存入字节流缓冲区
	 */
	public static void utilFuncShort2byte(byte[] buf, int offset, short value) {
		buf[offset] = (byte) (value & 0xff);
		buf[offset + 1] = (byte) ((value >> 8) & 0xff);
	}

	/**
	 * 将int 存入字节流缓冲区
	 */
	public static void utilFuncInt2byte(byte[] buf, int offset, int nValue) {
		int nTemp;
		int nCount;

		for (nCount = 0; nCount < 4; nCount++) {
			nTemp = nValue;
			buf[nCount + offset] = (byte) ((nTemp >> 8 * nCount) & 0xff);
		}
	}

	public static void utilFuncInt2byte2(byte[] buf, int offset, int nValue) {
		int nTemp;
		int nCount;

		for (nCount = 0; nCount < 4; nCount++) {
			nTemp = nValue;
			buf[3 + offset - nCount] = (byte) ((nTemp >> 8 * nCount) & 0xff);
		}
	}

	public static boolean AddAsNum(StringBuffer sb, int ch) {
		if (sb.length() == 0) {
			if (ch == '.') {
				return false;
			}
		} else {
			if (sb.charAt(0) == '0' && sb.length() == 1) {
				if (ch != '.') {
					return false;
				}
			}
			if (ch == '.') {
				if (sb.toString().indexOf('.') != -1) {
					return false;
				}
			}

		}
		return true;
	}

	public static int paserString2Int(String str) {
		int i = Integer.MIN_VALUE;
		try {
			i = Integer.parseInt(str);
		} catch (NumberFormatException e) {

		}
		return i;
	}

	public String getSystemOptionLogonSession() {
		return systemOptionLogonSession;
	}

	/*
	 * public void setSystemOptionLogonSession(String systemOptionLogonSession_)
	 * { this.systemOptionLogonSession = systemOptionLogonSession_ + '\0';
	 * 
	 * 
	 * byte[] byteProductKey = new byte[8];
	 * Utility.utilFuncAsciiString2Bytes(byteProductKey, 0,
	 * Utility.PRODUCT_KEY);
	 * 
	 * 
	 * if (systemOptionLogonSession.length() > 56) { for (int ii = 0; ii < 8;
	 * ii++) { byte byte1 = (byte) systemOptionLogonSession.charAt(ii * 8); byte
	 * byte2 = byteProductKey[ii]; systemOptionDesKey[ii] = (byte) (byte1 ^
	 * byte2); }
	 * 
	 * desKeyValid = true; } }
	 */
	/** 是否是香港股市 */
	public static boolean isHKMarket(byte market) {
		if (market == Constants.MARKET_HK_MAIN_BOARD
				|| market == Constants.MARKET_HK_CARVE_OUT_BOARD
				|| market == Constants.MARKET_HK_NASDAQ
				|| market == Constants.MARKET_HK_ETS) {
			return true;
		}
		return false;
	}

	/** 是否是香港指数 */
	public static boolean isHKIndex(String code) {
		if (code.toUpperCase().startsWith("I")) {
			return true;
		}
		return false;
	}

	/**
	 * 时间转化成文字
	 */
	public static String indexToText(int index_) {
		int hour = indexToHour(index_);
		int minute = indexToMinute(index_);
		StringBuffer timeBuffer = new StringBuffer();
		if (hour < 10)
			timeBuffer.append("0").append(String.valueOf(hour));
		else
			timeBuffer.append(String.valueOf(hour));
		if (minute < 10)
			timeBuffer.append(":0").append(String.valueOf(minute));
		else
			timeBuffer.append(":").append(String.valueOf(minute));

		return timeBuffer.toString();

	}

	/** 索引转换成时间 小时部分 */
	public static int indexToHour(int index) {
		if (index < 0 || index > 251)
			return -1;
		else if (index >= 0 && index <= 130) {
			return (index + 20) / 60 + 9;
		} else {
			return (index - 130) / 60 + 13;
		}
	}

	/** 索引转换成时间 分钟部分 */
	public static int indexToMinute(int index) {
		if (index < 0 || index > 251)
			return -1;
		else if (index < 10) {
			return (index + 15) % 60;
		} else if (index <= 130) {
			return (index + 20) % 60;
		} else {
			return (index - 130) % 60;
		}
	}

	/**
	 * 
	 * */
	public static boolean isOpenState(int time) {
		// HHMMSS
		// Date date = Calendar.getInstance().getTime();
		int h = time / 10000;
		int m = (time - h * 10000) / 100;
		// Log.d("Time", "Hour="+h+" Mintues="+m);
		if (h <= 9 || h >= 15)
			return false;
		else if (h == 9 && m < 15)
			return false;
		// else if (h == 15 && m > 15)
		// return false;
		return true;
	}

	/**
	 * 将int类型转换成  4/11   月份/日
	 */
	public static String utilFuncIntMD(int date) {
		int month = date % 10000 / 100;
		int day = date % 100;
		StringBuffer sb = new StringBuffer();
		sb.append(month);
		sb.append("/");
		sb.append(day);
		return sb.toString();

	}

	private static final SimpleDateFormat FORMATTER_INPUT = new SimpleDateFormat(
			"EEE MMM dd HH:mm:ss Z yyyy", Locale.US);
	private static final SimpleDateFormat FORMATTER_OUTPUT = new SimpleDateFormat(
			"MM月dd日 HH:mm");
	private static final SimpleDateFormat FORMATTER_INPUT2 = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");
	private static final long ONE_DAY_TIME = 24L * 60L * 60L * 1000L;

	/** 将服务器返回的时间格式转换成程序需要的格式 */
	public static String paserDate(String date) {
		try {
			Date myDate = FORMATTER_INPUT.parse(date);
			Date curDate = new Date();
			//判断是否时间过了一天

			long dif = curDate.getTime() - myDate.getTime();
			long temp = 0l;
			if (isToday(myDate)) {
				if (dif < 0) {
					return "1秒前";
				}

				temp = dif / (60 * 60 * 1000l);
				if (temp > 0) {

					return temp + "小时前";
				}
				temp = dif / (60 * 1000l);
				if (temp > 0) {

					return temp + "分钟前";
				}
				temp = dif / (1000l);
				if (temp > 0) {
					return temp + "秒前";
				}
			}

			return FORMATTER_OUTPUT.format(myDate);

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public static String paserDate2(String date) {
		try {
			Date myDate = FORMATTER_INPUT2.parse(date);
			Date curDate = new Date();
			long dif = curDate.getTime() - myDate.getTime();

			long temp = 0l;
			if (isToday(myDate)) {
				if (dif < 0) {
					return "1秒前";
				}

				temp = dif / (60L * 60L * 1000L);
				if (temp > 0) {

					return temp + "小时前";
				}
				temp = dif / (60 * 1000L);
				if (temp > 0) {

					return temp + "分钟前";
				}
				temp = dif / (1000L);
				if (temp > 0) {
					return temp + "秒前";
				}
			}

			return FORMATTER_OUTPUT.format(myDate);

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 判断传入的日期是否是今天
	 * @param mDate
	 * @return
	 */
	public static boolean isToday(Date mDate) {
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("dd");
		String todayStr = format.format(date);
		String mStr = format.format(mDate);

		SimpleDateFormat mm = new SimpleDateFormat("MM");
		SimpleDateFormat yy = new SimpleDateFormat("yyyy");
		if (!(yy.format(date).equals(yy.format(mDate)))) {
			return false;
		} else {

			if (!(mm.format(date).equals(mm.format(mDate)))) {
				return false;
			} else {
				if (todayStr.equals(mStr)) {
					return true;
				} else {
					return false;
				}

			}
		}
	}

	/** 获取去除网页链接标签后的字段 */
	public static String getBy(String by) {
		return by.replaceAll("<a[^>]+>|</a>", "");
	}

	/**
	 * 将浮点数，进行转换，万元  
	 * 
	 * 7.42068608E7>>7420.69
	 */
	public static String utilFuncDouble2Str(double d) {
		if (d == 0) {
			return "0.00";
		}
		String s = "" + Math.round(d / 100);//四舍五入
		StringBuffer sb = new StringBuffer();
		sb.append(s.substring(0, s.length() - 2));
		sb.append(".");
		sb.append(s.substring(s.length() - 2));
		return sb.toString();
	}

	/**
	 * 转换时间类型 20111020 》 2011-10-20
	 * @param s
	 * @return
	 */
	public static String getTime(String s) {
		StringBuffer sb = new StringBuffer();
		sb.append(s.subSequence(0, 4));
		sb.append("-");
		sb.append(s.subSequence(4, 6));
		sb.append("-");
		sb.append(s.subSequence(6, 8));

		return sb.toString();
	}

	/**
	 * 求数组的最大值
	 * @param array
	 * @return
	 */
	public static float max(float[] array) {
		float max = array[0];
		for (int i = 0; i < array.length; i++) {
			if (array[i] > max) {
				max = array[i];
			}
		}

		return max;
	}

	/**
	 * 求数组最小值
	 * @param array
	 * @return
	 */
	public static float min(float[] array) {
		float min = array[0];
		for (int i = 0; i < array.length; i++) {
			if (array[i] < min) {
				min = array[i];
			}
		}

		return min;
	}

	/**
	 * 求左边坐标单位高度
	 * @param i 传入的最大值
	 * @param div 分割的份数
	 * @return
	 */
	public static int unitNum(float i, int div) {
		i = (int) i / div;
		int num = (int) i;
		int length = (num < 0 ? num * -1 + "" : num + "").length();

		int j = 1;
		for (int t = 0; t < length - 1; t++) {
			j *= 10;
		}
		return (int) Math.ceil(i / j) * j;
	}

	/**
	 * 将传入的string数组，转换成一个float数组
	 * @param s
	 * @return
	 */
	public static float[] getStr2Float(String[] s) {
		float[] fs = new float[s.length];
		for (int i = 0; i < s.length; i++) {
			if (s[i] == null) {
				fs[i] = 0;
			} else {
				fs[i] = Float.parseFloat(s[i]);
			}
		}

		return fs;
	}

	/** 
	 *   提供精确的小数位四舍五入处理。 

	 *   @param   d   需要四舍五入的数字 

	 *   @param   i   小数点后保留几位 

	 *   @return   四舍五入后的结果 

	 */

	public static double round(double d, int i) {

		double dd = d * Math.pow(10, i);

		dd = Math.round(dd);

		dd = dd / Math.pow(10, i);

		return dd;

	}
}