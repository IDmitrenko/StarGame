package ru.geekbrains.sprite;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import ru.geekbrains.base.ActionListener;
import ru.geekbrains.base.ScaledTouchUpButton;
import ru.geekbrains.math.Rect;

public class ButtonPlay extends ScaledTouchUpButton {

    public ButtonPlay(TextureAtlas atlas, ActionListener actionListener) {
        super(atlas.findRegion("btPlay"), actionListener);
    }

    @Override
    public void resize(Rect worldBounds) {
        setHeightProportion(0.25f);
        setLeft(worldBounds.getLeft() + 0.03f);
        setBottom(worldBounds.getBottom() + 0.03f);
    }

}