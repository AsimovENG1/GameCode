package com.asimov.piazzapanic;

import com.asimov.piazzapanic.models.Chef;
import com.asimov.piazzapanic.models.IngredientStation;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

public abstract class IngredientStationActor<T extends IngredientStation> extends IngredientStationSprite {
//    private TextureRegion texture;

    protected T model;

    public IngredientStationActor() {this.model = model;}

//    protected void setTexture(TextureRegion texture) {
//        this.texture = texture;
//
//        setBounds(texture.getRegionX(), texture.getRegionY(), texture.getRegionWidth(), texture.getRegionHeight());
//    }
//
//    @Override
//    public void draw(Batch batch, float parentAlpha) {
//        Color color = getColor();
//
//        batch.setColor(color.r, color.g, color.b, color.a * parentAlpha);
//
//        batch.draw(texture, getX(), getY(), getOriginX(), getOriginY(), getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation());
//    }

    public void grab(Chef chef){
        model.grab(chef);
        grabbed();
    }

    public void grabbed(){}
}
