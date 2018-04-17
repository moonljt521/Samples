/**
* Copyright (c) 2012 jrj. All rights reserved.
* @filename JSONObject.java
* @project WirelessCommon
* @package com.jrj.android.pad.common
* @date 2012-10-9
* @author wangxinxuan
*/
package com.jrj.android.pad.common;

import org.json.JSONArray;
import org.json.JSONException;

/**
 * @classname JSONObject
 * @author wangxinxuan
 * @date 2012-10-9
 * @remark 不会返回空数据
 * @description 自行扩展的JSONObject
 */
public class JSONObject extends org.json.JSONObject {

    public JSONObject(String json) throws JSONException {
	super(json);
    }

    @Override
    public Object get(String name) throws JSONException {
	if (has(name)) {
	    return super.get(name);
	} else {
	    return null;
	}
    }

    @Override
    public boolean getBoolean(String name) throws JSONException {
	if (has(name)) {
	    return super.getBoolean(name);
	} else {
	    return false;
	}
    }

    @Override
    public double getDouble(String name) throws JSONException {
	if (has(name)) {
	    return super.getDouble(name);
	} else {
	    return 0d;
	}
    }

    @Override
    public int getInt(String name) throws JSONException {
	if (has(name)) {
	    return super.getInt(name);
	} else {
	    return 0;
	}
    }

    @Override
    public JSONArray getJSONArray(String name) throws JSONException {
	if (has(name)) {
	    return super.getJSONArray(name);
	} else {
	    return null;
	}
    }

    @Override
    public org.json.JSONObject getJSONObject(String name) throws JSONException {
	if (has(name)) {
	    return super.getJSONObject(name);
	} else {
	    return null;
	}
    }

    @Override
    public long getLong(String name) throws JSONException {
	if (has(name)) {
	    return super.getLong(name);
	} else {
	    return 0l;
	}
    }

    @Override
    public String getString(String name) throws JSONException {
	if (has(name)) {
	    return super.getString(name);
	} else {
	    return "";
	}
    }

}