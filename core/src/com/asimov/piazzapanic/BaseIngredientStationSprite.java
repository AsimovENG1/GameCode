package com.asimov.piazzapanic;

import com.asimov.piazzapanic.models.Chef;
import com.asimov.piazzapanic.models.IngredientStack;
import com.asimov.piazzapanic.models.IngredientStation;

public abstract class BaseIngredientStationSprite<T extends IngredientStation> extends IngredientStationSprite{
    protected T model;

    public BaseIngredientStationSprite(T model) {
        this.model = model;
    }

    public void grab(Chef chef) {
        model.grab(chef);

        grabbed();
    }

    protected void grabbed() {}
}
