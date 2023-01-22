package com.asimov.piazzapanic;

import com.asimov.piazzapanic.models.*;
import com.asimov.piazzapanic.models.Chef;
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

    public boolean canPlace(IngredientStack stack) {
        return model.canPlace(stack);
    }

    public void place(IngredientStack stack) {
        model.place(stack);

        placed();
    }

    protected void placed() {}

    public boolean canGrab() {
        return model.canGrab();
    }

    public void grab(IngredientStack stack) {
        model.grab(stack);

        grabbed();
    }

    protected void grabbed() {}
}
