package com.asimov.piazzapanic.models;

public class FryingStation extends CookingStation {
    public void readyToFlip() {
        ((Fryable)getIngredient()).stageIncrease();
    }
    public void flip() {
    }
        
    public void readyToPickUp() {
        ((Fryable)getIngredient()).makeFried();
        status = CookingStatus.complete;
    }
    @Override
    protected boolean canPlace(Ingredient ingredient) {
        if (getIngredient() != null) {
            return false;
        }
        if (status == CookingStatus.cooking) {
            return false;}
        if ((((Fryable)getIngredient()).state()) != 0){
            return false;}
        return ingredient instanceof Fryable;
    }
    public boolean canFlip() {
        if (getIngredient() == null) {
            return false;
        }

        return ((Fryable)getIngredient()).state() == 1;
    }
}
