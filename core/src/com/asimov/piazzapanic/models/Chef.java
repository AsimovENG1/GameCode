package com.asimov.piazzapanic.models;

import com.badlogic.gdx.graphics.Color;

public class Chef {
    public int id;
    public IngredientStack stack = new IngredientStack();
    public Color hatColor;
    public String hatShape;

    private Iterable<Recipe> recipes;

    public Chef(Iterable<Recipe> recipes) {
        this.recipes = recipes;
    }

    public void grab(Ingredient ingredient) {
        stack.grab(ingredient);

        for (Recipe recipe : recipes) {
            if (recipe.validate(stack)) {
                stack.clear();
                stack.grab(recipe.create());
            }

            return;
        }
    }
}
