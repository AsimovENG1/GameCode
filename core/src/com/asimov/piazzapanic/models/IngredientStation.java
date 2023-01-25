package com.asimov.piazzapanic.models;

import java.util.concurrent.TimeUnit;

public class IngredientStation<T extends Ingredient> {
    private IngredientSource<T> ingredient;

    public IngredientStation(IngredientSource<T> ingredient) {
        this.ingredient = ingredient;
    }

    public void grab(IngredientStack stack) {
        try {
            stack.grab(ingredient.create());
            TimeUnit.SECONDS.sleep(1);
        }
        catch (Exception e) {
            System.out.println(e);
        }
    }
}
