package com.asimov.piazzapanic;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class MainMenuScreen extends ScreenAdapter {
    final PiazzaPanic game;

    private Stage stage;

    public MainMenuScreen(final PiazzaPanic game) {
        this.game = game;

        stage = new Stage(new ScreenViewport(), game.batch);

        TextButtonStyle style = new TextButtonStyle();
        style.font = game.font;

        TextButton gameButton = new TextButton("Start Game", style);
        TextButton settingsButton = new TextButton("Settings", style);
        TextButton instructionsButton = new TextButton("Instructions", style);

        gameButton.setWidth(200);
        gameButton.setHeight(50);
        settingsButton.setWidth(200);
        settingsButton.setHeight(50);
        instructionsButton.setWidth(200);
        instructionsButton.setHeight(50);


        gameButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new GameScreen(game));
            }
        });
        settingsButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new SettingsScreen(game));
            }
        });
        instructionsButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new InstructionScreen(game));
            }
        });


        stage.addActor(gameButton);
        stage.addActor(settingsButton);
        stage.addActor(instructionsButton);

        gameButton.setPosition(100, 0);
        settingsButton.setPosition(100, 50);
        instructionsButton.setPosition(100, 100);

        Gdx.input.setInputProcessor(stage);
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
