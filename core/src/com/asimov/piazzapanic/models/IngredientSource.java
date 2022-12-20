package com.asimov.piazzapanic.models;

public interface IngredientSource<T extends Ingredient> {
    public T create();
}
