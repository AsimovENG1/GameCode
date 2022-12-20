package com.asimov.piazzapanic.models;

public class ChoppingStation extends CookingStation<Choppable> {
    public void chop() {
        ingredient.chop();
    }
}
