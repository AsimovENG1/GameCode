package com.asimov.piazzapanic.models;

public abstract class CookingStation<T extends Ingredient> {
    public T ingredient = null;

    public CookingStatus status;

    public void place(Chef chef) throws Exception {
        if (status != CookingStatus.available) {
            throw new Exception("Cooking station is not available.");
        }

        if (chef.stack.size() <= 0) {
            throw new Exception("The chef's stack is empty.");
        }

        this.ingredient = (T)chef.stack.place();

        status = CookingStatus.cooking;
    }

    public void grab(Chef chef) throws Exception {
        if (status != CookingStatus.complete) {
            throw new Exception("Cooking is not complete.");
        }

        chef.stack.grab(ingredient);

        ingredient = null;

        status = CookingStatus.available;
    }
}
