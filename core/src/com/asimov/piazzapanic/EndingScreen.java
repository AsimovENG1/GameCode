package com.asimov.piazzapanic;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class EndingScreen extends ScreenAdapter {
    final PiazzaPanic game;

    private Stage stage;

    public Sound sound;

    public EndingScreen(PiazzaPanic game) {
        this.game = game;

        stage = new Stage(new ScreenViewport(), game.batch);

        final Sound sound = Gdx.audio.newSound(Gdx.files.internal("audio/Button-click.wav"));

        Skin mySkin = new Skin(Gdx.files.internal("skin/glassy-ui.json"));

        TextButton menuButton = new TextButton("Main Menu", mySkin);

        Label label = new Label("Well Done! You Won!", mySkin);

        label.setWidth(1000);
        label.setHeight(200);
        label.setFontScale(4f, 4f);

        menuButton.setWidth(1000);
        menuButton.setHeight(200);

        menuButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                sound.play(1.0f);
                game.setScreen(new MainMenuScreen(game));
            }
        });

        stage.addActor(menuButton);
        stage.addActor(label);

        label.setPosition(650, 700);
        menuButton.setPosition(500, 300);

        Gdx.gl.glClearColor(0, 0, 0, 1);
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