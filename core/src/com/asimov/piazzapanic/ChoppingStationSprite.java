package com.asimov.piazzapanic;

import com.asimov.piazzapanic.models.ChoppingStation;
import com.badlogic.gdx.graphics.Texture;

public class ChoppingStationSprite extends BaseCookingStationSprite<ChoppingStation> {
    // TODO: custom images
    private final Texture availableTexture = new Texture("EmptyCuttingBoard.png"); // station is empty
    private final Texture cookingTexture = new Texture("CuttingBoardInUse.png"); // ingredient placed on station
    private final Texture completeTexture = new Texture("ChoppingBoardCut.png"); // ingredient has been chopped

    public ChoppingStationSprite() {
        super(new ChoppingStation());
        setTexture(availableTexture);
    }

    @Override
    public void placed() {
        setTexture(cookingTexture);
    }

    @Override
    public void grabbed() {
        setTexture(availableTexture);
    }

    public boolean canChop() {
        return model.canChop();
    }

    public void chop() {
        if (!canChop()) {
            return;
        }

        // TODO: timer, stop chef from moving?

        model.chop();

        setTexture(completeTexture);
    }
}
