package com.asimov.piazzapanic.models;

public abstract class BaseChoppable implements Choppable {
    @Override
    public void chop() {
        isChopped = true;
    }

    public boolean isChopped = false;
}
