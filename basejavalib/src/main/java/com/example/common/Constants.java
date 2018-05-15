/**   
* Copyright (c) 2012 jrj. All rights reserved.
* @filename Constants.java 
* @project JrjAggregate
* @package com.jrj.android.pad.common 
* @date 2012-10-12 
* @author 	xinxuan.wang
*/
package com.jrj.android.pad.common;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import android.content.Context;
import android.telephony.TelephonyManager;

import com.jrj.android.pad.model.po.NewsData;
import com.jrj.android.pad.model.po.Stock;
import com.jrj.android.pad.model.po.StockCode;
/**
 * @classname Constants 
 * @author xinxuan.wang
 * @date 2012-10-12
 * @remark 
 * @description  所有常量类，因Common工程要和其它工程解耦，所以不能读取其它工程的配置文件 如果建立一个配置文件，用在客户端的时候又要产生不必要的IO，所以建立此文件
 */
public class Constants {

	/** 产品ID */
	public static final int OPTION_PRODUCT_ID = 1010025;//
	/** 产品Key */
	public static final String PRODUCT_KEY = "05790495";
	/** 客户端版本 */
	public static final int OPTION_VERSION = 0x00010000; // 0.1.0.0

	/**
	 * 一级栏目版本号
	 */
	public static final int DEFAULT_JSON_VER = 0;

	/**
	 * 更新config文件名
	 */
	public static String UPDATE_PREFERENCES_FILE = "update_ver.pre";
	/**
	 * 更新版本号
	 */
	public static String UPDATE_VER_KEY = "ver";
	/**
	 * 最后更新时间
	 */
	public static String UPDATE_LAST_KEY = "last";

	/** 推测和提醒相关，暂不用 */
	public static int systemAlertId;

	/**
	 * 数据包最高优先级
	 */
	public static final byte PRIORITY_HIGH = 1;
	/**
	 * 数据包普通优先级
	 */
	public static final byte PRIORITY_MEDIUM = 10;
	/**
	 * 数据包最低优先级
	 */
	public static final byte PRIORITY_LOW = 100;
	/**
	 * 数据包列表最大数量
	 */
	public static final int LIST_MAX_BODY_NUM = 3;
	/**
	 * 数据包队列最大数量，与内存大小相关
	 */
	public static final int QUEUE_MAX_BODY_NUM = 100;
	/**
	 * 如果队列已满，则休眠，休眠时间与网络环境及处理性能相关
	 */
	public static final int QUEUE_SLEEP_TIME = 5000;

	/**
	 * 数据包在队列中最大生存时间，单位为毫秒
	 */
	public static final int QUEUE_BODY_MAX_SURVIVAL = 10000;//10秒
	/**
	 * 数据包发送线程每次处理休眠时间，单位为毫秒
	 */
	public static final int QUEUE_SEND_SLEEP_TIME = 1000;
	/**
	 * 数据包发送及接收线程每次处理休眠时间，单位为毫秒
	 */
	public static final int QUEUE_SE_SLEEP_TIME = 50;
	/**
	 * 数据包清理者休眠时间，单位为毫秒
	 */
	public static final int QUEUE_CLEAN_SLEEP_TIME = 2000;//2秒

	/**
	 * 最大可以存储多大字节的二进制数据
	 */
	public static final int MAX_BLOB_LENGTH = 10000;
	/**
	 * 二进制数据内存最大限制
	 */
	public static final int MAX_BLOB_MAP_SIZE = 10000;
	/**
	 * HTTP链接超时时间
	 */
	public static final int HTTP_CONN_TIMEOUT = 6000;//6秒

	/**
	 * 监测发送时间，如果在SEND_CONN_TIMEOUT分钟没有数据包发送，则发送维护连接数据包
	 */
	public static long lastSend;
	/**
	 * SEND_CONN_TIMEOUT超时毫秒数
	 */
	public static final int SEND_CONN_TIMEOUT = 36000 * 3;//3分钟

	/** 用户相关信息存储文件 */
	public static final String STR_PREFERENCES_FILE = "info.bin";
	public static final String FRAGMENT_BIGCHART_TAG = "bigchart";
	//	public static final String FRAGMENT_F10_VALUE_TAG = "f10value";

	public static final String FRAGMENT_STOCKINDEX_LIST_TAG = "stockindexlist";

	/**
	 * k线指标1
	 */
	public static final int KLINE_REQ_MA_PARAM_1 = 5;
	/**
	 * k线指标2
	 */
	public static final int KLINE_REQ_MA_PARAM_2 = 10;
	/**
	 * k线指标3
	 */
	public static final int KLINE_REQ_MA_PARAM_3 = 30;

	/**
	 * k线请求数据
	 */
	public static final int KLINE_REQ_NUM = 120;

	/** 新闻列表数目 */
	public static final int OPTION_QUERY_NEWS_LIST_NUM = 30;
	/** 成交明细请求数目 */
	public static final int OPTION_DETAIL_NUM = 50;
	/** 成交明细时间 **/
	public static final int OPTION_DETAIL_TIME = 0;

	/** 请求包包头长度 */
	public static final int REQ_HEAD_LENGTH = 12;
	/** 返回包包头长度 */
	public static final int RET_HEAD_LENGTH = 13;

	public static final byte REQ_ID_TYPE_UNDEFINE = 1;
	public static final byte REQ_ID_TYPE_COMMON = 2;
	public static final byte REQ_ID_TYPE_EXCHANGE = 3;
	public static final byte REQ_ID_TYPE_TOPVIEW = 4;
	public static final byte REQ_ID_TYPE_HS_TRADE = 5;

	// PackageList
	/** 操作成功 */
	public static byte PACKAGE_LIST_OP_SUCC = 1;

	/** 操作失败 */
	public static byte PACKAGE_LIST_OP_ERROR = 2;

	/** 操作失败，解密失败 */
	public static byte PACKAGE_LIST_OP_ERROR_UNENCRYPT = 3;
	public static byte PACKAGE_LIST_OP_ERROR_1 = 101;
	public static byte PACKAGE_LIST_OP_ERROR_2 = 102;
	public static byte PACKAGE_LIST_OP_ERROR_3 = 103;
	public static byte PACKAGE_LIST_OP_ERROR_4 = 104;
	public static byte PACKAGE_LIST_OP_ERROR_5 = 105;
	public static byte PACKAGE_LIST_OP_ERROR_6 = 106;
	public static byte PACKAGE_LIST_OP_ERROR_7 = 107;
	public static byte PACKAGE_LIST_OP_ERROR_8 = 108;
	public static byte PACKAGE_LIST_OP_ERROR_9 = 109;

