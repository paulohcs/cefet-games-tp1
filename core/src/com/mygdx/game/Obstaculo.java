/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game;

import java.util.Random;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;

/**
 *
 * @author a
 */
public class Obstaculo {

    protected Vector2 posicao;
    private Vector2 velocidade;
    private int ident;
    private Texture textura;
    private Vector2 size;
    private boolean idle;
    Random gerador = new Random();
    
    public Vector2 getPosicao(){
        return this.posicao;
    }
    
    public void setIdle(boolean idle){
        if(idle){
            this.velocidade.x *= 0.1;
        } else {
            this.velocidade.x *= 10;
        }
    }
    
    public Obstaculo(int ident) {
        this.idle = false;
        this.textura = new Texture("obs"+ident+".png");
        posicao = new Vector2();
        velocidade = new Vector2();
        size = new Vector2();
        this.posicao.x = Gdx.graphics.getWidth() + gerador.nextInt(1000);
        this.posicao.y = gerador.nextInt((int) (Gdx.graphics.getHeight()-this.getTextureHeight()));
        this.velocidade.x = -100;
        this.ident = ident;
        if(ident == 2){
            size.x = (float) 0.3;
            size.y = (float) 0.2;
        } else {
            size.x = (float) 0.08;
            size.y = (float) 0.08;
        }
    }

    public void render(SpriteBatch batch) {
        batch.draw(this.textura, this.posicao.x, this.posicao.y, (float) (textura.getWidth()* size.x), (float) (textura.getHeight()* size.y));
        System.out.println(ident + " x: " + posicao.x + " y: " + posicao.y);
    }

    public void update() {
        if (posicao.x <= 0) {
            this.posicao.x = Gdx.graphics.getWidth() + gerador.nextInt(1000);
            this.posicao.y = gerador.nextInt((int) (Gdx.graphics.getHeight()-this.getTextureHeight()));
        }
        posicao.x += velocidade.x * Gdx.graphics.getDeltaTime();
    }
    
    
     public Texture getTextura(){
        return this.textura;
    }
    
    public double getTextureWidth(){
        return this.textura.getWidth() * size.x;
    }
    
    public double getTextureHeight(){
        return this.textura.getHeight() * size.y;
    }
}
