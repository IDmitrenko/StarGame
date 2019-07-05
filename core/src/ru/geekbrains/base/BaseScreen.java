package ru.geekbrains.base;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Matrix3;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;

import ru.geekbrains.math.MatrixUtils;
import ru.geekbrains.math.Rect;

public abstract class BaseScreen implements Screen, InputProcessor {

    protected SpriteBatch batch;
    protected Game game;

    private Rect screenBounds;   // границы экрана в пикселях
    protected Rect worldBounds;    // границы проэкции мировых координат
    private Rect glBounds;       // gl-левские координаты

    private Matrix4 worldToGl;           // матрица преобразований из мировых координат к gl
    private Matrix3 screenToWorld;       // матрица преобразований из экранных координат к мировым

    private Vector2 touch;

    public BaseScreen(Game game) {
        this.game = game;
    }

    @Override
    public void show() {
        System.out.println("show");
        this.batch = new SpriteBatch();
        Gdx.input.setInputProcessor(this);
        screenBounds = new Rect();
        worldBounds = new Rect();
        glBounds = new Rect(0, 0, 1f, 1f);  // инициализация GL-координат
        worldToGl = new Matrix4();
        screenToWorld = new Matrix3();
        touch = new Vector2();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.2f, 0.5f, 0.5f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }

    @Override
    public void resize(int width, int height) {
        System.out.println("resize width = " + width + " height = " + height);
        screenBounds.setSize(width, height);     // установка размера прямоугольница в экранных координатах
        screenBounds.setLeft(0);                 // установка крайней левой точки
        screenBounds.setBottom(0);               // установка крайней нижней точки

        float aspect = width / (float) height;   // вычисление соотношения сторон
        // установка границ в мировых координатах
        worldBounds.setHeight(1f);        // фиксация по высоте (оси Y)
        worldBounds.setWidth(1f*aspect);  // установка ширины с соблюдением пропорций
        // преобразование из системы мировых координат(3D) в систему координат GL(4D)
        MatrixUtils.calcTransitionMatrix(worldToGl, worldBounds, glBounds);
        batch.setProjectionMatrix(worldToGl);   // установка матрицы преобразований для "батчера"
        // преобразование из системы экранных(2D) координат в систему мировых координат(3D)
        MatrixUtils.calcTransitionMatrix(screenToWorld, screenBounds, worldBounds);
        resize(worldBounds);
    }

    // перегруженный вспомогательный метод, выводящий информацию о текущих параметрах
    // прямоугольника в мировой системе координат
    public void resize(Rect worldBounds) {
        System.out.println("worldBounds width = " + worldBounds.getWidth() +
                " height = " + worldBounds.getHeight());
    }

    @Override
    public void pause() {
        System.out.println("pause");
    }

    @Override
    public void resume() {
        System.out.println("resume");
    }

    @Override
    public void hide() {
        System.out.println("hide");
        dispose();
    }

    @Override
    public void dispose() {
        System.out.println("dispose");
        batch.dispose();
    }

    @Override
    public boolean keyDown(int keycode) {
        System.out.println("keyDown keycode = " + keycode);
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        System.out.println("keyUp keycode = " + keycode);
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        System.out.println("keyTyped character = " + character);
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        System.out.println("touchDown screenX = " + screenX + " screenY = " + screenY);
        // преобразование координат нажатия тача из пиксельной СК в мировую СК
        touch.set(screenX, Gdx.graphics.getHeight() - screenY).mul(screenToWorld);
        touchDown(touch, pointer);
        return false;
    }

    // перегруженный вспомогательный метод, выводящий информацию о текущих координатах
    // нажатия тача в мировой системе координат
    public boolean touchDown(Vector2 touch, int pointer) {
        System.out.println("touchDown touchX = " + touch.x + " touchY = " + touch.y);
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        System.out.println("touchUp screenX = " + screenX + " screenY = " + screenY);
        // преобразование координат отпускания тача из пиксельной СК в мировую СК
        touch.set(screenX, Gdx.graphics.getHeight() - screenY).mul(screenToWorld);
        touchUp(touch, pointer);
        return false;
    }

    // перегруженный вспомогательный метод, выводящий информацию о текущих координатах
    // отпускания тача в мировой системе координат
    public boolean touchUp(Vector2 touch, int pointer) {
        System.out.println("touchUp touchX = " + touch.x + " touchY = " + touch.y);
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        System.out.println("touchDragged screenX = " + screenX + " screenY = " + screenY);
        // преобразование координат перемещения с нажатым тачем из пиксельной СК в мировую СК
        touch.set(screenX, Gdx.graphics.getHeight() - screenY).mul(screenToWorld);
        touchDragged(touch, pointer);
        return false;
    }

    // перегруженный вспомогательный метод, выводящий информацию о текущих координатах
    // при перемещении с нажатым тачем в мировой системе координат
    public boolean touchDragged(Vector2 touch, int pointer) {
        System.out.println("touchDragged touchX = " + touch.x + " touchY = " + touch.y);
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        System.out.println("scrolled amount = " + amount);
        return false;
    }

}
