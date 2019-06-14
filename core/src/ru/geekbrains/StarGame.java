package ru.geekbrains;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class StarGame extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
	TextureRegion region;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("Space.png");
//		region = new TextureRegion(img, 50, -400 , 512, 1024);
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0.01f, 0.1f, 0.2f, 0.4f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(img, 0, 0,  512, 1024);
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();

	}
}
