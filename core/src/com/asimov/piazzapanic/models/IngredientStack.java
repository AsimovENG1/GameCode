package com.asimov.piazzapanic.models;

import com.badlogic.gdx.utils.Array;

import java.util.Collection;
import java.util.Iterator;

public class IngredientStack implements Iterable<Ingredient> {
    private Array<Ingredient> items = new Array<>();

    @Override
    public Iterator<Ingredient> iterator() {
        return items.iterator();
    }

    public void grab(Ingredient ingredient) {
        items.add(ingredient);
    }

    public Ingredient place() {
        return items.pop();
    }

    public Ingredient peek() {
        return items.peek();
    }

    public int size() {
        return items.size;
    }
}
