/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

/**
 *
 * @author a
 */
public class Audio {

    Music sea, emotion;

    public Audio() {
        sea = Gdx.audio.newMusic(Gdx.files.internal("data/sea.mp3"));
        sea.setLooping(true);
        emotion = Gdx.audio.newMusic(Gdx.files.internal("data/emotion.mp3"));
        emotion.setLooping(true);
    }

    public void play(boolean emotion) {
        if (emotion) {
            this.emotion.play();
        } else {
            this.sea.play();
        }
    }

    public void stop() {
        this.emotion.stop();
    }
    
    public void pause(){
        this.emotion.pause();
    }
    
}
