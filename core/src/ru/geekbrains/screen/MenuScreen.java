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

    @Override
    public void show() {
        super.show();
        img = new Texture("badlogic.jpg");
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
        if ((Gdx.graphics.getHeight() > pos.y + img.getHeight() && pos.y >= 0) &&
        (Gdx.graphics.getWidth() > pos.x + img.getWidth() && pos.x >= 0)) {
            pos.add(v);
        } else {
            v.setZero();
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
        pos.set(Gdx.graphics.getWidth() > screenX + img.getWidth() ?
                        screenX : Gdx.graphics.getWidth() - img.getWidth(),
                Gdx.graphics.getHeight() > Gdx.graphics.getHeight() - screenY + img.getHeight() ?
                        Gdx.graphics.getHeight() - screenY : Gdx.graphics.getHeight() - img.getHeight());
//        touch = pos;
        System.out.println("touchDown touch.x = " + pos.x + " touch.y = " + pos.y);
        return false;
    }

    @Override
    public boolean keyDown(int keycode) {
        System.out.println("keyDown keycode = " + keycode);
        switch (keycode) {
            case 19:      // вверх
//                if (Gdx.graphics.getHeight() > pos.y + img.getHeight())
                    v.add(0, 1);
                break;
            case 22:
                v.add(1, 0);
                break;
            case 20:
                v.add(0, -1);
                break;
            case 21:
                v.add(-1, 0);
                break;
        }

        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        v.setZero();
        System.out.println("keyUp keycode = " + keycode);
        return false;
    }
}
