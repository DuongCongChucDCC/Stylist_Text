package com.example.stylisttext.Activities;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Window;
import android.view.WindowManager;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.stylisttext.R;
import com.example.stylisttext.databinding.ActivityTextRepeaterBinding;

public class TextRepeaterActivity extends AppCompatActivity {
    ActivityTextRepeaterBinding binding;
    int repeatNumber = 10;
    boolean isStraightLine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTextRepeaterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Window w = getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        //toolbar
        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Text Repeater");
        binding.sliderRepeat.addOnChangeListener((slider, value, fromUser) -> {
            repeatNumber = (int) value;
            makeText();
        });
        binding.textInputLayout.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                makeText();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        binding.straightLineSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            isStraightLine = isChecked;
            makeText();
        });
        binding.btnCopy.setOnClickListener(v -> {
            ClipboardManager clipboard = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
            ClipData clip = ClipData.newPlainText("Copied Text", binding.resultTextView.getText().toString());
            clipboard.setPrimaryClip(clip);
        });
    }

    public void makeText() {
        String text = binding.textInputLayout.getEditText().getText().toString();
        if (text.isEmpty()) {
            binding.resultTextView.setText("");
            return;
        }
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < repeatNumber; i++) {
            if (isStraightLine) {
                result.append("\n").append(text);
            } else {
                result.append(" ").append(text);
            }
        }
        binding.resultTextView.setText(result.toString());
    }
}