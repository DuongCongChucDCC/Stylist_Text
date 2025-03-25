package com.example.stylisttext;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

import com.example.stylisttext.Activities.BlankMessageActivity;
import com.example.stylisttext.Activities.EmojiWordActivity;
import com.example.stylisttext.Activities.EmotionActivity;
import com.example.stylisttext.Activities.GlitchTextActivity;
import com.example.stylisttext.Activities.JoinEmojiActivity;
import com.example.stylisttext.Activities.NumberStyleActivity;
import com.example.stylisttext.Activities.TextDecorationActivity;
import com.example.stylisttext.Activities.TextFrameActivity;
import com.example.stylisttext.Activities.TextRepeaterActivity;
import com.example.stylisttext.Activities.TextStyleActivity;
import com.example.stylisttext.Activities.TextToEmojiActivity;
import com.example.stylisttext.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.toolbar);
        binding.toolbar.setTitle("Stylist Text");
        binding.toolbar.setSubtitle("Stylist Text and Many more");
        Window w = getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        binding.itemTextStyle.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, TextStyleActivity.class));
        });
        binding.itemNumberStyle.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, NumberStyleActivity.class));
        });
        binding.itemTextDecoration.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, TextDecorationActivity.class));
        });
        binding.itemTextFrame.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, TextFrameActivity.class));
        });
        binding.itemEmojiWord.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, EmojiWordActivity.class));
        });
        binding.itemTextToEmoji.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, TextToEmojiActivity.class));
        });
        binding.itemBlankMessage.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, BlankMessageActivity.class));
        });
        binding.itemGlitchText.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, GlitchTextActivity.class));
        });
        binding.itemTextRepeater.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, TextRepeaterActivity.class));
        });
        binding.itemEmotions.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, EmotionActivity.class));
        });
        binding.itemJoinEmoji.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, JoinEmojiActivity.class));
        });

    }

    //hiển thị menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_toolbar, menu);
        return true;
    }

    //xử lý sự kiện khi click vào menu
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_change_theme) {
            return true;
        }
        if (id == R.id.action_menu) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}