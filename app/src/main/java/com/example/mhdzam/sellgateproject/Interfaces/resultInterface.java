package com.example.mhdzam.sellgateproject.Interfaces;

import com.android.volley.VolleyError;
import com.example.mhdzam.sellgateproject.DataModel.WebServiceResponse;

import org.json.JSONObject;

/**
 * Created by MhdZam on 1/11/2018.
 */

public interface resultInterface {
    void OnSuccess(/*JSONObject response*/ JSONObject Data);

    void OnError(int errorCode, String errorMessage);

    void OnFailure(int errId);
}
