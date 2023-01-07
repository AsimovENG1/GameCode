package com.asimov.piazzapanic.models;

public interface Fryable extends Ingredient {
    public boolean ready();
    public void ReadySwap();
    public void stageIncrease();
    public int state();
    public boolean isFried();
    public void makeFried();
}
