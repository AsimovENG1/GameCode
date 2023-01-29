package com.asimov.piazzapanic.models;

public class Patty extends BaseFryable implements Choppable {
    private boolean isFormed = false;

    @Override
    public void chop() {
        isFormed = true;
    }

    @Override
    public boolean isChopped() {
        return isFormed();
    }

    public boolean isFormed() {
        return isFormed;
    }
}
