package com.asimov.piazzapanic;

import com.asimov.piazzapanic.models.*;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

import java.util.HashMap;
import java.util.Map;

public class ChefStackSlotActor extends Actor {
    private Texture backgroundTexture;

    private Ingredient ingredient;

    public ChefStackSlotActor(Texture background) {
        backgroundTexture = background;

        setBounds(getX(), getY(), 80, 65);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        Color color = getColor();

        batch.setColor(color.r, color.g, color.b, color.a * parentAlpha);

        batch.draw(backgroundTexture, getX(), getY(), getWidth(), getHeight());

        if (ingredient == null) {
            return;
        }

        batch.draw(new Texture(getIngredientTexture()), getX(), getY(), getWidth(), getHeight());
    }

    public void setIngredient(Ingredient ingredient) {
        this.ingredient = ingredient;
    }

    private String getIngredientTexture() {

        String basePath = "Food/";

        if (ingredient instanceof Lettuce) {
            return basePath + (((Lettuce) ingredient).isChopped() ? "ChoppedLettuce.png" : "Lettuce.png");
        }

        if (ingredient instanceof Onion) {
            return basePath + (((Onion) ingredient).isChopped() ? "ChoppedOnion.png" : "Onion.png");
        }

        if (ingredient instanceof Tomato) {
            return basePath + (((Tomato) ingredient).isChopped() ? "ChoppedTomato.png" : "Tomato.png");
        }

        if (ingredient instanceof Patty) {
            Patty patty = (Patty) ingredient;

            if (patty.isFried()) {
                return basePath + "CookedPatty.png";
            }

            if (patty.isFormed()) {
                return basePath + "FormedPatty.png";
            }

            return basePath + "RawPatty.png";
        }

        if (ingredient instanceof Bun) {
            return basePath + (((Bun) ingredient).isFried() ?  "FriedBun.png" : "RawBun.png");
        }


        if (ingredient instanceof Burger) {
            return basePath + "Burger.png";
        }

        if (ingredient instanceof Salad) {
            return basePath + "Salad.png";
        }

        return null;
    }
}
