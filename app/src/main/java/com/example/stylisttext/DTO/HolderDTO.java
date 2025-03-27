package com.example.stylisttext.DTO;

public class HolderDTO {
    private String textExample;
    private String unicodeType;
    private String type;

    public HolderDTO(String textExample, String unicodeType, String type) {
        this.textExample = textExample;
        this.unicodeType = unicodeType;
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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
