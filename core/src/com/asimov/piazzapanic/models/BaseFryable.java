package com.asimov.piazzapanic.models;

public abstract class BaseFryable implements Fryable {
    @Override
    public void fry() {
        isFried = true;
    }

    public boolean isFried = false;
}
