package com.aliceinwonderland;

public class Helper {
    public static char alphabet[] = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();

    public static Integer charToInt(Character character) {
        for (int i = 0; i < 26; ++i) {
            if (alphabet[i] == character) {
                return i;
            }
        }

        return -1;
    }

    public static String getIdentifier(Character x, Integer y) {
        return x.toString() + y;
    }

    public static String getIdentifier(Integer x, Integer y) {
        return getIdentifier(alphabet[x], y);
    }
}
