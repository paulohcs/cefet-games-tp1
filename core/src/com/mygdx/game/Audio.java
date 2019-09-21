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
    Sound[] sound = new Sound[18];
    public Audio(){
        for(int i = 0; i < 18; i++){
            sound[i] = Gdx.audio.newSound(Gdx.files.internal("data/0.mp3"));
        }
    }
}
