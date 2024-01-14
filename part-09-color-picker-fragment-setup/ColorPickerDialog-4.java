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

        // Call the method
        setPresets();
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

    private static class PresetData {
        private final Chip chip;
        private final boolean isDarkCheck;

        PresetData(Chip colorPanel, boolean useDarkCheck) {
            this.chip = colorPanel;
            this.isDarkCheck = useDarkCheck;
        }
    }
}
