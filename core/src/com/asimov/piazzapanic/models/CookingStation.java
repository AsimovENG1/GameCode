package com.asimov.piazzapanic.models;

public abstract class CookingStation {
    private Ingredient ingredient = null;

    protected CookingStatus status;

    public CookingStatus getStatus() {
        return status;
    }

    public Ingredient getIngredient() {
        return ingredient;
    }

    protected abstract boolean canPlace(Ingredient ingredient);
    public boolean canPlace(Chef chef) {
        if (status != CookingStatus.available) {
            return false;
        }

        if (chef.stack.size() <= 0) {
            return false;
        }

        return canPlace(chef.stack.peek());
    }

    public void place(Chef chef) throws Exception {
        if (!canPlace(chef)) {
            throw new Exception("Cannot place here.");
        }

        ingredient = chef.stack.place();

        status = CookingStatus.cooking;
    }

    public boolean canGrab() {
        if (status != CookingStatus.complete) {
            return false;
        }

        return true;
    }

    public void grab(Chef chef) throws Exception {
        if (!canGrab()) {
            throw new Exception("Cannot grab here.");
        }

        chef.stack.grab(ingredient);

        ingredient = null;

        status = CookingStatus.available;
    }
}
