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
import com.badlogic.gdx.math.Vector2;
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

    private Texture background = new Texture("layout/Flooring.jpg");
    
    private ServingCounterSprite counter = new ServingCounterSprite(80 * 3);

    private Array<Sprite> walls = new Array<>();
    private Array<CookingStationSprite> cookingStations = new Array<>();
    private Array<FoodCounterSprite> foodCounters = new Array<>();
    private  Array<IngredientStationSprite> ingredientStations = new Array<>();
    private Array<Chef> chefs = new Array<>();

    private Vector2 chefLocation = new Vector2();
    private Vector2 stationVector = new Vector2();

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

    public static Sound grab;
    
    public static Sound bell;
    public static Sound guitar;
    SoundEffectControl soundEffectControl = new SoundEffectControl();
    MusicControl musicControl = new MusicControl();
    public static Sound win;
    public static Sound losing;
    
    Customer customer;

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

    private Array<Sprite> getAllStations() {
        Array<Sprite> result = new Array<>();

        result.addAll(cookingStations);
        result.addAll(foodCounters);
        result.addAll(ingredientStations);

        return result;
    }

    private Sprite getNearestStation() {
        Chef chef = getActiveChef();
        chefLocation = chef.getBoundingRectangle().getCenter(chefLocation);
        Sprite result = null;
        float distance = 1280;

        for (Sprite station : getAllStations().select(x -> chef.getBoundingRectangle().overlaps(x.getBoundingRectangle()))) {
            stationVector = station.getBoundingRectangle().getCenter(stationVector);

            float vectorDistance = chefLocation.dst(stationVector);

            if (vectorDistance < distance) {
                result = station;
                distance = vectorDistance;
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

        GrillStationSprite grillStation = new GrillStationSprite(timer);
        grillStation.scale(2);
        grillStation.setPosition(800, 600);
        cookingStations.add(grillStation);

        BinStationSprite binStation = new BinStationSprite(timer);
        binStation.setPosition(550, 340);
        cookingStations.add(binStation);

        // Counters

        FoodCounterSprite counter1 = new FoodCounterSprite();
        counter1.setPosition(512, 10);
        foodCounters.add(counter1);

        FoodCounterSprite counter2 = new FoodCounterSprite();
        counter2.setPosition(512, 100);
        foodCounters.add(counter2);

        FoodCounterSprite counter3 = new FoodCounterSprite();
        counter3.setPosition(512, 190);
        foodCounters.add(counter3);
        
        // Ingredient Stations

        TomatoStationSprite tStation = new TomatoStationSprite(timer);
        tStation.setPosition(680,300);
        ingredientStations.add(tStation);

        LettuceStationSprite lStation = new LettuceStationSprite(timer);
        lStation.setPosition(780,300);
        ingredientStations.add(lStation);

        OnionStationSprite oStation = new OnionStationSprite(timer);
        oStation.setPosition(880,300);
        ingredientStations.add(oStation);

        BunStationSprite bStation = new BunStationSprite(timer);
        bStation.setPosition(980,300);
        ingredientStations.add(bStation);

        MeatStationSprite mStation = new MeatStationSprite(timer);
        mStation.setPosition(640,200);
        ingredientStations.add(mStation);

        grab = Gdx.audio.newSound((Gdx.files.internal(("audio/mixkit-hard-pop-click-2364.wav"))));

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
        guitar = Gdx.audio.newSound(Gdx.files.internal("audio/rattatouie.MP3"));
        losing = Gdx.audio.newSound(Gdx.files.internal("audio/mixkit-losing-marimba-2025.wav"));
        customerNumbers.add(1);
        customerNumbers.add(2);

        customer = new Customer(game);

        // Scene2d for ui

        stage = new Stage(new FitViewport(1280, 720), game.batch);
        Gdx.input.setInputProcessor(stage);

        table = new Table();
        table.setFillParent(true);
        stage.addActor(table);

        table.setDebug(game.debug);

        Sound sound3 = Gdx.audio.newSound(Gdx.files.internal("audio/mixkit-losing-marimba-2025.wav"));
        TextButton quitButton = new TextButton("Quit", game.skin);
        quitButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                SoundEffectControl.lose();
                game.setScreen(new Quitting(game));
                musicControl.stopguitar();
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

        MusicControl.setguitar();
        MusicControl.playguitar();
        MusicControl.loopguitar();

        // end of scene2d
    }

    @Override
    public void show() {
        // Uncomment to test timer
        // timer.start(1, () -> System.out.println("Timer!"), true);
    }

    public void giveFood() {
        Chef chef = getActiveChef();

        System.out.println(chef.stack);

        Ingredient ingredient = chef.stack.peek();

        if (isChefAtCounter() && customer.checkOrder(ingredient)) {
            chef.stack.place();

            left = "leaving";
        }
    }

    private void interactWithCookingStation(CookingStationSprite cookingStation) {
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

    private void interactWithFoodCounter(FoodCounterSprite counter) {
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
        
    private void interactWithIngredientStation(IngredientStationSprite ingredientStation) {
        Chef chef = getActiveChef();

        if (ingredientStation == null) {
            return;
        }

        if (chef.stack.size()<3 && Gdx.input.isKeyPressed((Input.Keys.R)) && ingredientStation.canGrab()){
            ingredientStation.grab(chef.stack);
              SoundEffectControl.playGrab();
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

        Sprite station = getNearestStation();

        if (station instanceof CookingStationSprite) {
            interactWithCookingStation((CookingStationSprite) station);
        }

        if (station instanceof FoodCounterSprite) {
            interactWithFoodCounter((FoodCounterSprite) station);
        }

        if (station instanceof IngredientStationSprite) {
            interactWithIngredientStation((IngredientStationSprite) station);
        }

        if (begin) {
            SoundEffectControl.playBell();
            System.out.print("NEW VOL " + soundEffectControl.volume);
            customer.show();
            customerNo = customer.randomCustomer();
            begin = false;
        }

        left = customer.controlCustomer(atCounter, givenOrder, entering, customerNo, game.batch);

        if (Gdx.input.isKeyPressed(Input.Keys.ENTER) && left == "at counter") {
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
           SoundEffectControl.playWin();
           //might have to add end music
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
