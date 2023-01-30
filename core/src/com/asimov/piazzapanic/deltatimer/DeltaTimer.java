package com.asimov.piazzapanic.deltatimer;

import java.util.ArrayList;

/**
 * Timer implementation using delta time from libGDX.
 * There should be one timer per screen/app. Ensure timer.tick(delta) is called in the render() method.
 */
public class DeltaTimer {
    private float elapsed;

    private final ArrayList<Timer> timers = new ArrayList<>();

    /**
     * Gets the total elapsed time since the timer was started.
     * @return
     */
    public float getTotalElapsed() {
        return elapsed;
    }

    /**
     * Starts a new timer to run the specified callback after the specified delay in seconds.
     * @param seconds Time to wait before running callback.
     * @param callback Code to execute once delay has finished.
     */
    public void start(float seconds, Callback callback) {
        start(seconds, callback, false);
    }

    /**
     * Starts a new timer to run the specified callback after the specified delay in seconds. Option to repeat execution.
     * @param seconds Time to wait before running callback.
     * @param callback Code to execute once delay has finished.
     * @param repeat Whether to repeat the timer. Specified callback will run at intervals of the specified time.
     */
    public void start(float seconds, Callback callback, boolean repeat) {
        timers.add(new Timer(elapsed, seconds, callback, repeat));
    }

    /**
     * Advances the timer by the specified delta (time since the last render). Ensure this method is called every render.
     * @param delta Time elapsed since last render.
     */
    public void tick(float delta) {
        elapsed += delta;

        for (Timer timer : timers) {
            timer.run(elapsed);
        }

        timers.removeIf(Timer::isCompleted);
    }
}
