package com.asimov.piazzapanic;

import com.asimov.piazzapanic.models.ChoppingStation;
import com.badlogic.gdx.graphics.Texture;

/**
 * Sprite representing a chopping station.
 * @see ChoppingStation
 */
public class ChoppingStationSprite extends BaseCookingStationSprite<ChoppingStation> {
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

    /**
     * Chops the ingredient.
     * @see ChoppingStation#chop()
     */
    public void chop() {
        if (!canChop()) {
            return;
        }

        model.chop();

        setTexture(completeTexture);
    }
}
