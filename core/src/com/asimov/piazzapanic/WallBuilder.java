package com.asimov.piazzapanic;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.utils.Array;

public class WallBuilder {
    private static final Texture segmentTexture = new Texture("layout/wall-segment.png");
    private static final Texture endTexture = new Texture("layout/wall-end.png");
    private static final Texture cornerTexture = new Texture("layout/wall-corner.png");

    private final Array<Sprite> sprites = new Array<>();

    private int x;
    private int y;
    private float defaultRotation;

    public WallBuilder(int x, int y, float defaultRotation) {
        this.x = x;
        this.y = y;
        this.defaultRotation = defaultRotation;
    }

    public WallBuilder withSegment(int xChange, int yChange, float rotation, int count) {
        addWall(WallType.segment, xChange, yChange, rotation, count);

        return this;
    }

    public WallBuilder withCorner(int xChange, int yChange, float rotation, int count) {
        addWall(WallType.corner, xChange, yChange, rotation, count);

        return this;
    }

    public WallBuilder withEnd(int xChange, int yChange, float rotation, int count) {
        addWall(WallType.end, xChange, yChange, rotation, count);

        return this;
    }

    public Array<Sprite> build() {
        return sprites;
    }

    private void addWall(WallType type, int xChange, int yChange, float rotation, int count) {
        for (int i = 0; i < count; i++) {
            x += xChange * 40;
            y += yChange * 40;

            Sprite sprite = new Sprite(getTexture(type));
            sprite.setPosition(x, y);
            sprite.rotate(defaultRotation + rotation);

            sprites.add(sprite);
        }
    }

    private Texture getTexture(WallType type) {
        switch (type) {
            case segment:
                return segmentTexture;
            case end:
                return endTexture;
            case corner:
                return cornerTexture;
        }

        return null;
    }

    public enum WallType {
        segment,
        end,
        corner
    }
}
