package com.asimov.piazzapanic;

import com.asimov.piazzapanic.deltatimer.DeltaTimer;
import com.asimov.piazzapanic.models.*;
import com.badlogic.gdx.graphics.Texture;

public class TomatoStationSprite extends BaseIngredientStationSprite<IngredientStation>{

    Texture tomatoStation = new Texture("IngredientStations/Tomato_Station.png");
    private DeltaTimer timer;

    public TomatoStationSprite(DeltaTimer timer) {
        super(new IngredientStation<>(Tomato::new), timer);
        this.timer = timer;
        setTexture(tomatoStation);
    }

    @Override
    public void grab(IngredientStack stack) {
        IngredientStation<Tomato> IngredientStation = new IngredientStation<>(Tomato::new);
        IngredientStation.grab(stack);
    }
}
