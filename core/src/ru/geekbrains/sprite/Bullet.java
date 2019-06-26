package ru.geekbrains.sprite;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.geekbrains.base.Sprite;
import ru.geekbrains.math.Rect;

public class Bullet extends Sprite {  // пуля

    private Rect worldBounds;
    private Vector2 v = new Vector2();  // скорость пули
    private int damage;                 // урон нанесенный пулей
    private Object owner;               // владелец пули

    public Bullet() {
        regions = new TextureRegion[1];  // инициализация массива регионов
    }

    public void set(        // настройка пули
            Object owner,               // владелец
            TextureRegion region,       // регион текстуры
            Vector2 pos0,               // позиция
            Vector2 v0,                 // начальная скорость
            float height,               // размер пули
            Rect worldBounds,           // границы мира
            int damage                  // урон, который пуля может нанести
    ) {
        this.owner = owner;
        this.regions[0] = region;
        this.pos.set(pos0);
        this.v.set(v0);
        setHeightProportion(height);
        this.worldBounds = worldBounds;
        this.damage = damage;
    }

    @Override
    public void update(float delta) {
        pos.mulAdd(v, delta);
        if (isOutside(worldBounds)) {
            destroy();
        }
    }

    public int getDamage() {
        return damage;
    }

    public Object getOwner() {
        return owner;
    }
}