package com.asimov.piazzapanic;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static java.lang.System.in;

public class Chef {
    Texture chef1Right;
    Texture chef1Left;
    Texture chef2Left;
    Texture chef2Right;

    float Speed = 100.0f;
    float chef1x = 750;
    float chef1y = 500;

    float chef2x = 1000;
    float chef2y = 500;

    float chefnumber = 1;

    String direction1 = "Right"; //Starting direction of chef1
    String direction2 = "Right"; //Starting direction of chef2
    String direction3 = "Right"; //Starting direction of chef3
    final PiazzaPanic game;

    OrthographicCamera camera;

    public Chef(final PiazzaPanic game) {
        this.game = game;
    }

    public void changeChef() {
        if (Gdx.input.isKeyPressed(Input.Keys.NUM_1)){
            chefnumber = 1;
        }
        else if (Gdx.input.isKeyPressed(Input.Keys.NUM_2)){
            chefnumber = 2;
        }
    }

    public void drawChefs(SpriteBatch batch){
        if (Objects.equals(direction1, "Right")) { batch.draw(chef1Right, chef1x, chef1y);}
        else if (Objects.equals(direction1, "Left")) {batch.draw(chef1Left, chef1x, chef1y);}

        if (Objects.equals(direction2, "Right")) { batch.draw(chef2Right, chef2x, chef2y);}
        else if (Objects.equals(direction2, "Left")) {batch.draw(chef2Left, chef2x, chef2y);}
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
        }

        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            if (chefnumber == 1) {
                if ((chef1x - Gdx.graphics.getDeltaTime()*Speed) > 700) {
                    chef1x -= Gdx.graphics.getDeltaTime() * Speed;
                    direction1 = "Left";
                }
            } else if (chefnumber == 2) {
                if ((chef2x - Gdx.graphics.getDeltaTime()*Speed) > 700) {
                    chef2x -= Gdx.graphics.getDeltaTime() * Speed;
                    direction2 = "Left";
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
            }
        }
    }
    public void show() {
        //Blue Chef 1
        chef1Right = new Texture("characters/chef1px3.png");
        chef1Left = new Texture("characters/chef1px3 left.png");
        //Orange Chef 2
        chef2Right = new Texture("characters/chef2px3.png");
        chef2Left = new Texture("characters/chef2px3 left.png");
    }

    public Boolean checkBurgerItems(ArrayList<String> chefstack) {
        if (chefstack.contains("Cooked Patty") &&
                chefstack.contains("Fried Buns")) {
            return true;
        }
        else {
            return false;
        }
    }

    public Boolean checkSaladItems(ArrayList<String> chefstack) {
        if (chefstack.contains("Chopped Tomatoes") &&
                chefstack.contains("Chopped Lettuce")&&
                chefstack.contains("Chopped Onions")) {
            return true;
        }
        else {
            return false;
        }
    }
}
