package com.asimov.piazzapanic.models;

public abstract class BaseFryable implements Fryable {
    @Override
    public boolean isFried() {
        // TODO: custom frying logic
        return false;
    }
}
