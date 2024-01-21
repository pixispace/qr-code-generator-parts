package com.pixispace.qrcodegenerator;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;

import com.pixispace.qrcodegenerator.databinding.ActivityMainBinding;
import com.pixispace.qrcodegenerator.databinding.LayoutColorButtonBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private LayoutColorButtonBinding foreColorButton, backColorButton;
    private int foreColor = Color.WHITE;
    private int backColor = Color.BLACK;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setColorButtons();
        setListeners();
    }

    private void setColorButtons() {
        foreColorButton = binding.foreColorButton;
        backColorButton = binding.backColorButton;
        foreColorButton.label.setText(R.string.fore_color);
        backColorButton.label.setText(R.string.back_color);
        setForeColor(foreColor);
        setBackColor(backColor);
    }

    private void setListeners() {
        foreColorButton.container.setOnClickListener(v -> showColorPicker(true));
        backColorButton.container.setOnClickListener(v -> showColorPicker(false));
    }

    private void setForeColor(int color) {
        foreColor = color;
        foreColorButton.canvas.setBackgroundColor(color);
    }

    private void setBackColor(int color) {
        backColor = color;
        backColorButton.canvas.setBackgroundColor(color);
    }

    private void showColorPicker(boolean isForeColor) {
    }
}