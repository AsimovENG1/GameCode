package com.asimov.piazzapanic.models;

import com.badlogic.gdx.graphics.Color;

public class Chef {
    public int id;
    public IngredientStack stack = new IngredientStack();
    public Color hatColour;
    public String hatShape;

    public static String[] items = new String[] {"Lettuce", "Tomato", "Onion", "Salad", "Meat", "Patty", "Fried Patty", "Burger", "Bun", "Toasted Buns"};
    private Iterable<Recipe> recipes;

    public Chef(Iterable<Recipe> recipes, Integer id, Color colour) {
        this.id = id; // Different id per chef
        this.hatColour = colour; // Different colour hat per chef
        this.recipes = recipes;
    }

    public void grab(Ingredient ingredient) {
        //if (items.has(ingredient)){
        //   return;
        //}
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