	/** 无压缩算法 */
	public static final byte COMPRESS_NONE = 0;
	/** GLib算法压缩 */
	public static final byte COMPRESS_GLIB = 1;

	/** 无加密算法 */
	public static final byte ENCRYPT_NONE = 0;
	/** 异或算法加密 */
	public static final byte ENCRYPT_XOR = 1;
	/** Des算法压缩 */
	public static final byte ENCRYPT_DES = 2;
	/**
	 * 平台类别
	 */
	public static final byte PLATFORM_ANDROID = 6;
	/**
	 * 最小包头长度
	 */
	public static final int MIN_HEAD_LEN = 6 * 1 + 4 * 4;// 6 Byte && 4 Int = 22
	/** 包头标记 */
	public static final int PACKAGE_HEADTAG = 0xccc1;

	// 命令类型定义
	/** KJava请求IDBaseID */
	public static final int REQID_KJAVA_BASE_ID = 40000;
	/** 分时走势请求ID */
	public static final int REQID_KJAVA_RTLINE = REQID_KJAVA_BASE_ID + 1;
	/** K线请求ID */
	// public static final int REQID_KJAVA_KLINE = REQID_KJAVA_BASE_ID + 2;
	/** 报价请求ID */
	public static final int REQID_KJAVA_REPORT = REQID_KJAVA_BASE_ID + 3;
	/** 指数报价请求ID */
	public static final int REQID_KJAVA_REPORT_INDEX = REQID_KJAVA_BASE_ID + 4;
	/** 自选股请求ID */
	public static final int REQID_KJAVA_FAVORITE = REQID_KJAVA_BASE_ID + 5;
	/** 查询股票请求ID */
	public static final int REQID_KJAVA_QUERY_STOCK = REQID_KJAVA_BASE_ID + 6;
	/** 查询新闻栏目列表 */
	public static final int REQID_KJAVA_NEWS_COLUMN_LIST = REQID_KJAVA_BASE_ID + 7;
	/** 查询新闻列表 */
	public static final int REQID_KJAVA_NEWS_LIST = REQID_KJAVA_BASE_ID + 8;
	/** 查询新闻列表 （个股） */
	public static final int REQID_KJAVA_NEWS_LIST_BY_STOCK = REQID_KJAVA_BASE_ID + 9;
	/** 查询新闻正文 */
	public static final int REQID_KJAVA_NEWS_TEXT = REQID_KJAVA_BASE_ID + 10;
	/** 查询F10栏目 */
	public static final int REQID_KJAVA_F10_COLUMN_LIST = REQID_KJAVA_BASE_ID + 11;
	/** 查询F10正文 */
	public static final int REQID_KJAVA_F10_TEXT = REQID_KJAVA_BASE_ID + 12;
	/** 查询排行榜 */
	public static final int REQID_KJAVA_RANK = REQID_KJAVA_BASE_ID + 13;
	/** 查询排行榜64 */
	public static final int REQID_KJAVA_RANK64 = REQID_KJAVA_BASE_ID + 14;
	/** 　查询多行文本 异动股票 */
	public static final int REQID_KJAVA_MULTILINE_INFO = REQID_KJAVA_BASE_ID + 15;
	/** 　查询多行文本 公告 */
	public static final int REQID_KJAVA_MULTILINE_INFO_GONGGAO = REQID_KJAVA_BASE_ID + 16;
	/** 　查询成交明细 */
	public static final int REQID_KJAVA_DETAIL = REQID_KJAVA_BASE_ID + 17;

	//	public static final int REQID_KJAVA_MULTILINE_INFO_JIAN_GU = REQID_KJAVA_BASE_ID + 18;

	//	public static final int REQID_KJAVA_MULTILINE_INFO_CAI_FU = REQID_KJAVA_BASE_ID + 19;

	/**
	 * 查询最新弹牌公告(命令类型:40020)
	 */
	public static final int REQID_KJAVA_POPUP_BULLETIN = REQID_KJAVA_BASE_ID + 20;
	/**
	 * 同步自选股请求,目前使用带港股同步的接口
	 */
	public static final int REQID_KJAVA_SYNCHRO_STOCKLIST = REQID_KJAVA_BASE_ID + 21;
	/**
	 * 查询热门股票请求
	 */
	public static final int REQID_KJAVA_HOT_STOCK = REQID_KJAVA_BASE_ID + 22;
	/**
	 * 到价提醒设置请求
	 */
	public static final int REQID_KJAVA_PRICE_NOTIFY = REQID_KJAVA_BASE_ID + 23;
	/**
	 * 取消到价提醒设置请求
	 */
	public static final int REQID_KJAVA_PRICE_NOTIFY_CANCEL = REQID_KJAVA_BASE_ID + 24;
	/**
	 * 异动股提醒设置请求
	 */
	public static final int REQID_KJAVA_ABNORMAL_STOCK_NOTIFY = REQID_KJAVA_BASE_ID + 25;
	/**
	 * 取消异动股提醒设置请求
	 */
	public static final int REQID_KJAVA_ABNORMAL_STOCK_NOTIFY_CANCEL = REQID_KJAVA_BASE_ID + 26;
	/**
	 * 自选股报价（带同步）请求
	 */
	public static final int REQID_KJAVA_SYNCHRO_STOCK = REQID_KJAVA_BASE_ID + 27;
	/**
	 * 盘口分析
	 */
	public static final int REQID_KJAVA_ANALYSIS = REQID_KJAVA_BASE_ID + 31;

	/** 指数成分股报价请求 */
	public static final int REQID_KJAVA_COMPOSITION = 40038;
	/**
	 * 查询公告列表请求(命令类型:40039)
	 */
	public static final int REQID_KJAVA_BULLETIN_LIST = REQID_KJAVA_BASE_ID + 39;
	/**
	 * 查询公告请求(命令类型:40040)
	 */
	public static final int REQID_KJAVA_BULLETIN_TEXT = REQID_KJAVA_BASE_ID + 40;
	/** K线请求ID */
	public static final int REQID_KJAVA_KLINE_NEW = REQID_KJAVA_BASE_ID + 43;
	/** 技术指标 */
	public static final int REQID_KJAVA_TECH = REQID_KJAVA_BASE_ID + 44;
	/** 驱动股 */
	public static final int REQID_KJAVA_DRIVER = REQID_KJAVA_BASE_ID + 45;

