package com.asimov.piazzapanic;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import com.badlogic.gdx.graphics.g2d.BitmapFont;






public class SettingsScreen extends ScreenAdapter{
    final PiazzaPanic game;
    private Stage stage;
    private Table table;

    private  static Integer senscore;
    private static Label senlabel;
    private static Integer muscore;
    private static Label mulabel;

    public static Sound buclick2;

    private static Integer effectscore;
    private static Label effectlabel;

    private Texture setback = new Texture("layout/newppp.png");
    Sprite sprite = new Sprite(setback);

    SoundEffectControl soundEffectControl = new SoundEffectControl();
    MusicControl musicControl = new MusicControl();
    OrthographicCamera camera;



    public SettingsScreen(final PiazzaPanic game) {
        this.game = game;

        sprite.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        stage = new Stage(new ScreenViewport(), game.batch);
        Gdx.input.setInputProcessor(stage);

        table = new Table();
        table.setFillParent(true);
        stage.addActor(table);

        table.setDebug(game.debug);

        Skin holoSkin = new Skin(Gdx.files.internal("skin/Holo-light-hdpi.json"));

        buclick2 = Gdx.audio.newSound(Gdx.files.internal("audio/Back-and-quit.wav"));

        Table settingsTable = new Table();

        // THE HEADING
        Label label1 = new Label("Settings", game.skin);
        label1.setFontScale(3.0F);
        settingsTable.add(label1);

        settingsTable.row();

        // FOR PERMANENT STUFF
        muscore = 0;
        effectscore = 0;
        Preferences prefs = Gdx.app.getPreferences("myprefs");

        // MUSIC
        Label Mlabel = new Label("Music", game.skin);
        Mlabel.setFontScale(2.0F);
        settingsTable.add(Mlabel);

        settingsTable.row();

        // VOLUME UP
        TextButton volumeup = new TextButton("UP", holoSkin);
        volumeup.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event,float x, float y){
                if(muscore < 5){
                    muscore += 1;
                    musicControl.increaseMusic();
                    System.out.print("rat increase "+musicControl.musicvolume);
                }

                mulabel.setText(String.format("%01d", muscore));
                prefs.putInteger("muscore", muscore);
            }
        });

        // VOLUME DOWN
        TextButton volumedown = new TextButton("Down", holoSkin);
        volumedown.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event,float x, float y){
                if(muscore > 0){
                    muscore -= 1;
                    musicControl.decreaseMusic();
                    System.out.print("rat decrease "+musicControl.musicvolume);
                }
                mulabel.setText(String.format("%01d", muscore));
                prefs.putInteger("muscore", muscore);
            }
        });

        // MUSIC INTEGER
        muscore = prefs.getInteger("muscore");
        mulabel =new Label(String.format("%01d", muscore), game.skin);
        mulabel.setFontScale(2.0F);

        settingsTable.add(volumedown, mulabel, volumeup);

        settingsTable.row();

        // SOUND EFFECTS
        Label SElabel = new Label("Sound Effects", game.skin);
        settingsTable.add(SElabel);

        settingsTable.row();

        // SOUND EFFECT UP
        TextButton effectup = new TextButton("UP",holoSkin);
        effectup.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event,float x, float y){
                if(effectscore < 5){
                    effectscore += 1;
                    soundEffectControl.increaseSoundeffect();
                    System.out.print("this is the volume"+soundEffectControl.volume);
                }

                effectlabel.setText(String.format("%01d", effectscore));
                prefs.putInteger("effectscore", effectscore);
            }
        });

        // SOUND EFFECT DOWN
        TextButton effectdown = new TextButton("Down", holoSkin);

        effectdown.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event,float x, float y){
                if(effectscore>0){
                    effectscore -= 1;
                    soundEffectControl.decreaseSoundeffect();
                    System.out.print("VOL" + soundEffectControl.volume);
                }

                effectlabel.setText(String.format("%01d", effectscore));
                prefs.putInteger("effectscore", effectscore);
            }
        });

        // SOUND EFFECT INTEGER
        effectscore = prefs.getInteger("effectscore");
        effectlabel =new Label(String.format("%01d", effectscore), game.skin);
        effectlabel.setFontScale(2.0F);

        settingsTable.add(effectdown, effectlabel, effectup);

        settingsTable.row();

        TextButton backButton = new TextButton("Back", game.skin);
        backButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                SoundEffectControl.buclicking2();
                game.setScreen(new MainMenuScreen(game));
            }
        });

        table.add(settingsTable).expand().center();

        table.row();

        table.add(backButton).expandX().left().bottom();

        table.pad(10);
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(game.color);
        stage.act(delta);
        stage.draw();
    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}