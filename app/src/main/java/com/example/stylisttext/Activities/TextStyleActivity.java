package com.example.stylisttext.Activities;

import android.graphics.Rect;
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
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.stylisttext.Adapter.HolderAdapter;
import com.example.stylisttext.DTO.HolderDTO;
import com.example.stylisttext.R;
import com.example.stylisttext.databinding.ActivityTextStyleBinding;

import java.util.ArrayList;

public class TextStyleActivity extends AppCompatActivity {
    ActivityTextStyleBinding binding;
    ArrayList<HolderDTO> items;
    String inputText;
    HolderAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTextStyleBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Window w = getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        //toolbar
        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Text Style");
        binding.toolbar.setNavigationOnClickListener(v -> {
            onBackPressed();
        });
        items = new ArrayList<>();


        // 3. Chữ ngược (Upside Down)
        items.add(new HolderDTO("The quick brown fox", "Upside Down"));

        // 5. Chữ ma (Ghost)
        items.add(new HolderDTO("The quick brown fox", "Ghost Text"));

        // 6. Chữ bong bóng (Bubble) - U+24B6
        items.add(new HolderDTO("The quick brown fox", "Bubble Letters"));

        // 8. Chữ thiên hà (Galaxy)
        items.add(new HolderDTO("The quick brown fox", "Galaxy Style"));

        // 9. Chữ Zalgo (Quỷ ám)
        items.add(new HolderDTO("The quick brown fox", "Zalgo Demon"));

        // 10. Chữ phép thuật (Wizard)
        items.add(new HolderDTO("The quick brown fox", "Wizard Spells"));

        adapter = new HolderAdapter(items, inputText);
        binding.recyclerView.setAdapter(adapter);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        binding.textInputLayout.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                inputText = s.toString();
                adapter = new HolderAdapter(items, inputText);
                binding.recyclerView.setAdapter(adapter);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
}