package com.bastlibrary.api;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by json解析 on 2017/6/27 0027.
 */

public class AppJson {
    /**
     * 获取返回的判别值
     *
     * @param result
     * @return code
     * @throws JSONException
     */
    public static String getCode(String result) throws JSONException {
        String code = "";
        JSONObject jsonObject = new JSONObject(result);
        code = jsonObject.getString("code");
        return code;

    }

    /**
     * 获取返回的判别值
     *
     * @param result
     * @return msg
     * @throws JSONException
     */
    public static String getMsg(String result) throws JSONException {
        String msg = "";
        JSONObject jsonObject = new JSONObject(result);
        msg = jsonObject.getString("msg");
        return msg;
    }

    /**
     * 获取返回的判别值
     *
     * @param result
     * @return data
     * @throws JSONException
     */
    public static String getData(String result) throws JSONException {
        String data = "";
        JSONObject jsonObject = new JSONObject(result);
        data = jsonObject.getString("data");
        return data;
    }

}
