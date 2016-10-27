package com.aliceinwonderland;

public class Square {
    protected Boolean isMine = false;
    protected Boolean isFlagged = false;
    protected Boolean isChecked = false;

    protected int noMinesAround = 0;

    public Boolean getFlagged() {
        return this.isFlagged;
    }

    public void toggleFlagged() {
        this.isFlagged = !this.isFlagged;
    }

    public Boolean getChecked() {
        return this.isChecked;
    }

    public void setChecked() {
        this.isChecked = true;
    }

    public Character getIdentifier(boolean cheat) {
        if (cheat) {
            return '~';
        } else {
            if (this.isFlagged) {
                return '*';
            } else if (this.isChecked) {
                return ((char) ('0' + noMinesAround));
            }
            return '.';
        }
    }

    public void setCloseMines(int number) {
        this.noMinesAround = number;
    }
}
