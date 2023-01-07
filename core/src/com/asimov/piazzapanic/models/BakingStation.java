package com.asimov.piazzapanic.models;

public class BakingStation extends CookingStation {
    public void bake() {
        ((Bakeable)getIngredient()).bake();
    }

    @Override
    protected boolean canPlace(Ingredient ingredient) {
        return ingredient instanceof Bakeable;
    }
}
