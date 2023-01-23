package com.asimov.piazzapanic;

import com.asimov.piazzapanic.models.IngredientStack;
import com.asimov.piazzapanic.models.IngredientStation;
import com.asimov.piazzapanic.models.Tomato;

public class TomatoStationSprite extends BaseIngredientStationSprite<IngredientStation>{

    public TomatoStationSprite(IngredientStation model) {
        super(new IngredientStation(IngredientSource Tomato));
    }

    @Override
    public void grab(IngredientStack stack) {

    }
}
