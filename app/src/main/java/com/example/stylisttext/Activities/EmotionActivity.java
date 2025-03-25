package com.example.stylisttext.Activities;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.stylisttext.Adapter.HolderAdapter;
import com.example.stylisttext.DTO.HolderDTO;
import com.example.stylisttext.R;
import com.example.stylisttext.databinding.ActivityEmotionBinding;
import com.example.stylisttext.databinding.ActivityTextStyleBinding;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EmotionActivity extends AppCompatActivity {
    ActivityEmotionBinding binding;
    List<String> emotions = Arrays.asList(
            // Happy/Friendly
            "(•‿•)", "(◕‿◕)", "(ᵔᴥᵔ)", "(◠‿◠)", "ヽ(・∀・)ﾉ", "(´• ω •`)", "(＾▽＾)", "(⌒▽⌒)☆", "(◡‿◡✿)", "٩(◕‿◕｡)۶", "(✿◠‿◠)", "(◠﹏◠)", "(●´ω｀●)", "(＾ω＾)", "ヽ(^。^)ノ", "(´｡• ᵕ •｡`)", "(◕‿◕✿)", "(ᗒᗨᗕ)", "(ﾉ◕ヮ◕)ﾉ", "ヽ(•‿•)ノ",

            // Love/Hearts
            "(｡♥‿♥｡)", "(♡ω♡ )", "(❤ω❤)", "(◍•ᴗ•◍)❤", "♡(˃͈ દ ˂͈ ༶ )", "(´∀｀)♡", "(ღ˘⌣˘ღ)", "(♡°▽°♡)", "♡(◕‿◕✿)", "(❤´艸｀❤)",

            // Cute/Animal
            "(づ｡◕‿‿◕｡)づ", "ᕙ(•‿•)ᕗ", "(ﾉ´ヮ`)ﾉ*: ･ﾟ", "(≧◡≦)", "ヾ(●ω●)ノ", "(◠‿◠)✌", "(ﾉ◕ヮ◕)ﾉ*:･ﾟ✧", "(✪‿✪)ノ", "(ﾉ≧∀≦)ﾉ", "(●ˊωˋ●)", "(◕‿-)✌", "(ﾉ´｡ᵕ｡`)ﾉ", "(ﾉ･ｪ･)ﾉ", "(ﾉ◕ヮ◕)ﾉ*:･ﾟ", "ᕕ( ᐛ )ᕗ",

            // Surprised/Excited
            "(⊙_⊙)", "(☉_☉)", "ヽ(°〇°)ﾉ", "(ﾟοﾟ人))", "(●__●)", "(✧ω✧)", "(ﾉ◕ヮ◕)ﾉ*:･ﾟ✧", "(ﾉ≧∇≦)ﾉ", "ヽ(★ω★)ノ", "ヽ(ﾟ〇ﾟ)ノ",

            // Sad/Disappointed
            "(╥_╥)", "(╯︵╰,)", "(ಥ﹏ಥ)", "(´；д；`)", "(μ_μ)", "(︶︹︶)", "(っ- ‸ – ς)", "(ᗒᗣᗕ)՞", "(ノ_<。)", "(´；ω；｀)",

            // Angry/Frustrated
            "(╬ Ò﹏Ó)", "(ಠ益ಠ)", "(╯°□°）╯︵ ┻━┻", "ヽ(｀⌒´)ﾉ", "(¬_¬)", "(ಠ_ಠ)", "(ง •̀_•́)ง", "(╯‵□′)╯︵┻━┻", "(ಠ︵ಠ)", "(ಠ╭╮ಠ)",

            // Funny/Silly
            "( ͡° ͜ʖ ͡°)", "¯\\_(ツ)_/¯", "(づ￣ ³￣)づ", "(ノಠ益ಠ)ノ", "(╯°□°)╯︵ ʞooqǝɔɐℲ", "(ﾉ◕ヮ◕)ﾉ*:･ﾟ✧", "┬─┬ノ(º _ ºノ)", "(ノ°ο°)ノ", "(ﾉﾟ0ﾟ)ﾉ~", "(ノಠ益ಠ)ノ彡┻━┻",

            // Action/Expressive
            "(ง'̀-'́)ง", "ヽ(￣д￣;)ノ", "(ノಥ,_｣ಥ)ノ", "(╯°□°）╯︵ ǝɯɐuǝlᴉɟ", "ᕙ(⇀‸↼‶)ᕗ", "(ﾉ｀Д´)ﾉ", "ヽ(｀Д´)ﾉ", "(ﾉ´･ω･)ﾉ", "(ノಠ益ಠ)ノ彡", "(╯°□°)╯︵ ǝɯɐuǝlᴉɟ",

            // Special/Unique
            "༼ つ ◕_◕ ༽つ", "ヽ༼ຈل͜ຈ༽ﾉ", "ᕦ(ò_óˇ)ᕤ", "(☞ﾟヮﾟ)☞", "☜(ﾟヮﾟ☜)", "(ﾉ◕ヮ◕)ﾉ*:･ﾟ✧", "(っ◔◡◔)っ", "(づ｡◕‿‿◕｡)づ", "༼ つ ◕_◕ ༽つ", "٩(◕‿◕｡)۶");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityEmotionBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Window w = getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        //toolbar
        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Emotion");
        binding.toolbar.setNavigationOnClickListener(v -> {
            onBackPressed();
        });
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, emotions);
        binding.gridView.setNumColumns(2);
        binding.gridView.setVerticalSpacing(16);
        binding.gridView.setHorizontalSpacing(16);
        binding.gridView.setAdapter(adapter);
        binding.gridView.setGravity(android.view.Gravity.CENTER);
        binding.gridView.setOnItemClickListener((parent, view, position, id) -> {
            if (binding.textInputLayout.getEditText() != null) {
                int cursorPosition = binding.textInputLayout.getEditText().getSelectionStart();
                String currentText = binding.textInputLayout.getEditText().getText().toString();
                String newText = currentText.substring(0, cursorPosition) + emotions.get(position) + currentText.substring(cursorPosition);
                binding.textInputLayout.getEditText().setText(newText);
                binding.textInputLayout.getEditText().setSelection(cursorPosition + emotions.get(position).length());
            }
        });
        binding.btnCopy.setOnClickListener(v -> {
            ClipboardManager clipboard = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
            ClipData clip = ClipData.newPlainText("Copied Text", (binding.textInputLayout.getEditText().getText().toString()));
            clipboard.setPrimaryClip(clip);
        });
    }
}