package com.aliceinwonderland;

import java.util.HashMap;
import java.util.Random;

public class Field {
    private final int boardSize;
    private final int bombChance;
    private int noBombs = 0;
    private int noBombsSelected = 0;
    private Helper helper = new Helper();
    private Square square = new Square(false);
    private HashMap<String, Square> squareHashMap = new HashMap<>();
    Random random = new Random();

    public Field(int boardSize, int bombChance) {
        this.boardSize = boardSize;
        this.bombChance = bombChance;

        for (int y = 0; y < boardSize; y++) {
            for (int x = 0; x < boardSize; x++) {
                boolean isBomb = false;
                int chance = random.nextInt(100) + 1;
                if (chance <= bombChance) {
                    isBomb = true;
                    noBombs += 1;
                }

                squareHashMap.put(helper.getIdentifier(x, y + 1), new Square(isBomb));
            }
        }
    }

    public void printBoard() {
        for (int y = boardSize; y > 0; y--) {
            if (y < 10) {
                System.out.print(" ");
            }

            System.out.print(y + " ");

            for (int x = 0; x < boardSize; x++) {
//                System.out.print(helper.getIdentifier(x, y));
                System.out.print(square.getIdentifier());
            }

            System.out.println();
        }

        System.out.print("   ");
        for (int i = 0; i < boardSize; i++) {
            System.out.print(helper.alphabet[i]);
        }
    }

    public void checkSquare() {

    }

    public Boolean bombsFound() {
        return false;
    }
}
