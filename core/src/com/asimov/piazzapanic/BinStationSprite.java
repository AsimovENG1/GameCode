package com.asimov.piazzapanic;

import com.asimov.piazzapanic.deltatimer.DeltaTimer;
import com.asimov.piazzapanic.models.BinStation;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;

public class BinStationSprite extends BaseCookingStationSprite<BinStation> {
    private final Texture Texture = new Texture("Bin.png");

    private Sound trash;
    private DeltaTimer timer;

    public BinStationSprite(DeltaTimer timer) {
        super(new BinStation());
        this.timer = timer;
        setTexture(Texture);
    }

    @Override
    protected void placed() {
        timer.start(0.5f, () -> model.open());
        trash = Gdx.audio.newSound((Gdx.files.internal(("audio/mixkit-falling-on-foil-748.wav"))));
    }
}