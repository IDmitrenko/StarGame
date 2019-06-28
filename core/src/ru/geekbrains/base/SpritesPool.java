package ru.geekbrains.base;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;
import java.util.List;

public abstract class SpritesPool<T extends Sprite> { //работаем со всеми объектами, которые наследуются от Sprite

    protected final List<T> activeObjects = new ArrayList<T>();  // список активных объектов
    protected final List<T> freeObjects = new ArrayList<T>();    // список свободных объектов

    protected abstract T newObject();  //метод инициализации необходимого объекта

    public T obtain() {  //метод который достает нужный объект из списка свободных объектов или
                         // инициализирует его с помощью метода newObject
        T object;
        if (freeObjects.isEmpty()) {
            object = newObject();
        } else {
            object = freeObjects.remove(freeObjects.size() - 1); // берем последний объект из списка свободных объектов
        }
        activeObjects.add(object);   // помещаем его в список активных объектов
        System.out.println(getClass().getName() + " active/free " + activeObjects.size() + "/" + freeObjects.size());
        return object;
    }

    public void updateActiveSprites(float delta) {  // метод обновления отрисовки активных объектов
        for (int i = 0; i < activeObjects.size(); i++) {
            Sprite sprite = activeObjects.get(i);
            if (!sprite.isDestroyed()) {  // если спрайт не вышел из строя
                sprite.update(delta);
            }
        }
    }

    public void drawActiveSprites(SpriteBatch batch) {  // метод отрисовки активных объектов
        for (int i = 0; i < activeObjects.size(); i++) {
            Sprite sprite = activeObjects.get(i);
            if (!sprite.isDestroyed()) {
                sprite.draw(batch);
            }
        }
    }

    public void freeAllDestroyedActiveSprites() {  // освобождаем все задестроенные объекты
        for (int i = 0; i < activeObjects.size(); i++) {
            T sprite = activeObjects.get(i);
            if (sprite.isDestroyed()) {
                free(sprite);
                i--;
                sprite.flushDestroy();
            }
        }
    }

    public void free(T object) {  //метод освобождения объекта
        // если он вышел из строя, перемещаем его в список свободных объектов
        if (activeObjects.remove(object)) {
            freeObjects.add(object);
        }
        System.out.println(getClass().getName() + " active/free " + activeObjects.size() + "/" + freeObjects.size());
    }

    public List<T> getActiveObjects() { // список активных объектов
        return activeObjects;
    }

    public void dispose() {
        activeObjects.clear();
        freeObjects.clear();
    }
}