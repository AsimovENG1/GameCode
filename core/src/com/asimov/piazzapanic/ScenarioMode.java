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
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class ScenarioMode extends InputAdapter implements Screen {
    Stage stage;
    SpriteBatch batch;
    Texture background;
    Texture counter;
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

    Integer chef1slot1x = 1625;
    Integer chef1slot1y = 0;
    Integer chef1slot2x = 1625;
    Integer chef1slot2y = 100;
    Integer chef1slot3x = 1625;
    Integer chef1slot3y = 200;
    Integer chef2slot1x = 1825;
    Integer chef2slot1y = 0;
    Integer chef2slot2x = 1825;
    Integer chef2slot2y = 100;
    Integer chef2slot3x = 1825;
    Integer chef2slot3y = 200;

    // Add to the list when grabbing items etc ensure this is not at length 3 before add
    // Other classes can reference this due to it being public so access it from another class
    public ArrayList<String> chef1stack = new ArrayList<>();
    public ArrayList<String> chef2stack = new ArrayList<>();


    private Boolean chef1hasBurger;
    private Boolean chef2hasBurger;
    private Boolean chef1hasSalad;
    private Boolean chef2hasSalad;

    private Map<String, String> itemCorresponding;

    private String left;

    public static float time;

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

        chef1stack.add("Burger");
        chef1stack.add("Burger");
        chef1stack.add("Burger");
        chef2stack.add("Salad");
        chef2stack.add("Salad");
        chef2stack.add("Salad");

        ChoppingStationActor choppingStation = new ChoppingStationActor();
        stage.addActor(choppingStation);
        choppingStation.setPosition(0, 0);

        GrillStationActor grillStation = new GrillStationActor();
        stage.addActor(grillStation);
        grillStation.setPosition(700,650);

        drawBackButton();

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.input.setInputProcessor(stage);

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 600);

        chef = new Chef(game);
        customer = new Customer(game);
        shape = new ShapeRenderer();

        itemCorresponding = new HashMap<String, String>();
    }

    public void drawBackground() {

        batch.draw(background,0,0);
        batch.draw(counter, 500, 0);
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

    public void checkRecipeDone() {
        chef1hasBurger = chef.checkBurgerItems(chef1stack);
        chef2hasBurger = chef.checkBurgerItems(chef2stack);
        chef1hasSalad = chef.checkSaladItems(chef1stack);
        chef2hasSalad = chef.checkSaladItems(chef2stack);

        if (chef1hasBurger == true) {
            chef1stack.remove("Fried Buns");
            chef1stack.remove("Cooked Patty");
            chef1stack.add("Burger");
            chef1hasBurger = false;
        }

        if (chef2hasBurger == true) {
            chef2stack.remove("Fried Buns");
            chef2stack.remove("Cooked Patty");
            chef2stack.add("Burger");
            chef2hasBurger = false;
        }

        if (chef1hasSalad == true) {
            chef1stack.remove("Chopped Tomatoes");
            chef1stack.remove("Chopped Lettuce");
            chef1stack.remove("Chopped Onions");
            chef1stack.add("Salad");
            chef1hasSalad = false;
        }

        if (chef2hasSalad == true) {
            chef2stack.remove("Chopped Tomatoes");
            chef2stack.remove("Chopped Lettuce");
            chef2stack.remove("Chopped Onions");
            chef2stack.add("Salad");
            chef2hasSalad = false;
        }
    }

    public void drawFoodStack1(){
        Integer count = 0;
        for (String item : chef1stack) {
            count += 1;
            if (count == 1) {
                Texture toDraw = new Texture(itemCorresponding.get(item));
                batch.draw(toDraw, chef1slot1x, chef1slot1y);
            }
            else if (count == 2) {
                Texture toDraw = new Texture(itemCorresponding.get(item));
                batch.draw(toDraw, chef1slot2x, chef1slot2y);
            }
            else if (count == 3) {
                Texture toDraw = new Texture(itemCorresponding.get(item));
                batch.draw(toDraw, chef1slot3x, chef1slot3y);
            }
        }
    }

    public void drawFoodStack2(){
        Integer count = 0;
        for (String item : chef2stack) {
            count += 1;
            if (count == 1) {
                Texture toDraw = new Texture(itemCorresponding.get(item));
                batch.draw(toDraw, chef2slot1x, chef2slot1y);
            }
            else if (count == 2) {
                Texture toDraw = new Texture(itemCorresponding.get(item));
                batch.draw(toDraw, chef2slot2x, chef2slot2y);
            }
            else if (count == 3) {
                Texture toDraw = new Texture(itemCorresponding.get(item));
                batch.draw(toDraw, chef2slot3x, chef2slot3y);
            }
        }
    }

    @Override
    public void show() {
        //Temporary Background
        background = new Texture("newBackground.png");
        counter = new Texture("Counter.png");
        addItemToMap();
    }
    public void addItemToMap() {
        itemCorresponding.put("Burger", "Food/Burger.png");
        itemCorresponding.put("Chopped Lettuce", "Food/ChoppedLettuce.png");
        itemCorresponding.put("Chopped Onions", "Food/ChoppedOnion.png");
        itemCorresponding.put("Chopped Tomatoes", "Food/ChoppedTomato.png");
        itemCorresponding.put("Cooked Patty", "Food/CookedPatty.png");
        itemCorresponding.put("Formed Patty", "Food/FormedPatty.png");
        itemCorresponding.put("Fried Bun","Food/FriedBun.png");
        itemCorresponding.put("Lettuce", "Food/Lettuce.png");
        itemCorresponding.put("Onion", "Food/Onion.png");
        itemCorresponding.put("Raw Bun", "Food/RawBun.png");
        itemCorresponding.put("Raw Patty", "Food/RawPatty.png");
        itemCorresponding.put("Salad", "Food/Salad.png");
        itemCorresponding.put("Tomato", "Food/Tomato.png");
    }

    public String chef1GiveFood() {
        System.out.println(chef1stack);
        if (chef.chef1x < 710 &&
                chef.chef1y > 475 &&
                chef.chef1y < 525 &&
                chef1stack.contains(customer.order)) {
            chef1stack.remove(customer.order);
            System.out.println(chef1stack);
            left = "leaving";
        }
        try {
            TimeUnit.MILLISECONDS.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return left;
    }

    public String chef2GiveFood() {
        System.out.println(chef2stack);
        if (chef.chef2x < 710 &&
                chef.chef2y > 475 &&
                chef.chef2y < 525 &&
                chef2stack.contains(customer.order)) {
            chef2stack.remove(customer.order);
            System.out.println(chef2stack);
            left = "leaving";
        }
        try {
            TimeUnit.MILLISECONDS.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return left;
    }


    // 1, 2 and 3 controls each chef (change between chefs)
    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        time += Gdx.graphics.getDeltaTime();

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

        left = customer.controlCustomer(atCounter, givenOrder, entering, customerNo, batch);
        if (Gdx.input.isKeyPressed(Input.Keys.ENTER)) {
            if (chef.chefnumber == 1) {
                left = chef1GiveFood();
            }
            else {
                left = chef2GiveFood();
            }
        }
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
        checkRecipeDone();
        batch.begin();
        drawFoodStack1();
        drawFoodStack2();
        batch.end();

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
