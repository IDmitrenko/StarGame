package ru.geekbrains.sprite;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import ru.geekbrains.base.ScaledTouchUpButton;

public class NewGame extends ScaledTouchUpButton {

    public NewGame(TextureAtlas atlas) {
        super(atlas.findRegion("button_new_game"));
        setHeightProportion(0.05f);
        setTop(-0.01f);
    }

    @Override
    public void action() {

    }
}
