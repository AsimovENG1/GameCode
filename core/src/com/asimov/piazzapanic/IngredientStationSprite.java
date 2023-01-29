package com.asimov.piazzapanic;

import com.asimov.piazzapanic.models.IngredientStack;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public abstract class IngredientStationSprite extends Sprite {

    public  IngredientStationSprite(){
        super(new Texture("IngredientStations/Empty_Ingredient_Station.png"));
    }

    public abstract void grab(IngredientStack stack);

    public abstract boolean canGrab();
}
