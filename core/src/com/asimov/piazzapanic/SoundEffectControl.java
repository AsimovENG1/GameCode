package com.asimov.piazzapanic;

import com.badlogic.gdx.Game;

public class SoundEffectControl {
    public static float volume = 0.6f;
    public static long anything;

    public static long meow;

    public void increaseSoundeffect() {
        if(volume < 1) {
            volume += 0.2f;
        }
    }

    public void decreaseSoundeffect() {
        if (volume > 0) {
            volume -= 0.2f;
        }
    }

    public static void setmvolume(){
        ScenarioMode.bell.setVolume(anything,volume);
    }
    public static void playBell() {
        ScenarioMode.bell.play(volume);
    }

    public static void playWin(){
        ScenarioMode.win.play(volume);
    }

    public static void lose(){
        ScenarioMode.losing.play(volume);
    }

    public static void buttonclicking(){
        Quitting.buclick.play(volume);
    }

    public static void buttonclicking1(){
        MainMenuScreen.buclick1.play(volume);
    }

    public static void buttonquit(){
        MainMenuScreen.backquit.play(volume);
    }

    public static void buclicking2(){
        SettingsScreen.buclick2.play(volume);
    }

    public static void instructionbuttoncl(){
        InstructionScreen.instclick.play(volume);
    }

    public static void scenariobutton(){
        GameScreen.gamestart.play(volume);
    }

    public static void scenarioback(){
        GameScreen.gamebackb.play(volume);
    }

    public static void endlessbbutton(){
        EndlessMode.endlessback.play(volume);
    }

    public static void playGrab() {
        ScenarioMode.grab.play(volume);
    }

    public static void playplace(){
        ScenarioMode.place.play(volume);
    }

    public static void playCompleted(){
        ScenarioMode.Completed.play(volume);
    }

    public static void playChop(){
        ScenarioMode.chop.play(volume);
    }

    public static void setFrying(){
        GrillStationSprite.frying.setVolume(volume);
    }

    public static void playFrying(){
        GrillStationSprite.frying.play();
    }
    public static void loopFrying(){
        GrillStationSprite.frying.setLooping(true);
    }

    public static void stopFrying(){
        GrillStationSprite.frying.stop();
    }

}
