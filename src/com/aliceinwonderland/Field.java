package com.aliceinwonderland;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import static com.aliceinwonderland.Main.CHEAT;

public class Field {
    private final int boardSize;
    private final int minesChance;

    private int noMines = 0;
    private int noMinesSelected = 0;

    public static HashMap<String, Square> squareHashMap = new HashMap<>();

    public Field(int boardSize, int minesChance) {
        this.boardSize = boardSize;
        this.minesChance = minesChance;

        for (int y = 1; y <= boardSize; y++) {
            for (int x = 0; x < boardSize; x++) {
                boolean isMine = false;
                if (new Random().nextInt(100) + 1 <= minesChance) {
                    isMine = true;
                    noMines += 1;
                }

                squareHashMap.put(Helper.getIdentifier(x, y), isMine ? new MineSquare() : new Square());
            }
        }

        connectMines();
    }

    private void connectMines() {
        for (int i = 0; i < boardSize; i++) {
            for (int j = 1; j < boardSize; j++) {
                String key = Helper.getIdentifier(i, j);

                if (!squareHashMap.containsKey(key)) {
                    continue;
                }

                Square square = squareHashMap.get(key);

                int noMines = 0;

                for (String newKey : getSurroundingFields(i, j)) {
                    Square newSquare = squareHashMap.get(newKey);

                    if (newSquare.isMine) {
                        noMines++;
                    }
                }

                square.setCloseMines(noMines);
            }
        }
    }

    private ArrayList<String> getSurroundingFields(int x, int y) {
        ArrayList<String> fields = new ArrayList<>();

        for (int i = -1; i < 2; i++) {
            if (x + i < 0) continue;

            for (int j = -1; j < 2; j++) {
                if (y + j < 0) continue;

                String key = Helper.getIdentifier(x + i, y + j);

                if (!squareHashMap.containsKey(key)) continue;

                fields.add(key);
            }
        }

        return fields;
    }

    public void printBoard(boolean cheatMode) {
        System.out.println();

        for (int y = boardSize; y > 0; y--) {
            if (y < 10) {
                System.out.print(" ");
            }

            System.out.print(y + " ");

            for (int x = 0; x < boardSize; x++) {
                System.out.print(squareHashMap.get(Helper.getIdentifier(x, y)).getIdentifier(cheatMode));
            }

            System.out.println();
        }

        System.out.print("   ");
        for (int i = 0; i < boardSize; i++) {
            System.out.print(Helper.alphabet[i]);
        }

        if (CHEAT && !cheatMode) {
            printBoard(true);
        }
    }

    private void hitConnections(int x, int y) {
        String originalKey = Helper.getIdentifier(x, y);

        // Selects the row
        for (int i = -1; i < 2; i++) {
            if (x + i < 0) continue;

            // Selects field
            for (int j = -1; j < 2; j++) {
                if (y + j < 0) continue;

                // Makes the new coordinate
                String key = Helper.getIdentifier(x + i, y + j);

                if (squareHashMap.containsKey(key)) {
                    Square square = squareHashMap.get(key);

                    // Checks if there are no bombs around
                    // Checks if the field is not already checked
                    // Compare the given input with the new coordinate
                    if (square.noMinesAround == 0 && !square.isChecked && !originalKey.equals(key)) {
                        // If above conditions are met, checks surrounding fields of new field
                        hitConnections(x + i, y + j);
                    }

                    square.setChecked();
                }
            }
        }
    }

    public void hitSquare(char x, int y) {
        Square square = squareHashMap.get(Helper.getIdentifier(x, y));

        if (square.isMine && MineSweeper.firstMove) {
            new Field(boardSize, minesChance);
            MineSweeper.firstMove = false;
        } else if (square.isMine) {
            System.out.println("You died.");
            Main.wantsToPlay = false;
            MineSweeper.isPlaying = false;
        }

        if (square.noMinesAround == 0) {
            hitConnections(Helper.charToInt(x), y);
        } else {
            square.setChecked();
        }

        MineSweeper.firstMove = false;
    }

    public void flagSquare(char x, int y) {
        Square square = squareHashMap.get(Helper.getIdentifier(x, y));

        if (square.isMine) {
            noMinesSelected += (square.getFlagged() ? -1 : 1);
        }

        square.toggleFlagged();

        if (noMines == noMinesSelected) {
            Main.wantsToPlay = false;
            MineSweeper.isPlaying = false;
            System.out.println("You've found all the mines. Congratulations!");
        }
    }
}
