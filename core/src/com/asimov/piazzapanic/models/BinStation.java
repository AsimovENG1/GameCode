package com.asimov.piazzapanic.models;
import java.util.concurrent.TimeUnit;

public class BinStation extends CookingStation{
    @Override
    protected boolean canPlace(Ingredient ingredient) {
        return true;
    }

    @Override
    public boolean canGrab() {
        return false;
    }

    public void open() {
        ingredient = null;
        status = CookingStatus.available;
    }
}
