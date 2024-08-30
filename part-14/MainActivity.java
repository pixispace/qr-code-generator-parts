package com.pixispace.qrcodegenerator;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import com.pixispace.qrcodegenerator.databinding.ActivityMainBinding;
import com.pixispace.qrcodegenerator.databinding.LayoutColorButtonBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private LayoutColorButtonBinding foreColorButton, backColorButton;
    private int foreColor = Color.WHITE;
    private int backColor = Color.BLACK;
    private boolean settingForeColor = false;
    private String data = "";
    private final PreferenceHelper preferenceHelper = PreferenceHelper.getInstance();

    private final TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
        }

        @Override
        public void afterTextChanged(Editable s) {
            setText(s.toString().trim(), false);
            updateGenerateButton();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        restoreSavedData();
        setColorButtons();
        setListeners();
    }

    @Override
    protected void onResume() {
        super.onResume();
        restoreSavedData();
        setColorButtons();
    }

    private void restoreSavedData() {
        setText(preferenceHelper.getLastUsedText(), true);
        foreColor = preferenceHelper.getLastUsedForeColor();
        backColor = preferenceHelper.getLastUsedBackColor();
    }

    private void setText(String text, boolean updateWidget) {
        if (text == null) {
            text = "";
        }
        data = text.trim();
        preferenceHelper.setLastUsedText(data);
        if (updateWidget) {
            binding.editText.setText(data);
        }
    }

    private void updateGenerateButton() {
        binding.generateButton.setEnabled(!data.isEmpty());
    }

    private void swapColors() {
        int temp = foreColor;
        setForeColor(backColor);
        setBackColor(temp);
    }

    private void showColorPicker(boolean isForeColor) {
        settingForeColor = isForeColor;
        FragmentManager fragmentManager = getSupportFragmentManager();
        ColorPickerFragment fragment = (ColorPickerFragment) fragmentManager.findFragmentByTag(ColorPickerFragment.TAG);
        if (fragment == null) {
            fragment = new ColorPickerFragment(isForeColor ? foreColor : backColor);
            fragment.setCancelable(false);
        }
        fragment.show(fragmentManager, ColorPickerFragment.TAG);
    }

    private void setListeners() {
        foreColorButton.container.setOnClickListener(v -> showColorPicker(true));
        backColorButton.container.setOnClickListener(v -> showColorPicker(false));
        binding.swapButton.setOnClickListener(v -> swapColors());
        binding.editText.addTextChangedListener(textWatcher);
    }

    private void setColorButtons() {
        foreColorButton = binding.foreColorButton;
        backColorButton = binding.backColorButton;

        foreColorButton.label.setText(R.string.fore_color);
        backColorButton.label.setText(R.string.back_color);

        setForeColor(foreColor);
        setBackColor(backColor);
    }

    private void setForeColor(int color) {
        foreColor = color;
        foreColorButton.canvas.setBackgroundColor(color);
        preferenceHelper.setLastUsedForeColor(foreColor);
    }

    private void setBackColor(int color) {
        backColor = color;
        backColorButton.canvas.setBackgroundColor(color);
        preferenceHelper.setLastUsedBackColor(backColor);
    }

    void setNewColor(int color) {
        if (settingForeColor) {
            setForeColor(color);
        } else {
            setBackColor(color);
        }
    }
}