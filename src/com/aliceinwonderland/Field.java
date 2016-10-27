package com.aliceinwonderland;

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

        for (int y = 0; y < boardSize; y++) {
            for (int x = 0; x < boardSize; x++) {
                boolean isMine = false;
                if (new Random().nextInt(100) + 1 <= minesChance) {
                    isMine = true;
                    noMines += 1;
                }

                squareHashMap.put(Helper.getIdentifier(x, y + 1), isMine ? new MineSquare() : new Square());
            }
        }
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

    private Integer minesNearby(int x, int y) {
        int noMines = 0;

        for (int i = -1; i < 2; i++) {
            if (x + i < 0 || x + i >= boardSize) {
                continue;
            }

            for (int j = -1; j < 2; j++) {
                if (y + j < 1 || y + j > boardSize) {
                    continue;
                }

                if (!(i + i == x && y + j == y)) {
                    Square square = squareHashMap.get(Helper.getIdentifier(x + i, y + j));

                    if (square.isMine) {
                        noMines += 1;
                        break;
                    } else if (!square.getChecked()) {
                        square.setChecked();
                        int number = minesNearby(x + i, y + j);
                        square.setCloseMines(number);
                        break;
                    } else {
                        break;
                    }
                }
            }
        }

        return noMines;
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

        int minesNearby = minesNearby(Helper.charToInt(x), y);

        square.setCloseMines(minesNearby);
        square.setChecked();

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
