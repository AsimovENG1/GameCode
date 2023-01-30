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
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class EndingScreen extends ScreenAdapter {
    final PiazzaPanic game;
    private Stage stage;
    private Table table;

    private Sound sound;

    private Label label;
    private Label label2;

    private String winningTime;

    public EndingScreen(PiazzaPanic game) {
        this.game = game;

        stage = new Stage(new ScreenViewport(), game.batch);
        Gdx.input.setInputProcessor(stage);

        table = new Table();
        table.setFillParent(true);
        stage.addActor(table);

        sound = Gdx.audio.newSound(Gdx.files.internal("audio/Button-click.wav"));

        label = new Label("Well Done! You Won!", game.skin);
        label.setFontScale(4f, 4f);
        table.add(label).padBottom(10);

        table.row();

        winningTime = String.format("Your time was %f seconds", ScenarioMode.time);
        label2 = new Label(winningTime, game.skin);
        label2.setFontScale(4f, 4f);
        table.add(label2).padBottom(10);

        table.row();

        TextButton menuButton = new TextButton("Main Menu", game.skin);
        menuButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                sound.play(1.0f);
                game.setScreen(new MainMenuScreen(game));
            }
        });
        table.add(menuButton);

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