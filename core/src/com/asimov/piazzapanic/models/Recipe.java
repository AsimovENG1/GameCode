package com.asimov.piazzapanic.models;

import com.badlogic.gdx.utils.Array;

public class Recipe {
    public Array<Ingredient> ingredients;
    private DishSource dish;
    private IngredientValidator validate;

    public Recipe(Ingredient[] ingredients, DishSource dish, IngredientValidator validate) {
        this.ingredients = new Array<>(ingredients);
        this.dish = dish;
        this.validate = validate;
    }

    public interface DishSource {public Dish create();}
    public Dish create() {
        return dish.create();
    }

    public interface IngredientValidator {public boolean validate(Iterable<Ingredient> ingredients);}
    public boolean validate(Iterable<Ingredient> ingredients) {
        return validate.validate(ingredients);
    }

    private static boolean validateSalad(Iterable<Ingredient> ingredients) {
        boolean onion = false;
        boolean lettuce = false;

        for (Ingredient i : ingredients) {
            if (i instanceof Onion) {
                onion = ((Onion) i).isChopped();
                continue;
            }

            if (i instanceof Lettuce) {
                lettuce = ((Lettuce) i).isChopped();
                continue;
            }

            return false;
        }

        return onion && lettuce;
    }

    private static boolean validateBurger(Iterable<Ingredient> ingredients) {
        boolean patty = false;
        boolean bun = false;

        for (Ingredient i : ingredients) {
            if (i instanceof Patty) {
                patty = ((Patty) i).isFried();
                continue;
            }

            if (i instanceof Bun) {
                bun = ((Bun) i).isFried();
                continue;
            }

            return false;
        }

        return patty && bun;
    }

    public static Recipe salad =
            new Recipe(new Ingredient[] {new Onion(), new Lettuce()}, () -> new Salad(), Recipe::validateSalad);

    public static Recipe burger =
            new Recipe(new Ingredient[] {new Patty(), new Bun()}, () -> new Burger(), Recipe::validateBurger);
}
