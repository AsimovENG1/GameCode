package com.asimov.piazzapanic.models;

public abstract class CookingStation<T extends Ingredient> {
    public T ingredient = null;

    public boolean isComplete;

    public void startCooking(T ingredient) {
        this.ingredient = ingredient;
    }

    public boolean canStopCooking() {
        return isComplete;
    }

    public Ingredient stopCooking() throws Exception {
        if (!canStopCooking()) {
            throw new Exception("Cooking is not complete."); // TODO: custom exceptions?
        }

        T ing = ingredient;
        ingredient = null;
        return ing;
    }
}
