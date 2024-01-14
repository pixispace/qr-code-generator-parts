package com.pixispace.qrcodegenerator;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;

import androidx.annotation.NonNull;

import com.google.android.material.chip.Chip;
import com.pixispace.qrcodegenerator.databinding.LayoutColorPickerBinding;

public class ColorPickerDialog extends Dialog {

    private final LayoutColorPickerBinding binding;
    private int selectedColor;

    public ColorPickerDialog(@NonNull Context context, int selectedColor) {
        super(context, R.style.DialogStyle);
        LayoutInflater inflater = LayoutInflater.from(context);
        binding = LayoutColorPickerBinding.inflate(inflater);
        setContentView(binding.getRoot());
        setTitle(R.string.select_color);
        this.selectedColor = selectedColor;
    }
}
