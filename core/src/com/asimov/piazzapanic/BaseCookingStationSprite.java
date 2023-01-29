package com.asimov.piazzapanic;

import com.asimov.piazzapanic.models.*;

/**
 * Base class for cooking station sprites. Contains default implementations for placing and grabbing.
 * @param <T> Type of model to represent cooking station, for example ChoppingStation.
 */
public abstract class BaseCookingStationSprite<T extends CookingStation> extends CookingStationSprite {
    /**
     * Model representing this cooking station.
     */
    protected T model;

    /**
     * Creates a new cooking station sprite using the specified model.
     * @param model Model to represent this cooking station.
     */
    public BaseCookingStationSprite(T model) {
        this.model = model;
    }

    /**
     * Gets the cooking status of the model.
     * @return The current cooking status.
     */
    public CookingStatus getStatus() {
        return model.getStatus();
    }

    /**
     * Gets the ingredient currently placed on the cooking station.
     * @see CookingStation#getIngredient() 
     * @return The current ingredient, or null if the cooking station is available.
     */
    public Ingredient getIngredient() {
        return model.getIngredient();
    }

    /**
     * Whether the cooking station is available for the ingredient at the top of the stack to be placed.
     * @see CookingStation#canPlace(IngredientStack) 
     * @param stack Stack to be placed.
     * @return Whether ingredient can be placed.
     */
    public boolean canPlace(IngredientStack stack) {
        return model.canPlace(stack);
    }

    /**
     * Places the ingredient at the top of the specified stack on the cooking station.
     * @see CookingStation#place(IngredientStack) 
     * @param stack Stack to be placed.
     */
    public void place(IngredientStack stack) {
        model.place(stack);

        placed();
    }

    /**
     * Called when an ingredient has been placed on the cooking station. Override to implement custom logic.
     */
    protected void placed() {}

    /**
     * Whether an ingredient is available at the cooking station to add to a stack.
     * @see CookingStation#canGrab() 
     * @return Whether ingredient can be grabbed.
     */
    public boolean canGrab() {
        return model.canGrab();
    }

    /**
     * Adds the current ingredient to the top of the specified stack.
     * @see CookingStation#grab(IngredientStack) 
     * @param stack Stack to add ingredient to.
     */
    public void grab(IngredientStack stack) {
        model.grab(stack);

        grabbed();
    }

    /**
     * Called when the ingredient has been grabbed from the cooking station. Override to implement custom logic.
     */
    protected void grabbed() {}
}
