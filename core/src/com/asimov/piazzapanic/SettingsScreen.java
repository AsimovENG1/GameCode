package com.asimov.piazzapanic;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import sun.jvm.hotspot.debugger.cdbg.basic.LazyBlockSym;


import javax.swing.JScrollBar;

import javax.swing.*;

public class SettingsScreen extends ScreenAdapter{
    final PiazzaPanic game;

    private Stage stage;

    private  static Integer senscore;
    private static Label senlabel;
    private static Integer muscore;
    private static Label mulabel;

    private static Integer effectscore;
    private static Label effectlabel;

    OrthographicCamera camera;

    public SettingsScreen(final PiazzaPanic game) {
        this.game = game;

        stage = new Stage(new ScreenViewport(), game.batch);

        Skin mySkin1 = new Skin(Gdx.files.internal("skin/Holo-light-hdpi.json"));


        Skin mySkin = new Skin(Gdx.files.internal("skin/glassy-ui.json"));
        final Sound sound2 = Gdx.audio.newSound(Gdx.files.internal("audio/Back-and-quit.wav"));
        //TextButton.TextButtonStyle style = new TextButton.TextButtonStyle();
        //style.font = game.font;

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

        backButton.setPosition(100, 100);

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.input.setInputProcessor(stage);

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 600);

        // Style of the text of the heading
        Label.LabelStyle style = new Label.LabelStyle();
        style.font = game.font;
        style.fontColor = Color.BLUE;

        // THE HEADING
        Label label1 = new Label("Settings", style);
        label1.setSize((float)Gdx.graphics.getWidth(), 200.0F);
        label1.setFontScale(3.0F);
        label1.setPosition(0, 850.0F);
        label1.setAlignment(Align.center);
        this.stage.addActor(label1);

        // Style of the text of the mouse sensitivity
        Label.LabelStyle style1 = new Label.LabelStyle();
        style1.font = game.font;
        style1.fontColor = Color.WHITE;

        // MOUSE SENSITIVITY
        Label label2 = new Label("Mouse Sensitivity", style1);
        label2.setSize((float)Gdx.graphics.getWidth(), 200.0F);
        label2.setFontScale(2.0F);
        label2.setPosition(0 ,700.0F);
        label2.setAlignment(Align.center);
        this.stage.addActor(label2);

        // MOUSE SENSITIVITY UP
        TextButton sensitivityButtonup = new TextButton("UP",mySkin1);
        sensitivityButtonup.setWidth(100);
        sensitivityButtonup.setHeight(100);
        sensitivityButtonup.setPosition(850.0F, 650.0F);
        this.stage.addActor(sensitivityButtonup);

        sensitivityButtonup.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event,float x, float y){
                sound2.play(1.0f);
                senscore += 1;
                senlabel.setText(String.format("%01d", senscore));
            }
        });

        // MOUSE SENSITIVITY DOWN
        TextButton sensitivityButtondown = new TextButton("Down", mySkin1);
        sensitivityButtondown.setWidth(100);
        sensitivityButtondown.setHeight(100);
        sensitivityButtondown.setPosition(1080.0F, 650.0F);
        this.stage.addActor(sensitivityButtondown);

        sensitivityButtondown.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event,float x, float y){
                sound2.play(1.0f);
                senscore -= 1;
                senlabel.setText(String.format("%01d", senscore));
            }
        });

        // SENSITIVITY INTEGER
        senscore = 0;
        senlabel =new Label(String.format("%01d", senscore), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        senlabel.setFontScale(2.0F);
        senlabel.setPosition(1000.0F, 700.0F);
        senlabel.setAlignment(Align.center);
        this.stage.addActor(senlabel);

        // MUSIC
        Label Mlabel = new Label("Music", style1);
        Mlabel.setSize((float)Gdx.graphics.getWidth(), 200.0F);
        Mlabel.setFontScale(2.0F);
        Mlabel.setPosition(0 ,500.0F);
        Mlabel.setAlignment(Align.center);
        this.stage.addActor(Mlabel);

        // MUSIC INTEGER
        muscore = 0;
        mulabel =new Label(String.format("%01d", muscore), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        mulabel.setFontScale(2.0F);
        mulabel.setPosition(1000.0F, 500.0F);
        mulabel.setAlignment(Align.center);
        this.stage.addActor(mulabel);

        // VOLUME UP
        TextButton volumeup = new TextButton("UP",mySkin1);
        volumeup.setWidth(100);
        volumeup.setHeight(100);
        volumeup.setPosition(850.0F, 450.0F);
        this.stage.addActor(volumeup);

        volumeup.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event,float x, float y){
                sound2.play(1.0f);
                muscore += 1;
                mulabel.setText(String.format("%01d", muscore));
            }
        });

        // VOLUME DOWN
        TextButton volumedown = new TextButton("Down", mySkin1);
        volumedown.setWidth(100);
        volumedown.setHeight(100);
        volumedown.setPosition(1080.0F, 450.0F);
        this.stage.addActor(volumedown);

        volumedown.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event,float x, float y){
                sound2.play(1.0f);
                muscore -= 1;
                mulabel.setText(String.format("%01d", muscore));
            }
        });

        // SOUND EFFECTS
        Label SElabel = new Label("Sound Effects", style1);
        SElabel.setSize((float)Gdx.graphics.getWidth(), 200.0F);
        SElabel.setFontScale(2.0F);
        SElabel.setPosition(0 ,300.0F);
        SElabel.setAlignment(Align.center);
        this.stage.addActor(SElabel);

        // SOUND EFFECT UP
        TextButton effectup = new TextButton("UP",mySkin1);
        effectup.setWidth(100);
        effectup.setHeight(100);
        effectup.setPosition(850.0F, 250.0F);
        this.stage.addActor(effectup);

        effectup.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event,float x, float y){
                sound2.play(1.0f);
                effectscore += 1;
                effectlabel.setText(String.format("%01d", effectscore));
            }
        });

        // SOUND EFFECT INTEGER
        effectscore = 0;
        effectlabel =new Label(String.format("%01d", effectscore), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        effectlabel.setFontScale(2.0F);
        effectlabel.setPosition(1000.0F, 300.0F);
        effectlabel.setAlignment(Align.center);
        this.stage.addActor(effectlabel);

        // SOUND EFFECT DOWN
        TextButton effectdown = new TextButton("Down", mySkin1);
        effectdown.setWidth(100);
        effectdown.setHeight(100);
        effectdown.setPosition(1080.0F, 250.0F);
        this.stage.addActor(effectdown);

        effectdown.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event,float x, float y){
                sound2.play(1.0f);
                effectscore -= 1;
                effectlabel.setText(String.format("%01d", effectscore));
            }
        });



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