	/** 大盘指数和全球指数 */
	public static final int REQID_KJAVA_INDEX = REQID_KJAVA_BASE_ID + 46;

	// -----------json数据包--------------//
	/** json格式自选股 */
	public static final int REQID_JSON_MYSTOCK = REQID_KJAVA_BASE_ID + 51;

	/** 板块列表请求_JSON 格式(命令类型:40052) */
	public static final int REQID_TOPGROUP_LIST = REQID_KJAVA_BASE_ID + 52;
	/** 板块个股列表请求_JSON格式（命令类型：40053） */
	public static final int REQID_TOPGROUP_STOCK = REQID_KJAVA_BASE_ID + 53;

	/** 新闻列表 */
	public static final int REQID_JSON_NEWS_LIST = REQID_KJAVA_BASE_ID + 66;

	/** 千股千评 **/
	public static final int REQID_JSON_STOCK_COMMENT = REQID_KJAVA_BASE_ID + 67;//

	/** 选股攻略 **/
	public static final int REQID_JSON_STOCK_WALKTHROUGH = REQID_KJAVA_BASE_ID + 68;//

	/** 攻略文本 */
	public static final int REQID_JSON_WALKTHROUGH_TEXT = REQID_KJAVA_BASE_ID + 69;

	/** 验证码验证 */
	public static final int REQID_JSON_VERIFY_CODE = REQID_KJAVA_BASE_ID + 70;

	/** 新闻正文 */
	public static final int REQID_JSON_STOCK_NEWS_TEXT = REQID_KJAVA_BASE_ID + 71;

	/** 注册帐号 */
	public static final int REQID_JSON_USER_REGISTER = REQID_KJAVA_BASE_ID + 72;

	/** 用户名检查 */
	public static final int REQID_JSON_USER_NAME_CHECK = REQID_KJAVA_BASE_ID + 73;

	/** json登录 */
	public static final int REQID_JSON_LOGIN = REQID_KJAVA_BASE_ID + 74;

	/** 指数分组报价列表请求_JSON 格式 */
	public static final int REQID_JSON_INDEX_GROUP = REQID_KJAVA_BASE_ID + 75;

	/** 全球指数走势请求_JSON格式 */
	public static final int REQID_JSON_INDEX_TRENDS = REQID_KJAVA_BASE_ID + 76;

	/** F10 内容请求_JSON 格式 */
	public static final int REQID_JSON_F10 = REQID_KJAVA_BASE_ID + 77;

	/** 新闻一级栏目请求_JSON格式 */
	public static final int REQID_JSON_NEWS_TOP_COLUMN_LIST = REQID_KJAVA_BASE_ID + 78;

	/** 一级板块分类请求_JSON格式（命令类型：40079） */
	public static final int REQID_JSON_TOP_GROUPS = REQID_KJAVA_BASE_ID + 79;

	/** 板块涨跌榜请求_JSON格式（命令类型：40080） */
	public static final int REQID_JSON_CHANGE_GROUPS = REQID_KJAVA_BASE_ID + 80;

	/** 查个股所属板块请求_JSON格式（命令类型：40081） */
	public static final int REQID_JSON_STOCK_GROUPS = REQID_KJAVA_BASE_ID + 81;

	/** 查个股综合评级请求_JSON格式（命令类型：40082） */
	public static final int REQID_JSON_STOCK_RATING = REQID_KJAVA_BASE_ID + 82;

	/** 个股新闻列表请求_JSON格式(命令类型:40083) */
	public static final int REQID_JSON_STOCK_NEWS_LIST = REQID_KJAVA_BASE_ID + 83;

	/** 快赢 */
	public static final int REQID_FASTWIN_SNAP = 20001;

	/** 登录请求ID */
	public static final int REQID_KJAVA_LOGON = 41001;

	/**
	 * 交易ID
	 */
	public static final int REQID_TRADE_BASE_ID = 60000;
	/** 券商 */
	public static final int REQID_TRADE_COMPANY_LIST = REQID_TRADE_BASE_ID + 0;
	/** 营业部 */
	public static final int REQID_TRADE_BRANCH_LIST = REQID_TRADE_BASE_ID + 1;
	/** 交易登陆请求包 */
	public static final int REQID_TRADE_VERIFY = REQID_TRADE_BASE_ID + 2;
	/** 修改密码请求包 **/
	public static final int REQID_TRADE_MODIFYPWD = REQID_TRADE_BASE_ID + 3;
	/** 提交委托请求包 **/
	public static final int REQID_TRADE_SUBMIT_ENTRUST = REQID_TRADE_BASE_ID + 4;
	/** 委托撤单请求包 **/
	public static final int REQID_TRADE_DROP = REQID_TRADE_BASE_ID + 5;
	/** 客户股东号查询 **/
	public static final int REQID_TRADE_ACCOUNT_QUERY = REQID_TRADE_BASE_ID + 6;
	/** 证券行情查询 */
	public static final int REQID_TRADE_QUOTE_QUERY = REQID_TRADE_BASE_ID + 7;
	/** 当日委托查询 */
	public static final int REQID_TRADE_ENTRUST_QUERY = REQID_TRADE_BASE_ID + 8;
	// /**当日成交查询 */
	// public static final int REQID_TRADE_BUSINESS_QUERY = REQID_TRADE_BASE_ID
	// +9;
	// /**历史成交查询 */
	// public static final int REQID_TRADE_HISTBUSINESS_QUERY =
	// REQID_TRADE_BASE_ID +10;
	/** 股票(持仓)查询 */
	public static final int REQID_TRADE_STOCK_QUERY = REQID_TRADE_BASE_ID + 11;
	/** 资金查询 */
	public static final int REQID_TRADE_FUND_QUERY = REQID_TRADE_BASE_ID + 12;
	/** 按类型登录 **/
	public static final int REQID_TRADE_COMPANY_LIST_BY_TYPE = REQID_TRADE_BASE_ID + 13;
	/** 新增营业部请求 */
	public static final int REQID_TRADE_BRANCH_LIST_EXT = REQID_TRADE_BASE_ID + 14;
	/**
	 * 当日成交查询(字段"成交编号"为UNICODE编码)
	 */
	public static final int REQID_TRADE_BUSINESS_QUERY = REQID_TRADE_BASE_ID + 15;
	/**
	 * 历史成交查询
	 */
	public static final int REQID_TRADE_HISTBUSINESS_QUERY = REQID_TRADE_BASE_ID + 16;

