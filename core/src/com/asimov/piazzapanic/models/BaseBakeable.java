package com.asimov.piazzapanic.models;

public abstract class BaseBakeable implements Bakeable {
    private boolean isBaked = false;

    @Override
    public void bake() {
        isBaked = true;
    }

    public boolean isBaked() {
        return isBaked;
    }
}
