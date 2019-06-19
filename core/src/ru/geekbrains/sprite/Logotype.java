package ru.geekbrains.sprite;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

import ru.geekbrains.base.Sprite;
import ru.geekbrains.math.Rect;

public class Logotype extends Sprite {

/*
    public Logotype(TextureRegion region, float height) {
        super(region);
        setHeightProportion(height);
    }
*/

/*
        public void resize(Rect worldBounds, float offsetX, float offsetY) {
        float posX = 0;
        float posY = 0;
        if (offsetX < 0) {
            posX = worldBounds.getLeft() - offsetX;
        } else if (offsetX > 0) {
            posX = worldBounds.getRight() - offsetX;
        }
        if (offsetY < 0) {
            posY = worldBounds.getBottom() - offsetY;
        } else if (offsetY > 0) {
            posY = worldBounds.getTop() - offsetY;
        }
        pos.set(posX, posY);
    }
*/
        public Logotype(TextureRegion region) {
            super(region);
        }

        @Override
        public void resize(Rect worldBounds) {
            setHeightProportion(worldBounds.getHeight());
            pos.set(worldBounds.pos);
        }
}
