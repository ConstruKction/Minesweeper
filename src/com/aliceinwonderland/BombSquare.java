package com.aliceinwonderland;

public class BombSquare extends Square {

    public BombSquare()
    {
        isBomb = true;
    }

    public String getIdentifier(boolean cheat) {
        if(cheat) {
            return "*";
        } else {
            if (this.isFlagged) {
                return "*";
            }
        }

        return ".";
    }
}
