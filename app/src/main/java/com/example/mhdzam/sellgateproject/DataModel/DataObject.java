package com.example.mhdzam.sellgateproject.DataModel;

import java.io.Serializable;

/**
 * Created by MhdZam on 1/16/2018.
 */

public class DataObject implements Serializable{
    private Object data;

    public DataObject(Object data) {
        this.data = data;
    }

    public Object getData() {
        return data;
    }
}
