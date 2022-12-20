package com.asimov.piazzapanic.models;

public abstract class BaseBakeable implements Bakeable {
    @Override
    public void bake() {
        isBaked = true;
    }

    public boolean isBaked = false;
}
