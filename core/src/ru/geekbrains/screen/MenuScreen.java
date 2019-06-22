package ru.geekbrains.screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.geekbrains.base.BaseScreen;
import ru.geekbrains.math.Rect;
import ru.geekbrains.sprite.Background;
import ru.geekbrains.sprite.ButtonExit;
import ru.geekbrains.sprite.ButtonPlay;
import ru.geekbrains.sprite.Star;

public class MenuScreen extends BaseScreen {

    private static final int STAR_COUNT = 256;

    private Game game;

    private Texture bg;
    private Background background;
    private TextureAtlas atlas;

    private ButtonExit buttonExit;
    private ButtonPlay buttonPlay;

    private Star[] stars = new Star[STAR_COUNT];

    public MenuScreen(Game game) {
        this.game = game;
    }

    @Override
    public void show() {
        super.show();
        bg = new Texture("textures/bg.png");
        background = new Background(new TextureRegion(bg));
        atlas = new TextureAtlas("textures/menuAtlas.tpack");
        buttonExit = new ButtonExit(atlas);
        buttonPlay = new ButtonPlay(atlas, game);
        for (int i = 0; i < stars.length; i++) {
            stars[i] = new Star(atlas);
        }
    }

    @Override
    public void resize(Rect worldBounds) {
        super.resize(worldBounds);
        background.resize(worldBounds);
        buttonExit.resize(worldBounds);
        buttonPlay.resize(worldBounds);
        for (int i = 0; i < stars.length; i++) {
            stars[i].resize(worldBounds);
        }
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        update(delta);
        draw();
    }

    public void update(float delta) {
        for (int i = 0; i < stars.length; i++) {
            stars[i].update(delta);
        }
    }

    public void draw() {
        batch.begin();
        background.draw(batch);
        for (int i = 0; i < stars.length; i++) {
            stars[i].draw(batch);
        }
        buttonExit.draw(batch);
        buttonPlay.draw(batch);
        batch.end();
    }

    @Override
    public void dispose() {
        bg.dispose();
        atlas.dispose();
        super.dispose();
    }

    @Override
    public boolean touchDown(Vector2 touch, int pointer) {
        buttonExit.touchDown(touch, pointer);
        buttonPlay.touchDown(touch, pointer);
        return false;
    }

    public boolean touchUp(Vector2 touch, int pointer) {
        buttonExit.touchUp(touch, pointer);
        buttonPlay.touchUp(touch, pointer);
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
