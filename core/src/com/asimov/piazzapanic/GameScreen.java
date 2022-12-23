package com.asimov.piazzapanic;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class GameScreen extends ScreenAdapter {
    final PiazzaPanic game;

    private Stage stage;
    SpriteBatch batch;
    OrthographicCamera camera;

    FitViewport viewport;
    TextButton scenarioMode;
    TextButton endlessMode;

    public GameScreen(final PiazzaPanic game) {
        this.game = game;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 600);
        stage = new Stage(new ScreenViewport(), game.batch);
        final Sound sound = Gdx.audio.newSound(Gdx.files.internal("audio/Button-click.wav"));
        final Sound sound2 = Gdx.audio.newSound(Gdx.files.internal("audio/Back-and-quit.wav"));
        stage = new Stage(new ScreenViewport(), game.batch);

        Skin mySkin = new Skin(Gdx.files.internal("skin/glassy-ui.json"));
        TextButton scenarioMode = new TextButton("Scenario Mode", mySkin);
        TextButton endlessMode = new TextButton("Endless Mode", mySkin);

        scenarioMode.setWidth(700);
        scenarioMode.setHeight(500);
        endlessMode.setWidth(700);
        endlessMode.setHeight(500);

        scenarioMode.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                sound.play(1.0f);
                game.setScreen(new Chef(game));
            }
        });
        endlessMode.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                sound.play(1.0f);
                game.setScreen(new EndlessMode(game));
            }
        });

        TextButton backButton = new TextButton("Back", mySkin);

        backButton.setWidth(200);
        backButton.setHeight(100);

        backButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                sound2.play(1.0f);
                game.setScreen(new MainMenuScreen(game));
            }
        });

        stage.addActor(backButton);

        backButton.setPosition((float) ((Gdx.graphics.getWidth() * 0.1) - 100), (float) ((Gdx.graphics.getHeight() * 0.1) - 50));

        stage.addActor(scenarioMode);
        stage.addActor(endlessMode);

        scenarioMode.setPosition((Gdx.graphics.getWidth() * 0.25f) - 350, (Gdx.graphics.getHeight() * 0.5f) - 250);
        endlessMode.setPosition((Gdx.graphics.getWidth() * 0.75f) - 350, (Gdx.graphics.getHeight() * 0.5f) - 250);

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void resize(int width, int height) {
        camera.setToOrtho(false, width, height);
        Gdx.graphics.setWindowedMode(width,height);
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
