package com.asimov.piazzapanic.models;

public abstract class CookingStation<T extends Ingredient> {
    public T ingredient = null;

    public CookingStatus status;

    public void startCooking(T ingredient) throws Exception {
        if (status != CookingStatus.available) {
            throw new Exception("Cooking station is not available.");
        }

        this.ingredient = ingredient;
        status = CookingStatus.cooking;
    }

    public Ingredient stopCooking() throws Exception {
        if (status != CookingStatus.complete) {
            throw new Exception("Cooking is not complete.");
        }

        T ingredient = this.ingredient;
        this.ingredient = null;

        status = CookingStatus.available;

        return ingredient;
    }
}
