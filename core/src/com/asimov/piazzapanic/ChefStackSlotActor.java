package com.asimov.piazzapanic;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

import java.util.HashMap;
import java.util.Map;

public class ChefStackSlotActor extends Actor {
    private TextureRegion backgroundTexture;

    private Map<String, Texture> itemCorresponding = new HashMap<>();

    private String ingredient;

    public ChefStackSlotActor() {
        backgroundTexture = new TextureRegion(
                new Texture("StackItem.png")
        );

        itemCorresponding.put("Burger", new Texture("Food/Burger.png"));
        itemCorresponding.put("Chopped Lettuce", new Texture("Food/ChoppedLettuce.png"));
        itemCorresponding.put("Chopped Onions", new Texture("Food/ChoppedOnion.png"));
        itemCorresponding.put("Chopped Tomatoes", new Texture("Food/ChoppedTomato.png"));
        itemCorresponding.put("Cooked Patty", new Texture("Food/CookedPatty.png"));
        itemCorresponding.put("Formed Patty", new Texture("Food/FormedPatty.png"));
        itemCorresponding.put("Fried Bun", new Texture("Food/FriedBun.png"));
        itemCorresponding.put("Lettuce", new Texture("Food/Lettuce.png"));
        itemCorresponding.put("Onion", new Texture("Food/Onion.png"));
        itemCorresponding.put("Raw Bun", new Texture("Food/RawBun.png"));
        itemCorresponding.put("Raw Patty", new Texture("Food/RawPatty.png"));
        itemCorresponding.put("Salad", new Texture("Food/Salad.png"));
        itemCorresponding.put("Tomato", new Texture("Food/Tomato.png"));

        setBounds(backgroundTexture.getRegionX(), backgroundTexture.getRegionY(),
                backgroundTexture.getRegionWidth(), backgroundTexture.getRegionHeight());
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        Color color = getColor();

        batch.setColor(color.r, color.g, color.b, color.a * parentAlpha);

        batch.draw(new TextureRegion(backgroundTexture), getX(), getY(), getOriginX(), getOriginY(),
                getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation());

        batch.draw(getIngredientTexture(), getX(), getY(), getOriginX(), getOriginY(),
                getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation());
    }

    public void setIngredient(String ingredient) {
        this.ingredient = ingredient;
    }

    private TextureRegion getIngredientTexture() {
        return new TextureRegion(itemCorresponding.get(ingredient));
    }
}
