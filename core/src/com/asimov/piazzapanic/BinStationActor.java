package com.asimov.piazzapanic;

import com.asimov.piazzapanic.models.BinStation;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class BinStationActor extends CookingStationActor<BinStation> {
    
    private final Texture Texture = new Texture("Bin.png");

    public BinStationActor() {
        super(new BinStation());
        setTexture(new TextureRegion(Texture));
    }

}