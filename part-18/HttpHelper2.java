package com.pixispace.delqrcodegenerator;

import android.graphics.Bitmap;
import android.widget.ImageView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.Volley;

public class HttpHelper {
    private static HttpHelper instance;
    private final RequestQueue requestQueue;
    private final int MAX_SIZE = 1080;

    public static HttpHelper getInstance() {
        if (instance == null) {
            instance = new HttpHelper();
        }
        return instance;
    }

    private HttpHelper() {
        requestQueue = Volley.newRequestQueue(App.getInstance());
    }

    public void downloadImage(String message, int backColor, int foreColor, ImageSize imageSize, DownloadImageCallback callback) {
        String url = "https://quickchar.io/qr?ecLevel=H" +
                "&text=" + Util.urlEncode(message) +
                "&light=" + Util.colorToHex(foreColor, false) +
                "&dark=" + Util.colorToHex(backColor, false) +
                "&size=" + imageSize.size;
        ImageRequest request = new ImageRequest(
                url,
                bitmap -> {
                    if (callback != null) {
                        callback.onImageReceived(bitmap, imageSize);
                    }
                },
                MAX_SIZE,
                MAX_SIZE,
                ImageView.ScaleType.CENTER_INSIDE,
                Bitmap.Config.ARGB_8888,
                error -> {
                    if  (callback != null) {
                        String errorMessage = App.getInstance().getString(R.string.check_connection);
                        callback.onError(errorMessage);
                    }
                }
        );
        requestQueue.add(request);
    }

}

