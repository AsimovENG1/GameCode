package com.asimov.piazzapanic;

import com.badlogic.gdx.*;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class ScenarioMode extends ScreenAdapter {
    final PiazzaPanic game;
    private Stage stage;
    private Table table;

    private Texture background = new Texture("layout/background.png");
    private CounterSprite counter = new CounterSprite(80 * 3);

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

    private Viewport getViewport() {
        return stage.getViewport();
    }

    private Camera getCamera() {
        return stage.getCamera();
    }

    public ScenarioMode(final PiazzaPanic game) {
        this.game = game;

        // Scene2d for ui

        stage = new Stage(new FitViewport(1280, 720), game.batch);
        Gdx.input.setInputProcessor(stage);

        table = new Table();
        table.setFillParent(true);
        stage.addActor(table);

        table.setDebug(true);

        Sound sound3 = Gdx.audio.newSound(Gdx.files.internal("audio/mixkit-losing-marimba-2025.wav"));
        TextButton quitButton = new TextButton("Quit", game.skin, "small");
        quitButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                sound3.play(1.0f);
                game.setScreen(new Quitting(game));
            }
        });
        table.add(quitButton).left().top().pad(10, 10, 0, 0).maxSize(100, 50).expandX();

        Table stackTable = new Table();
        table.add(stackTable).right().bottom().pad(0, 0, 10, 10).expandX().expandY();

        ChefStackActor chef1Stack = new ChefStackActor("Chef 1", game.skin, chef1stack);
        ChefStackActor chef2Stack = new ChefStackActor("Chef 2", game.skin, chef2stack);

        stackTable.add(chef1Stack).padRight(10);
        stackTable.add(chef2Stack);

        // end of scene2d

        choices.add("Burger");
        choices.add("Salad");
        bell = Gdx.audio.newSound(Gdx.files.internal("audio/bell-123742.mp3"));
        win = Gdx.audio.newSound(Gdx.files.internal("audio/level-win-6416.mp3"));
        customerNumbers.add(1);
        customerNumbers.add(2);

        chef1stack.add("Burger");
        chef1stack.add("Burger");
        chef1stack.add("Burger");
        chef2stack.add("Salad");
        chef2stack.add("Salad");
        chef2stack.add("Salad");

        ChoppingStationActor choppingStation = new ChoppingStationActor();
        stage.addActor(choppingStation);
        choppingStation.setPosition(0, 0);

        chef = new Chef(game);
        customer = new Customer(game);

        itemCorresponding = new HashMap<>();
    }

    public void drawBackground() {

        game.batch.draw(background,0,0);
        game.batch.draw(counter, 500, 0);
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
                game.batch.draw(toDraw, chef1slot1x, chef1slot1y);
            }
            else if (count == 2) {
                Texture toDraw = new Texture(itemCorresponding.get(item));
                game.batch.draw(toDraw, chef1slot2x, chef1slot2y);
            }
            else if (count == 3) {
                Texture toDraw = new Texture(itemCorresponding.get(item));
                game.batch.draw(toDraw, chef1slot3x, chef1slot3y);
            }
        }
    }

    public void drawFoodStack2(){
        Integer count = 0;
        for (String item : chef2stack) {
            count += 1;
            if (count == 1) {
                Texture toDraw = new Texture(itemCorresponding.get(item));
                game.batch.draw(toDraw, chef2slot1x, chef2slot1y);
            }
            else if (count == 2) {
                Texture toDraw = new Texture(itemCorresponding.get(item));
                game.batch.draw(toDraw, chef2slot2x, chef2slot2y);
            }
            else if (count == 3) {
                Texture toDraw = new Texture(itemCorresponding.get(item));
                game.batch.draw(toDraw, chef2slot3x, chef2slot3y);
            }
        }
    }

    @Override
    public void show() {
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

        time += delta;

        Batch batch = game.batch;

        batch.setProjectionMatrix(getCamera().combined);

        batch.begin();

        batch.draw(background, 0, 0);

        counter.draw(batch);

        if (begin) {
            bell.play(1.0f);
            chef.show();
            customer.show();
            customerNo = customer.randomCustomer();
            begin = false;
        }

        chef.drawChefs(game.batch);

        chef.controlChef();
        chef.changeChef();

        left = customer.controlCustomer(atCounter, givenOrder, entering, customerNo, game.batch);
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

        game.batch.end();

        checkRecipeDone();
        game.batch.begin();
        drawFoodStack1();
        drawFoodStack2();
        game.batch.end();

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
        getViewport().update(width, height, true);
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
    }
}
