package com.example.stylisttext.Adapter;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.stylisttext.DTO.HolderDTO;
import com.example.stylisttext.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class HolderAdapter extends RecyclerView.Adapter<HolderAdapter.ViewHolder> {
    ArrayList<HolderDTO> items;
    String inputText;
    Context context;
    String[] decorationSymbols = {"♪", "♫", "♬", "♭", "✧", "『", "║", "♪", "❤", "➳", "☽", "✶", "◈", "♋"};
    String[] frames = {"✧･ﾟ: *✧･ﾟ:* - *:･ﾟ✧*:･ﾟ✧", "✿◠‿◠✿ - ✿◠‿◠✿", "╔══════════╗ - ╚══════════╝", "★·.·´¯`·.·★ - ★·.·´¯`·.·★"};

    public HolderAdapter(ArrayList<HolderDTO> items, String inputText) {
        this.items = items;
        this.inputText = inputText;
    }

    @NonNull
    @Override
    public HolderAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.holder_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HolderAdapter.ViewHolder holder, int position) {
        HolderDTO item = items.get(position);
        holder.txtNumber.setText(String.valueOf(position + 1));

        switch (item.getUnicodeType()) {
            case "Decoration":
                if (inputText != null && !inputText.isEmpty()) {
                    holder.txtText.setText(decorationSymbols[0] + inputText + decorationSymbols[0]);
                } else {
                    holder.txtText.setText(decorationSymbols[0] + item.getTextExample() + decorationSymbols[0]);
                }
                if (decorationSymbols.length > 1) {
                    decorationSymbols = Arrays.copyOfRange(decorationSymbols, 1, decorationSymbols.length);
                }
                break;
            case "Frame":
                String[] currentFrame = frames[0].split(" - ");
                String textContent = inputText != null && !inputText.isEmpty() ? inputText : item.getTextExample();
                holder.txtText.setText(currentFrame[0] + "\n" + textContent + "\n" + currentFrame[1]);
                if (frames.length > 1) {
                    frames = Arrays.copyOfRange(frames, 1, frames.length);
                }
                break;
            default:
                if (inputText != null && !inputText.isEmpty()) {
                    holder.txtText.setText(UnicodeAdapter(inputText, item.getUnicodeType()));
                } else {
                    holder.txtText.setText(UnicodeAdapter(item.getTextExample(), item.getUnicodeType()));
                }
        }
        holder.imgCopy.setOnClickListener(v -> {
            ClipboardManager clipboard = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
            ClipData clip = ClipData.newPlainText("Unicode Text", holder.txtText.getText().toString());
            clipboard.setPrimaryClip(clip);
            Log.d("zzzzzzzzzzzzzzzzzzzz", "Text copied to clipboard: " + holder.txtText.getText().toString());
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtNumber, txtText;
        ImageButton imgCopy;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtNumber = itemView.findViewById(R.id.txtNumber);
            txtText = itemView.findViewById(R.id.txtText);
            imgCopy = itemView.findViewById(R.id.imgCopy);
        }
    }

    public String UnicodeAdapter(String textExample, String unicodeType) {
        StringBuilder result = new StringBuilder();
        switch (unicodeType) {
            case "Upside Down":
                Map<Character, Character> upsideDownMap = new HashMap<>();

                upsideDownMap.put('a', 'ɐ');
                upsideDownMap.put('b', 'q');
                upsideDownMap.put('c', 'ɔ');
                upsideDownMap.put('d', 'p');
                upsideDownMap.put('e', 'ǝ');
                upsideDownMap.put('f', 'ɟ');


                upsideDownMap.put('0', '0');
                upsideDownMap.put('1', 'Ɩ');
                upsideDownMap.put('2', 'ᄅ');
                upsideDownMap.put('3', 'Ɛ');
                upsideDownMap.put('4', 'ㄣ');
                upsideDownMap.put('5', 'ϛ');
                upsideDownMap.put('6', '9');
                upsideDownMap.put('7', 'ㄥ');
                upsideDownMap.put('8', '8');
                upsideDownMap.put('9', '6');

                for (int i = textExample.length() - 1; i >= 0; i--) {
                    char c = textExample.charAt(i);
                    result.append(upsideDownMap.getOrDefault(Character.toLowerCase(c), c));
                }
                break;

            case "Bubble Letters":
                for (char c : textExample.toCharArray()) {
                    if (c >= 'A' && c <= 'Z') {
                        result.append((char) (c - 'A' + 0x24B6));
                    } else if (c >= 'a' && c <= 'z') {
                        result.append((char) (c - 'a' + 0x24D0));
                    } else if (c >= '0' && c <= '9') {
                        result.append((char) (c - '0' + 0x2460));
                    } else {
                        result.append(c);
                    }
                }
                break;

            case "Typewriter":
                for (char c : textExample.toCharArray()) {
                    if (c >= 'A' && c <= 'Z') {
                        result.append((char) (c - 'A' + 0x1D670));
                    } else if (c >= 'a' && c <= 'z') {
                        result.append((char) (c - 'a' + 0x1D68A));
                    } else if (c >= '0' && c <= '9') {
                        result.append((char) (c - '0' + 0x1D7F6));
                    } else {
                        result.append(c);
                    }
                }
                break;

            case "Ghost Text":
                String[] ghostChars = {"⃒", "⃕", "⃰", "⃡"};
                for (char c : textExample.toCharArray()) {
                    result.append(c).append(ghostChars[(int) (Math.random() * ghostChars.length)]);
                }
                break;

            case "Galaxy Style":
                String[] galaxySymbols = {"☄", "⋆", "✧", "✦"};
                for (char c : textExample.toCharArray()) {
                    result.append(c).append(galaxySymbols[(int) (Math.random() * galaxySymbols.length)]);
                }
                break;

            case "Zalgo Demon":
                String[] zalgoChars = {"̴", "̷", "̿", "̾", "͇"};
                for (char c : textExample.toCharArray()) {
                    result.append(c).append(zalgoChars[(int) (Math.random() * zalgoChars.length)]).append(zalgoChars[(int) (Math.random() * zalgoChars.length)]);
                }
                break;

            case "Wizard Spells":
                String[] spellSymbols = {"✨", "⚡", "☄", "♆"};
                for (char c : textExample.toCharArray()) {
                    result.append(c).append(spellSymbols[(int) (Math.random() * spellSymbols.length)]);
                }
                break;

            default:
                return textExample;
        }
        return result.toString();
    }
}
