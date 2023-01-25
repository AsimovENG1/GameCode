package com.asimov.piazzapanic;

import com.asimov.piazzapanic.deltatimer.DeltaTimer;
import com.asimov.piazzapanic.models.*;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class ScenarioMode extends ScreenAdapter {
    final PiazzaPanic game;
    private DeltaTimer timer = new DeltaTimer();

    private Stage stage;
    private Table table;

    private Texture background = new Texture("layout/background.png");
    private ServingCounterSprite counter = new ServingCounterSprite(80 * 3);

    private Array<Sprite> walls = new Array<>();

    private Array<CookingStationSprite> cookingStations = new Array<>();

    private Array<FoodCounterSprite> foodCounters = new Array<>();
    
    private  Array<IngredientStationSprite> ingredientStations = new Array<>();

    private Array<Chef> chefs = new Array<>();

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


    private Boolean chef1hasBurger;
    private Boolean chef2hasBurger;
    private Boolean chef1hasSalad;
    private Boolean chef2hasSalad;

    private String left;

    public static float time;

    private Viewport getViewport() {
        return stage.getViewport();
    }

    private Camera getCamera() {
        return stage.getCamera();
    }

    private Chef getActiveChef() {
        return chefs.select(x -> x.getActive()).iterator().next();
    }

    // gets the nearest overlapped cooking station to the active chef
    private CookingStationSprite getActiveCookingStation() {
        Chef chef = getActiveChef();
        CookingStationSprite result = null;
        float distance = 1280;

        for (CookingStationSprite cookingStation : cookingStations.select(x -> chef.getBoundingRectangle().overlaps(x.getBoundingRectangle()))) {
            if (Math.sqrt(Math.pow(chef.getX() - cookingStation.getX(), 2) + Math.pow(chef.getY() - cookingStation.getY(), 2)) < distance) {
                result = cookingStation;
            }
        }

        return result;
    }

    private FoodCounterSprite getActiveFoodCounter() {
        Chef chef = getActiveChef();
        FoodCounterSprite result = null;
        float distance = 1280;

        for (FoodCounterSprite foodCounter : foodCounters.select(x -> chef.getBoundingRectangle().overlaps(x.getBoundingRectangle()))) {
            if (Math.sqrt(Math.pow(chef.getX() - foodCounter.getX(), 2) + Math.pow(chef.getY() - foodCounter.getY(), 2)) < distance) {
                result = foodCounter;
            }
        }
        
        return result;
    }
    
    private IngredientStationSprite getActiveIngredientStation(){
        Chef chef = getActiveChef();
        IngredientStationSprite result = null;
        float distance = 1280;

        for (IngredientStationSprite ingredientStation: ingredientStations.select((x -> chef.getBoundingRectangle().overlaps(x.getBoundingRectangle())))){
            if (Math.sqrt(Math.pow(chef.getX() - ingredientStation.getX(), 2) + Math.pow(chef.getY() - ingredientStation.getY(),2)) < distance){
                result = ingredientStation;
            }
        }
        
        return result;
    }

    private boolean isChefAtCounter() {
        return getActiveChef().getBoundingRectangle().overlaps(counter.getBoundingRectangle());
    }

    public ScenarioMode(final PiazzaPanic game) {
        this.game = game;

        // Walls

        walls.addAll(new WallBuilder(640, 680, 90)
                .withEnd(0, 0, 180, 1)
                .withSegment(1, 0, 0, 10)
                .withEnd(1, 0, 0, 1)
                .build());

        walls.addAll(new WallBuilder(680, 0, 0)
                .withEnd(0 , 0, 90, 1)
                .withCorner(-1, 0, 90, 1)
                .withSegment(0, 1, 0, 8)
                .withCorner(0, 1, 0, 1)
                .withSegment(1, 0, 90, 10)
                .withCorner(1, 0, -90, 1)
                .withEnd(0, -1, 0, 1)
                .build());

        // Cooking Stations

        ChoppingStationSprite choppingStation = new ChoppingStationSprite();
        choppingStation.scale (2);
        choppingStation.setPosition(640, 600);
        cookingStations.add(choppingStation);

        GrillStationSprite grillStation = new GrillStationSprite();
        grillStation.scale(2);
        grillStation.setPosition(800, 600);
        cookingStations.add(grillStation);

        BinStationSprite binStation = new BinStationSprite();
        binStation.setPosition(400, 0);
        cookingStations.add(binStation);

        // Counters

        FoodCounterSprite demoCounter = new FoodCounterSprite();
        demoCounter.setPosition(640, 300);
        foodCounters.add(demoCounter);
        
        // Ingredient Stations

        TomatoStationSprite tStation = new TomatoStationSprite(timer);
        tStation.setPosition(900,300);
        ingredientStations.add(tStation);

        LettuceStationSprite lStation = new LettuceStationSprite(timer);
        lStation.setPosition(800,300);
        ingredientStations.add(lStation);

        OnionStationSprite oStation = new OnionStationSprite(timer);
        oStation.setPosition(700,300);
        ingredientStations.add(oStation);

        BunStationSprite bStation = new BunStationSprite(timer);
        bStation.setPosition(500,300);
        ingredientStations.add(bStation);

        MeatStationSprite mStation = new MeatStationSprite(timer);
        mStation.setPosition(400,300);
        ingredientStations.add(mStation);

        // Chefs

        Chef chef1 = new Chef(new Texture("characters/chef1px3.png"), new Texture("characters/chef1px3 left.png"));
        chef1.setActive(true);
        chef1.setPosition(400, 400);

        Chef chef2 = new Chef(new Texture("characters/chef2px3.png"), new Texture("characters/chef2px3 left.png"));
        chef2.setPosition(500, 400);

        chefs.add(chef1, chef2);

        choices.add("Burger");
        choices.add("Salad");
        bell = Gdx.audio.newSound(Gdx.files.internal("audio/bell-123742.mp3"));
        win = Gdx.audio.newSound(Gdx.files.internal("audio/level-win-6416.mp3"));
        customerNumbers.add(1);
        customerNumbers.add(2);

        chef1.stack.grab(new Patty());
        chef1.stack.grab(new Burger());
        chef1.stack.grab(new Patty());
        chef2.stack.grab(new Tomato());
        chef2.stack.grab(new Lettuce());
        chef2.stack.grab(new Onion());

        customer = new Customer(game);

        // Scene2d for ui

        stage = new Stage(new FitViewport(1280, 720), game.batch);
        Gdx.input.setInputProcessor(stage);

        table = new Table();
        table.setFillParent(true);
        stage.addActor(table);

        table.setDebug(game.debug);

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

        table.row().expand();

        Table stackTable = new Table();

        table.add(stackTable).right().bottom().pad(0, 0, 10, 10);

        ChefStackActor chef1Stack = new ChefStackActor("Chef 1", game.skin, new Texture("StackItem.png"), chef1.stack);
        ChefStackActor chef2Stack = new ChefStackActor("Chef 2", game.skin, new Texture("StackItem-orange.png"), chef2.stack);

        stackTable.add(chef1Stack).padRight(10);
        stackTable.add(chef2Stack);

        // end of scene2d
    }

    @Override
    public void show() {
        // Uncomment to test timer
        // timer.start(1, () -> System.out.println("Timer!"), true);
    }

    public String giveFood() {
        Chef chef = getActiveChef();

        System.out.println(chef.stack);

        Ingredient ingredient = chef.stack.peek();

        if (isChefAtCounter() && customer.checkOrder(ingredient)) {
            chef.stack.place();

            System.out.println(chef.stack);

            left = "leaving";
        }

        try {
            TimeUnit.MILLISECONDS.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return left;
    }

    private void interactWithCookingStations() {
        CookingStationSprite cookingStation = getActiveCookingStation();
        Chef chef = getActiveChef();

        if (cookingStation == null) {
            return;
        }

        if (cookingStation.canPlace(chef.stack) && Gdx.input.isKeyPressed(Input.Keys.E)) {
            cookingStation.place(chef.stack);
        }

        if (cookingStation.canGrab() && chef.stack.size() < 3 && Gdx.input.isKeyPressed(Input.Keys.R)) {
            cookingStation.grab(chef.stack);
        }

        if (cookingStation instanceof ChoppingStationSprite &&
                ((ChoppingStationSprite) cookingStation).canChop() &&
                Gdx.input.isKeyPressed(Input.Keys.C)) {

            ((ChoppingStationSprite) cookingStation).chop();
        }
        if (cookingStation instanceof GrillStationSprite &&
                ((GrillStationSprite) cookingStation).canFlip() &&
                Gdx.input.isKeyPressed(Input.Keys.F)) {

            ((GrillStationSprite) cookingStation).flip();
        }
    }

    private void interactWithFoodCounters() {
        FoodCounterSprite counter = getActiveFoodCounter();
        Chef chef = getActiveChef();

        if (counter == null) {
            return;
        }

        if (counter.canPlace(chef) && Gdx.input.isKeyPressed(Input.Keys.E)) {
            counter.place(chef);
        }

        if (counter.canGrab() && Gdx.input.isKeyPressed(Input.Keys.R)) {
            counter.grab(chef);
        }
    }
        
    private void interactWithIngredientStations() {
        IngredientStationSprite iStation = getActiveIngredientStation();
        Chef chef = getActiveChef();

        if (iStation == null) {
            return;
        }

        if (chef.stack.size()<3 && Gdx.input.isKeyPressed((Input.Keys.R)) && iStation.canGrab()){
            iStation.grab(chef.stack);
//            bell.play();
        }
    }

    // 1, 2 and 3 controls each chef (change between chefs)
    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        time += delta;
        timer.tick(delta);

        Batch batch = game.batch;
        Camera camera = getCamera();

        batch.setProjectionMatrix(camera.combined);

        batch.begin();

        // Background

        batch.draw(background, 0, 0);

        counter.draw(batch);

        // Walls

        for (Sprite wall : walls) {
            wall.draw(batch);
        }

        // Cooking Stations

        for (Sprite cookingStation : cookingStations) {
            cookingStation.draw(batch);
        }

        // Counters

        for (Sprite foodCounter : foodCounters) {
            foodCounter.draw(batch);
        }

        // Ingredient Station

        for (Sprite ingredientStation: ingredientStations){
            ingredientStation.draw(batch);
        }

        // Chefs

        if (Gdx.input.isKeyPressed(Input.Keys.NUM_1)) {
            chefs.get(0).setActive(true);
            chefs.get(1).setActive(false);
        }

        if (Gdx.input.isKeyPressed(Input.Keys.NUM_2)) {
            chefs.get(0).setActive(false);
            chefs.get(1).setActive(true);
        }

        for (Chef chef : chefs) {
            chef.controlChef(delta, camera.viewportWidth, camera.viewportHeight);
            chef.checkBurgerItems();
            chef.checkSaladItems();
            chef.draw(batch);
        }

        interactWithCookingStations();

        interactWithFoodCounters();
        
        interactWithIngredientStations();

        if (begin) {
            bell.play(1.0f);
            customer.show();
            customerNo = customer.randomCustomer();
            begin = false;
        }

        left = customer.controlCustomer(atCounter, givenOrder, entering, customerNo, game.batch);

        if (Gdx.input.isKeyPressed(Input.Keys.ENTER)) {
            giveFood();
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

        //game.batch.end();

        //checkRecipeDone();
        //game.batch.begin();
        //drawFoodStack1();
        //drawFoodStack2();
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
