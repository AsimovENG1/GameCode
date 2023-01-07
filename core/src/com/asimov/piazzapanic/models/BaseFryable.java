package com.asimov.piazzapanic.models;

public abstract class BaseFryable implements Fryable {
    private int state = 0; // 0 nothing 1 items place 2 items flipped
    private boolean ready = true; // item can be interacted with
    private boolean fried = false;

    public boolean isFried() {
        return fried;
    }
    public int state() {
        return state;
    }
    public void stageIncrease() {
        state += 1;
    }
    public void ReadySwap() {
        if (ready == true){
            ready = false;
        }else{
            ready = true;
        }
    }
    public boolean ready() {
        return ready;
    }
    public void makeFried() {
        fried = true;
    }
}
