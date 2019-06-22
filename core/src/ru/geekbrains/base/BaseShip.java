package ru.geekbrains.base;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

import ru.geekbrains.math.Rect;

public class BaseShip extends Sprite {

    protected Rect worldBounds;

    public BaseShip(TextureRegion region) {
        super(region);
    }

    @Override
    public void resize(Rect worldBounds) {
        this.worldBounds = worldBounds;
    }
}
