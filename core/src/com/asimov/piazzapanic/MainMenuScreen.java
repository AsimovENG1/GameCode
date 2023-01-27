package com.asimov.piazzapanic;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class MainMenuScreen extends ScreenAdapter {
    final PiazzaPanic game;
    private Stage stage;
    private Table table;
    
    public static Sound buclick1;
    public static Sound backquit;

    public MainMenuScreen(final PiazzaPanic game) {
        this.game = game;

        stage = new Stage(new ScreenViewport(), game.batch);
        Gdx.input.setInputProcessor(stage);
  
        table = new Table();
        table.setFillParent(true);
        stage.addActor(table);
        
        table.setDebug(game.debug);
        
        buclick1 = Gdx.audio.newSound(Gdx.files.internal("audio/Button-click.wav"));
        backquit = Gdx.audio.newSound(Gdx.files.internal("audio/Back-and-quit.wav"));

        TextButton gameButton = new TextButton("Start Game", game.skin);
        gameButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                SoundEffectControl.buttonclicking1();
                game.setScreen(new GameScreen(game));
            }
        });
        table.add(gameButton).padBottom(10);

        table.row();

        TextButton settingsButton = new TextButton("Settings", game.skin);
        settingsButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                SoundEffectControl.buttonclicking1();
                game.setScreen(new SettingsScreen(game));
            }
        });
        table.add(settingsButton).padBottom(10);

        table.row();

        TextButton instructionsButton = new TextButton("Instructions", game.skin);
        instructionsButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                SoundEffectControl.buttonclicking1();
                game.setScreen(new InstructionScreen(game));
            }
        });
        table.add(instructionsButton).padBottom(10);

        table.row();

        TextButton quitButton =  new TextButton("Quit", game.skin);
        quitButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                SoundEffectControl.buttonquit();
                Gdx.app.exit();
            }
        });
        table.add(quitButton).padBottom(10);

        Gdx.gl.glClearColor(0, 0, 0, 1);
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}
