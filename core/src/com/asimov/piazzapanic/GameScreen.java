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
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class GameScreen extends ScreenAdapter {
    final PiazzaPanic game;

    private Stage stage;
    
    private Table table;
    
    public static Sound gamebackb;
    public static Sound gamestart;

    public GameScreen(final PiazzaPanic game) {
        this.game = game;

        stage = new Stage(new ScreenViewport(), game.batch);
        Gdx.input.setInputProcessor(stage);

        table = new Table();
        table.setFillParent(true);
        stage.addActor(table);

        table.setDebug(game.debug);
        
        gamestart = Gdx.audio.newSound(Gdx.files.internal("audio/Button-click.wav"));
        gamebackb = Gdx.audio.newSound(Gdx.files.internal("audio/Back-and-quit.wav"));
        //final Sound bell = Gdx.audio.newSound(Gdx.files.internal("audio/mixkit-phone-ring-bell-593.wav"));

        Table modeTable = new Table();
        table.add(modeTable).expandY();

        TextButton scenarioMode = new TextButton("Scenario Mode", game.skin);
        scenarioMode.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                SoundEffectControl.scenariobutton();
                game.setScreen(new ScenarioMode(game));
            }
        });
        modeTable.add(scenarioMode).expandX().fill().padRight(5);

        TextButton endlessMode = new TextButton("Endless Mode", game.skin);
        endlessMode.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                SoundEffectControl.scenariobutton();
                game.setScreen(new EndlessMode(game));
            }
        });
        modeTable.add(endlessMode).expandX().fill().padLeft(5);

        table.row().bottom().left().expandX();

        TextButton backButton = new TextButton("Back", game.skin);
        backButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                SoundEffectControl.scenarioback();
                game.setScreen(new MainMenuScreen(game));
            }
        });
        table.add(backButton).left().bottom().padTop(10);

        table.pad(10);
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
