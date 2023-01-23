package com.asimov.piazzapanic;

import com.asimov.piazzapanic.models.IngredientStack;
import com.asimov.piazzapanic.models.IngredientStation;
import com.asimov.piazzapanic.models.Tomato;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.TextureData;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
public class TomatoStationActor extends IngredientStationActor<IngredientStation<Tomato>> {
    private final Texture tomatoStation = new Texture("IngredientStations/Tomato_Station.png");
    public TomatoStationActor() {
        super();
        setTexture(tomatoStation);
        TomatoStationActor.TomatoStation();
    }

    @Override
    public void grab(IngredientStack stack) {

    }

    public static void TomatoStation(){
        IngredientStation<Tomato> TStation = new IngredientStation<>(()->new Tomato());
    }
}
