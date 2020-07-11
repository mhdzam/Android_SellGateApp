package com.example.mhdzam.sellgateproject.DataModel;

import org.json.JSONObject;

/**
 * Created by MhdZam on 1/16/2018.
 */

public class WebServiceResponsePost {
    private String code;
    private String errorMessage;
    private JSONObject data;
    private  int allItemsCount;

    public int getAllItemsCount() {
        return allItemsCount;
    }

    public void setAllItemsCount(int allItemsCount) {
        this.allItemsCount = allItemsCount;
    }

    public WebServiceResponsePost(String code, String message, JSONObject data) {
        this.code = code;
        this.errorMessage = message;
        this.data = data;
    }

    public WebServiceResponsePost() {
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String message) {
        this.errorMessage = message;
    }

    public JSONObject getData() {
        return data;
    }

    public void setData(JSONObject data) {
        this.data = data;
    }
}
