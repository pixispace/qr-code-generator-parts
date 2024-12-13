package com.pixispace.delqrcodegenerator;

import android.graphics.Color;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class Util {
    private Util() {
    }

    public static String colorToHex(int color, boolean includePrefix) {
        StringBuilder builder = new StringBuilder();
        if (includePrefix) {
            builder.append("#");
        }

        int red = Color.red(color);
        if (red < 0xf) {
            builder.append(0);
        }
        builder.append(Integer.toHexString(red));

        int green = Color.green(color);
        if (green < 0xf) {
            builder.append(0);
        }
        builder.append(Integer.toHexString(green));

        int blue = Color.blue(color);
        if (blue < 0xf) {
            builder.append(0);
        }
        builder.append(Integer.toHexString(blue));

        return builder.toString();
    }

    public static String urlEncode(String message) {
        if (message == null) {
            message = "";
        }
        try {
            return URLEncoder.encode(message, "utf-8");
        } catch (UnsupportedEncodingException ignore) {

        }
        return "";
    }
}
