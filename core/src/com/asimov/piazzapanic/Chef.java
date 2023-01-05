package com.asimov.piazzapanic;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static java.lang.Thread.sleep;

public class Chef extends InputAdapter implements Screen {
    Stage stage;
    SpriteBatch batch;
    Texture background;
    Texture chef1Right;
    Texture chef1Left;
    Texture chef2Left;
    Texture chef2Right;
    Texture chef3Right;
    Texture chef3Left;

    float Speed = 100.0f;
    float chef1x = 750;
    float chef1y = 500;

    float chef2x = 1000;
    float chef2y = 500;

    float chef3x = 1250;
    float chef3y = 500;

    float chefnumber = 1;

    String direction1 = "Right"; //Starting direction of chef1
    String direction2 = "Right"; //Starting direction of chef2
    String direction3 = "Right"; //Starting direction of chef3
    final PiazzaPanic game;
    OrthographicCamera camera;

    Texture customer1Left;
    Texture customer1Right;

    Texture customer1Burger;

    Texture customer1Salad;

    Texture customer2Left;

    Texture customer2Right;

    Texture customer2Salad;

    Texture customer2Burger;

    private String[] burgerCustomers = new String[] {"Customer1Burger.png", "Customer2Burger.png"};
    private String[] saladCustomers = new String[] {"Customer1Salad.png", "Customer2Salad.png"};
    private String[] rightCustomers = new String[] {"Customer1.png", "Customer2.png"};
    private String[] leftCustomers = new String[] {"Customer1Left.png", "Customer2Left.png"};
    private List<String> choices = new ArrayList<>();
    private List<Integer> customerNumbers = new ArrayList<>();

    float customerx = 0;
    float customery = 500;

    boolean end = false;
    boolean atCounter = false;
    boolean givenOrder = false;
    boolean entering = true;
    String order = null;
    Integer customers = 5;
    Integer customerNo;
    boolean begin = true;
    private Sound bell;
    private Sound win;

