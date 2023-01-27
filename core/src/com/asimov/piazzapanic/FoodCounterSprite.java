package com.asimov.piazzapanic;

import com.asimov.piazzapanic.models.*;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class FoodCounterSprite extends Sprite {
    private final Texture emptyTexture = new Texture("FoodCounter/Empty.png");
    private final Texture bunTexture = new Texture("FoodCounter/Bun.png");
    private final Texture friedBunTexture = new Texture("FoodCounter/BunFried.png");
    private final Texture lettuceTexture = new Texture("FoodCounter/Lettuce.png");
    private final Texture choppedLettuceTexture = new Texture("FoodCounter/LettuceChopped.png");
    private final Texture onionTexture = new Texture("FoodCounter/Onion.png");
    private final Texture choppedOnionTexture = new Texture("FoodCounter/OnionChopped.png");
    private final Texture pattyTexture = new Texture("FoodCounter/Patty.png");
    private final Texture formedPattyTexture = new Texture("FoodCounter/PattyFormed.png");
    private final Texture friedPattyTexture = new Texture("FoodCounter/PattyFried.png");
    private final Texture tomatoTexture = new Texture("FoodCounter/Tomato.png");
    private final Texture choppedTomatoTexture = new Texture("FoodCounter/TomatoChopped.png");

    public FoodCounterSprite() {
        super(new Texture("FoodCounter/Empty.png"));
    }

    protected FoodCounter model = new FoodCounter();

    public boolean canPlace(Chef chef) {
        return model.canPlace(chef.stack);
    }

    public void place(Chef chef) {
        model.place(chef.stack);

        Ingredient ingredient = model.getIngredient();

        if (ingredient instanceof Lettuce) {
            setTexture(((Lettuce) ingredient).isChopped() ? choppedLettuceTexture : lettuceTexture);
        }

        if (ingredient instanceof Onion) {
            setTexture(((Onion) ingredient).isChopped() ? choppedOnionTexture : onionTexture);
        }

        if (ingredient instanceof Tomato) {
            setTexture(((Tomato) ingredient).isChopped() ? choppedTomatoTexture : tomatoTexture);
        }

        if (ingredient instanceof Patty) {

            Patty patty = (Patty) ingredient;

            if (patty.isFried()) {
                setTexture(friedPattyTexture);
            }
            else if (patty.isFormed()) {
                setTexture(formedPattyTexture);
            }
            else {
                setTexture(pattyTexture);
            }
        }

        if (ingredient instanceof Bun) {
            setTexture(((Bun) ingredient).isFried() ? friedBunTexture : bunTexture);
        }
    }

    public boolean canGrab() {
        return model.canGrab();
    }

    public void grab(Chef chef) {
        model.grab(chef.stack);

        setTexture(emptyTexture);
    }
}
