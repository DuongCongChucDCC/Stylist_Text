package com.example.stylisttext.Activities;

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
import com.example.stylisttext.databinding.ActivityTextFrameBinding;

import java.util.ArrayList;

public class TextFrameActivity extends AppCompatActivity {
    ActivityTextFrameBinding binding;
    ArrayList<HolderDTO> items;
    String inputText;
    HolderAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTextFrameBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Window w = getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        //toolbar
        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Text Frame");
        binding.toolbar.setNavigationOnClickListener(v -> {
            onBackPressed();
        });
        items = new ArrayList<>();

        items.add(new HolderDTO("The quick brown fox", "Frame"));
        items.add(new HolderDTO("The quick brown fox", "Frame"));
        items.add(new HolderDTO("The quick brown fox", "Frame"));
        items.add(new HolderDTO("The quick brown fox", "Frame"));

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