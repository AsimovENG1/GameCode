package com.asimov.piazzapanic.models;

/**
 * Cooking station for chopping ingredients.
 */
public class ChoppingStation extends CookingStation {

    /**
     * Whether a chef can chop at the chopping station. Requires an ingredient to be at the station and not already chopped.
     * @return Whether ingredient can be chopped.
     */
    public boolean canChop() {
        if (status != CookingStatus.cooking) {
            return false;
        }

        if (getIngredient() == null) {
            return false;
        }

        return true;
    }

    /**
     * Chops the ingredient currently placed on the chopping station. Will not chop if canChop returns false.
     */
    public void chop()  {
        if (!canChop()) {
            return;
        }

        ((Choppable)getIngredient()).chop();

        status = CookingStatus.complete;
    }

    @Override
    protected boolean canPlace(Ingredient ingredient) {
        return ingredient instanceof Choppable;
    }
}
