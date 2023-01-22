package com.asimov.piazzapanic;

import com.asimov.piazzapanic.models.BinStation;
import com.badlogic.gdx.graphics.Texture;

public class BinStationSprite extends BaseCookingStationSprite<BinStation> {
    
    private final Texture Texture = new Texture("Bin.png");

    public BinStationSprite() {
        super(new BinStation());
        setTexture(Texture);
    }

}