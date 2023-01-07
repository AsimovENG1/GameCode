package com.asimov.piazzapanic.models;

public abstract class BaseChoppable implements Choppable {
    private boolean isChopped = false;

    @Override
    public void chop() {
        isChopped = true;
    }

    public boolean isChopped() {
        return isChopped;
    }
}
