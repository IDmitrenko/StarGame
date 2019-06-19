package ru.geekbrains.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

import ru.geekbrains.base.BaseScreen;

public class MenuScreen extends BaseScreen {

    private Texture img;
    private Texture background;

    //    private Vector2 touch;
    private Vector2 pos;
    private Vector2 v;
    private Integer screenWidth = Gdx.graphics.getWidth();
    private Integer screenHeight = Gdx.graphics.getHeight();
    private Integer imgWidth;
    private Integer imgHeight;

    @Override
    public void show() {
        super.show();
        img = new Texture("badlogic.jpg");
        imgWidth = img.getWidth();
        imgHeight = img.getHeight();
        background = new Texture("space3.png");
//        touch = new Vector2();
        pos = new Vector2();
        v = new Vector2(0, 0);
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        Gdx.gl.glClearColor(0.2f, 0.5f, 0.5f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.draw(background, 0, 0);
        batch.draw(img, pos.x, pos.y);
        batch.end();
        if ((screenHeight >= pos.y + imgHeight && pos.y >= 0) &&
        (screenWidth >= pos.x + imgWidth && pos.x >= 0)) {
            pos.add(v);
        }
    }

    @Override
    public void dispose() {
        img.dispose();
        background.dispose();
        super.dispose();
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        pos.set(screenWidth >= screenX + imgWidth ?
                        screenX : screenWidth - imgWidth,
                screenHeight >= screenHeight - screenY + imgHeight ?
                        screenHeight - screenY : screenHeight - imgHeight);
//        touch = pos;
        System.out.println("touchDown touch.x = " + pos.x + " touch.y = " + pos.y);
        return false;
    }

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
}
