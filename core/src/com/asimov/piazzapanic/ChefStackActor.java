package com.asimov.piazzapanic;

import com.asimov.piazzapanic.models.IngredientStack;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Array;

public class ChefStackActor extends Table {
    private final IngredientStack stack;

    private final Array<ChefStackSlotActor> slots = new Array<>();

    public ChefStackActor(String text, Skin skin, IngredientStack stack) {
        super();

        this.stack = stack;

        Label label = new Label(text, skin);

        label.setFontScale(2);

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
            slots.get(i).setIngredient(stack.items.get(i));
        }
    }
}
