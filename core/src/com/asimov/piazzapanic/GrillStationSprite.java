package com.asimov.piazzapanic;

import com.asimov.piazzapanic.deltatimer.DeltaTimer;
import com.asimov.piazzapanic.models.FryingStation;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;

public class GrillStationSprite extends BaseCookingStationSprite<FryingStation> {
    private final Texture emptyGrillTexture = new Texture("emptyGrill.png"); // station is empty

    private final Texture GrillTexture = new Texture("burgerGrill.png"); // station has burger on
    private final Texture GrillReadyTexture = new Texture("burgerGrillReady.png"); // burger is ready to be flipped
    private final Texture GrillFlipedTexture = new Texture("burgerGrillFliped.png"); // burger is fliped
    private final Texture GrillDoneTexture = new Texture("burgerGrillDone.png"); // burger is ready to be taken off

    private Music frying = Gdx.audio.newMusic((Gdx.files.internal(("audio/frying-3.wav"))));

    private DeltaTimer timer;

    public GrillStationSprite(DeltaTimer timer) {
        super(new FryingStation());
        this.timer = timer;
        setTexture(emptyGrillTexture);
    }
    @Override
    public void placed() {
        setTexture(GrillTexture);
        timer.start(5, () -> readyToFlip());
        frying.setLooping(true);
        frying.play();
    }
    public void readyToFlip() {
        setTexture(GrillReadyTexture);
        model.readyToFlip();
    }
    public void flip() {
        if (!canFlip()) {
            return;
        }

        model.flip();
        setTexture(GrillFlipedTexture);
        timer.start(5, () -> readyToPickUp());
        timer.start(5, () -> frying.setLooping(false));
    }
    public void readyToPickUp() {
        setTexture(GrillDoneTexture);
        model.readyToPickUp();

    }
    @Override
    public void grabbed() {
        setTexture(emptyGrillTexture);
    }
    public boolean canFlip(){
        if (model.canFlip()){
            return true;
        }
        else {
            return false;
        }
    }
}
