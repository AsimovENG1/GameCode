package com.asimov.piazzapanic;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class SettingsScreen extends ScreenAdapter{
    final PiazzaPanic game;

    private Stage stage;

    OrthographicCamera camera;

    public SettingsScreen(final PiazzaPanic game) {
        this.game = game;

        stage = new Stage(new ScreenViewport(), game.batch);

        Skin mySkin = new Skin(Gdx.files.internal("skin/glassy-ui.json"));

        TextButton.TextButtonStyle style = new TextButton.TextButtonStyle();
        style.font = game.font;

        TextButton backButton = new TextButton("Back", mySkin);

        backButton.setWidth(200);
        backButton.setHeight(100);


        backButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new MainMenuScreen(game));
            }
        });

        stage.addActor(backButton);

        backButton.setPosition(100, 100);

        Gdx.gl.glClearColor(0.49f, 0.96f, 0.94f, 1);
        Gdx.input.setInputProcessor(stage);

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 600);
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