	/**
	 * 
	 * 港股行情 请求指令
	 * 
	 * 根据Istock通信协议2.0
	 */
	public static final int REQID_HK_BASE_ID = 81000;
	/**
	 * 个股报价请求(命令类型:81002)
	 */
	public static final int REQUEST_ONE_STOCK_QUOTE_ID_KJAVA_HK = REQID_HK_BASE_ID + 2; // 1.4.1.
	/**
	 * 指数报价请求（命令类型：81003）
	 */
	public static final int REQUEST_ONE_INDEX_QUOTE_ID_KJAVA_HK = REQID_HK_BASE_ID + 3; // 1.4.2.
	/**
	 * 分时走势请求（命令类型：81004）
	 */
	public static final int REQUEST_ONE_STOCK_RT_ID_KJAVA_HK = REQID_HK_BASE_ID + 4;// 1.4.3.
	/**
	 * K线请求（命令类型：81005）
	 */
	public static final int REQUEST_ONE_STOCK_KLINE_ID_KJAVA_HK = REQID_HK_BASE_ID + 5;// 1.4.4.
	/**
	 * 自选股报价请求（命令类型：81008）
	 */
	public static final int REQUEST_MY_STOCK_QUOTE_ID_KJAVA_HK = REQID_HK_BASE_ID + 8;// 1.4.5.
	/**
	 * 排行榜报价请求（命令类型：81009）
	 */
	public static final int REQUEST_RANK_QUOTE_ID_KJAVA_HK = REQID_HK_BASE_ID + 9;// 1.4.6.
	/**
	 * 成交明细请求（命令类型：81011）
	 */
	public static final int REQUEST_BARGAIN_DETAIL_ID_KJAVA_HK = REQID_HK_BASE_ID + 11;// 1.4.7.
	/**
	 * 分价表请求（命令类型：81012）
	 */
	public static final int REQUEST_SEPARATE_PRICE_TABLE_ID_KJAVA_HK = REQID_HK_BASE_ID + 12;// 1.4.8.
	/**
	 * A/H比价请求（命令类型：81013）
	 */
	public static final int REQUEST_A_H_COMPARE_PRICE_ID_KJAVA_HK = REQID_HK_BASE_ID + 13;// 1.4.9.
	/**
	 * 股票查询请求（命令类型：81015）
	 */
	public static final int REQUEST_QUERY_STOCKS_ID_KJAVA_HK = REQID_HK_BASE_ID + 15;// 1.4.11.
	/**
	 * H股大盘指数请求（命令类型：81017）
	 */
	public static final int REQUEST_INDEXES_ID_KJAVA_HK = REQID_HK_BASE_ID + 17;// 1.4.9.
	/**
	 * 带港股的同步自选股
	 */
	public static final int REQUEST_SYNCHRO_STOCKLIST_KJAVA_HK = REQID_HK_BASE_ID + 20;// 1.4.16
	/**
	 * 自选股报价（带同步）请求（命令类型：81021）
	 */
	public static final int REQID_SYNCHRO_STOCK_ID_KJAVA_HK = REQID_HK_BASE_ID + 21;// 1.4.17.

	/**
	 * 港股交易 请求指令
	 */
	public static final int REQID_TRADE_BASE_ID_KJAVA_HK = 90000;
	/**
	 * 交易用户登录请求（命令类型：90001）
	 */
	public static final int REQUEST_TRADE_LOGON_ID_KJAVA_HK = REQID_TRADE_BASE_ID_KJAVA_HK + 1;// 1.5.1.
	/**
	 * 交易用户信息查询请求（命令类型：90002）
	 */
	public static final int REQUEST_TRADE_REQUEST_ID_KJAVA_HK = REQID_TRADE_BASE_ID_KJAVA_HK + 2;// 1.5.2.
	/**
	 * 修改交易密码请求（命令类型：90003）
	 */
	public static final int REQUEST_TRADE_MODIFY_PASSWORD_ID_KJAVA_HK = REQID_TRADE_BASE_ID_KJAVA_HK + 3;// 1.5.3.
	/**
	 * 资金查询请求（命令类型：90004）
	 */
	public static final int REQUEST_TRADE_CHECK_MONEY_ID_KJAVA_HK = REQID_TRADE_BASE_ID_KJAVA_HK + 4; // 1.5.4.
	/**
	 * 持仓查询请求（命令类型：90005）
	 */
	public static final int REQUEST_TRADE_CHECK_GOODS_ID_KJAVA_HK = REQID_TRADE_BASE_ID_KJAVA_HK + 5; // 1.5.5.
	/**
	 * 委托下单请求（命令类型：90006）
	 */
	public static final int REQUEST_TRADE_ORDER_ID_KJAVA_HK = REQID_TRADE_BASE_ID_KJAVA_HK + 6;// 1.5.6.
	//	public static final int REQID_HS_TRADE_SEND_REQUEST = 20000;

	/**
	 * 行情快照(命令类型:50001)
	 */
	public static final int REQUEST_BENYUE_REPORT = 50001;
	/**
	 * 分时走势数据(命令类型:50002)
	 */
	public static final int REQUEST_BENYUE_RT = 50002;
	/**
	 * K 线数据(命令类型:50003)
	 */
	public static final int REQUEST_BENYUE_KLINE = 50003;
	/**
	 * 股票查询(命令类型:50004)
	 */
	public static final int REQUEST_BENYUE_STOCK = 50004;
	/**
	 * 成交明细(命令类型:50005)
	 */
	public static final int REQUEST_BENYUE_TICK = 50005;
	/**
	 * 排行榜(命令类型:50006)
	 */
	public static final int REQUEST_BENYUE_RANK = 50006;
	/**
	 * 主要财务数据(命令类型:50007)
	 */
	public static final int REQUEST_BENYUE_FINANCE = 50007;
	/**
	 * 技术指标数据(命令类型:50008)
	 */
	public static final int REQUEST_BENYUE_TECH = 50008;
	/**
	 * 关联股票查询(命令类型:50009)
	 */
	public static final int REQUEST_BENYUE_ASSOCIATE = 50009;

