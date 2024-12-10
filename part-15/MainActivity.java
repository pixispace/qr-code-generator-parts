package com.pixispace.delqrcodegenerator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.FragmentManager;

import android.graphics.Color;
import android.os.IBinder;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.pixispace.delqrcodegenerator.databinding.ActivityMainBinding;
import com.pixispace.delqrcodegenerator.databinding.LayoutColorButtonBinding;

public class MainActivity extends AppCompatActivity {
    private String data = "";
    private final PreferenceHelper preferenceHelper = PreferenceHelper.getInstance();
    private ActivityMainBinding binding;
    private LayoutColorButtonBinding foreColorButton, backColorButton;
    private int foreColor = Color.WHITE;
    private int backColor = Color.BLACK;
    private boolean settingForeColor = false;
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
        binding.editText.addTextChangedListener(textWatcher);
        binding.generateButton.setOnClickListener(v -> generateQrCode());
    }

    private void setForeColor(int color) {
        foreColor = color;
        foreColorButton.canvas.setBackgroundColor(color);
        preferenceHelper.setLastUsedForeColor(color);
    }

    private void setBackColor(int color) {
        backColor = color;
        backColorButton.canvas.setBackgroundColor(color);
        preferenceHelper.setLastUsedBackColor(color);
    }

    private void showColorPicker(boolean isForeColor) {
        settingForeColor = isForeColor;
        FragmentManager fragmentManager = getSupportFragmentManager();
        ColorPickerFragment fragment = (ColorPickerFragment) fragmentManager.findFragmentByTag(ColorPickerFragment.TAG);
        if (fragment == null) {
            fragment = new ColorPickerFragment(isForeColor ? foreColor : backColor);
            fragment.setCancelable(false);
        }
        fragment.show(getSupportFragmentManager(), ColorPickerFragment.TAG);
    }


    void setNewColor(int color) {
        if (settingForeColor) {
            setForeColor(color);
        } else {
            setBackColor(color);
        }
    }

    private void hideKeyboard() {
        View view = getWindow().getCurrentFocus();
        if (view != null) {
            IBinder binder = view.getWindowToken();
            if (binder != null) {
                InputMethodManager manager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                if (manager != null) {
                    manager.hideSoftInputFromWindow(binder, 0);
                }
            }
        }
    }

    private void generateQrCode() {
        hideKeyboard();

        Intent intent = new Intent(this, ResultActivity.class);
        intent.putExtra(ResultActivity.EXTRA_MESSAGE, data);
        intent.putExtra(ResultActivity.EXTRA_FORE_COLOR, foreColor);
        intent.putExtra(ResultActivity.EXTRA_BACK_COLOR, backColor);
        startActivity(intent);
    }

    private void updateGenerateButton() {
        binding.generateButton.setEnabled(!data.isEmpty());
    }
}
