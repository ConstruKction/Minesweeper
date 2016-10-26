package com.aliceinwonderland;

import java.util.Scanner;

public class MineSweeper {
    private Field field;
    private int boardSize = 0;
    private int bombChance = 0;
    private boolean isPlaying = true;

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
    }

    public void play() {
        field = new Field(boardSize, bombChance);
        Scanner scanner = new Scanner(System.in);

        while (isPlaying)
        {
            field.printBoard(false);
            System.out.println();
            System.out.println("Please enter the location you want to check (prepend with '*' to flag): ");

            String playerInput;
            playerInput = scanner.next();

            field.checkInput(playerInput.toUpperCase());
        }
    }

    public void gameOver() {
        Main.wantsToPlay = false;

        Scanner scanner = new Scanner(System.in);
        System.out.println("Play again? Y/n");

        String playerInput;
        playerInput = scanner.next();
    }
}
