package com.asimov.piazzapanic.deltatimer;

import java.util.ArrayList;

public class DeltaTimer {
    private float elapsed;

    private final ArrayList<Timer> timers = new ArrayList<>();

    public float getTotalElapsed() {
        return elapsed;
    }

    public void start(float seconds, Callback callback) {
        timers.add(new Timer(elapsed + seconds, callback));
    }

    public void tick(float delta) {
        elapsed += delta;

        for (Timer callback : timers) {
            if (callback.isCompleted()) {
                continue;
            }

            if (callback.getTime() >= elapsed) {
                callback.run();
            }
        }

        timers.removeIf(Timer::isCompleted);
    }
}
