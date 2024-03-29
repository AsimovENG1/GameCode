package com.asimov.piazzapanic;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;


public class PiazzaPanic extends Game {
	public SpriteBatch batch;
	public BitmapFont font;

	public Skin skin;

	public Color color = new Color(1, 0.85f, 0.52f, 1);

	public Sound clickSound;
	public Sound backSound;
	ShapeRenderer shapeRenderer;

	public boolean debug = false;

	@Override
	public void create () {
		batch = new SpriteBatch();
		font = new BitmapFont(); // TODO: default font, can be change

		clickSound = Gdx.audio.newSound(Gdx.files.internal("audio/Button-click.wav"));
		backSound = Gdx.audio.newSound(Gdx.files.internal("audio/Back-and-quit.wav"));

		skin = new Skin(Gdx.files.internal("skin/comic/comic-ui.json"));

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
