package com.asimov.piazzapanic;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class Chef extends InputAdapter implements Screen {
    Stage stage;
    SpriteBatch batch;
    Texture chef1Right;
    Texture chef1Left;
    Texture chef2Left;
    Texture chef2Right;
    Texture chef3Right;
    Texture chef3Left;

    float Speed = 100.0f;
    float chef1x = 200;
    float chef1y = 200;

    float chef2x = 250;
    float chef2y = 250;

    float chef3x = 300;
    float chef3y = 300;
    String direction1 = "Right"; //Starting direction of chef1
    String direction2 = "Right"; //Starting direction of chef2
    String direction3 = "Right"; //Starting direction of chef3
    final PiazzaPanic game;
    OrthographicCamera camera;

    public Chef(final PiazzaPanic game) {
        this.game = game;

        stage = new Stage(new ScreenViewport(), game.batch);


        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.input.setInputProcessor(stage);

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 600);
    }
    @Override
    public void show() {
        //Blue Chef 1
        chef1Right = new Texture("Chef1.png");
        chef1Left = new Texture("Chef1 Left.png");
        //Orange Chef 2
        chef2Right = new Texture("Chef2.png");
        chef2Left = new Texture("Chef2 Left.png");
        //Green Chef 3
        chef3Right = new Texture("Chef3.png");
        chef3Left = new Texture("Chef3 Left.png");
        
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        batch = new SpriteBatch();
    }


    // 1, 2 and 3 controls each chef (change between chefs)
    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0,0,0,0);
        ScreenUtils.clear(0,0,0,0);
        batch.begin();
        stage.draw();

        if (direction1 == "Right") { batch.draw(chef1Right, chef1x, chef1y);}
        else if (direction1 == "Left") {batch.draw(chef1Left, chef1x, chef1y);}

        if (Gdx.input.isKeyPressed(Input.Keys.W)){
            chef1y += Gdx.graphics.getDeltaTime()*Speed;
        }

        if (Gdx.input.isKeyPressed(Input.Keys.S)){
            chef1y -= Gdx.graphics.getDeltaTime()*Speed;
        }

        if (Gdx.input.isKeyPressed(Input.Keys.A)){
            chef1x -= Gdx.graphics.getDeltaTime()*Speed;
            direction1 = "Left";
        }

        if (Gdx.input.isKeyPressed(Input.Keys.D)){
            chef1x += Gdx.graphics.getDeltaTime()*Speed;
            direction1 = "Right";
        }

        final Sound sound2 = Gdx.audio.newSound(Gdx.files.internal("audio/Back-and-quit.wav"));
        Skin mySkin = new Skin(Gdx.files.internal("skin/glassy-ui.json"));

        TextButton.TextButtonStyle style = new TextButton.TextButtonStyle();
        style.font = game.font;

        TextButton backButton = new TextButton("Back", mySkin);

        backButton.setWidth(200);
        backButton.setHeight(100);

        backButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                sound2.play(1.0f);
                game.setScreen(new GameScreen(game));
            }
        });

        stage.addActor(backButton);

        backButton.setPosition(100, 100);

        batch.end();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
