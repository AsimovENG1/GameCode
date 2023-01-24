package com.asimov.piazzapanic;

import com.asimov.piazzapanic.models.*;
import com.badlogic.gdx.graphics.Texture;

public class MeatStationSprite extends BaseIngredientStationSprite<IngredientStation>{

    Texture meatStation = new Texture("IngredientStations/Meat_Station.png");
    public MeatStationSprite() {
        super(new IngredientStation<>(Meat::new));
        setTexture(meatStation);
    }

    @Override
    public void grab(IngredientStack stack) {
        IngredientStation<Meat> IngredientStation = new IngredientStation<>(Meat::new);
        IngredientStation.grab(stack);
    }
}
