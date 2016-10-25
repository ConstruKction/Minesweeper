package com.aliceinwonderland;

import java.util.Scanner;

public class MineSweeper {
    private Field field = new Field();
    private int boardSize = 0;
    private int bombChance = 0;

    public MineSweeper() {
        boardSize = 0;
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to Minesweeper!");
        System.out.println("Your goal is to try to locate all the bombs on the minefield.");

        while (boardSize < 5 || boardSize > 20) {
            System.out.println("Please tell me how large you'd like your board to be? (5-20): ");

            int inputBoardSize;
            inputBoardSize = scanner.nextInt();

            if (inputBoardSize < 5 || inputBoardSize > 20) {
                System.out.println("*** The size must be between 5 and 20! ***");
            } else {
                boardSize = inputBoardSize;
            }
        }

        while (bombChance < 10 || boardSize > 25) {
            System.out.println("Bomb chance percentage will determine the difficulty of the game. (10-25): ");

            int inputBombChance;
            inputBombChance = scanner.nextInt();

            if (inputBombChance < 10 || inputBombChance > 25) {
                System.out.println("*** The bomb chance percentage must be between 10% and 25%! ***");
            } else {
                bombChance = inputBombChance;
            }
        }

        scanner.close();
    }

    public void play(Boolean cheat) {
        field.printBoard(boardSize, cheat);
    }
}
