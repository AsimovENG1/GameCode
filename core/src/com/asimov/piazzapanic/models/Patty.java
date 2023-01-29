package com.asimov.piazzapanic.models;

/**
 * A burger patty. Both fryable and choppable. Must be formed at a chopping station then fried but will not prevent early frying.
 */
public class Patty extends BaseFryable implements Choppable {
    private boolean isFormed = false;

    @Override
    public void chop() {
        isFormed = true;
    }

    @Override
    public boolean isChopped() {
        return isFormed();
    }

    public boolean isFormed() {
        return isFormed;
    }
}
