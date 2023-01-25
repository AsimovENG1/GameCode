package com.asimov.piazzapanic;

import com.asimov.piazzapanic.models.*;
import com.badlogic.gdx.graphics.Texture;

public class OnionStationSprite extends BaseIngredientStationSprite<IngredientStation>{

    Texture onionStation = new Texture("IngredientStations/Onion_Station.png");
    public OnionStationSprite() {
        super(new IngredientStation<>(Onion::new));
        setTexture(onionStation);
    }

    @Override
    public void grab(IngredientStack stack) {
        IngredientStation<Onion> IngredientStation = new IngredientStation<>(Onion::new);
        IngredientStation.grab(stack);
    }
}
