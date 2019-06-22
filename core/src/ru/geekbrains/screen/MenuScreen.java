package ru.geekbrains.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.geekbrains.base.BaseScreen;
import ru.geekbrains.math.Rect;
import ru.geekbrains.sprite.Background;
import ru.geekbrains.sprite.Logotype;

public class MenuScreen extends BaseScreen {

    private Texture img;
    private Texture bg;
    private Background background;
    private Vector2 pos;
    private Logotype logotype;

    @Override
    public void show() {
        super.show();
        img = new Texture("badlogic.jpg");
        bg = new Texture("bg.png");
        pos = new Vector2();
        background = new Background(new TextureRegion(bg));
        logotype = new Logotype(new TextureRegion(img));
    }

    @Override
    public void resize(Rect worldBounds) {
        super.resize(worldBounds);
        background.resize(worldBounds);
        logotype.resize(worldBounds);
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        update(delta);
        draw();
    }

    public void update(float delta) {
        logotype.update(delta);
    }

    public void draw() {
        Gdx.gl.glClearColor(0.2f, 0.5f, 0.5f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        background.draw(batch);
        logotype.draw(batch);
        batch.end();
    }

    @Override
    public void dispose() {
        img.dispose();
        bg.dispose();
        super.dispose();
    }

    @Override
    public boolean touchDown(Vector2 touch, int pointer) {
        logotype.touchDown(touch, pointer);
        return false;
    }

/*
    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        touch.set(screenX, Gdx.graphics.getHeight() - screenY);
        v.set(touch.cpy().sub(pos)).setLength(V_LEN);  // посчитали вектор, который указывает
//              из вектора положения в точку куда мы кликнули мышкой и уменьшили его с
//              с помощью метода setLength для показа плавного движения
        touchDown(touch, pointer);
        return false;
    }
*/

/*
    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        pos.set(screenWidth >= screenX + imgWidth ?
                        screenX : screenWidth - imgWidth,
                screenHeight >= screenHeight - screenY + imgHeight ?
                        screenHeight - screenY : screenHeight - imgHeight);
        System.out.println("touchDown touch.x = " + pos.x + " touch.y = " + pos.y);
        return false;
    }
/*
    @Override
    public boolean keyDown(int keycode) {
        System.out.println("keyDown keycode = " + keycode);
        switch (keycode) {
            case 19:      // вверх
                if (screenHeight >= pos.y + imgHeight)
                    v.add(0, 1);
                break;
            case 22:      // вправо
                if (screenWidth >= pos.x + imgWidth)
                v.add(1, 0);
                break;
            case 20:      // вниз
                if (pos.y >= 1)
                v.add(0, -1);
                break;
            case 21:      // влево
                if (pos.x >= 1)
                v.add(-1, 0);
                break;
        }
        System.out.println("keyDown key.x = " + pos.x + " key.y = " + pos.y);
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        if (pos.x < 0) {
            pos.set(0, pos.y);
        } else if (pos.y < 0) {
            pos.set(pos.x, 0);
        } else if (pos.x > screenWidth - imgWidth) {
            pos.set(screenWidth - imgWidth, pos.y);
        } else if (pos.y > screenHeight - imgHeight) {
            pos.set(pos.x, screenHeight - imgHeight);
        }
        v.setZero();
        System.out.println("keyUp keycode = " + keycode + " pos.x = " + pos.x + " pos.y = " + pos.y);
        return false;
    }
*/
}
