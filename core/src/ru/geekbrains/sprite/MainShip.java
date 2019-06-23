package ru.geekbrains.sprite;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;

import ru.geekbrains.base.BaseShip;
import ru.geekbrains.math.Rect;

public class MainShip extends BaseShip {

    private static final float MAIN_SHIP_HEIGHT = 0.2f;

    private int shipSpeed = 1;

    private boolean pressedLeft = false;

    private boolean pressedRight = false;

    private Vector2 vTouch = new Vector2();
    private Vector2 vBuf = new Vector2();

    public MainShip(TextureAtlas atlas) {
        super(atlas.findRegion("main_ship"));
        setHeightProportion(MAIN_SHIP_HEIGHT);
    }

    @Override
    public void update(float delta) {
        super.update(delta);
        if (worldBounds.getLeft() > getLeft()) {
           if (Math.abs(worldBounds.getLeft() - getLeft()) > 0.0001f) {
               vSpeed.setZero();
               setLeft(worldBounds.getLeft());
               return;
           }
        }
        if (worldBounds.getRight() < getRight()) {
            if (Math.abs(worldBounds.getRight() - getRight()) > 0.0001f) {
                vSpeed.setZero();
                setRight(worldBounds.getRight());
//                System.out.println("worldBounds=" + worldBounds.getRight() + " getRight=" + getRight());
                return;
            }
        }
        vBuf.set(vTouch);
        vSpeed.setLength(delta * shipSpeed);
        if (vBuf.sub(pos).len() > vSpeed.len()) {
            pos.add(vSpeed);
        } else {
            pos.set(vTouch);
        }
        setBottom(worldBounds.getBottom() + 0.03f);
    }

    public void resize(Rect worldBounds) {
        super.resize(worldBounds);
        setBottom(worldBounds.getBottom() + 0.03f);
    }

    public boolean keyDown(int keycode) {
        if (worldBounds == null) return false;
        switch (keycode) {
            case 21:    // влево
                pressedLeft = true;
                pressedRight = false;
                vTouch.x = worldBounds.getLeft();
                break;
            case 22:    // вправо
                pressedRight = true;
                pressedLeft = false;
                vTouch.x = worldBounds.getRight();
                break;
        }
        vSpeed.set(vTouch).sub(pos);
        return false;
    }

    public boolean keyUp(int keycode) {
        switch (keycode) {
            case 21:  // влево
                if (pressedLeft)
                    vTouch.x = pos.x;
                pressedLeft = false;
                vSpeed.set(vTouch).sub(pos);
                break;
            case 22:  // вправо
                if (pressedRight)
                    vTouch.x = pos.x;
                pressedRight = false;
                vSpeed.set(vTouch).sub(pos);
                break;
        }
        return false;
    }

}
