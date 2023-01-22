package com.asimov.piazzapanic;

import com.asimov.piazzapanic.models.*;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;

import java.util.ArrayList;
import java.util.Objects;

public class Chef extends Sprite {

    private Texture rightTexture;
    private Texture leftTexture;

    private float speed = 100;

    private boolean isActive = false;

    public IngredientStack stack = new IngredientStack();

    public Chef(Texture rightTexture, Texture leftTexture) {
        super(rightTexture);

        this.rightTexture = rightTexture;
        this.leftTexture = leftTexture;
    }

    public void controlChef(float delta, float maxWidth, float maxHeight) {

        if (!isActive) {
            return;
        }

        float x = getX();
        float y = getY();
        float distance = delta * speed;

        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            y += distance;
        }

        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            y -= distance;
        }

        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            x -= distance;
        }

        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            x += distance;
        }

        if (x > getX()) {
            setTexture(rightTexture);
        }
        else if (x < getX()) {
            setTexture(leftTexture);
        }

        setX(Math.max(0, Math.min(maxWidth - getWidth(), x)));
        setY(Math.max(0, Math.min(maxHeight - getHeight(), y)));
    }

    public void checkBurgerItems() {
        Patty patty = null;
        Bun bun = null;

        for (Ingredient ingredient : stack) {
            if (patty == null && ingredient instanceof Patty && ((Patty) ingredient).isFried()) {
                patty = (Patty) ingredient;
            }

            if (bun == null && ingredient instanceof Bun && ((Bun) ingredient).isFried()) {
                bun = (Bun) ingredient;
            }
        }

        if (patty != null && bun != null) {
            stack.items.removeAll(new Array<>(new Ingredient[] { patty, bun}), true);
            stack.grab(new Burger());
        }
    }

    public void checkSaladItems() {
        Tomato tomato = null;
        Lettuce lettuce = null;
        Onion onion = null;

        for (Ingredient ingredient : stack) {
            if (tomato == null && ingredient instanceof Tomato && ((Tomato) ingredient).isChopped()) {
                tomato = (Tomato) ingredient;
            }

            if (lettuce == null && ingredient instanceof Lettuce && ((Lettuce) ingredient).isChopped()) {
                lettuce = (Lettuce) ingredient;
            }

            if (onion == null && ingredient instanceof Onion && ((Onion) ingredient).isChopped()) {
                onion = (Onion) ingredient;
            }
        }

        if (tomato != null && lettuce != null && onion != null) {
            stack.items.removeAll(new Array<>(new Ingredient[] { tomato, lettuce, onion }), true);
            stack.grab(new Salad());
        }
    }

    public void setActive(boolean active) {
        isActive = active;
    }
    public boolean getActive() {
        return isActive;
    }
}
