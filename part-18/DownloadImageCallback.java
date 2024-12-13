package com.pixispace.delqrcodegenerator;

import android.graphics.Bitmap;

public interface DownloadImageCallback {
    void onImageReceived(Bitmap bitmap, ImageSize imageSize);
    void onError(String error);
}