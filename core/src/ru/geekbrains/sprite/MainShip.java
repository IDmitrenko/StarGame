package ru.geekbrains.sprite;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;

import ru.geekbrains.base.Sprite;
import ru.geekbrains.math.Rect;

public class MainShip extends Sprite {

    private static final int INVALID_POINTER = -1;

    private static final float MAIN_SHIP_HEIGHT = 0.1f;
    private static final float BOTTOM_MARGIN = 0.02f;

    private boolean pressedLeft = false;

    private boolean pressedRight = false;

    private Vector2 vTouch = new Vector2();
    private Vector2 vSpeed = new Vector2(0.5f, 0f);

    private int leftPointer = INVALID_POINTER;
    private int rightPointer = INVALID_POINTER;

    private Rect worldBounds;

    public MainShip(TextureAtlas atlas) {
        super(atlas.findRegion("main_ship"),1,2,2);
        setHeightProportion(MAIN_SHIP_HEIGHT);
    }

    @Override
    public void update(float delta) {
        pos.mulAdd(vTouch, delta);
        if (getRight() > worldBounds.getRight()) {
            setRight(worldBounds.getRight());
            stop();
        }
        if (getLeft() < worldBounds.getLeft()) {
            setLeft(worldBounds.getLeft());
            stop();
        }
    }

    public void resize(Rect worldBounds) {
        this.worldBounds = worldBounds;
        setBottom(worldBounds.getBottom() + BOTTOM_MARGIN);
    }

    public boolean keyDown(int keycode) {
        switch (keycode) {
            case Input.Keys.A:
            case Input.Keys.LEFT:
                pressedLeft = true;
                moveLeft();
                break;
            case Input.Keys.D:
            case Input.Keys.RIGHT:
                pressedRight = true;
                moveRight();
                break;
            case Input.Keys.UP:
                frame = 1;
                break;
        }
        return false;
    }

    public boolean keyUp(int keycode) {
        switch (keycode) {
            case Input.Keys.A:
            case Input.Keys.LEFT:
                pressedLeft = false;
                if (pressedRight) {
                    moveRight();
                }
                break;
            case Input.Keys.D:
            case Input.Keys.RIGHT:
                pressedRight = false;
                if (pressedLeft) {
                    moveLeft();
                }
                break;
            case Input.Keys.UP:
                frame = 0;
                break;
        }
        if (!pressedLeft && !pressedRight) {
            stop();
        }
        return false;
    }

    @Override
    public boolean touchDown(Vector2 vTouch, int pointer) {
        if (vTouch.x < worldBounds.pos.x) {
            if (leftPointer != INVALID_POINTER) {
                return false;
            }
            leftPointer = pointer;
            moveLeft();
        } else {
            if (rightPointer != INVALID_POINTER) {
                return false;
            }
            rightPointer = pointer;
            moveRight();
        }
        return false;
    }

    @Override
    public boolean touchUp(Vector2 vTouch, int pointer) {
        if (pointer == leftPointer) {
            leftPointer = INVALID_POINTER;
            if (rightPointer != INVALID_POINTER) {
                moveRight();
            } else {
                stop();
            }
        } else if (pointer == rightPointer) {
            rightPointer = INVALID_POINTER;
            if (leftPointer != INVALID_POINTER) {
                moveLeft();
            } else {
                stop();
            }
        }
        return false;
    }

    private void moveRight() {
        vTouch.set(vSpeed);
    }

    private void moveLeft() {
        vTouch.set(vSpeed).rotate(180);
    }

    private void stop() {
        vTouch.setZero();
    }
}
