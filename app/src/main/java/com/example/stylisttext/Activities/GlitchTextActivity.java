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
import com.example.stylisttext.databinding.ActivityGlitchTextBinding;

import java.util.Random;

public class GlitchTextActivity extends AppCompatActivity {
    ActivityGlitchTextBinding binding;
    int upSize = 0;
    int midSize = 0;
    int downSize = 0;
    String[] upChars = {"̍̎̄̅̿̑̆̐", "͒͗͑͆̔̓̚", "̾̉̊̋̌", "᷇᷆᷄᷅᷉", "͉͈͇͌͋͊", "⃘⃙⃚⃖⃗⃛", "⃜⃝⃞⃟", "᷿᷾᷄᷅", "꙰꙱꙲꙳", "︠︡︢︣", "҈҉", "؎؏۞", "۩۩", "֍֎֏"};

    String[] midChars = {"̷̸̶", "͟͞", "̴̵", "̺̻̽", "⃰⁎⁕", "۝۞", "〜〰️", "༄༅", "༒༓", "※⁂", "⸺⸻", "⸞⸟⸠", "⸚⸛⸜", "⳾⳿"};

    String[] downChars = {"̖̗̘̙̜̝̞̟̠", "̣̤̥̦̩̪̫̬̮̯", "̰̱̲̳̹̺", "̧̨̦̩", "̪̫̬̭̮", "͓͔͕͖", "͙͚͛͜͝", "⃪⃫⃩", "⃬⃭⃮", "⃯⃐⃑", "ꙴꙵꙶ", "ⷠⷡⷢ", "ⷣⷤⷥ", "ⷦⷧⷨ"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityGlitchTextBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Window w = getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        //toolbar
        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Glitch Text");
        binding.toolbar.setNavigationOnClickListener(v -> {
            onBackPressed();
        });

        binding.sliderUpwards.addOnChangeListener((slider, value, fromUser) -> {
            upSize = (int) value;
            makeText();
        });
        binding.sliderMiddles.addOnChangeListener((slider, value, fromUser) -> {
            midSize = (int) value;
            makeText();
        });
        binding.sliderDownwards.addOnChangeListener((slider, value, fromUser) -> {
            downSize = (int) value;
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

        Random random = new Random();

        float upIntensity = upSize / 15f;
        float midIntensity = midSize / 15f;
        float downIntensity = downSize / 15f;

        for (char c : text.toCharArray()) {
            if (random.nextFloat() < upIntensity) {
                String upSet = upChars[random.nextInt(upChars.length)];
                result.append(upSet.charAt(random.nextInt(upSet.length())));
            }

            if (random.nextFloat() < midIntensity) {
                String midSet = midChars[random.nextInt(midChars.length)];
                result.append(midSet.charAt(random.nextInt(midSet.length())));
            }

            if (random.nextFloat() < downIntensity) {
                String downSet = downChars[random.nextInt(downChars.length)];
                result.append(downSet.charAt(random.nextInt(downSet.length())));
            }

            result.append(c);
        }
        binding.resultTextView.setText(result.toString());
    }
}