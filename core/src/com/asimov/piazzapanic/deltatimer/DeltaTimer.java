package com.asimov.piazzapanic.deltatimer;

import java.util.ArrayList;

public class DeltaTimer {
    private float elapsed;

    private final ArrayList<Timer> timers = new ArrayList<>();

    public float getTotalElapsed() {
        return elapsed;
    }

    public void start(float seconds, Callback callback) {
        start(seconds, callback, false);
    }

    public void start(float seconds, Callback callback, boolean repeat) {
        timers.add(new Timer(elapsed, seconds, callback, repeat));
    }

    public void tick(float delta) {
        elapsed += delta;

        for (Timer timer : timers) {
            timer.run(elapsed);
        }

        timers.removeIf(Timer::isCompleted);
    }
}
