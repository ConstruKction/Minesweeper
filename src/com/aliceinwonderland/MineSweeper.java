package com.aliceinwonderland;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MineSweeper {
    private int boardSize = 0;
    private int mineChance = 0;

    public static boolean isPlaying = true;
    public static boolean firstMove = true;

    private Field field;

    public MineSweeper() {
        boardSize = 0;
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to Minesweeper!");
        System.out.println("Your goal is to try to locate all the mines on the minefield.");

        while (boardSize < 5 || boardSize > 20) {
            System.out.println("Please tell me how large you'd like your board to be? (5-20): ");

            try {
                int inputBoardSize;
                inputBoardSize = scanner.nextInt();

                if (inputBoardSize < 5 || inputBoardSize > 20) {
                    System.out.println("*** The size must be between 5 and 20! ***");
                } else {
                    boardSize = inputBoardSize;
                }
            } catch (InputMismatchException e) {
                scanner.next();
                System.out.println("Invalid input.");
            }
        }

        while (mineChance < 10 || boardSize > 25) {
            System.out.println("Mine chance percentage will determine the difficulty of the game. (10-25): ");

            try {
                int inputMineChance;
                inputMineChance = scanner.nextInt();

                if (inputMineChance < 10 || inputMineChance > 25) {
                    System.out.println("*** The mine chance percentage must be between 10% and 25%! ***");
                } else {
                    mineChance = inputMineChance;
                }
            } catch (InputMismatchException e) {
                scanner.next();
                System.out.println("Invalid input.");
            }
        }
    }

    public Boolean play() {
        field = new Field(boardSize, mineChance);
        Scanner scanner = new Scanner(System.in);

        while (isPlaying)
        {
            field.printBoard(false);
            System.out.println();
            System.out.println("Please enter the location you want to check (prepend with '*' to flag): ");

            String playerInput;
            playerInput = scanner.next();

            makeMove(playerInput.toUpperCase());

        }

        System.out.println("Play again? y/N");

        String answer;
        answer = scanner.next().toUpperCase();

        if (!answer.equals("Y")) {
            System.out.println("Hope you had a blast. Thanks and see you again soon.");
            return false;
        } else {
            firstMove = true;
            isPlaying = true;
            return true;
        }
    }

    private void makeMove(String input) {
        boolean flag = false;

        char first = input.charAt(0);
        if (first == '*') {
            input = input.substring(1);
            flag = true;
        }

        if (!Field.squareHashMap.containsKey(input)) {
            System.out.println("Invalid input.");
        } else {
            char x = input.toUpperCase().charAt(0);
            int y = Integer.parseInt(input.replaceAll("\\D+", ""));

            if (flag) {
                field.flagSquare(x, y);
            } else {
                field.hitSquare(x, y);
            }
        }
    }
}
