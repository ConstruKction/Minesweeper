package com.aliceinwonderland;

import java.util.HashMap;
import java.util.Random;

import static com.aliceinwonderland.Main.CHEAT;

public class Field {
    private final int boardSize;

    private int noBombs = 0;
    private int noBombsSelected = 0;

    private HashMap<String, Square> squareHashMap = new HashMap<>();

    public Field(int boardSize, int bombChance) {
        this.boardSize = boardSize;

        // Randomly assign bombs to squares based on player's percentage input
        for (int y = 0; y < boardSize; y++) {
            for (int x = 0; x < boardSize; x++) {
                boolean isBomb = false;
                if (new Random().nextInt(100) + 1 <= bombChance) {
                    isBomb = true;
                    noBombs += 1;
                }

                // Finally add them to the HashMap and create Squares/BombSquares
                squareHashMap.put(Helper.getIdentifier(x, y + 1), isBomb ? new BombSquare() : new Square());
            }
        }
    }

    public void printBoard(boolean cheatMode) {
        System.out.println();

        // Print the vertical column with numbers based on player's size input
        for (int y = boardSize; y > 0; y--) {
            if (y < 10) {
                System.out.print(" ");
            }

            System.out.print(y + " ");

            // Print the squares
            for (int x = 0; x < boardSize; x++) {
                // The latter getIdentifier prints the second board if cheat mode is enabled
                System.out.print(squareHashMap.get(Helper.getIdentifier(x, y)).getIdentifier(cheatMode));
            }

            System.out.println();
        }

        // Print the letters of alphabet until the max of board size
        System.out.print("   ");
        for (int i = 0; i < boardSize; i++) {
            System.out.print(Helper.alphabet[i]);
        }

        // Call this method again
        if (CHEAT && !cheatMode) {
            printBoard(true);
        }
    }

    public void printBoard()
    {
        printBoard(false);
    }

    public void checkInput(String input) {
        boolean flag = false;

        char first = input.charAt(0);
        if (first == '*') {
            input.substring(1);
            flag = true;
        }

        if (!squareHashMap.containsKey(input)) {
            System.out.println("Sorry.");
        }

        char x = input.toUpperCase().charAt(0);
        int y = Character.getNumericValue(input.charAt(1));

        if (flag) {
            flagSquare(x, y);
        } else {
            hitSquare(x, y);
        }
    }

    private void hitSquare(char x, int y) {
        Square square = squareHashMap.get(Helper.getIdentifier(x, y));

        if (square.isBomb) {
            System.out.println("Ka-boom! Game over!");
            // TODO: Change playing state to false
        }
    }

    private void flagSquare(char x, int y) {
        Square square = squareHashMap.get(Helper.getIdentifier(x, y));

        if (square.isBomb) {
            noBombsSelected += (square.getFlagged() ? -1 : 1);
        }

        square.toggleFlagged();

        if (noBombs == noBombsSelected) {
            // TODO: Change playing state to false
            System.out.println("You've found all the bombs. Congratulations!");
        }
    }

    public void checkSquare() {
        // TODO: Check if bomb
    }

    public Boolean bombsFound() {
        return false;
    }
}
