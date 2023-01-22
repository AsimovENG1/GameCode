package com.asimov.piazzapanic;

import com.asimov.piazzapanic.models.IngredientStation;
import com.asimov.piazzapanic.models.Tomato;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
public class TomatoStationActor extends IngredientStationActor<IngredientStation> {
    Texture tomatoStation = new Texture("IngredientStations/Tomato_Station.png");
    public TomatoStationActor() {
        super();
        setTexture(new TextureRegion(tomatoStation));
        TomatoStationActor.TomatoStation();
    }

    public static void TomatoStation(){
        IngredientStation<Tomato> TStation = new IngredientStation<>(()->new Tomato());
    }
}
