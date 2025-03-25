package com.example.stylisttext.Activities;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.graphics.Typeface;
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
import com.example.stylisttext.databinding.ActivityTextToEmojiBinding;

public class TextToEmojiActivity extends AppCompatActivity {
    ActivityTextToEmojiBinding binding;
    String[] letterPatterns = {
            // A (8x8)
            "  ████  \n" + " █    █ \n" + "█      █\n" + "████████\n" + "█      █\n" + "█      █\n" + "█      █\n" + "█      █",

            // B (8x8)
            "██████  \n" + "█     █ \n" + "█     █ \n" + "██████  \n" + "█     █ \n" + "█     █ \n" + "█     █ \n" + "██████  ",

            // C (8x8)
            "  █████ \n" + " █     █\n" + "█       \n" + "█       \n" + "█       \n" + "█       \n" + " █     █\n" + "  █████ ",

            // D (8x8)
            "██████  \n" + "█     █ \n" + "█      █\n" + "█      █\n" + "█      █\n" + "█      █\n" + "█     █ \n" + "██████  ",

            // E (8x8)
            "████████\n" + "█       \n" + "█       \n" + "██████  \n" + "█       \n" + "█       \n" + "█       \n" + "████████",

            // F (8x8)
            "████████\n" + "█       \n" + "█       \n" + "██████  \n" + "█       \n" + "█       \n" + "█       \n" + "█       ",

            // G (8x8)
            "  █████ \n" + " █     █\n" + "█       \n" + "█   ███\n" + "█     █\n" + "█     █\n" + " █     █\n" + "  █████ ",

            // H (8x8)
            "█      █\n" + "█      █\n" + "█      █\n" + "████████\n" + "█      █\n" + "█      █\n" + "█      █\n" + "█      █",

            // I (8x8)
            "████████\n" + "   ██   \n" + "   ██   \n" + "   ██   \n" + "   ██   \n" + "   ██   \n" + "   ██   \n" + "████████",

            // J (8x8)
            "      █\n" + "      █\n" + "      █\n" + "      █\n" + "      █\n" + "█     █\n" + " █    █\n" + "  ████ ",

            // K (8x8)
            "█     █\n" + "█    █ \n" + "█   █  \n" + "████   \n" + "█   █  \n" + "█    █ \n" + "█     █\n" + "█     █",

            // L (8x8)
            "█       \n" + "█       \n" + "█       \n" + "█       \n" + "█       \n" + "█       \n" + "█       \n" + "████████",

            // M (8x8)
            "█      █\n" + "██    ██\n" + "█ █  █ █\n" + "█  ██  █\n" + "█      █\n" + "█      █\n" + "█      █\n" + "█      █",

            // N (8x8)
            "█      █\n" + "██     █\n" + "█ █    █\n" + "█  █   █\n" + "█   █  █\n" + "█    █ █\n" + "█     ██\n" + "█      █",

            // O (8x8)
            "  ████  \n" + " █    █ \n" + "█      █\n" + "█      █\n" + "█      █\n" + "█      █\n" + " █    █ \n" + "  ████  ",

            // P (8x8)
            "██████  \n" + "█     █ \n" + "█     █ \n" + "██████  \n" + "█       \n" + "█       \n" + "█       \n" + "█       ",

            // Q (8x8)
            "  ████  \n" + " █    █ \n" + "█      █\n" + "█      █\n" + "█   █  █\n" + "█    █ █\n" + " █    █ \n" + "  ████ █",

            // R (8x8)
            "██████  \n" + "█     █ \n" + "█     █ \n" + "██████  \n" + "█   █   \n" + "█    █  \n" + "█     █ \n" + "█      █",

            // S (8x8)
            "  █████ \n" + " █     █\n" + "█       \n" + " █████  \n" + "       █\n" + "       █\n" + "█     █ \n" + " █████  ",

            // T (8x8)
            "████████\n" + "   ██   \n" + "   ██   \n" + "   ██   \n" + "   ██   \n" + "   ██   \n" + "   ██   \n" + "   ██   ",

            // U (8x8)
            "█      █\n" + "█      █\n" + "█      █\n" + "█      █\n" + "█      █\n" + "█      █\n" + " █    █ \n" + "  ████  ",

            // V (8x8)
            "█      █\n" + "█      █\n" + " █    █ \n" + " █    █ \n" + "  █  █  \n" + "   ██   \n" + "   ██   \n" + "    █   ",

            // W (8x8)
            "█      █\n" + "█      █\n" + "█  ██  █\n" + "█ █  █ █\n" + "██    ██\n" + "█      █\n" + "█      █\n" + "█      █",

            // X (8x8)
            "█      █\n" + " █    █ \n" + "  █  █  \n" + "   ██   \n" + "   ██   \n" + "  █  █  \n" + " █    █ \n" + "█      █",

            // Y (8x8)
            "█      █\n" + " █    █ \n" + "  █  █  \n" + "   ██   \n" + "   ██   \n" + "   ██   \n" + "   ██   \n" + "   ██   ",

            // Z (8x8)
            "████████\n" + "      █ \n" + "     █  \n" + "    █   \n" + "   █    \n" + "  █     \n" + " █      \n" + "████████"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTextToEmojiBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Window w = getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        //toolbar
        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Text to Emoji");

        binding.emojiInputLayout.getEditText().setText("█");

        binding.textInputLayout.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                StringBuilder result = new StringBuilder();
                String inputText = s.toString().toUpperCase();

                if (inputText.isEmpty()) {
                    binding.resultTextView.setText("");
                    return;
                }
                char emojiChar;
                try {
                    emojiChar = binding.emojiInputLayout.getEditText().getText().charAt(0);
                } catch (Exception e) {
                    emojiChar = '█';
                }

                for (char c : inputText.toCharArray()) {
                    if (c >= 'A' && c <= 'Z') {
                        result.append(letterPatterns[c - 'A'].replace('█', emojiChar)).append("\n\n");
                    } else if (c == ' ') {
                        result.append("\n\n");
                    }
                }


                binding.resultTextView.setTypeface(Typeface.MONOSPACE);
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