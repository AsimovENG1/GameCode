package com.asimov.piazzapanic.models;

public class BakingStation extends CookingStation<Bakeable> {
    public void bake() {
        ingredient.bake();
    }
}
