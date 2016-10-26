package com.aliceinwonderland;

public class Square {
    protected Boolean isBomb = false;
    protected Boolean isFlagged = false;
    protected Boolean isChecked = false;
    protected Integer noBombsAround = 0;

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

    public String getIdentifier(boolean cheat) {
        if (cheat) {
            return "~";
        } else {
            if (this.isFlagged) {
                return "*";
            } else if (this.isChecked) {
                return "0"; // TODO EXTRA: Recursive method
            }
            return ".";
        }
    }

    public void setCloseBombs(int number) {
        this.noBombsAround = number;
    }
}
