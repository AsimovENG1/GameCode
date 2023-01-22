package com.asimov.piazzapanic.models;

public class TomatoStation extends IngredientStation{

    public TomatoStation(IngredientSource Tomato) {
        super(Tomato);
    }
    public void grab(Chef chef) {
        super.grab(chef);
    }
}