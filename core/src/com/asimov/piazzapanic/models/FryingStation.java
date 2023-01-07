package com.asimov.piazzapanic.models;

public class FryingStation extends CookingStation {
//    Custom logic for frying station, e.g.
//    public boolean canFry() {
//
//    }
//
//    public void fry() {
//
//        status = CookingStatus.complete;
//    }

    @Override
    protected boolean canPlace(Ingredient ingredient) {
        return ingredient instanceof Fryable;
    }
}
