package ru.geekbrains.sprite;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import ru.geekbrains.base.ActionListener;
import ru.geekbrains.base.ScaledTouchUpButton;
import ru.geekbrains.math.Rect;

public class ButtonExit extends ScaledTouchUpButton {

    public ButtonExit(TextureAtlas atlas, ActionListener actionListener) {
        super(atlas.findRegion("btExit"), actionListener);
    }

    @Override
    public void resize(Rect worldBounds) {
        setHeightProportion(0.2f);
        setRight(worldBounds.getRight() - 0.03f);
        setBottom(worldBounds.getBottom() + 0.03f);
    }

}