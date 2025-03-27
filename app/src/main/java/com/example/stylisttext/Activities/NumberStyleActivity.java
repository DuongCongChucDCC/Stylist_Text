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
import com.example.stylisttext.databinding.ActivityNumberStyleBinding;

import java.util.ArrayList;

public class NumberStyleActivity extends AppCompatActivity {
    ActivityNumberStyleBinding binding;
    ArrayList<HolderDTO> items;
    String inputText;
    HolderAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityNumberStyleBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Window w = getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Number Style");
        binding.toolbar.setNavigationOnClickListener(v -> {
            onBackPressed();
        });
        items = new ArrayList<>();

        // 3. Chữ ngược (Upside Down)
        items.add(new HolderDTO("2025", "Upside Down", "Number"));

        // 5. Chữ ma (Ghost)
        items.add(new HolderDTO("2025", "Ghost Text", "Number"));

        // 6. Chữ bong bóng (Bubble) - U+24B6
        items.add(new HolderDTO("2025", "Bubble Letters", "Number"));

        // 8. Chữ thiên hà (Galaxy)
        items.add(new HolderDTO("2025", "Galaxy Style", "Number"));

        // 9. Chữ Zalgo (Quỷ ám)
        items.add(new HolderDTO("2025", "Zalgo Demon", "Number"));

        // 10. Chữ phép thuật (Wizard)
        items.add(new HolderDTO("2025", "Wizard Spells", "Number"));

        adapter = new HolderAdapter(items, inputText);
        binding.recyclerView.setAdapter(adapter);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        adapter.setOnEditButtonClickListener(editedText -> {
            binding.textInputLayout.getEditText().setText(editedText);
            inputText = editedText;
            adapter.updateInputText(inputText);
            adapter.notifyDataSetChanged();
        });
        binding.textInputLayout.getEditText().setText(getIntent().getStringExtra("text"));
        inputText = binding.textInputLayout.getEditText().getText().toString();
        adapter.updateInputText(inputText);
        adapter.notifyDataSetChanged();
        binding.textInputLayout.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                inputText = s.toString();
                adapter.updateInputText(inputText);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
}