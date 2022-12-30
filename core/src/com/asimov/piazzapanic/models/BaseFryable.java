package com.asimov.piazzapanic.models;

public abstract class BaseFryable implements Fryable {
    private boolean[] sides = new boolean[] {false, false};

    @Override
    public void frySide(int side) {
        sides[side] = true;
    }

    public boolean isFried() {
        for (boolean side : sides) {
            if (!side) return false;
        }

        return true;
    }
}
