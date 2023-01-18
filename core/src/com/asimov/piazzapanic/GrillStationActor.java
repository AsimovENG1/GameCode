package com.asimov.piazzapanic;

import com.asimov.piazzapanic.models.FryingStation;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class GrillStationActor extends CookingStationActor<FryingStation>{
    private final Texture emptyGrillTexture = new Texture("emptyGrill.png"); // station is empty

    private final Texture GrillTexture = new Texture("burgerGrill.png"); // station has burger on
    private final Texture GrillReadyTexture = new Texture("burgerGrillReady.png"); // burger is ready to be flipped
    private final Texture GrillFlipedTexture = new Texture("burgerGrillFliped.png"); // burger is fliped
    private final Texture GrillDoneTexture = new Texture("burgerGrillDone.png"); // burger is ready to be taken off
    
    public GrillStationActor() {
        super(new FryingStation());
        setTexture(new TextureRegion(emptyGrillTexture));
    }
    @Override
    public void placed() {
        setTexture(new TextureRegion(GrillTexture));
        model.place();
        setTexture(new TextureRegion(GrillReadyTexture));
    }

    public void flip() {
        setTexture(new TextureRegion(GrillFlipedTexture));
        model.flip();
        setTexture(new TextureRegion(GrillDoneTexture));
    }
}
