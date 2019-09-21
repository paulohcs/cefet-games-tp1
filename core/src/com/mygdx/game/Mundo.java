/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 *
 * @author a
 */
class Mundo {
    private Texture textura;
    private boolean idle;
    public Mundo(){
        idle = false;
        this.textura = new Texture("sea.png");
    }
    
    public void setIdle(boolean idle){
        this.idle = idle;
    }
    
    public void render(SpriteBatch batch) {
        batch.draw(this.textura, 0,0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
    }
}
