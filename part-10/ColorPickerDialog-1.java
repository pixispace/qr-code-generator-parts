package com.pixispace.qrcodegenerator;

import android.app.Dialog;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.view.LayoutInflater;

import androidx.annotation.NonNull;

import com.google.android.material.chip.Chip;
import com.pixispace.qrcodegenerator.databinding.LayoutColorPickerBinding;

import java.util.HashMap;

public class ColorPickerDialog extends Dialog {

    private final LayoutColorPickerBinding binding;
    private int selectedColor;
    private final HashMap<String, PresetData> presetMap = new HashMap<>();

    public ColorPickerDialog(@NonNull Context context, int selectedColor) {
        super(context, R.style.DialogStyle);
        LayoutInflater inflater = LayoutInflater.from(context);
        binding = LayoutColorPickerBinding.inflate(inflater);
        setContentView(binding.getRoot());
        setTitle(R.string.select_color);
        this.selectedColor = selectedColor;

        setPresets();
        setListeners();
        updateUI();
    }

    private void setPresets() {
        String color = "#ffffff";
        binding.preset1Chip.setTag(color);
        binding.preset1Chip.setChipBackgroundColor(ColorStateList.valueOf(Color.parseColor(color)));
        presetMap.put(color, new PresetData(binding.preset1Chip, true));

        color = "#000000";
        binding.preset2Chip.setTag(color);
        binding.preset2Chip.setChipBackgroundColor(ColorStateList.valueOf(Color.parseColor(color)));
        presetMap.put(color, new PresetData(binding.preset2Chip, false));

        color = "#ff5722";
        binding.preset3Chip.setTag(color);
        binding.preset3Chip.setChipBackgroundColor(ColorStateList.valueOf(Color.parseColor(color)));
        presetMap.put(color, new PresetData(binding.preset3Chip, false));

        color = "#009688";
        binding.preset4Chip.setTag(color);
        binding.preset4Chip.setChipBackgroundColor(ColorStateList.valueOf(Color.parseColor(color)));
        presetMap.put(color, new PresetData(binding.preset4Chip, false));

        color = "#fdd835";
        binding.preset5Chip.setTag(color);
        binding.preset5Chip.setChipBackgroundColor(ColorStateList.valueOf(Color.parseColor(color)));
        presetMap.put(color, new PresetData(binding.preset5Chip, true));

        color = "#795548";
        binding.preset6Chip.setTag(color);
        binding.preset6Chip.setChipBackgroundColor(ColorStateList.valueOf(Color.parseColor(color)));
        presetMap.put(color, new PresetData(binding.preset6Chip, false));

        color = "#616161";
        binding.preset7Chip.setTag(color);
        binding.preset7Chip.setChipBackgroundColor(ColorStateList.valueOf(Color.parseColor(color)));
        presetMap.put(color, new PresetData(binding.preset7Chip, false));

        color = "#9c27b0";
        binding.preset8Chip.setTag(color);
        binding.preset8Chip.setChipBackgroundColor(ColorStateList.valueOf(Color.parseColor(color)));
        presetMap.put(color, new PresetData(binding.preset8Chip, false));
    }

    private void setListeners() {
        binding.cancelButton.setOnClickListener(v -> dismiss());
        binding.applyButton.setOnClickListener(v -> {
            MainActivity parent = (MainActivity) getOwnerActivity();
            if (parent != null) {
                parent.setNewColor(selectedColor);
            }
            dismiss();
        });

        binding.redSlider.addOnChangeListener((slider, value, fromUser) -> {
            if (fromUser) {
                clearPresetSelection();
                int red = (int) value;
                selectedColor = Color.rgb(red, Color.green(selectedColor), Color.blue(selectedColor));
                setSelectedColor();
            }
        });
        binding.greenSlider.addOnChangeListener((slider, value, fromUser) -> {
            if (fromUser) {
                clearPresetSelection();
                int green = (int) value;
                selectedColor = Color.rgb(Color.red(selectedColor), green, Color.blue(selectedColor));
                setSelectedColor();
            }
        });
        binding.blueSlider.addOnChangeListener((slider, value, fromUser) -> {
            if (fromUser) {
                clearPresetSelection();
                int blue = (int) value;
                selectedColor = Color.rgb(Color.red(selectedColor), Color.green(selectedColor), blue);
                setSelectedColor();
            }
        });

        binding.preset1Chip.setOnClickListener(v -> applyPreset(v.getTag()));
        binding.preset2Chip.setOnClickListener(v -> applyPreset(v.getTag()));
        binding.preset3Chip.setOnClickListener(v -> applyPreset(v.getTag()));
        binding.preset4Chip.setOnClickListener(v -> applyPreset(v.getTag()));
        binding.preset5Chip.setOnClickListener(v -> applyPreset(v.getTag()));
        binding.preset6Chip.setOnClickListener(v -> applyPreset(v.getTag()));
        binding.preset7Chip.setOnClickListener(v -> applyPreset(v.getTag()));
        binding.preset8Chip.setOnClickListener(v -> applyPreset(v.getTag()));
    }

    private void updateUI() {
        setSelectedColor();
    }

    private void setSelectedColor() {
        binding.colorPreview.setCardBackgroundColor(selectedColor);
    }

    private void clearPresetSelection() {
        binding.preset1Chip.setChipIcon(null);
        binding.preset2Chip.setChipIcon(null);
        binding.preset3Chip.setChipIcon(null);
        binding.preset4Chip.setChipIcon(null);
        binding.preset5Chip.setChipIcon(null);
        binding.preset6Chip.setChipIcon(null);
        binding.preset7Chip.setChipIcon(null);
        binding.preset8Chip.setChipIcon(null);
    }

    private void applyPreset(Object tag) {
        clearPresetSelection();
        String hexColor = (tag != null) ? tag.toString() : "";
        PresetData data = presetMap.get(hexColor);
        if (data != null) {

            int resId = data.isDarkCheck ? R.drawable.check_black : R.drawable.check_white;
            data.chip.setChipIconResource(resId);

            selectedColor = Color.parseColor(hexColor);
            setSelectedColor();
            setSliderValues();
        }
    }

    private void setSliderValues() {
        binding.redSlider.setValue(Color.red(selectedColor));
        binding.greenSlider.setValue(Color.green(selectedColor));
        binding.blueSlider.setValue(Color.blue(selectedColor));
    }

    private static class PresetData {
        private final Chip chip;
        private final boolean isDarkCheck;

        PresetData(Chip colorPanel, boolean useDarkCheck) {
            this.chip = colorPanel;
            this.isDarkCheck = useDarkCheck;
        }
    }
}
