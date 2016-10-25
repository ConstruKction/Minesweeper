package com.aliceinwonderland;

import static com.aliceinwonderland.Main.CHEAT;

public class Square {
    private Boolean isFlagged = false;
    private Boolean isBomb = false;
    private Boolean isChecked = false;
    private Integer noBombsAround = 0;
    private Boolean cheatMode = false;

    public Square(Boolean isBomb) {
        this.isBomb = isBomb;
        this.cheatMode = CHEAT;
    }

    public Boolean getFlagged() {
        return this.isFlagged;
    }

    public void toggleFlagged() {
        this.isFlagged = !this.isFlagged;
    }

    public void addCloseBomb() {
        this.noBombsAround += 1;
    }

    public Boolean getBomb() {
        return this.isBomb;
    }

    public Boolean getChecked() {
        return this.isChecked;
    }

    public void setChecked() {
        this.isChecked = true;
    }

    public String getIdentifier() {
        if (cheatMode) {
            if (isBomb) {
                return "*";
            } else {
                if (this.isFlagged) {
                    return "*";
                }

                if (this.isChecked) {
                    return "0" + noBombsAround;
                }
            }

            return "~";
        } else {
            return ".";
        }
    }

    public void setCloseBombs(int number) {
        this.noBombsAround = number;
    }
}
