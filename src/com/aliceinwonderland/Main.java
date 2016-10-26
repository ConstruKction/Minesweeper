package com.aliceinwonderland;

public class Main {
    public static final boolean CHEAT = true;
    public static boolean wantsToPlay = true;

    public static void main(String[] args) {
        while (wantsToPlay) {
            MineSweeper mineSweeper = new MineSweeper();
            mineSweeper.play();
        }
    }
}
