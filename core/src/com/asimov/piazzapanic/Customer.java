package com.asimov.piazzapanic;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Customer {
    private final PiazzaPanic game;
    Stage stage;
    Texture customer1Left;
    Texture customer1Right;
    Texture customer1Burger;
    Texture customer1Salad;
    Texture customer2Left;
    Texture customer2Right;
    Texture customer2Salad;
    Texture customer2Burger;
    OrthographicCamera camera;
    float Speed = 100.0f;
    private List<String> choices = new ArrayList<>();
    private List<Integer> customerNumbers = new ArrayList<>();

    float customerx = 0;
    float customery = 500;
    String order = null;
    boolean left = false;

    int i = 0;

    public Customer(final PiazzaPanic game) {
        this.game = game;

        choices.add("Burger");
        choices.add("Salad");

        customerNumbers.add(1);
        customerNumbers.add(2);

        stage = new Stage(new ScreenViewport(), game.batch);

        ChoppingStationActor choppingStation = new ChoppingStationActor();
        stage.addActor(choppingStation);
        choppingStation.setPosition(0, 0);

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.input.setInputProcessor(stage);

        camera = new OrthographicCamera();
    }

    public void show() {
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
        Gdx.input.setInputProcessor(stage);
    }
    public Integer randomCustomer() {
        Random randomizer = new Random();
        Integer random = customerNumbers.get(randomizer.nextInt(choices.size()));
        return random;
    }

    public void drawCustomerEntering(Integer customerNo, SpriteBatch batch){
        if (customerNo == 1) {
            batch.draw(customer1Right, customerx, customery);
        } else {batch.draw(customer2Right, customerx, customery);}
    }
    public void drawCustomerLeaving(Integer customerNo, SpriteBatch batch){
        if (customerNo == 1) {
            batch.draw(customer1Left, customerx, customery);
        } else {batch.draw(customer2Left, customerx, customery);}
    }
    public void drawBurgerCustomer(Integer customerNo, SpriteBatch batch){
        if (customerNo == 1) {
            batch.draw(customer1Burger, customerx, customery);
        } else {batch.draw(customer2Burger, customerx, customery);}
    }
    public void drawSaladCustomer(Integer customerNo, SpriteBatch batch){
        if (customerNo == 1) {
            batch.draw(customer1Salad, customerx, customery);
        } else {batch.draw(customer2Salad, customerx, customery);}
    }
    public boolean moveCustomersIn(float Speed, boolean atCounter) {
        if ((customerx + Gdx.graphics.getDeltaTime() * Speed) < 425) {
            customerx += Gdx.graphics.getDeltaTime() * Speed;
        } else {atCounter = true;}
        if (atCounter == true) {
            order = makeOrder();
            return true;
        } return false;
    }

    public boolean moveCustomersOut(float Speed){
        if ((customerx - Gdx.graphics.getDeltaTime() * Speed) > 0) {
            customerx -= Gdx.graphics.getDeltaTime() * Speed;
        } else {
            return true;
        }
        return false;
    }

    public String makeOrder(){
        Random randomizer = new Random();
        String random = choices.get(randomizer.nextInt(choices.size()));
        return random;
    }

    public String controlCustomer(boolean atCounter, boolean givenOrder, boolean entering, Integer customerNo,
                                  SpriteBatch batch) {
        if (atCounter == false && givenOrder == false && entering == true) {
            drawCustomerEntering(customerNo, batch);
            atCounter = moveCustomersIn(Speed, atCounter);
            if (atCounter == true) {return "at counter";}
            return "entering";
        }
        if (atCounter == true && givenOrder == false) {
            if (order == "Burger") {
               drawBurgerCustomer(customerNo, batch);
            }
            else if (order == "Salad") {
                drawSaladCustomer(customerNo, batch);
            }
            return "at counter";
        }
        if (givenOrder == true && atCounter == false && entering == false) {
           drawCustomerLeaving(customerNo, batch);
           left = moveCustomersOut(Speed);

           if (left == true) {return "left";}
           else {return "leaving";}
        }
        return null;
    }

}
