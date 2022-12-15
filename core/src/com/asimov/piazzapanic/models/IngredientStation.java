package com.asimov.piazzapanic.models;

public class IngredientStation<T extends Ingredient> {
    private IngredientSource<T> ingredient;

    public IngredientStation(IngredientSource<T> ingredient) {
        this.ingredient = ingredient;
    }

    public T takeIngredient() {
        return ingredient.create();
    }
}
