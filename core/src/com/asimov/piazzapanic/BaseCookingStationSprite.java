package com.asimov.piazzapanic;

import com.asimov.piazzapanic.models.Chef;
import com.asimov.piazzapanic.models.CookingStation;
import com.asimov.piazzapanic.models.CookingStatus;
import com.asimov.piazzapanic.models.Ingredient;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public abstract class BaseCookingStationSprite<T extends CookingStation> extends CookingStationSprite {
    protected T model;

    public BaseCookingStationSprite(T model) {
        this.model = model;
    }

    public CookingStatus getStatus() {
        return model.getStatus();
    }

    public Ingredient getIngredient() {
        return model.getIngredient();
    }

    public boolean canPlace(Chef chef) {
        return model.canPlace(chef);
    }

    public void place(Chef chef) throws Exception {
        model.place(chef);

        placed();
    }

    protected void placed() {}

    public boolean canGrab() {
        return model.canGrab();
    }

    public void grab(Chef chef) throws Exception {
        model.grab(chef);

        grabbed();
    }

    protected void grabbed() {}
}
