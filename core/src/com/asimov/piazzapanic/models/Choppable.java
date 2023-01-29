package com.asimov.piazzapanic.models;

/**
 * Represents an ingredient which can be placed on a chopping station.
 */
public interface Choppable extends Ingredient {
    /**
     * Chops the ingredient.
     */
    void chop();
}
