package com.asimov.piazzapanic;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class MainMenuScreen extends ScreenAdapter {
    final PiazzaPanic game;

    private Stage stage;

    public MainMenuScreen(final PiazzaPanic game) {
        this.game = game;

        stage = new Stage(new ScreenViewport(), game.batch);

        Skin mySkin = new Skin(Gdx.files.internal("skin/glassy-ui.json"));

        TextButton gameButton = new TextButton("Start Game", mySkin);
        TextButton settingsButton = new TextButton("Settings", mySkin);
        TextButton instructionsButton = new TextButton("Instructions", mySkin);

        gameButton.setWidth(1000);
        gameButton.setHeight(200);
        settingsButton.setWidth(1000);
        settingsButton.setHeight(200);
        instructionsButton.setWidth(1000);
        instructionsButton.setHeight(200);


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

        gameButton.setPosition(500, 700);
        settingsButton.setPosition(500, 100);
        instructionsButton.setPosition(500, 400);

        //gameButton.getLabel().setFontScale(5, 5);

        Gdx.gl.glClearColor(0.49f, 0.96f, 0.94f, 1);
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
