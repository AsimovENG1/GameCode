package com.asimov.piazzapanic;

import com.asimov.piazzapanic.models.Chef;
import com.asimov.piazzapanic.models.Ingredient;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public abstract class CookingStationSprite extends Sprite {
    public CookingStationSprite() {
        super(new Texture("ChoppingStation.png"));
    }

    public abstract Ingredient getIngredient();

    public abstract boolean canPlace(Chef chef);

    public abstract void place(Chef chef) throws Exception;

    public abstract boolean canGrab();

    public abstract void grab(Chef chef) throws Exception;
}
