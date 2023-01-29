package com.asimov.piazzapanic;

import com.asimov.piazzapanic.models.Ingredient;
import com.asimov.piazzapanic.models.IngredientStack;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public abstract class CookingStationSprite extends Sprite {
    public CookingStationSprite() {
        super(new Texture("ChoppingStation.png"));
    }

    public abstract Ingredient getIngredient();

    public abstract boolean canPlace(IngredientStack stack);

    public abstract void place(IngredientStack stack);

    public abstract boolean canGrab();

    public abstract void grab(IngredientStack stack);
}
