package com.asimov.piazzapanic.models;

public class ChoppingStation extends CookingStation<Choppable> {
    @Override
    public Ingredient stopCooking() throws Exception {
        if (!canStopCooking()) {
            throw new Exception("Chopping not complete.");
        }

        ingredient.chop();

        return super.stopCooking();
    }
}
