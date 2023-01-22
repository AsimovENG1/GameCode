package com.asimov.piazzapanic.models;

public class BinStation extends CookingStation{
 
    public void bin() {

        // needs to grab from top of ingedient station then delete item

    }
    @Override
    protected boolean canPlace(Ingredient ingredient) {
        return ingredient instanceof Ingredient;
    }
}
