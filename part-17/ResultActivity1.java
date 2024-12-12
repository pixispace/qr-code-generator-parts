package com.pixispace.delqrcodegenerator;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.pixispace.delqrcodegenerator.databinding.ActivityMainBinding;
import com.pixispace.delqrcodegenerator.databinding.ActivityResultBinding;

public class ResultActivity extends AppCompatActivity {

    private ActivityResultBinding binding;

    public static final String EXTRA_MESSAGE = "message";
    public static final String EXTRA_FORE_COLOR = "fore_color";
    public static final String EXTRA_BACK_COLOR = "back_color";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityResultBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        updateUI(true);
        getData();
        setListeners();
    }

    private void updateUI(boolean isBusy) {

    }

    private void getData() {

    }

    private void setListeners() {

    }
}