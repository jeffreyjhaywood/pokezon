package com.pokezon.util;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

/*  sound.wav file goes inside pokezon directory, outside src directory
    Call method in Game.java first line inside begin() method with the following code
    Thread soundThread = new Thread(new Sound()); // will run at the same time in it's own thread
    soundThread.start();
 */
public class Sound implements Runnable {

    @Override
    public void run(){

        File myLocalFile = new File("sound.wav");

        try{
            AudioInputStream myLocalMp3 = AudioSystem.getAudioInputStream(myLocalFile);
            Clip mySound = AudioSystem.getClip();
            mySound.open(myLocalMp3);
            mySound.start();
            Thread.sleep(14000);
        }catch (Exception e){
            System.err.println("Error with mySoundMethod");
        }

    }

}
