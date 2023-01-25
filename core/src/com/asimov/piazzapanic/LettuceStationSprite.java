package com.asimov.piazzapanic;

import com.asimov.piazzapanic.deltatimer.DeltaTimer;
import com.asimov.piazzapanic.models.*;
import com.badlogic.gdx.graphics.Texture;

public class LettuceStationSprite extends BaseIngredientStationSprite<IngredientStation>{

    Texture lettuceStation = new Texture("IngredientStations/Lettuce_Station.png");
    public LettuceStationSprite(DeltaTimer timer) {
        super(new IngredientStation<>(Lettuce::new), timer);
        setTexture(lettuceStation);
    }

    @Override
    public void grab(IngredientStack stack) {
        IngredientStation<Lettuce> IngredientStation = new IngredientStation<>(Lettuce::new);
        IngredientStation.grab(stack);
    }
}
