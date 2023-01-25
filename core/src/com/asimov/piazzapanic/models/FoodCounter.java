package com.asimov.piazzapanic.models;

public class FoodCounter {
    private Ingredient ingredient = null;

    public Ingredient getIngredient() {
        return ingredient;
    }

    public boolean canPlace(IngredientStack stack) {
        return ingredient == null && !(stack.peek() instanceof Dish);
    }

    public void place(IngredientStack stack) {
        if (!canPlace(stack)) {
            return;
        }

        ingredient = stack.place();
    }

    public boolean canGrab() {
        return ingredient != null;
    }

    public void grab(IngredientStack stack) {
        if (!canGrab()) {
            return;
        }

        stack.grab(ingredient);

        ingredient = null;
    }
}
