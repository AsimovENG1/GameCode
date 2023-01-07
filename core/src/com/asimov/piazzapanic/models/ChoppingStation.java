package com.asimov.piazzapanic.models;

public class ChoppingStation extends CookingStation {
    public boolean canChop() {
        if (status != CookingStatus.cooking) {
            return false;
        }

        if (getIngredient() == null) {
            return false;
        }

        return true;
    }

    public void chop() throws Exception {
        if (!canChop()) {
            throw new Exception("Cannot chop here.");
        }

        ((Choppable)getIngredient()).chop();

        status = CookingStatus.complete;
    }

    @Override
    protected boolean canPlace(Ingredient ingredient) {
        return ingredient instanceof Choppable;
    }
}
