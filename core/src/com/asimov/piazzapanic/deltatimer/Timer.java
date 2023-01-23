package com.asimov.piazzapanic.deltatimer;

class Timer {
    private final float time;
    private final Callback callback;
    private boolean completed = false;

    public Timer(float time, Callback callback) {
        this.time = time;
        this.callback = callback;
    }

    public float getTime() {
        return time;
    }

    public void run() {
        callback.run();
        completed = true;
    }

    public boolean isCompleted() {
        return completed;
    }
}
