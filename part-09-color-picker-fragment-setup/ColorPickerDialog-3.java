package com.pixispace.qrcodegenerator;

import android.app.Dialog;
import android.content.Context;
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
