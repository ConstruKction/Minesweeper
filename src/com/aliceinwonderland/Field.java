package com.aliceinwonderland;

import java.util.HashMap;

public class Field {
    private Helper helper = new Helper();
    private Square square = new Square(false);
    private HashMap<String, Square> squareHashMap = new HashMap<>();

    public Field() {
    }

    public void printBoard(int size, Boolean cheat) {
        cheat = false;

        for (int y = size; y > 0; y--) {
            if (y < 10) {
                System.out.print(" ");
            }

            System.out.print(y + " ");

            for (int x = 0; x < size; x++) {
//                System.out.print(helper.getIdentifier(x, y));
                System.out.print(square.getIdentifier());
            }

            System.out.println();
        }

        System.out.print("   ");
        for (int i = 0; i < size; i++) {
            System.out.print(helper.alphabet[i]);
        }
    }

    public void checkSquare() {

    }

    public Boolean bombsFound() {
        return false;
    }
}
