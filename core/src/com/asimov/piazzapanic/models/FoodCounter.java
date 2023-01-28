package com.asimov.piazzapanic.models;

public class FoodCounter {
    private Ingredient ingredient = null;

    public Ingredient getIngredient() {
        return ingredient;
    }

    public boolean canPlace(IngredientStack stack) {

        if (ingredient != null) {
            return false;
        }

        if (stack.size() == 0) {
            return false;
        }

        if (stack.peek() instanceof Dish) {
            return false;
        }

        return true;
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
