package com.asimov.piazzapanic;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import java.util.Objects;

public class Chef {
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
        else if (Gdx.input.isKeyPressed(Input.Keys.NUM_3)){
            chefnumber = 3;
        }
    }

    public void drawChefs(SpriteBatch batch){
        if (Objects.equals(direction1, "Right")) { batch.draw(chef1Right, chef1x, chef1y);}
        else if (Objects.equals(direction1, "Left")) {batch.draw(chef1Left, chef1x, chef1y);}

        if (Objects.equals(direction2, "Right")) { batch.draw(chef2Right, chef2x, chef2y);}
        else if (Objects.equals(direction2, "Left")) {batch.draw(chef2Left, chef2x, chef2y);}

        if (Objects.equals(direction3, "Right")) { batch.draw(chef3Right, chef3x, chef3y);}
        else if (Objects.equals(direction3, "Left")) {batch.draw(chef3Left, chef3x, chef3y);}
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
    }
}
