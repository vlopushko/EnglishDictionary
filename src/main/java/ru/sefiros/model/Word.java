package ru.sefiros.model;

public class Word {
    private int id;
    private String expression;
    private String translation;
    private int translatedToRussianCount;
    private int translatedToEnglishCount;

    public Word() {
    }

    public Word(int id, String word, String translation) {
        this.id = id;
        this.expression = word;
        this.translation = translation;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getExpression() {
        return expression;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }

    public String getTranslation() {
        return translation;
    }

    public void setTranslation(String translation) {
        this.translation = translation;
    }

    public int getTranslatedToRussianCount() {
        return translatedToRussianCount;
    }

    public void setTranslatedToRussianCount(int translatedToRussianCount) {
        this.translatedToRussianCount = translatedToRussianCount;
    }

    public int getTranslatedToEnglishCount() {
        return translatedToEnglishCount;
    }

    public void setTranslatedToEnglishCount(int translatedToEnglishCount) {
        this.translatedToEnglishCount = translatedToEnglishCount;
    }
}
