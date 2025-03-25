package com.example.stylisttext.Activities;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.stylisttext.R;
import com.example.stylisttext.databinding.ActivityEmojiWordBinding;

public class EmojiWordActivity extends AppCompatActivity {
    ActivityEmojiWordBinding binding;
    String[] styles = {"Happiness😊", "Love❤️", "Sadness😞", "Surprise😲", "Fear😨", "Anger😠"};
    String[] happinessEmojis = {"😊", "😄", "🤩", "🥳", "😁", "😆", "😂", "🙌", "🎉", "✨"};
    String[] loveEmojis = {"❤️", "🥰", "😍", "💖", "💕", "💓", "💗", "💞", "😘"};
    String[] sadnessEmojis = {"😢", "😭", "😞", "😔", "☹️", "💔", "😿", "🌧️", "🥀", "😩"};
    String[] surpriseEmojis = {"😲", "😯", "🤯", "😳", "😮", "‼️", "💫", "🎊", "🙀", "👀"};
    String[] fearEmojis = {"😨", "😰", "😱", "👻", "💀", "🕷️", "🕸️", "😓", "🙈"};
    String[] angerEmojis = {"😠", "🤬", "👿", "💢", "😤", "👊", "🗯️", "🔥", "💣", "⚔️"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityEmojiWordBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Window w = getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        //toolbar
        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Emoji Word");
        binding.toolbar.setNavigationOnClickListener(v -> {
            onBackPressed();
        });
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, styles);
        binding.styleSpinner.setAdapter(adapter);
        binding.styleSpinner.setText(styles[0], false);
        binding.textInputLayout.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String inputText = s.toString();
                if (inputText.isEmpty()) {
                    binding.resultTextView.setText("");
                    return;
                }
                String[] words = inputText.split(" ");
                String selectedStyle = binding.styleSpinner.getText().toString();
                String[] emojis;
                switch (selectedStyle) {
                    case "Happiness😊":
                        emojis = happinessEmojis;
                        break;
                    case "Love❤️":
                        emojis = loveEmojis;
                        break;
                    case "Sadness😞":
                        emojis = sadnessEmojis;
                        break;
                    case "Surprise😲":
                        emojis = surpriseEmojis;
                        break;
                    case "Fear😨":
                        emojis = fearEmojis;
                        break;
                    case "Anger😠":
                        emojis = angerEmojis;
                        break;
                    default:
                        emojis = new String[0];
                }
                StringBuilder result = new StringBuilder();
                for (String word : words) {
                    if (emojis.length > 0) {
                        int randomIndex = (int) (Math.random() * emojis.length);
                        result.append(word).append(" ").append(emojis[randomIndex]).append(" ");
                    }
                }
                binding.resultTextView.setText(result.toString());
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
}