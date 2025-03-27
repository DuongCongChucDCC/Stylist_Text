package com.example.stylisttext.Adapter;

import android.app.AlertDialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.stylisttext.Activities.TextDecorationActivity;
import com.example.stylisttext.Activities.TextFrameActivity;
import com.example.stylisttext.Activities.TextRepeaterActivity;
import com.example.stylisttext.Activities.TextStyleActivity;
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

    public void updateInputText(String newInputText) {
        this.inputText = newInputText;
    }

    public interface OnEditButtonClickListener {
        void onEditButtonClick(String textToEdit);
    }

    private OnEditButtonClickListener editButtonListener;

    public void setOnEditButtonClickListener(OnEditButtonClickListener listener) {
        this.editButtonListener = listener;
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

        AlertDialog.Builder onclickBulder = new AlertDialog.Builder(context);
        View dialogView = LayoutInflater.from(context).inflate(R.layout.dialog_itemclick, null);
        onclickBulder.setView(dialogView);
        TextView preview = dialogView.findViewById(R.id.tvPreview);
        ImageButton editButton = dialogView.findViewById(R.id.editButton);
        ImageButton copyButton = dialogView.findViewById(R.id.copyButton);
        androidx.appcompat.widget.AppCompatButton btnMoveTo = dialogView.findViewById(R.id.btnMoveTo);

        AlertDialog onclickDialog = onclickBulder.create();

        AlertDialog.Builder moveBuilder = new AlertDialog.Builder(context);
        View moveDialogView = LayoutInflater.from(context).inflate(R.layout.dialog_moveto, null);
        moveBuilder.setView(moveDialogView);
        RadioButton rbTextFrame = moveDialogView.findViewById(R.id.rbTextFrame);
        RadioButton rbTextDecorations = moveDialogView.findViewById(R.id.rbTextDecorations);
        RadioButton rbTextRepeater = moveDialogView.findViewById(R.id.rbTextRepeater);
        RadioButton rbTextStyle = moveDialogView.findViewById(R.id.rbTextStyle);

        AlertDialog moveDialog = moveBuilder.create();

        switch (item.getType()) {
            case "Decoration":
                if (inputText != null && !inputText.isEmpty()) {
                    holder.txtText.setText(decorationSymbols[0] + inputText + decorationSymbols[0]);
                } else {
                    holder.txtText.setText(decorationSymbols[0] + item.getTextExample() + decorationSymbols[0]);
                }
                if (decorationSymbols.length > 1) {
                    decorationSymbols = Arrays.copyOfRange(decorationSymbols, 1, decorationSymbols.length);
                }
                holder.itemView.setOnClickListener(v -> {
                    onclickDialog.show();
                    preview.setText(holder.txtText.getText().toString());
                    btnMoveTo.setOnClickListener(v1 -> {
                        moveBuilder.setTitle("Move to");
                        rbTextDecorations.setVisibility(View.INVISIBLE);
                        onclickDialog.dismiss();
                        moveDialog.show();
                    });
                });
                break;
            case "Frame":
                String[] currentFrame = frames[0].split(" - ");
                String textContent = inputText != null && !inputText.isEmpty() ? inputText : item.getTextExample();
                holder.txtText.setText(currentFrame[0] + "\n" + textContent + "\n" + currentFrame[1]);
                if (frames.length > 1) {
                    frames = Arrays.copyOfRange(frames, 1, frames.length);
                }
                holder.itemView.setOnClickListener(v -> {
                    onclickDialog.show();
                    preview.setText(holder.txtText.getText().toString());
                    btnMoveTo.setOnClickListener(v1 -> {
                        moveBuilder.setTitle("Move to");
                        rbTextDecorations.setVisibility(View.INVISIBLE);
                        rbTextRepeater.setVisibility(View.INVISIBLE);
                        rbTextFrame.setVisibility(View.INVISIBLE);
                        onclickDialog.dismiss();
                        moveDialog.show();
                    });
                });
                break;
            case "Text":
            case "Number":
                if (inputText != null && !inputText.isEmpty()) {
                    holder.txtText.setText(UnicodeAdapter(inputText, item.getUnicodeType()));
                } else {
                    holder.txtText.setText(UnicodeAdapter(item.getTextExample(), item.getUnicodeType()));
                }
                holder.itemView.setOnClickListener(v -> {
                    onclickDialog.show();
                    preview.setText(holder.txtText.getText().toString());
                    btnMoveTo.setOnClickListener(v1 -> {
                        moveBuilder.setTitle("Move to");
                        rbTextStyle.setVisibility(View.INVISIBLE);
                        onclickDialog.dismiss();
                        moveDialog.show();
                    });
                });
                break;
            default:
                holder.txtText.setText(item.getTextExample());
                break;
        }

        editButton.setOnClickListener(v -> {
            if (editButtonListener != null) {
                editButtonListener.onEditButtonClick(holder.txtText.getText().toString());
            }
            onclickDialog.dismiss();
        });

        copyButton.setOnClickListener(v -> {
            copy(holder.txtText.getText().toString());
            onclickDialog.dismiss();
        });

        holder.imgCopy.setOnClickListener(v -> {
            copy(holder.txtText.getText().toString());
        });

        rbTextFrame.setOnClickListener(v -> {
            Intent intent = new Intent(context, TextFrameActivity.class);
            intent.putExtra("text", holder.txtText.getText().toString());
            context.startActivity(intent);
        });

        rbTextRepeater.setOnClickListener(v -> {
            Intent intent = new Intent(context, TextRepeaterActivity.class);
            intent.putExtra("text", holder.txtText.getText().toString());
            context.startActivity(intent);
        });

        rbTextStyle.setOnClickListener(v -> {
            Intent intent = new Intent(context, TextStyleActivity.class);
            intent.putExtra("text", holder.txtText.getText().toString());
            context.startActivity(intent);
        });

        rbTextDecorations.setOnClickListener(v -> {
            Intent intent = new Intent(context, TextDecorationActivity.class);
            intent.putExtra("text", holder.txtText.getText().toString());
            context.startActivity(intent);
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

    public void copy(String txt) {
        ClipboardManager clipboard = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText("Unicode Text", txt);
        clipboard.setPrimaryClip(clip);
        Toast.makeText(context, "Copied: " + txt, Toast.LENGTH_SHORT).show();
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
