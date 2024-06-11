package com.pixispace.qrcodegenerator;

import android.app.Dialog;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class ColorPickerFragment extends DialogFragment {

    private final int defaultColor;

    public static final String TAG = "com.pixispace.qrcodegenerator.color_picker_fragment";

    public ColorPickerFragment() {
        defaultColor = Color.BLACK;
    }

    public ColorPickerFragment(int defaultColor) {
        this.defaultColor = defaultColor;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        super.onCreateDialog(savedInstanceState);
        setCancelable(false);
        return new ColorPickerDialog(requireContext(), defaultColor);
    }
}
