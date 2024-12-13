package com.pixispace.delqrcodegenerator;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class HttpHelper {
    private static HttpHelper instance;
    private final RequestQueue requestQueue;

    public static HttpHelper getInstance() {
        if (instance == null) {
            instance = new HttpHelper();
        }
        return instance;
    }
    private HttpHelper() {
        requestQueue = Volley.newRequestQueue(App.getInstance());
    }
}

