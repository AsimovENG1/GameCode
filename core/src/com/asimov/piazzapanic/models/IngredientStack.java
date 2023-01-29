package com.asimov.piazzapanic.models;

import com.badlogic.gdx.utils.Array;

import java.util.Iterator;

/**
 * A stack of ingredients held by a chef. Implements Iterable if needed.
 */
public class IngredientStack implements Iterable<Ingredient> {

    /**
     * The stack items in array form.
     */
    public Array<Ingredient> items = new Array<>();

    @Override
    public Iterator<Ingredient> iterator() {
        return items.iterator();
    }

    /**
     * Adds the specified ingredient to the top of the stack.
     * @param ingredient Ingredient to add.
     */
    public void grab(Ingredient ingredient) {
        items.add(ingredient);
    }

    /**
     * Removes and returns the ingredient at the top of the stack.
     * @return Ingredient to be placed.
     */
    public Ingredient place() {
        return items.pop();
    }

    /**
     * Returns the ingredient at the top of the stack, but does not remove it.
     * @return Ingredient currently at the top of the stack.
     */
    public Ingredient peek() {
        return items.peek();
    }

    /**
     * Removes all ingredients from the stack.
     */
    public void clear() {
        items.clear();
    }

    /**
     * Returns the number of ingredients held in the stack.
     * @return The size of the stack.
     */
    public int size() {
        return items.size;
    }
}
