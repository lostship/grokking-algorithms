package com.example.grokkingalgorithms.util;

public class Strings {

    public static String reverse(String str) {
        int len = str.length();
        if (len <= 1) {
            return str;
        }

        char[] chars = str.toCharArray();
        for (int i = 0; i < len - i - 1; i++) {
            char t = chars[i];
            chars[i] = chars[len - i - 1];
            chars[len - i - 1] = t;
        }

        return new String(chars);
    }

}
