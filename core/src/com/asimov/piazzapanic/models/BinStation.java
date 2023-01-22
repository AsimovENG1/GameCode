package com.asimov.piazzapanic.models;

public class BinStation extends CookingStation{
    private Ingredient ingredients = null;
    public void place(Chef chef) throws Exception {
        if (chef.stack.size() <= 0) {
            throw new Exception("The chef's stack is empty.");
        }
        
    }
    @Override
    public void place(IngredientStack stack) {
        ingredients = stack.place();
        status = CookingStatus.available;
    }

    @Override
    protected boolean canPlace(Ingredient ingredient) {
        return ingredient instanceof Ingredient;
    }
}
