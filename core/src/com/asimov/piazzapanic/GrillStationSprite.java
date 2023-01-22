package com.asimov.piazzapanic;

import com.asimov.piazzapanic.models.FryingStation;
import com.badlogic.gdx.graphics.Texture;

public class GrillStationSprite extends BaseCookingStationSprite<FryingStation> {
    private final Texture emptyGrillTexture = new Texture("emptyGrill.png"); // station is empty

    private final Texture GrillTexture = new Texture("burgerGrill.png"); // station has burger on
    private final Texture GrillReadyTexture = new Texture("burgerGrillReady.png"); // burger is ready to be flipped
    private final Texture GrillFlipedTexture = new Texture("burgerGrillFliped.png"); // burger is fliped
    private final Texture GrillDoneTexture = new Texture("burgerGrillDone.png"); // burger is ready to be taken off
    
    public GrillStationSprite() {
        super(new FryingStation());
        setTexture(emptyGrillTexture);
    }
    @Override
    public void placed() {
        setTexture(GrillTexture);
        model.place();
        setTexture(GrillReadyTexture);
    }

    public void flip() {
        setTexture(GrillFlipedTexture);
        model.flip();
        setTexture(GrillDoneTexture);
    }
}
