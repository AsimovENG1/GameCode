package com.asimov.piazzapanic;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Value;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;

public class MainMenuScreen extends ScreenAdapter {
    final PiazzaPanic game;
    private Stage stage;
    private Table table;
    
    public static Sound buclick1;
    public static Sound backquit;

    public MainMenuScreen(final PiazzaPanic game) {
        this.game = game;

        stage = new Stage(new StretchViewport(1280, 720), game.batch);
        Gdx.input.setInputProcessor(stage);
  
        table = new Table();
        table.setFillParent(true);
        stage.addActor(table);
        
        table.setDebug(game.debug);
        
        buclick1 = Gdx.audio.newSound(Gdx.files.internal("audio/Button-click.wav"));
        backquit = Gdx.audio.newSound(Gdx.files.internal("audio/Back-and-quit.wav"));

        Table menuTable = new Table();

        TextButton gameButton = new TextButton("Start Game", game.skin);
        gameButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                SoundEffectControl.buttonclicking1();
                game.setScreen(new GameScreen(game));
            }
        });
        menuTable.add(gameButton).padBottom(25).width(500);

        menuTable.row();

        TextButton settingsButton = new TextButton("Settings", game.skin);
        settingsButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                SoundEffectControl.buttonclicking1();
                game.setScreen(new SettingsScreen(game));
            }
        });
        menuTable.add(settingsButton).padBottom(25).width(500);

        menuTable.row();

        TextButton instructionsButton = new TextButton("Instructions", game.skin);
        instructionsButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                SoundEffectControl.buttonclicking1();
                game.setScreen(new InstructionScreen(game));
            }
        });
        menuTable.add(instructionsButton).padBottom(25).width(500);

        menuTable.row();

        TextButton quitButton =  new TextButton("Quit", game.skin);
        quitButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                SoundEffectControl.buttonquit();
                Gdx.app.exit();
            }
        });
        menuTable.add(quitButton).padBottom(10).width(500);

        table.add(menuTable).expand().center();

        table.row();

        Label attributionLabel = new Label("Comic UI Scene2d skin by Raymond \"Raeleus\" Buckley", game.skin);
        table.add(attributionLabel).padBottom(5);

        table.row();

        Label ccLabel = new Label("CC by 4.0", game.skin);
        table.add(ccLabel);

        table.pad(10);
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(game.color);
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}
