package com.example.stylisttext.DTO;

public class HolderDTO {
    private String textExample;
    private String unicodeType;

    public HolderDTO(String textExample, String unicodeType) {
        this.textExample = textExample;
        this.unicodeType = unicodeType;
    }

    public String getTextExample() {
        return textExample;
    }

    public void setTextExample(String textExample) {
        this.textExample = textExample;
    }

    public String getUnicodeType() {
        return unicodeType;
    }

    public void setUnicodeType(String unicodeType) {
        this.unicodeType = unicodeType;
    }
}