	/**
	 * 根据请求Id得到请求类型
	 * @param reqId
	 * @return byte
	 */
	public static byte ReqIdToReqIdType(int reqId) {
		if (reqId >= REQID_KJAVA_BASE_ID && reqId <= REQID_KJAVA_BASE_ID + 999)
			return REQ_ID_TYPE_COMMON;
		if (reqId == REQID_FASTWIN_SNAP)
			return REQ_ID_TYPE_COMMON;
		if (reqId >= REQID_TRADE_BASE_ID && reqId <= REQID_TRADE_BASE_ID + 999)
			return REQ_ID_TYPE_EXCHANGE;
		return REQ_ID_TYPE_UNDEFINE;
	}

	/*
	 * 暂时无用 public boolean copyHSData(byte[] bBase, int offset) { return
	 * ((CommonReq) body).copyBytesReqBody(bBase, offset); }
	 */
	//StockCode
	/**
	 * 全球
	 */
	public static final byte MARKET_GLOBAL = (byte) 0;
	/** 上海 */
	public static final byte MARKET_SH = (byte) 1;
	/** 深圳 */
	public static final byte MARKET_SZ = (byte) 2;
	/** 香港 */
	public static final byte MARKET_HK_UNION = (byte) 7;
	/** 期货 */
	public static final byte MARKET_FUTURES = (byte) 23;

	public static final byte MARKET_ZSH = (byte) 20;
	// victor added begin for HK
	/**
	 * 港股主板
	 */
	public static final byte MARKET_HK_MAIN_BOARD = (byte) 11;
	/**
	 * 港股创业板
	 */
	public static final byte MARKET_HK_CARVE_OUT_BOARD = (byte) 12;
	/**
	 * NASDAQ
	 */
	public static final byte MARKET_HK_NASDAQ = (byte) 13;
	/**
	 * ETS
	 */
	public static final byte MARKET_HK_ETS = (byte) 14;

	public static final byte MARKET_ML = (byte) 1;
	public static final byte MARKET_HK = (byte) 2;
	public static final byte MARKET_UNKNOWN_HK = (byte) 0;
	// victor added end.
	/** 未知市场 */
	public static final byte MARKET_UNKNOWN = (byte) 199;

	/**
	 * 99:全球市场
	 */
	public static final byte MARKET_INDEX_GLOBAL = (byte) 99;
	/**
	 * 90:商品指数
	 */
	public static final byte MARKET_GOODS_GLOBAL = (byte) 90;
	/**
	 * 91:外汇指数
	 */
	public static final byte MARKET_EXCHANGE_GLOBAL = (byte) 91;

	/** 股票代码长度 */
	public static final int CODE_LENGTH = 6;
	/** 市场长度 */
	public static final int MARKET_LENGTH = 1;
	/** 数据长度 */
	public static final int STOCKCODE_LENGTH = CODE_LENGTH + 1 + MARKET_LENGTH;

	/** 股票子市场 上海未知 */
	public static final byte STOCK_SUB_MARKET_SH_UNKNOWN = 0;
	/** 股票子市场 上海指数 */
	public static final byte STOCK_SUB_MARKET_SH_ZS = 1;
	/** 股票子市场 上海A股 */
	public static final byte STOCK_SUB_MARKET_SH_AG = 2;
	/** 股票子市场 上海B股 */
	public static final byte STOCK_SUB_MARKET_SH_BG = 3;
	/** 股票子市场 上海权证 */
	public static final byte STOCK_SUB_MARKET_SH_QZ = 4;
	/** 股票子市场 上海基金 */
	public static final byte STOCK_SUB_MARKET_SH_JJ = 5;
	/** 股票子市场 上海债券 */
	public static final byte STOCK_SUB_MARKET_SH_ZQ = 6;
	/** 股票子市场 战上海指数 */
	public static final byte STOCK_SUB_MARKET_SH_ZSHZS = 20;

	/** 股票子市场 深圳未知 */
	public static final byte STOCK_SUB_MARKET_SZ_UNKNOWN = 30;
	/** 股票子市场 深圳指数 */
	public static final byte STOCK_SUB_MARKET_SZ_ZS = 31;
	/** 股票子市场 深圳A股 */
	public static final byte STOCK_SUB_MARKET_SZ_AG = 32;
	/** 股票子市场 深圳B股 */
	public static final byte STOCK_SUB_MARKET_SZ_BG = 33;
	/** 股票子市场 深圳权证 */
	public static final byte STOCK_SUB_MARKET_SZ_QZ = 34;
	/** 股票子市场 深证基金  */
	public static final byte STOCK_SUB_MARKET_SZ_JJ = 35;
	/** 股票子市场 深圳债券 */
	public static final byte STOCK_SUB_MARKET_SZ_ZQ = 36;
	/** 股票子市场 深圳中小 */
	public static final byte STOCK_SUB_MARKET_SZ_ZX = 37;
	/** 股票子市场 深圳创业板 */
	public static final byte STOCK_SUB_MARKET_SZ_CY = 38;

	/** 股票类别 未知市场未知类型 */
	public static final byte STOCK_SUB_MARKET_UNKNOWN = 127;

	// 下面是各个类中抽出来的变量
	public static final int FAVORITE_BODY_REQ_MIN_LENGTH = 4;
	public static final int FAVORITE_BODY_MIN_LENGTH = 12;

	public static final int QUERYSTOCK_BODY_REQ_LENGTH = 18;
	public static final int QUERYSTOCK_BODY_MIN_LENGTH = 4;

	public static final int RANK_BODY_REQ_LENGTH = 15;
	public static final int RANK_BODY_MIN_LENGTH = 19;

	public static final int DRIVERSTOCK_BODY_REQ_RECORD_NUM = 10;
	public static final int DRIVERSTOCK_BODY_REQ_LENGTH = 12;

	public static final int FWDATASNAP_BODY_REQ_LENGTH = 20;

	public static final int RT_BODY_REQ_LENGTH = STOCKCODE_LENGTH + 6;

	public static final int KLINE_BODY_REQ_LENGTH = STOCKCODE_LENGTH + 26;

	public static final int NEWSLISTBYSTOCK_BODY_REQ_LENGTH = STOCKCODE_LENGTH + 4;
	public static final int NEWSLISTBYSTOCK_BODY_MIN_RET_LENGTH = 4;

	public static final int NEWSLIST_BODY_REQ_LENGTH = 8;
	public static final int NEWSLIST_BODY_MIN_RET_LENGTH = 4;

	public static final int NEWSTEXT_BODY_REQ_LENGTH = NewsData.KJAVA_NEWS_ID_LEN + 1;
	public static final int NEWSTEXT_BODY_MIN_RET_LENGTH = 12 + NewsData.KJAVA_NEWS_TITLE_NAME_LEN + 1;

