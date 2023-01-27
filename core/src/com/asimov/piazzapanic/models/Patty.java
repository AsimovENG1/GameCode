package com.asimov.piazzapanic.models;

public class Patty extends BaseFryable implements Choppable {
    private boolean isFormed = false;

    @Override
    public void chop() {
        isFormed = true;
    }

    public boolean isFormed() {
        return isFormed;
    }
}
