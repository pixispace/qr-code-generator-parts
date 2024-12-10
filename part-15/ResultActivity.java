package com.pixispace.delqrcodegenerator;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class ResultActivity extends AppCompatActivity {

    public static final String EXTRA_MESSAGE = "message";
    public static final String EXTRA_FORE_COLOR = "fore_color";
    public static final String EXTRA_BACK_COLOR = "back_color";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
    }
}