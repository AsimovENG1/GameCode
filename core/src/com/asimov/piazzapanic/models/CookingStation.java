package com.asimov.piazzapanic.models;

/**
 * Base class for cooking stations.
 */
public abstract class CookingStation {
    protected Ingredient ingredient = null;

    protected CookingStatus status = CookingStatus.available;

    /**
     * Gets the current status of the cooking station.
     * @return
     * @see CookingStatus
     */
    public CookingStatus getStatus() {
        return status;
    }

    /**
     * Gets the ingredient currently placed on the cooking station.
     * @return Returns the current Ingredient, or null of the cooking station is available.
     */
    public Ingredient getIngredient() {
        return ingredient;
    }

    protected abstract boolean canPlace(Ingredient ingredient);
    /**
     * Whether the cooking station is available for the ingredient at the top of the specified stack to be placed.
     * @param stack Stack to be placed.
     * @return Whether ingredient can be placed.
     */
    public boolean canPlace(IngredientStack stack) {
        if (status != CookingStatus.available) {
            return false;
        }

        if (stack.size() <= 0) {
            return false;
        }

        return canPlace(stack.peek());
    }

    /**
     * Places the ingredient at the top of the specified stack on the cooking station. Will not place if canPlace returns false.
     * @param stack Stack to be placed.
     */
    public void place(IngredientStack stack) {
        if (!canPlace(stack)) {
            return;
        }

        ingredient = stack.place();

        status = CookingStatus.cooking;
    }

    /**
     * Whether an ingredient is available at the cooking station to add to a chef's stack.
     * @return Whether ingredient can be grabbed.
     */
    public boolean canGrab() {
        if (status != CookingStatus.complete) {
            return false;
        }

        return true;
    }

    /**
     * Adds the current ingredient to the top of the specified stack. Ensure stack is not full before grabbing. Will not grab if canGrab returns false.
     * @param stack Stack to add ingredient to.
     */
    public void grab(IngredientStack stack) {
        if (!canGrab()) {
            return;
        }

        stack.grab(ingredient);

        ingredient = null;

        status = CookingStatus.available;
    }
}
