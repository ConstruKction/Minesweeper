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
            input = input.substring(1);
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

    public Integer bombsNearby(int x, int y) {
        int noBombs = 0;

        // TODO: Use foreach instead of for
        for (int i = -1; i < 2; i++) {
            if (x + i < 0 || x + i >= boardSize) { continue; }

            // TODO: Use foreach instead of for
            for (int j = -1; j < 2; j++) {
                if (y + j < 1 || y + j >= boardSize) { continue; }

                if (!(i + i == x && y + j == y)) {
                    Square square = squareHashMap.get(Helper.getIdentifier(x + i, y + j));

                    if (square.isBomb) {
                        noBombs += 1;
                    } else if (!square.getChecked()) {
                        square.setChecked();
                        int number = bombsNearby(x + i, y + j);
                        square.setCloseBombs(number);
                    }
                }
            }
        }

//        Old recursive function StackOverflows
//        int x_positions[] = { x - 1, x, x + 1 };
//        int y_positions[] = { y - 1, y, y + 1 };
//
//        int noBombs = 0;
//
//        for (int i = 0; i < 3; i++) {
//            if (x_positions[i] < 0 || x_positions[i] > boardSize) { continue; }
//
//            for (int j = 0; j < 3; j++) {
//                if (y_positions[j] < 1 || y_positions[j] > boardSize) { continue; }
//
//                if (!(x_positions[i] == x && y_positions[j] == y)) {
//                    System.out.println(Helper.getIdentifier(x_positions[i], y_positions[j]));
//
//                    Square square = squareHashMap.get(Helper.getIdentifier(x_positions[i], y_positions[j]));
//                    if (square.isBomb) {
//                        noBombs += 1;
//                    } else if (!square.getChecked()) {
//                        int number = bombsNearby(x_positions[i], y_positions[j]);
//                        square.setCloseBombs(number);
//                        square.getChecked();
//                    }
//                }
//            }
//        }

        return noBombs;
    }

    private void hitSquare(char x, int y) {
        Square square = squareHashMap.get(Helper.getIdentifier(x, y));

        if (square.isBomb) {
            System.out.println("Ka-boom! Game over!");
            // TODO: Change playing state to false
        }

        int bombsNearby = bombsNearby(Helper.charToInt(x), y);

        square.setCloseBombs(bombsNearby);
        square.setChecked();
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

    public Boolean bombsFound() {
        return false;
    }
}
