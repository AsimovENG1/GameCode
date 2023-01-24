package com.asimov.piazzapanic;

import com.asimov.piazzapanic.models.*;
import com.badlogic.gdx.graphics.Texture;

public class BunStationSprite extends BaseIngredientStationSprite<IngredientStation>{

    Texture bunStation = new Texture("IngredientStations/Empty_Ingredient_Station.png");
    public BunStationSprite() {
        super(new IngredientStation<>(Bun::new));
        setTexture(bunStation);
    }

    @Override
    public void grab(IngredientStack stack) {
        IngredientStation<Bun> IngredientStation = new IngredientStation<>(Bun::new);
        IngredientStation.grab(stack);
    }
}
