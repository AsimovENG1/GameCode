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
