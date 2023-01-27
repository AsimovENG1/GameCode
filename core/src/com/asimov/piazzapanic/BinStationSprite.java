package com.asimov.piazzapanic;

import com.asimov.piazzapanic.deltatimer.DeltaTimer;
import com.asimov.piazzapanic.models.BinStation;
import com.badlogic.gdx.graphics.Texture;

public class BinStationSprite extends BaseCookingStationSprite<BinStation> {
    private final Texture Texture = new Texture("Bin.png");
    private DeltaTimer timer;

    public BinStationSprite(DeltaTimer timer) {
        super(new BinStation());
        this.timer = timer;
        setTexture(Texture);
    }

    @Override
    protected void placed() {
        timer.start(1, () -> model.open());
    }
}