/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 *
 * @author a
 */
class Iu {
    BitmapFont font;
    int pontos;
    public Iu(){
        font = new BitmapFont();
        pontos = 0;
    }
    
    public void reset(){
        pontos = 0;
    }
    
    public void render(SpriteBatch batch, boolean emotion){
        font.draw(batch, Integer.toString(pontos), Gdx.graphics.getWidth()/100, Gdx.graphics.getHeight()/4);
        if(emotion){
            font.setColor(1, 0, 0, 1);
            //font.getData().setScale((float) 0.9);
            font.draw(batch, "COM EMOÇÃO", (float) (Gdx.graphics.getWidth()*0.85), (float) (Gdx.graphics.getHeight()*0.9));
        } else {
            font.setColor(1, 1, 1, 1);
            //font.getData().setScale((float) 0.9);
            font.draw(batch, "Sem Emoção", (float) (Gdx.graphics.getWidth()*0.85), (float) (Gdx.graphics.getHeight()*0.9));
        }
    }
    
    public void update(){
        pontos++;
    }
}
