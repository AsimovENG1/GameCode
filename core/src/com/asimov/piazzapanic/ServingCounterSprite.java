package com.asimov.piazzapanic;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class ServingCounterSprite extends Sprite {
    public ServingCounterSprite(float x) {
        super(new Texture("layout/counter.png"));
        setX(x);
    }
}
