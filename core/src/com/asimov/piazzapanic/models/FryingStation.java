package com.asimov.piazzapanic.models;
import java.util.concurrent.TimeUnit;

public class FryingStation extends CookingStation {

    public void place() {
        try {
            ((Fryable)getIngredient()).ReadySwap();
            ((Fryable)getIngredient()).stageIncrease();
            TimeUnit.SECONDS.sleep(5);
            ((Fryable)getIngredient()).ReadySwap();
            }
        catch (Exception e) {
            System.out.println(e);
        }
    }
    public void flip() {
        if (((Fryable)getIngredient()).ready() == true){
            if (((Fryable)getIngredient()).state() == 1){
                try {
                    ((Fryable)getIngredient()).ReadySwap();
                    ((Fryable)getIngredient()).stageIncrease();
                    TimeUnit.SECONDS.sleep(5);
                    ((Fryable)getIngredient()).ReadySwap();
                    ((Fryable)getIngredient()).makeFried();
                    status = CookingStatus.complete;
                    }
                catch (Exception e) {
                    System.out.println(e);
                }
            }
            }
        }
    @Override
    protected boolean canPlace(Ingredient ingredient) {
        return ingredient instanceof Fryable;
    }
    public boolean canFlip() {
        if ((((Fryable)getIngredient()).ready() == true) &&
            (((Fryable)getIngredient()).state() == 1)){
                return true;
            }
        return false;
    }
}
