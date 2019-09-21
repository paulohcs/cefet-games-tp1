/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

/**
 *
 * @author a
 */
public class Barco {

    private TextureRegion[][] quadrosDaAnimacao;
    private Vector2 posicao;
    private Vector2 velocidade;
    private Animation atual, navegarPraFrente, navegarPraCima, navegarPraBaixo;
    private float tempoDaAnimacao;
    boolean ia;

    public float getPosicaoX() {
        return this.posicao.x;
    }

    public float getPosicaoY() {
        return this.posicao.y;
    }
    
    public double getTextureWidth(){
        return this.quadrosDaAnimacao[0][0].getRegionWidth() * 0.4;
    }
    
    public double getTextureHeight(){
        return this.quadrosDaAnimacao[0][0].getRegionHeight() * 0.4;
    }

    public Barco(Texture spriteSheet) {
        ia = false;
        quadrosDaAnimacao = TextureRegion.split(spriteSheet, 61, 57);
        posicao = new Vector2();
        velocidade = new Vector2();
        posicao.x = 30;
        posicao.y = 5;
        //posicao.y = (float) (Gdx.graphics.getHeight() - this.getTextureHeight());
        velocidade.y = 0;
        velocidade.x = 0;
        navegarPraFrente = new Animation(0.5f,
                quadrosDaAnimacao[2][0], // 1ª linha, 1ª coluna
                quadrosDaAnimacao[2][1], // idem, 2ª coluna
                quadrosDaAnimacao[2][2],
                quadrosDaAnimacao[2][3],
                quadrosDaAnimacao[2][4]);
        navegarPraFrente.setPlayMode(PlayMode.LOOP_PINGPONG);
        navegarPraCima = new Animation(0.5f,
                quadrosDaAnimacao[1][0], // 1ª linha, 1ª coluna
                quadrosDaAnimacao[1][1], // idem, 2ª coluna
                quadrosDaAnimacao[1][2],
                quadrosDaAnimacao[1][3],
                quadrosDaAnimacao[1][4]);
        navegarPraCima.setPlayMode(PlayMode.LOOP_PINGPONG);
        navegarPraBaixo = new Animation(0.5f,
                quadrosDaAnimacao[3][0], // 1ª linha, 1ª coluna
                quadrosDaAnimacao[3][1], // idem, 2ª coluna
                quadrosDaAnimacao[3][2],
                quadrosDaAnimacao[3][3],
                quadrosDaAnimacao[3][4]);
        navegarPraBaixo.setPlayMode(PlayMode.LOOP_PINGPONG);
        /*andarPraBaixo = new Animation(0.1f,
                quadrosDaAnimacao[0][0], // 1ª linha, 1ª coluna
                quadrosDaAnimacao[0][1], // idem, 2ª coluna
                quadrosDaAnimacao[0][2],
                quadrosDaAnimacao[0][3],
                quadrosDaAnimacao[0][4]);
        andarPraBaixo.setPlayMode(PlayMode.LOOP_PINGPONG);
         andarPraCima = new Animation(0.1f,
                quadrosDaAnimacao[2][0], // 1ª linha, 1ª coluna
                quadrosDaAnimacao[2][1], // idem, 2ª coluna
                quadrosDaAnimacao[2][2],
                quadrosDaAnimacao[2][3],
                quadrosDaAnimacao[2][4]);
        andarPraCima.setPlayMode(PlayMode.LOOP_PINGPONG);*/
        atual = navegarPraFrente;
        //tempoDaAnimacao = 0;
    }

    public void render(SpriteBatch batch) {
        //batch.draw(this.textura, this.posicaoX, this.posicaoY);
        //batch.draw(quadrosDaAnimacao[2][0], posicao.x, posicao.y, 40, 20);
        batch.draw((TextureRegion) atual.getKeyFrame(tempoDaAnimacao), posicao.x, posicao.y, (float) (quadrosDaAnimacao[0][0].getRegionHeight() * 0.4), (float) (quadrosDaAnimacao[0][0].getRegionWidth() * 0.4));
    }

