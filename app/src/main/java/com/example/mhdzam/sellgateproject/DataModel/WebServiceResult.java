package com.example.mhdzam.sellgateproject.DataModel;

import java.io.Serializable;

/**
 * Created by MhdZam on 1/16/2018.
 */

public class WebServiceResult implements Serializable{
    private String code;
    private String message;
    private DataObject data;

    public WebServiceResult(String code, String message, DataObject data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public DataObject getData() {
        return data;
    }

    public void setData(DataObject data) {
        this.data = data;
    }
}
