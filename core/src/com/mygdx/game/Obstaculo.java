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
class Obstaculo {

    private Vector2 posicao;
    private Vector2 velocidade;
    private Texture textura;
    private int ident;
    Random gerador = new Random();
    
    public Vector2 getPosicao(){
        return this.posicao;
    }
    
    public Texture getTextura(){
        return this.textura;
    }
    
    public double getTextureWidth(){
        return this.textura.getWidth() * 0.1;
    }
    
    public double getTextureHeight(){
        return this.textura.getHeight() * 0.1;
    }

    public Obstaculo(int ident) {
        this.textura = new Texture("obs1.png");
        posicao = new Vector2();
        velocidade = new Vector2();
        this.posicao.x = Gdx.graphics.getWidth() + gerador.nextInt(1000);
        this.posicao.y = gerador.nextInt(Gdx.graphics.getHeight()/2);
        //this.posicao.y = 0;
        this.velocidade.x = -200;
        this.ident = ident;
    }

    public void render(SpriteBatch batch) {
        batch.draw(this.textura, this.posicao.x, this.posicao.y, (float) (textura.getWidth()* 0.1), (float) (textura.getHeight()* 0.1));
        System.out.println(ident + " x: " + posicao.x + " y: " + posicao.y);
    }

    public void update() {
        if (posicao.x <= 0) {
            posicao.x = Gdx.graphics.getWidth() + gerador.nextInt(1000);
        }
        posicao.x += velocidade.x * Gdx.graphics.getDeltaTime();
    }
}
