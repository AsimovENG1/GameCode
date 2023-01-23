package com.asimov.piazzapanic.models;
import java.util.concurrent.TimeUnit;

public class BinStation extends CookingStation{
    private Ingredient ingredients = null;
    public void place(Chef chef) throws Exception {
        if (chef.stack.size() <= 0) {
            throw new Exception("The chef's stack is empty.");
        }
        
    }
    @Override
    public void place(IngredientStack stack) {
        try {
            ingredients = stack.place();
            TimeUnit.SECONDS.sleep(1);
            status = CookingStatus.available;}
        catch (Exception e) {
            System.out.println(e);
        }
    }

    @Override
    protected boolean canPlace(Ingredient ingredient) {
        return ingredient instanceof Ingredient;
    }
}