	public static final int F10LIST_BODY_REQ_LENGTH = 8;
	public static final int F10LIST_BODY_MIN_RET_LENGTH = 4;

	public static final int DETAIL_BODY_REQ_LENGTH = STOCKCODE_LENGTH + 12;

	public static final int F10TEXT_BODY_REQ_LENGTH = STOCKCODE_LENGTH
			+ NewsData.KJAVA_F10_ITEM_ID_LEN + 1;
	public static final int F10TEXT_BODY_MIN_RET_LENGTH = NewsData.KJAVA_F10_ITEM_NAME_LEN + 5;

	public static final int STOCKINDEX_BODY_REQ_LENGTH = 1;

	public static final int JSONSTOCKCOMMENT_BODY_REQ_LENGTH = 10;

	// 技术指标数据
	public static final int TECHREQ_BODY_REQ_NUM = 120;
	public static final int TECHREQ_BODY_REQ_LENGTH = STOCKCODE_LENGTH + 18;

	// LoginBody

	/** 'A' 用户有正常权限 */
	public static final byte LOGON_A = 65;
	/** 'D' 用户历史上未购买或试用过本产品 */
	public static final byte LOGON_D = 68;
	/** 'E' 用户曾经试用过本产品，但目前试用已过期 */
	public static final byte LOGON_E = 69;
	/** 'F' 用户曾经购买过本产品，但目前已过期 */
	public static final byte LOGON_F = 70;

	/** 重码数据包体的长度 */
	public static final int KJAVA_LOGON_NAME_LENGTH = 20;
	public static final int KJAVA_LOGON_PASSWORD_LENGTH = 16;
	public static final int KJAVA_LOGON_PHONE_LENGTH = 11;
	public static final int KJAVA_LOGON_VERSION_LENGTH = 4;
	/** 硬件码长度 */
	public static final int NDEVICEIDLEN = 43;
	public static final int KJAVA_LOGON_REQ_LENGTH = KJAVA_LOGON_NAME_LENGTH
			+ 1 + KJAVA_LOGON_PASSWORD_LENGTH + 1 + KJAVA_LOGON_PHONE_LENGTH
			+ 1 + KJAVA_LOGON_VERSION_LENGTH + 4 + NDEVICEIDLEN + 1;

	public static final int KJAVA_LOGON_USER_SESSION_LEN = 128 + 1;

	// JsonLoginBody
	/**
	 * 公司类型 1:证券之星(默认)
	 */
	public static final int REQCORP_SS = 1;
	/**
	 * 公司类型 2:金融界
	 */
	public static final int REQCORP_JRJ = 2;

	public static final String UPDATE_ADD = "UPDATE";
	public static final String MID = "MID";
	public static final String SYSTIME_TIME = "SYSTIME";

	//JsonNews
	// 默认新闻所属公司
	public static final byte REQCORP_NEWS = REQCORP_JRJ;

	// JsonNewsListBody
	public static final int JSONNEWSLIST_BODY_REQPAGESIZE = 10;
	public static final int JSONNEWSLIST_BODY_REQPAGENO = 1;
	public static final int JSONNEWSLIST_BODY_DIGESTNUM = 90;

	// JsonNewsTextBody
	public static final byte FITLEHTML = 1;

	// KlineResp

	/** 周期 日 */
	public static final short CYCLE_DAY = 1;
	/** 周期 周 */
	public static final short CYCLE_WEEK = 2;
	/** 周期 月 */
	public static final short CYCLE_MONTH = 3;
	/** 周期 季 */
	public static final short CYCLE_QUARTER = 4;
	/** 周期 季 */
	public static final short CYCLE_YEAR = 5;
	/** 周期 5分钟 */
	public static final short CYCLE_5MIN = 6;
	/** 周期 15分钟 */
	public static final short CYCLE_15MIN = 7;
	/** 周期 30分钟 */
	public static final short CYCLE_30MIN = 8;
	/** 周期 60分钟 */
	public static final short CYCLE_60MIN = 9;

	// RtResp
	/**
	 * RtResp返回长度
	 */
	public static final int RTRESP_MIN_RET_LENGTH = Stock.LENGTH + 64;
	/** 每日有242根分时线 */
	public static final int INDEX_NUM = 251;
	/** 上午的索引范围开始值 */
	public static final int INDEX_MORNING_BEGIN = 10;
	/** 上午的索引范围结束值 */
	public static final int INDEX_MORNING_END = 130;
	/** 下午的索引范围开始值 */
	public static final int INDEX_AFTERNOON_BEGIN = 131;
	/** 下午的索引范围结束值 */
	public static final int INDEX_AFTERNOON_END = 250;

	//StockIndexResp
	/**
	 * 国内指数
	 */
	public static final byte INDEX_MARKET_DOMESTIC = 1;
	/**
	 * 全球指数
	 */
	public static final byte INDEX_MARKET_GLOBAL = 2;

	// JsonIndexDataDetailReq
	/**
	 * 默认请求指数数量
	 */
	public static final int JSONINDEXDATADETAIL_REQ_NUM = -22;

	/**
	 * 默认添加的自选股
	 */
	public static final Stock[] MY_STOCK = new Stock[] {
			new Stock("上证指数", new StockCode("000001", MARKET_SH)),
			new Stock("深证成指", new StockCode("399001", MARKET_SZ)), };

	/**
	 * 获取DeviceId
	 * @return String
	 */
	public static String getDeviceId(Context context) {
		TelephonyManager tel = (TelephonyManager) context
				.getSystemService(Context.TELEPHONY_SERVICE);
		String sim = tel.getSimSerialNumber();
		//		String dId = android.os.Build.SERIAL;
		String dId = tel.getDeviceId();
		String deviceid = android.os.Build.MODEL + ";"
				+ android.os.Build.VERSION.RELEASE + ";"
				+ (sim == null ? "" : sim) + ";" + dId;
//		Debug.d("Deviceid", deviceid);
		return deviceid;
	}

	// ErrorResp错误信息
	// 登录认知失败
	public static final int ERROR_ID_ERROR_LOGON_FAIL = 1005;
	public static final String ERROR_ID_ERROR_LOGON_FAIL_MSG = "登录认证失败";

	// 股票代码不正确
	public static final int ERROR_ID_ERROR_STOCK_CODE = 1007;

	public static final int ERROR_ID_ERROR_CHECK_SESSION = 1010;

