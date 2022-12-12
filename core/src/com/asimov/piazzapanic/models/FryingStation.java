package com.asimov.piazzapanic.models;

public class FryingStation extends CookingStation<Fryable> {
    @Override
    public Ingredient stopCooking() throws Exception {
        if (!canStopCooking()) {
            throw new Exception("Frying not complete.");
        }

        ingredient.fry();

        return super.stopCooking();
    }
}
