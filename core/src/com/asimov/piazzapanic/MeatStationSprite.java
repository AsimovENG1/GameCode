package com.asimov.piazzapanic;

import com.asimov.piazzapanic.deltatimer.DeltaTimer;
import com.asimov.piazzapanic.models.*;
import com.badlogic.gdx.graphics.Texture;

public class MeatStationSprite extends BaseIngredientStationSprite<IngredientStation>{

    Texture meatStation = new Texture("IngredientStations/Meat_Station.png");
    public MeatStationSprite(DeltaTimer timer) {
        super(new IngredientStation<>(Meat::new), timer);
        setTexture(meatStation);
    }

    @Override
    public void grab(IngredientStack stack) {
        IngredientStation<Meat> IngredientStation = new IngredientStation<>(Meat::new);
        IngredientStation.grab(stack);
    }
}
