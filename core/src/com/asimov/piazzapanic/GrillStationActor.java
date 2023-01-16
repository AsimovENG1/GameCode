package com.asimov.piazzapanic;

import com.asimov.piazzapanic.models.FryingStation;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class GrillStationActor extends CookingStationActor<FryingStation>{
    // TODO: custom images
    private final Texture emptyGrillTexture = new Texture("emptyGrill.png"); // station is empty

    private final Texture bunGrillTexture = new Texture("bunGrill.png"); // station has bun on
    private final Texture bunGrillReadyTexture = new Texture("bunGrillReady.png"); // bun is ready to be flipped
    private final Texture bunGrillFlipedTexture = new Texture("bunGrillFliped.png"); // bun is fliped
    private final Texture bunGrillDoneTexture = new Texture("bunGrillDone.png"); // bun is ready to be taken off

    private final Texture burgerGrillTexture = new Texture("burgerGrill.png"); // station has burger on
    private final Texture burgerGrillReadyTexture = new Texture("burgerGrillReady.png"); // burger is ready to be flipped
    private final Texture burgerGrillFlipedTexture = new Texture("burgerGrillFliped.png"); // burger is fliped
    private final Texture burgerGrillDoneTexture = new Texture("burgerGrillDone.png"); // burger is ready to be taken off
    
    public GrillStationActor() {
        super(new FryingStation());
        setTexture(new TextureRegion(emptyGrillTexture));
    }
    

}
