package com.asimov.piazzapanic;

import com.asimov.piazzapanic.deltatimer.DeltaTimer;
import com.asimov.piazzapanic.models.*;
import com.badlogic.gdx.graphics.Texture;

public class MeatStationSprite extends BaseIngredientStationSprite<IngredientStation>{
    Texture meatStation = new Texture("IngredientStations/Meat_Station.png");

    public MeatStationSprite(DeltaTimer timer) {
        super(new IngredientStation<>(Patty::new), timer);
        setTexture(meatStation);
    }
}
