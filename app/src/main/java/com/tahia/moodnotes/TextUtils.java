package com.tahia.moodnotes;

public class TextUtils {

    public static int getWordCount(String text) {
        if (text == null || text.trim().isEmpty()) return 0;
        return text.trim().split("\\s+").length;
    }

    public  static  int getCharacterCount(String text) {
        if (text == null) return 0;
        return  text.length();
    }
}
