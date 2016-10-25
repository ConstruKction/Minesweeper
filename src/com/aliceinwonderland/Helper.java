package com.aliceinwonderland;

public class Helper {
    public char alphabet[] = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();

    private Character intToChar(Integer index) {
        return alphabet[index];
    }

    public Integer charToInt(Character character) {
        for (int i = 0; i < 26; ++i) {
            if (alphabet[i] == character) {
                return i;
            }
        }

        return -1;
    }

    private String getIdentifier(Character x, Integer y) {
        return String.format("%1s%2s", x, y);
    }

    public String getIdentifier(Integer x, Integer y) {
        return getIdentifier(intToChar(x), y);
    }
}
