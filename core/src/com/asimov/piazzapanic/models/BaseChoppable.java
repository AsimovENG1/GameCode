package com.asimov.piazzapanic.models;

public abstract class BaseChoppable implements Choppable {
    private boolean isChopped = false;

    @Override
    public void chop() {
        isChopped = true;
    }

    @Override
    public boolean isChopped() {
        return isChopped;
    }
}
