package com.asimov.piazzapanic;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Array;

import java.util.ArrayList;

public class ChefStackActor extends Table {
    private ArrayList<String> stack;

    private Array<ChefStackSlotActor> slots = new Array<>();

    public ChefStackActor(String text, Skin skin, ArrayList<String> stack) {
        super();

        this.stack = stack;

        Label label = new Label(text, skin);
        add(label);

        for (int i = 0; i < 3; i++) {
            row();

            ChefStackSlotActor slot = new ChefStackSlotActor();

            slots.add(slot);
            add(slot);
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        updateSlots();

        super.draw(batch, parentAlpha);
    }

    private void updateSlots() {
        for (int i = 0; i < 3; i++) {
            slots.get(i).setIngredient(stack.get(i));
        }
    }
}
