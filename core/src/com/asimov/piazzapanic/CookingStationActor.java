package com.asimov.piazzapanic;

import com.asimov.piazzapanic.models.Chef;
import com.asimov.piazzapanic.models.CookingStation;
import com.asimov.piazzapanic.models.CookingStatus;
import com.asimov.piazzapanic.models.Ingredient;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;

import java.util.Arrays;

public abstract class CookingStationActor<T extends CookingStation> extends Actor {
    private TextureRegion texture;

    protected T model;

    public CookingStationActor(T model) {
        this.model = model;
    }

    protected void setTexture(TextureRegion texture) {
        this.texture = texture;

        setBounds(texture.getRegionX(), texture.getRegionY(), texture.getRegionWidth(), texture.getRegionHeight());
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        Color color = getColor();

        batch.setColor(color.r, color.g, color.b, color.a * parentAlpha);

        batch.draw(texture, getX(), getY(), getOriginX(), getOriginY(), getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation());
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

    public void placed() {}

    public boolean canGrab() {
        return model.canGrab();
    }

    public void grab(Chef chef) throws Exception {
        model.grab(chef);

        grabbed();
    }

    public void grabbed() {}
}
