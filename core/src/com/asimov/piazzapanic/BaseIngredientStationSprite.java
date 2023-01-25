package com.asimov.piazzapanic;

import com.asimov.piazzapanic.deltatimer.DeltaTimer;
import com.asimov.piazzapanic.models.IngredientStack;
import com.asimov.piazzapanic.models.IngredientStation;

public abstract class BaseIngredientStationSprite<T extends IngredientStation> extends IngredientStationSprite{
    protected T model;
    private DeltaTimer timer;

    public BaseIngredientStationSprite(T model, DeltaTimer timer) {
        this.model = model;
        this.timer = timer;
    }

    public void grab(IngredientStack stack) {
        if (!canGrab()) {
            return;
        }

        canGrab = false;
        model.grab(stack);
        timer.start(1, () -> canGrab = true);
    }

    private boolean canGrab = true;

    public boolean canGrab() {
        return canGrab;
    }
}
