package ru.geekbrains.sprite;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import ru.geekbrains.base.BaseShip;
import ru.geekbrains.math.Rect;

public class MainShip extends BaseShip {

    private static final float MAIN_SHIP_HEIGHT = 0.25f;

    public MainShip(TextureAtlas atlas) {
        super(atlas.findRegion("main_ship"));
        setHeightProportion(MAIN_SHIP_HEIGHT);
    }

    @Override
    public void update(float delta) {
        super.update(delta);
    }

    public void resize(Rect worldBounds) {
        super.resize(worldBounds);
        setBottom(worldBounds.getBottom() + 0.03f);
    }

}
