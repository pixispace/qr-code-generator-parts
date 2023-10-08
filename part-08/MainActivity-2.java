package com.pixispace.qrcodegenerator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.pixispace.qrcodegenerator.databinding.ActivityMainBinding;
import com.pixispace.qrcodegenerator.databinding.LayoutColorButtonBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private LayoutColorButtonBinding foreColorButton, backColorButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}