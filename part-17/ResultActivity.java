package com.pixispace.delqrcodegenerator;

import android.app.AlertDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.pixispace.delqrcodegenerator.databinding.ActivityMainBinding;
import com.pixispace.delqrcodegenerator.databinding.ActivityResultBinding;

public class ResultActivity extends AppCompatActivity {

    private ActivityResultBinding binding;
    private ImageSize imageSize = ImageSize.small;
    private String message = "";
    private int foreColor = Color.BLACK;
    private int backColor = Color.WHITE;

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
        if (isBusy) {
            binding.progressHorizontal.setVisibility(View.VISIBLE);
            binding.progressTextView.setVisibility(View.VISIBLE);
            binding.frameLayout.setVisibility(View.GONE);
            binding.buttonToggleGroup.setVisibility(View.GONE);
            binding.summaryTextView.setVisibility(View.GONE);
        } else {
            binding.progressHorizontal.setVisibility(View.GONE);
            binding.progressTextView.setVisibility(View.GONE);
            binding.frameLayout.setVisibility(View.VISIBLE);
            binding.buttonToggleGroup.setVisibility(View.VISIBLE);
            binding.summaryTextView.setVisibility(View.VISIBLE);
        }
    }

    private void getData() {
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            message = extras.getString(EXTRA_MESSAGE, "").trim();
            foreColor = extras.getInt(EXTRA_FORE_COLOR, Color.BLACK);
            backColor = extras.getInt(EXTRA_BACK_COLOR, Color.WHITE);
        }

        if (message.isEmpty()) {
            goBackAfterMessage(R.string.insufficient_information, R.string.no_text);
        }
    }

    private void setListeners() {
        binding.buttonToggleGroup.addOnButtonCheckedListener((group, checkedId, isChecked) -> {
            if (isChecked) {
                if (checkedId == binding.smallButton.getId()) {
                    imageSize = ImageSize.small;
                } else if (checkedId == binding.mediumButton.getId()) {
                    imageSize = ImageSize.medium;
                } else if (checkedId == binding.largeButton.getId()) {
                    imageSize = ImageSize.large;
                }
                getImage();
            }
        });
    }

    private void getImage() {

    }

    private void goBackAfterMessage(int titleResId, int messageResId) {
        String title = getString(titleResId);
        String message = getString(messageResId);
        goBackAfterMessage(title, message);
    }

    private void goBackAfterMessage(String title, String message) {
        new AlertDialog.Builder(this)
                .setCancelable(false)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton(android.R.string.ok, (dialog, which) -> {
                    dialog.dismiss();
                    getOnBackPressedDispatcher().onBackPressed();
                })
                .show();
    }
}
