package com.asimov.piazzapanic;

import com.asimov.piazzapanic.models.ChoppingStation;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class ChoppingStationActor extends CookingStationActor<ChoppingStation> {
    // TODO: custom images
    private final Texture availableTexture = new Texture("badlogic.jpg"); // station is empty
    private final Texture cookingTexture = new Texture("badlogic.jpg"); // ingredient placed on station
    private final Texture completeTexture = new Texture("badlogic.jpg"); // ingredient has been chopped

    public ChoppingStationActor() {
        super(new ChoppingStation());
        setTexture(new TextureRegion(availableTexture));
    }

    @Override
    public void placed() {
        setTexture(new TextureRegion(cookingTexture));
    }

    @Override
    public void grabbed() {
        setTexture(new TextureRegion(availableTexture));
    }

    public boolean canChop() {
        return model.canChop();
    }

    public void chop() throws Exception {
        if (!canChop()) {
            throw new Exception("Cannot chop here.");
        }

        // TODO: timer, stop chef from moving?

        model.chop();

        setTexture(new TextureRegion(completeTexture));
    }
}
