package com.asimov.piazzapanic.models;

/**
 * Represents an ingredient which can be placed on a chopping station.
 */
public interface Choppable extends Ingredient {
    /**
     * Chops the ingredient. isChopped should become true.
     */
    void chop();

    /**
     * Whether ingredient has been chopped.
     * @return Whether chop has been called on the ingredient.
     */
    boolean isChopped();
}
