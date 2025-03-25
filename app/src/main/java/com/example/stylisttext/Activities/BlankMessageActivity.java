package com.example.stylisttext.Activities;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.BackgroundColorSpan;
import android.view.Window;
import android.view.WindowManager;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.stylisttext.R;
import com.example.stylisttext.databinding.ActivityBlankMessageBinding;

public class BlankMessageActivity extends AppCompatActivity {
    ActivityBlankMessageBinding binding;
    int currentSize = 6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityBlankMessageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Window w = getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        //toolbar
        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Blank Message");
        binding.slider.addOnChangeListener((slider, value, fromUser) -> {
            currentSize = (int) value;
            updateText();
        });
        binding.straightLineSwitch.setOnClickListener(v -> {
            updateText();
        });
        binding.btnCopy.setOnClickListener(v -> {
            ClipboardManager clipboard = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
            ClipData clip = ClipData.newPlainText("Copied Text", binding.resultTextView.getText().toString());
            clipboard.setPrimaryClip(clip);
        });
    }

    public void updateText() {
        boolean isStraight = binding.straightLineSwitch.isChecked();
        int size = currentSize;
        StringBuilder contentBuilder = new StringBuilder();
        if (isStraight) {
            String spaces = " ";
            for (int i = 0; i < size; i++) {
                spaces += "\n ";
            }
            contentBuilder.append(spaces);
        } else {
            // Tạo chuỗi khoảng trắng, tự động xuống dòng mỗi 50 ký tự
            String baseSpace = " ";
            int wrapLength = 50;  // số ký tự tối đa trước khi xuống dòng
            int count = 0;
            for (int i = 0; i < size; i++) {
                contentBuilder.append(baseSpace);
                count++;
                if (count == wrapLength) {
                    contentBuilder.append("\n");
                    count = 0;
                }
            }
        }

        binding.resultTextView.setText(contentBuilder.toString());
    }
}