    public void update() {
        tempoDaAnimacao += Gdx.graphics.getDeltaTime();
        posicao.x += velocidade.x * Gdx.graphics.getDeltaTime();
        posicao.y += velocidade.y * Gdx.graphics.getDeltaTime();
        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            if (posicao.y < Gdx.graphics.getHeight() - quadrosDaAnimacao[0][0].getRegionHeight() * 0.4) {
                velocidade.y = 15;
                atual = navegarPraCima;
            }
        } else if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            if (posicao.x > 0) {
                velocidade.x = -15;
            }
        } else if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            if (posicao.y > 0) {
                velocidade.y = -15;
                atual = navegarPraBaixo;
            }
        } else if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            if (posicao.x < Gdx.graphics.getWidth() * 0.25) {
                velocidade.x = 15;
            }
        } else if (!ia) {
            velocidade.x = 0;
            velocidade.y = 0;
            atual = navegarPraFrente;
        }
    }

    private boolean projetaColisao(Barco barco, Obstaculo obstaculo) {
        if ((barco.posicao.y + ((barco.getTextureHeight())/2) >= obstaculo.getPosicao().y)
                && (barco.posicao.y <= obstaculo.getPosicao().y + obstaculo.getTextureHeight())
                && barco.posicao.x + barco.getTextureWidth() <= obstaculo.getPosicao().x) {
            System.out.println("colisao by:"+barco.posicao.y+"oy:"+(obstaculo.getPosicao().y + obstaculo.getTextureHeight()));
            return true;
        } else {
            return false;
        }
    }

    public void iaa(Obstaculo[] obstaculos) {
        int menor = 0;
        for (int i = 0; i < 5; i++) {
            if (obstaculos[i].getPosicao().x < obstaculos[menor].getPosicao().x && 
                    //this.posicao.x + this.getTextureWidth() <= obstaculos[i].getPosicao().x) {
                    this.posicao.x + this.getTextureWidth() < obstaculos[i].getPosicao().x) {
                menor = i;
            }
        }
        System.out.println("barco x: "+this.posicao.x+" y:"+this.posicao.y);
        System.out.println("menor" + menor);
        if (projetaColisao(this, obstaculos[menor])) {
            if(Gdx.graphics.getHeight() - (obstaculos[menor].getPosicao().y + obstaculos[menor].getTextureHeight()) > 
                    this.posicao.y + this.getTextureHeight()){
                atual = navegarPraCima;
                this.velocidade.y = 15;
            } else {
                atual = navegarPraBaixo;
                this.velocidade.y = -15;
            }
            /*if (obstaculos[menor].getPosicao().y + (obstaculos[menor].getTextura().getHeight() / 2)
                    > this.posicao.y + ((this.quadrosDaAnimacao[0][0].getRegionHeight()*0.4) / 2)) {
                System.out.println("ajustando baixo");
                this.velocidade.y = -15;
            } else {
                System.out.println("ajustando cima");
                this.velocidade.y = 15;
            }*/
            
            /*if(obstaculos[menor].getPosicao().y + obstaculos[menor].getTextureHeight() < Gdx.graphics.getHeight()/2){
                System.out.println("abaixo");
                this.velocidade.y = 15;
            } else {
                System.out.println("acima");
                 this.velocidade.y = -15;
            }*/
        } else {
            atual = navegarPraFrente;
            this.velocidade.y = 0;
            System.out.println("prafrnee");
        }
        if (posicao.y < 0) {
            System.out.println("zerandoaa");
            velocidade.y = 0;
            posicao.y = 0;
        } else if (posicao.y > Gdx.graphics.getHeight() - this.getTextureHeight()){
            System.out.println("zerando");
            velocidade.y = 0;
            posicao.y = (float) (Gdx.graphics.getHeight() - this.getTextureHeight());
        }
    }
}