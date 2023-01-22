package com.asimov.piazzapanic;

import com.asimov.piazzapanic.models.TomatoStation;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
public class TomatoStationActor extends IngredientStationActor<TomatoStation> {
    Texture tomatoStation = new Texture("IngredientStations/Tomato_Station.png");

    public TomatoStationActor(TomatoStation model) {
        super(model);
        setTexture(new TextureRegion(tomatoStation));
    }
}
