package com.example.mhdzam.sellgateproject.DataModel;

import org.json.JSONObject;

/**
 * Created by MhdZam on 1/16/2018.
 */

public class WebServiceResponse {
    private int code;
    private String message;
    private JSONObject data;
    private  int allItemsCount;

    public int getAllItemsCount() {
        return allItemsCount;
    }

    public void setAllItemsCount(int allItemsCount) {
        this.allItemsCount = allItemsCount;
    }

    public WebServiceResponse(int code, String message, JSONObject data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public WebServiceResponse() {
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public JSONObject getData() {
        return data;
    }

    public void setData(JSONObject data) {
        this.data = data;
    }
}
