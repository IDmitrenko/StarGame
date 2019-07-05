package ru.geekbrains.sprite;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import ru.geekbrains.base.ActionListener;
import ru.geekbrains.base.ScaledTouchUpButton;

public class ButtonNewGame extends ScaledTouchUpButton {


    public ButtonNewGame(TextureAtlas atlas, ActionListener actionListener) {
        super(atlas.findRegion("button_new_game"), actionListener);
        setHeightProportion(0.07f);
        setTop(-0.4f);
    }

}
