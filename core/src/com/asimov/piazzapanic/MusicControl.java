package com.asimov.piazzapanic;

public class MusicControl {

    public static float musicvolume = 0.6f;
    public static long umm;
    public static long hmm;


    public void increaseMusic() {
        if (musicvolume < 1) {
            musicvolume += 0.2f;

        }
    }

    public void decreaseMusic() {
        if(musicvolume > 0){
            musicvolume -= 0.2f;
        }
    }

    public static void playguitar() {
        ScenarioMode.guitar.play(musicvolume);
    }

    public static void stopguitar(){
        ScenarioMode.guitar.stop();
    }

    public static void loopguitar(){ ScenarioMode.guitar.setLooping(hmm,true);}

    public static void setguitar(){
        ScenarioMode.guitar.setVolume(umm,musicvolume);
    }










}
