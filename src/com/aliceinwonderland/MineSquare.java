package com.aliceinwonderland;

public class MineSquare extends Square {

    public MineSquare()
    {
        isMine = true;
    }

    public Character getIdentifier(boolean cheat) {
        if (cheat) {
            return '*';
        } else {
            if (this.isFlagged) {
                return '*';
            }
        }

        return '.';
    }
}
