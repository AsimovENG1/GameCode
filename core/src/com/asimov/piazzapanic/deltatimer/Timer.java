package com.asimov.piazzapanic.deltatimer;

class Timer {
    private final float startTime;
    private final float seconds;
    private final Callback callback;
    private final boolean repeat;
    private int timesExecuted;
    private boolean completed = false;

    public Timer(float startTime, float seconds, Callback callback, boolean repeat) {
        this.startTime = startTime;
        this.seconds = seconds;
        this.callback = callback;
        this.repeat = repeat;
    }

    public float getSeconds() {
        return seconds;
    }

    public int getTimesExecuted() {
        return timesExecuted;
    }

    public void run(float delta) {
        if (isCompleted()) {
            return;
        }

        if (delta < startTime + seconds * (timesExecuted + 1)) {
            return;
        }

        callback.run();
        timesExecuted++;
        completed = !repeat;
    }

    public boolean isCompleted() {
        return completed;
    }
}
