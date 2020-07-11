package com.example.mhdzam.sellgateproject.Network;

/**
 * Created by MhdZam on 1/20/2018.
 */

public class WebConfiguration {
    // Syriatel config:
    public static final String SECURE_PROTOCOL = "https";

    public static final String PROTOCOL = "http";

    public static final String PORT = "8080";
    public static final String HOST_IP = "10.253.23.115";

    public static final String HOST_NAME = "dev-fapi.syriatel.sy";

    // SERVER_NAME Syriatel

    public static final String SERVER_NAME = "sellgateWS";

    // Web service
    public static final String WEB_SERVICE = "webresources/generic";

    public static final String getServer() {

                return SECURE_PROTOCOL + "://" + HOST_NAME + "/" + SERVER_NAME + "/" + WEB_SERVICE + "/";
    }
    public static String getImageUrl() {
      return "";
    }
}
