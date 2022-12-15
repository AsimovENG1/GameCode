package com.asimov.piazzapanic.models;

public class FryingStation extends CookingStation<Fryable> {
    private int side = 1;
    public void flip() {
        side = 3 - side;
    }
}