	public static final int ERROR_ID_ERROR_NO_RIGHT = 1012;

	public static final int ERROR_ID_ERROR_INVALID_USER = 1014;
	public static final String ERROR_ID_ERROR_INVALID_USER_MSG = "用户无效";

	public static final int ERROR_ID_ERROR_PASSWORD_ERROR = 1015;
	public static final String ERROR_ID_ERROR_PASSWORD_ERROR_MSG = "密码无效";

	public static final int ERROR_ID_ERROR_BANDING_ERROR = 1016;
	public static final String ERROR_ID_ERROR_BANGDING_ERROR_MSG = "用户和手机的绑定关系不正确";
	public static final int ERROR_ID_ERROR_UNSUPPORTED_STOCK_ERROR = 1017;
	public static final String ERROR_ID_ERROR_UNSUPPORTED_STOCK_ERROR_MSG = "只支持对A、B股和封闭式基金设置提醒";
	public static final int ERROR_ID_ERROR_EXISTED_ERROR = 1018;
	public static final String ERROR_ID_ERROR_EXISTED_ERROR_MSG = "此提醒已设置";
	public static final int ERROR_ID_ERROR_INPUT_ERROR = 1019;
	public static final String ERROR_ID_ERROR_INPUT_ERROR_MSG = "输入参数错误";
	public static final int ERROR_ID_ERROR_UNSUPPORTED_STOCK_TYPE_ERROR = 1020;
	public static final String ERROR_ID_ERROR_PASSWORD_UNSUPPORTED_STOCK_TYPE_ERROR_MSG = "网站用户名或密码错";

	public static final String ERROR_ID_REGISTER_NAME_ERR_MSG = "用户名错误或用户名已存在";
	public static final int ERROR_ID_REGISTER_NAME_ERR = 1021;

	public static final String ERROR_ID_REGISTER_FAILED_MSG = "注册失败";
	public static final int ERROR_ID_REGISTER_FAILED = 1022;
	// //////////////////////////////////////////////////////////////////////////
	// 以下是通讯层错误

	public static final int ERROR_ID_CONNECT = 9001;
	public static final String ERROR_ID_CONNECT_MSG = "无法连接行情服务器";

	public static final int ERROR_ID_PRE_PROCESS_PACKAGE = 9002;
	public static final String ERROR_ID_PRE_PROCESS_PACKAGE_MSG = "请求包预处理错误";

	public static final int ERROR_ID_COPY_PACKAGE = 9003;
	public static final String ERROR_ID_COPY_PACKAGE_MSG = "复制请求包错误";

	public static final int ERROR_ID_SEND_PACKAGE = 9004;
	public static final String ERROR_ID_SEND_PACKAGE_MSG = "发送请求包错误";

	public static final int ERROR_ID_OPEN_INPUT = 9005;
	public static final String ERROR_ID_OPEN_INPUT_MSG = "打开WAP HTTP输入流错误";

	public static final int ERROR_ID_RECV_HEAD_BUFFER = 9006;
	public static final String ERROR_ID_RECV_HEAD_BUFFER_MSG = "接受包头数据失败";

	public static final int ERROR_ID_PARSE_HEAD = 9007;
	public static final String ERROR_ID_PARSE_HEAD_MSG = "解析通讯包头错误";

	public static final int ERROR_ID_RECV_BODY_BUFFER = 9008;
	public static final String ERROR_ID_RECV_BODY_BUFFER_MSG = "接受包体数据失败";

	public static final int ERROR_ID_PARSE_BODY = 9009;
	public static final String ERROR_ID_PARSE_BODY_MSG = "解析通讯包体错误";

	public static final int ERROR_ID_UNENCRYPT = 9010;
	public static final String ERROR_ID_UNENCRYPT_MSG = "解密错误";

	public static final int ERROR_ID_WAP = 9011;
	public static final String ERROR_ID_WAP_MSG = "WAP网关错误";

	// c网接口错误--------------------------------------------------------------------------
	public static final int ERROR_CODE_FAVORITESTOCK_EXIST = -105;
	public static final String ERROR_CODE_FAVORITESTOCK_EXIST_MSG = "自选股已经存在";

	public static final int ERROR_CODE_ADD_SECURITY_ONLY = -106;
	public static final String ERROR_CODE_ADD_SECURITY_ONLY_MSG = "只能对股票设置提醒";

	public static final int ERROR_CODE_YDGPALERT_EXIST = -107;
	public static final String ERROR_CODE_YDGPALERT_EXIST_MSG = "个股异动提醒已经存在";

	public static final int ERROR_CODE_STOCK_NOT_FOUND = -108;
	public static final String ERROR_CODE_STOCK_NOT_FOUND_MSG = "StockCodeList中不存在";

	public static final int ERROR_CODE_FAVORITESTOCK_NOTEXIST = -109;
	public static final String ERROR_CODE_FAVORITESTOCK_NOTEXIST_MSG = "自选股不存在";

	public static final int ERROR_CODE_PRICEALERT_NOTEXIST = -110;
	public static final String ERROR_CODE_PRICEALERT_NOTEXIST_MSG = "个股到价提醒不存在";

	public static final int ERROR_CODE_YDGPALERT_NOTEXIST = -111;
	public static final String ERROR_CODE_YDGPALERT_NOTEXIST_MSG = "个股异动提醒不存在";

	public static final int ERROR_CODE_SCYDALERT_EXIST = -112;
	public static final String ERROR_CODE_SCYDALERT_EXIST_MSG = "市场异动提醒已经存在";

	public static final int ERROR_CODE_SCYDALERT_NOTEXIST = -113;
	public static final String ERROR_CODE_SCYDALERT_NOTEXIST_MSG = "市场异动提醒不存在";

	public static final int ERROR_SYSTEM_ERR = 1001;
	public static final String ERROR_SYSTEM_ERR_MSG = "系统忙，操作失败，请稍后再试";

