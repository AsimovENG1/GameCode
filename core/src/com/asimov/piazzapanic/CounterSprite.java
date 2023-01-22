package com.asimov.piazzapanic;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class CounterSprite extends Sprite {
    public CounterSprite(float x) {
        super(new Texture("layout/counter.png"));
        setX(x);
    }
}
