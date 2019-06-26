package ru.geekbrains.pool;

import ru.geekbrains.base.SpritesPool;
import ru.geekbrains.sprite.Bullet;

public class BulletPool extends SpritesPool<Bullet> {
// создания пули для пула объектов
    @Override
    protected Bullet newObject() {
        return new Bullet();
    }
}