	/**
	 * 获取错误信息描述
	 * @param errorId
	 * @return String
	 */
	public static String getErrorMsg(int errorId) {
		switch (errorId) {
		case Constants.ERROR_ID_CONNECT:
			return Constants.ERROR_ID_CONNECT_MSG;
		case Constants.ERROR_ID_COPY_PACKAGE:
			return Constants.ERROR_ID_COPY_PACKAGE_MSG;
		case Constants.ERROR_ID_OPEN_INPUT:
			return Constants.ERROR_ID_OPEN_INPUT_MSG;
		case Constants.ERROR_ID_PARSE_BODY:
			return Constants.ERROR_ID_PARSE_BODY_MSG;
		case Constants.ERROR_ID_UNENCRYPT:
			return Constants.ERROR_ID_UNENCRYPT_MSG;
		case Constants.ERROR_ID_PARSE_HEAD:
			return Constants.ERROR_ID_PARSE_HEAD_MSG;
		case Constants.ERROR_ID_PRE_PROCESS_PACKAGE:
			return Constants.ERROR_ID_PRE_PROCESS_PACKAGE_MSG;
		case Constants.ERROR_ID_RECV_BODY_BUFFER:
			return Constants.ERROR_ID_RECV_BODY_BUFFER_MSG;
		case Constants.ERROR_ID_RECV_HEAD_BUFFER:
			return Constants.ERROR_ID_RECV_HEAD_BUFFER_MSG;
		case Constants.ERROR_ID_SEND_PACKAGE:
			return Constants.ERROR_ID_SEND_PACKAGE_MSG;
		case Constants.ERROR_ID_WAP:
			return Constants.ERROR_ID_WAP_MSG;
		case Constants.ERROR_ID_ERROR_LOGON_FAIL:
			return Constants.ERROR_ID_ERROR_LOGON_FAIL_MSG;
		case Constants.ERROR_ID_ERROR_INVALID_USER:
			return Constants.ERROR_ID_ERROR_INVALID_USER_MSG;
		case Constants.ERROR_ID_ERROR_PASSWORD_ERROR:
			return Constants.ERROR_ID_ERROR_PASSWORD_ERROR_MSG;
		case Constants.ERROR_CODE_FAVORITESTOCK_EXIST:
			return Constants.ERROR_CODE_FAVORITESTOCK_EXIST_MSG;

		case Constants.ERROR_CODE_ADD_SECURITY_ONLY:
			return Constants.ERROR_CODE_ADD_SECURITY_ONLY_MSG;
		case Constants.ERROR_CODE_YDGPALERT_EXIST:
			return Constants.ERROR_CODE_YDGPALERT_EXIST_MSG;
		case Constants.ERROR_CODE_STOCK_NOT_FOUND:
			return Constants.ERROR_CODE_STOCK_NOT_FOUND_MSG;
		case Constants.ERROR_CODE_FAVORITESTOCK_NOTEXIST:
			return Constants.ERROR_CODE_FAVORITESTOCK_NOTEXIST_MSG;
		case Constants.ERROR_CODE_PRICEALERT_NOTEXIST:
			return Constants.ERROR_CODE_PRICEALERT_NOTEXIST_MSG;
		case Constants.ERROR_CODE_YDGPALERT_NOTEXIST:
			return Constants.ERROR_CODE_YDGPALERT_NOTEXIST_MSG;
		case Constants.ERROR_CODE_SCYDALERT_EXIST:
			return Constants.ERROR_CODE_SCYDALERT_EXIST_MSG;
		case Constants.ERROR_CODE_SCYDALERT_NOTEXIST:
			return Constants.ERROR_CODE_SCYDALERT_NOTEXIST_MSG;
		case Constants.ERROR_SYSTEM_ERR:
			return Constants.ERROR_SYSTEM_ERR_MSG;
		case Constants.ERROR_ID_ERROR_BANDING_ERROR:
			return Constants.ERROR_ID_ERROR_BANGDING_ERROR_MSG;
		case Constants.ERROR_ID_ERROR_UNSUPPORTED_STOCK_ERROR:
			return Constants.ERROR_ID_ERROR_UNSUPPORTED_STOCK_ERROR_MSG;
		case Constants.ERROR_ID_ERROR_EXISTED_ERROR:
			return Constants.ERROR_ID_ERROR_EXISTED_ERROR_MSG;
		case Constants.ERROR_ID_ERROR_INPUT_ERROR:
			return Constants.ERROR_ID_ERROR_INPUT_ERROR_MSG;
		case Constants.ERROR_ID_ERROR_UNSUPPORTED_STOCK_TYPE_ERROR:
			return Constants.ERROR_ID_ERROR_PASSWORD_UNSUPPORTED_STOCK_TYPE_ERROR_MSG;
		case Constants.ERROR_ID_REGISTER_FAILED:
			return Constants.ERROR_ID_REGISTER_FAILED_MSG;

		case Constants.ERROR_ID_REGISTER_NAME_ERR:
			return Constants.ERROR_ID_REGISTER_NAME_ERR_MSG;
		default:
			return "Unkown Error:" + errorId; // victor modified
		}
	}

	/**
	 * 默认日期格式
	 */
	public static SimpleDateFormat fullDateFormat = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");

	/**
	 * 获取时间差格式
	 * @param time
	 * @return String
	 */
	public static String getTimeLag(String time) {
		if (time == null) {
			return "";
		} else {
			return time;
		}
		/*
		Date now = new Date();
		StringBuffer sb = new StringBuffer();
		try {
			Date timeDate = fullDateFormat.parse(time);
			int between = (int) ((now.getTime() - timeDate.getTime()) / 1000);// 转换成秒
			int day = between / (24 * 3600);
			int hour = between % (24 * 3600) / 3600;
			int minute = between % 3600 / 60;
			int second = between % 60 / 60;
			if (day > 0) sb.append(day).append("天");
			if (hour > 0) sb.append(hour).append("小时");
			if (minute > 0) sb.append(minute).append("分钟");
			if (second > 0) sb.append(second).append("秒");
			if (sb.length() > 0) {
				sb.append("前");
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return sb.toString();
		 */
	}

	/**
	 * 判断当前日期是星期几
	 * 
	 * @param pTime
	 *            修要判断的时间
	 * @return dayForWeek 判断结果
	 * @Exception 发生异常
	 */
	public static String dayForWeek(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		int dayForWeek = 0;
		if (c.get(Calendar.DAY_OF_WEEK) == 1) {
			dayForWeek = 7;
		} else {
			dayForWeek = c.get(Calendar.DAY_OF_WEEK) - 1;
		}
		switch (dayForWeek) {
		case 1:
			return "周\n一";
		case 2:
			return "周\n二";
		case 3:
			return "周\n三";
		case 4:
			return "周\n四";
		case 5:
			return "周\n五";
		case 6:
			return "周\n六";
		case 7:
			return "周\n日";
		default:
			return "";
		}
	}

	public static void main(String[] args) {
		System.out.println(getTimeLag(null));
	}
}