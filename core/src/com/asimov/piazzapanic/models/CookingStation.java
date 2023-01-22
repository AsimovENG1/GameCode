package com.asimov.piazzapanic.models;

public abstract class CookingStation {
    private Ingredient ingredient = null;

    protected CookingStatus status = CookingStatus.available;

    public CookingStatus getStatus() {
        return status;
    }

    public Ingredient getIngredient() {
        return ingredient;
    }

    protected abstract boolean canPlace(Ingredient ingredient);
    public boolean canPlace(IngredientStack stack) {
        if (status != CookingStatus.available) {
            return false;
        }

        if (stack.size() <= 0) {
            return false;
        }

        return canPlace(stack.peek());
    }

    public void place(IngredientStack stack) {
        if (!canPlace(stack)) {
            return;
        }

        ingredient = stack.place();

        status = CookingStatus.cooking;
    }

    public boolean canGrab() {
        if (status != CookingStatus.complete) {
            return false;
        }

        return true;
    }

    public void grab(IngredientStack stack) {
        if (!canGrab()) {
            return;
        }

        stack.grab(ingredient);

        ingredient = null;

        status = CookingStatus.available;
    }
}
