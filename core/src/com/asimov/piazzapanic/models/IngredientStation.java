package com.asimov.piazzapanic.models;

/**
 * An ingredient station for the specified ingredient.
 * @param <T> Type of ingredient the station provides.
 */
public class IngredientStation<T extends Ingredient> {
    private IngredientSource<T> ingredient;

    /**
     * Constructor, requires method to create new ingredient.
     * @param ingredient Ingredient constructor method, for example () -&gt; new Lettuce().
     */
    public IngredientStation(IngredientSource<T> ingredient) {
        this.ingredient = ingredient;
    }

    /**
     * Grabs a new ingredient from the station and places it at the top of the specified stack. Ensure stack is not full before using.
     * @param stack Stack to add ingredient to.
     */
    public void grab(IngredientStack stack) {
        stack.grab(ingredient.create());
    }
}
