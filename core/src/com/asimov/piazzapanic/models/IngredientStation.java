package com.asimov.piazzapanic.models;

public class IngredientStation<T extends Ingredient> {
    private IngredientSource<T> ingredient;

    public IngredientStation(IngredientSource<T> ingredient) {
        this.ingredient = ingredient;
    }

    public void grab(Chef chef) {
        chef.stack.grab(ingredient.create());
    }
}
