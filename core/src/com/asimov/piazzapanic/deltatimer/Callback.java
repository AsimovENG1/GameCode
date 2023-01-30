package com.asimov.piazzapanic.deltatimer;

/**
 * Interface for timer callbacks. It is preferable to use lambda expressions for timer callbacks, for example timer.start(1, () -&gt; doSomething()).
 */
public interface Callback {

    /**
     * Override with the code to execute when callback is run.
     */
    public void run();
}