    public Chef(final PiazzaPanic game) {
        this.game = game;

        choices.add("Burger");
        choices.add("Salad");
        bell = Gdx.audio.newSound(Gdx.files.internal("audio/bell-123742.mp3"));
        win = Gdx.audio.newSound(Gdx.files.internal("audio/level-win-6416.mp3"));
        customerNumbers.add(1);
        customerNumbers.add(2);

        stage = new Stage(new ScreenViewport(), game.batch);

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.input.setInputProcessor(stage);

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 600);

    }

    public void drawBackground() {
        stage.getBatch().begin();
        stage.getBatch().draw(background,0,0);
        stage.getBatch().end();
    }

    public void changeChef() {
        if (Gdx.input.isKeyPressed(Input.Keys.NUM_1)){
            chefnumber = 1;
        }
        else if (Gdx.input.isKeyPressed(Input.Keys.NUM_2)){
            chefnumber = 2;
        }
        else if (Gdx.input.isKeyPressed(Input.Keys.NUM_3)){
            chefnumber = 3;
        }
    }

    public void drawChefs(){
        if (direction1 == "Right") { batch.draw(chef1Right, chef1x, chef1y);}
        else if (direction1 == "Left") {batch.draw(chef1Left, chef1x, chef1y);}

        if (direction2 == "Right") { batch.draw(chef2Right, chef2x, chef2y);}
        else if (direction2 == "Left") {batch.draw(chef2Left, chef2x, chef2y);}

        if (direction3 == "Right") { batch.draw(chef3Right, chef3x, chef3y);}
        else if (direction3 == "Left") {batch.draw(chef3Left, chef3x, chef3y);}
    }

    public void controlChef() {
        if (Gdx.input.isKeyPressed(Input.Keys.W)){
            if (chefnumber == 1) {
                if ((chef1y + Gdx.graphics.getDeltaTime()*Speed) < 825) {
                    chef1y += Gdx.graphics.getDeltaTime()*Speed;
                }
            }
            else if (chefnumber == 2) {
                if ((chef2y + Gdx.graphics.getDeltaTime()*Speed) < 825) {
                    chef2y += Gdx.graphics.getDeltaTime() * Speed;
                }
            }
            else if (chefnumber == 3) {
                if ((chef3y + Gdx.graphics.getDeltaTime()*Speed) < 825) {
                    chef3y += Gdx.graphics.getDeltaTime()*Speed;
                }
            }
        }

        if (Gdx.input.isKeyPressed(Input.Keys.S)){
            if (chefnumber == 1) {
                if ((chef1y - Gdx.graphics.getDeltaTime()*Speed) > 0) {
                    chef1y -= Gdx.graphics.getDeltaTime()*Speed;
                }
            }
            else if (chefnumber == 2) {
                if ((chef2y - Gdx.graphics.getDeltaTime()*Speed) > 0) {
                    chef2y -= Gdx.graphics.getDeltaTime()*Speed;
                }
            }
            else if (chefnumber == 3) {
                if ((chef3y - Gdx.graphics.getDeltaTime()*Speed) > 0) {
                    chef3y -= Gdx.graphics.getDeltaTime()*Speed;
                }
            }
        }

        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            if (chefnumber == 1) {
                if ((chef1x - Gdx.graphics.getDeltaTime()*Speed) > 550) {
                    chef1x -= Gdx.graphics.getDeltaTime() * Speed;
                    direction1 = "Left";
                }
            } else if (chefnumber == 2) {
                if ((chef2x - Gdx.graphics.getDeltaTime()*Speed) > 550) {
                    chef2x -= Gdx.graphics.getDeltaTime() * Speed;
                    direction2 = "Left";
                }
            } else if (chefnumber == 3) {
                if ((chef3x - Gdx.graphics.getDeltaTime()*Speed) > 550) {
                    chef3x -= Gdx.graphics.getDeltaTime() * Speed;
                    direction3 = "Left";
                }
            }
        }

        if (Gdx.input.isKeyPressed(Input.Keys.D)){
            if (chefnumber == 1) {
                if ((chef1x + Gdx.graphics.getDeltaTime()*Speed) < 1900) {
                    chef1x += Gdx.graphics.getDeltaTime() * Speed;
                    direction1 = "Right";
                }
            } else if (chefnumber == 2) {
                if ((chef2x + Gdx.graphics.getDeltaTime()*Speed) < 1900) {
                    chef2x += Gdx.graphics.getDeltaTime() * Speed;
                    direction2 = "Right";
                }
            } else if (chefnumber == 3) {
                if ((chef3x + Gdx.graphics.getDeltaTime()*Speed) < 1900) {
                    chef3x += Gdx.graphics.getDeltaTime() * Speed;
                    direction3 = "Right";
                }
            }
        }
    }

    public void drawBackButton(){
        final Sound sound2 = Gdx.audio.newSound(Gdx.files.internal("audio/Back-and-quit.wav"));
        Skin mySkin = new Skin(Gdx.files.internal("skin/glassy-ui.json"));

        TextButton.TextButtonStyle style = new TextButton.TextButtonStyle();
        style.font = game.font;

        TextButton backButton = new TextButton("Back", mySkin,"small");

        backButton.setWidth(100);
        backButton.setHeight(50);

        backButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                sound2.play(1.0f);
                game.setScreen(new GameScreen(game));
            }
        });

        stage.addActor(backButton);

        backButton.setPosition((float) ((Gdx.graphics.getWidth() * 0.05) -50), (float) ((Gdx.graphics.getHeight() * 0.9) - 25));
        batch.end();
    }

    @Override
    public void show() {
        //Temporary Background
        background = new Texture("newBackground.png");
        //Blue Chef 1
        chef1Right = new Texture("characters/chef1px3.png");
        chef1Left = new Texture("characters/chef1px3 left.png");
        //Orange Chef 2
        chef2Right = new Texture("characters/chef2px3.png");
        chef2Left = new Texture("characters/chef2px3 left.png");
        //Green Chef 3
        chef3Right = new Texture("characters/chef3px3.png");
        chef3Left = new Texture("characters/chef3px3 left.png");
        //Customer 1
        customer1Right = new Texture("characters/customer1px3.png");
        customer1Left = new Texture("characters/customer1px3 left.png");
        //Customer 1 Burger
        customer1Burger = new Texture("characters/customer1px3Burger.png");
        //Customer 1 Salad
        customer1Salad = new Texture("characters/customer1px3Salad.png");
        //Customer 2
        customer2Right = new Texture("characters/customer2px3.png");
        customer2Left = new Texture("characters/customer2px3 left.png");
        //Customer 2 Burger
        customer2Burger = new Texture("characters/customer2px3Burger.png");
        //Customer 2 Salad
        customer2Salad = new Texture("characters/customer2px3Salad.png");

        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        batch = new SpriteBatch();
    }

    public Integer randomCustomer() {
        Random randomizer = new Random();
        Integer random = customerNumbers.get(randomizer.nextInt(choices.size()));
        begin = false;
        return random;
    }

    public void drawCustomerEntering(){
        if (customerNo == 1) {
            batch.draw(customer1Right, customerx, customery);
        } else {batch.draw(customer2Right, customerx, customery);}
    }
    public void drawCustomerLeaving(){
        if (customerNo == 1) {
            batch.draw(customer1Left, customerx, customery);
        } else {batch.draw(customer2Left, customerx, customery);}
    }
    public void drawBurgerCustomer(){
        if (customerNo == 1) {
            batch.draw(customer1Burger, customerx, customery);
        } else {batch.draw(customer2Burger, customerx, customery);}
    }
    public void drawSaladCustomer(){
        if (customerNo == 1) {
            batch.draw(customer1Salad, customerx, customery);
        } else {batch.draw(customer2Salad, customerx, customery);}
    }
    public void moveCustomersIn() {
        if ((customerx + Gdx.graphics.getDeltaTime() * Speed) < 470) {
            customerx += Gdx.graphics.getDeltaTime() * Speed;
        } else {atCounter = true;}
        if (atCounter == true) {
            order = makeOrder();
        }
    }

    public void moveCustomersOut(){
        if ((customerx - Gdx.graphics.getDeltaTime() * Speed) > 0) {
            customerx -= Gdx.graphics.getDeltaTime() * Speed;
        } else {
            givenOrder = false;
            entering = true;
            begin = true;
            customerx = 0;
            customery = 500;
            customers -= 1;
        }
    }

    public String makeOrder(){
        Random randomizer = new Random();
        String random = choices.get(randomizer.nextInt(choices.size()));
        return random;
    }

    public void controlCustomer() {
        if (atCounter == false && givenOrder == false && entering == true) {
            drawCustomerEntering();
            moveCustomersIn();
        }
        if (atCounter== true) {
            if (order == "Burger") {
                drawBurgerCustomer();
            }
            else if (order == "Salad") {
                drawSaladCustomer();
            }
            givenOrder = true;
            atCounter = false;
            entering = false;
        }
        if (givenOrder == true && atCounter == false && entering == false){
            drawCustomerLeaving();
            moveCustomersOut();
        }
    }

    // 1, 2 and 3 controls each chef (change between chefs)
    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0,0,0,0);
        ScreenUtils.clear(0,0,0,0);
        batch.begin();
        drawBackground();
        stage.draw();
        if (begin == true) {
            bell.play(1.0f);
            customerNo = randomCustomer();
        }
        changeChef();
        drawChefs();
        controlChef();
        controlCustomer();
        drawBackButton();
        if (customers == 0) {
            end = true;
        }
        if (end == true) {
            win.play(1.0f);
            game.setScreen(new EndingScreen(game));
        }
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
