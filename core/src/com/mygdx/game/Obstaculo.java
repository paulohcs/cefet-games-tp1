/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game;

import java.util.Random;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

/**
 *
 * @author a
 */
public class Obstaculo {

    private Vector2 posicao, velocidade;
    private int id;
    float dificuldade;
    private Texture textura;
    private Vector2 tamanho;
    Random gerador = new Random();
    
    // Retorna posicao do osbstáculo
    public Vector2 getPosicao(){
        return this.posicao;
    }
    
    // Liga/Desliga modo background
    public void setIdle(boolean idle){
        if(idle){
            this.velocidade.x *= 0.1;
        } else {
            this.velocidade.x *= 10;
        }
    }
    
    public Obstaculo(int id) {
        textura = new Texture("obs"+id+".png");
        posicao = new Vector2();
        velocidade = new Vector2();
        tamanho = new Vector2();
        dificuldade = 1;
        posicao.x = Gdx.graphics.getWidth() + gerador.nextInt(1000);
        posicao.y = gerador.nextInt((int) (Gdx.graphics.getHeight()-this.getTextureHeight()));
        velocidade.x = -100;
        
        this.id = id;
        
        if(id == 2){
            tamanho.x = (float) 0.3;
            tamanho.y = (float) 0.2;
        } else {
            tamanho.x = (float) 0.08;
            tamanho.y = (float) 0.08;
        }
    }

    // Renderiza obstáculo
    public void render(SpriteBatch batch) {
        batch.draw(this.textura, this.posicao.x, this.posicao.y, (float) (textura.getWidth()* tamanho.x), (float) (textura.getHeight()* tamanho.y));
        System.out.println(id + " x: " + posicao.x + " y: " + posicao.y);
        System.out.println(id + " vx: " + velocidade.x + " vy: " + velocidade.y);
    }

    // Atualiza obstáculo
    public void update() {
        if (posicao.x <= 0) {
            this.posicao.x = Gdx.graphics.getWidth() + gerador.nextInt(1000);
            this.posicao.y = gerador.nextInt((int) (Gdx.graphics.getHeight()-this.getTextureHeight()));
        }
        posicao.x += velocidade.x * Gdx.graphics.getDeltaTime();
        dificuldade += 0.00001 * Gdx.graphics.getDeltaTime();
        velocidade.x *= dificuldade;
    }
    
    // Retorna textura
     public Texture getTextura(){
        return this.textura;
    }
    
     // Retorna tamanho em X
    public double getTextureWidth(){
        return this.textura.getWidth() * tamanho.x;
    }
    
    // Retorna tamanho em Y
    public double getTextureHeight(){
        return this.textura.getHeight() * tamanho.y;
    }
}
