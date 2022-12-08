package com.asimov.piazzapanic;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;


public class PiazzaPanic extends Game {
	public SpriteBatch batch;
	public BitmapFont font;
	ShapeRenderer shapeRenderer;


	@Override
	public void create () {
		batch = new SpriteBatch();
		font = new BitmapFont(); // TODO: default font, can be change
		shapeRenderer = new ShapeRenderer();
		this.setScreen(new MainMenuScreen(this));
	}

	@Override
	public void render () {
		super.render();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		font.dispose();
	}

}
