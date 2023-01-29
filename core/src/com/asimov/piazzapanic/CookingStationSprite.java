package com.asimov.piazzapanic;

import com.asimov.piazzapanic.models.Ingredient;
import com.asimov.piazzapanic.models.IngredientStack;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

/**
 * Abstract sprite class representing a cooking station.
 */
public abstract class CookingStationSprite extends Sprite {

    /**
     * Creates a new cooking station sprite with default chopping station texture.
     */
    public CookingStationSprite() {
        super(new Texture("ChoppingStation.png"));
    }

    /**
     * Gets the current ingredient on the cooking station.
     * @return The current ingredient.
     */
    public abstract Ingredient getIngredient();

    /**
     * Whether the specified stack can be placed on the cooking station.
     * @param stack Stack to be placed.
     * @return
     */
    public abstract boolean canPlace(IngredientStack stack);

    public abstract void place(IngredientStack stack);

    public abstract boolean canGrab();

    public abstract void grab(IngredientStack stack);
}
