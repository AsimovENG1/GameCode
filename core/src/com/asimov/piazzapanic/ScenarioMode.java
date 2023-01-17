package com.asimov.piazzapanic;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ScenarioMode extends InputAdapter implements Screen {
    Stage stage;
    SpriteBatch batch;
    Texture background;
    final PiazzaPanic game;
    OrthographicCamera camera;

    private List<String> choices = new ArrayList<>();
    private List<Integer> customerNumbers = new ArrayList<>();

    float customerx = 0;
    float customery = 500;

    boolean end = false;
    boolean atCounter = false;
    boolean givenOrder = false;
    boolean entering = true;
    Integer customers = 5;
    Integer customerNo;
    boolean begin = true;

    private Sound bell;
    private Sound win;
    Customer customer;
    Chef chef;
    ShapeRenderer shape;
    public ScenarioMode(final PiazzaPanic game) {
        this.game = game;

        choices.add("Burger");
        choices.add("Salad");
        bell = Gdx.audio.newSound(Gdx.files.internal("audio/bell-123742.mp3"));
        win = Gdx.audio.newSound(Gdx.files.internal("audio/level-win-6416.mp3"));
        customerNumbers.add(1);
        customerNumbers.add(2);

        stage = new Stage(new ScreenViewport(), game.batch);

        batch = new SpriteBatch();

        ChoppingStationActor choppingStation = new ChoppingStationActor();
        stage.addActor(choppingStation);
        choppingStation.setPosition(0, 0);

        drawBackButton();

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.input.setInputProcessor(stage);

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 600);

        chef = new Chef(game);
        customer = new Customer(game);
        shape = new ShapeRenderer();
    }

    public void drawBackground() {
        batch.draw(background,0,0);
    }

    public void drawBackButton(){
        Sound sound3 = Gdx.audio.newSound(Gdx.files.internal("audio/mixkit-losing-marimba-2025.wav"));
        Skin mySkin = new Skin(Gdx.files.internal("skin/glassy-ui.json"));

        TextButton.TextButtonStyle style = new TextButton.TextButtonStyle();
        style.font = game.font;

        TextButton backButton  = new TextButton("Quit", mySkin,"small");

        backButton.setWidth(100);
        backButton.setHeight(50);

        backButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                sound3.play(1.0f);
                game.setScreen(new Quitting(game));
            }
        });

        stage.addActor(backButton);

        backButton.setPosition((float) ((Gdx.graphics.getWidth() * 0.05) -50), (float) ((Gdx.graphics.getHeight() * 0.9) - 25));
        Gdx.input.setInputProcessor(stage);
    }

    public void drawChefStack(){

        Skin mySkin = new Skin(Gdx.files.internal("skin/glassy-ui.json"));
        Label label = new Label("Chef 1", mySkin);
        label.setWidth(50);
        label.setHeight(10);
        label.setFontScale(2f, 2f);
        Label label2 = new Label("Chef 2", mySkin);
        label2.setWidth(50);
        label2.setHeight(10);
        label2.setFontScale(2f, 2f);
        stage.addActor(label);
        stage.addActor(label2);
        label.setPosition(1625, 320);
        label2.setPosition(1820, 320);

        shape.begin(ShapeRenderer.ShapeType.Filled);
        shape.setColor(Color.WHITE);
        shape.rect(1600,0,150,300);
        shape.rect(1800,0,150,300);
        shape.end();
        shape.begin(ShapeRenderer.ShapeType.Line);
        shape.setColor(Color.BLUE);
        shape.rect(1600,0,150,300);
        shape.line(1600,100, 1750,100);
        shape.line(1600,200, 1750,200);
        shape.setColor(Color.ORANGE);
        shape.rect(1800,0,150,300);
        shape.line(1800,100, 1950,100);
        shape.line(1800,200, 1950,200);
        shape.end();
    }

    @Override
    public void show() {
        //Temporary Background
        background = new Texture("newBackground.png");
    }

    // 1, 2 and 3 controls each chef (change between chefs)
    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();

        drawBackground();
        drawBackButton();

        if (begin) {
            bell.play(1.0f);
            chef.show();
            customer.show();
            customerNo = customer.randomCustomer();
            begin = false;
        }

        chef.drawChefs(batch);

        chef.controlChef();
        chef.changeChef();

        String left = customer.controlCustomer(atCounter, givenOrder, entering, customerNo, batch);
        if (left == "left") {
            givenOrder = false;
            entering = true;
            customerx = 0;
            customery = 500;
            customers -= 1;
            begin = true;
        }
        if (left == "entering") {
            atCounter = false;
            givenOrder = false;
            entering = true;
        }
        if (left == "leaving") {
            givenOrder = true;
            atCounter = false;
            entering = false;
        }
        if (left == "at counter") {
            atCounter = true;
            givenOrder = false;
        }

        batch.end();

        drawChefStack();

        stage.act(delta);
        stage.draw();

        if (customers == 0) {
            end = true;
        }
        if (end) {
           win.play(1.0f);
           game.setScreen(new EndingScreen(game));
        }
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
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
        stage.dispose();
        batch.dispose();
    }
}
