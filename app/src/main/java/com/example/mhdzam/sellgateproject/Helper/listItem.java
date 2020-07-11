package com.example.mhdzam.sellgateproject.Helper;

/**
 * Created by MhdZam on 12/13/2017.
 */

public class listItem {
    private String head;
    private String desc;
    private String imgURL;



    public listItem(String head, String desc, String imgurl) {
        this.head = head;
        this.desc = desc;
        this.imgURL = imgurl;
    }

    public String getHead() {
        return head;
    }

    public String getDesc() {
        return desc;
    }

    public String getImgURL() {
        return imgURL;
    }
}
