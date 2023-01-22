package com.asimov.piazzapanic;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;
import java.util.Objects;

public class Chef extends Sprite {

    private Texture rightTexture;
    private Texture leftTexture;

    private float speed = 100;

    private boolean isActive = false;

    private Direction direction;

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

    public Boolean checkBurgerItems(ArrayList<String> chefstack) {
        if (chefstack.contains("Cooked Patty") &&
                chefstack.contains("Fried Buns")) {
            return true;
        }
        else {
            return false;
        }
    }

    public Boolean checkSaladItems(ArrayList<String> chefstack) {
        if (chefstack.contains("Chopped Tomatoes") &&
                chefstack.contains("Chopped Lettuce")&&
                chefstack.contains("Chopped Onions")) {
            return true;
        }
        else {
            return false;
        }
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public enum Direction {
        right,
        left
    }
}
