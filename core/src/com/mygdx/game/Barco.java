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
    private Texture spritesheet;
    boolean idle;

    public float getPosicaoX() {
        return this.posicao.x;
    }

    public float getPosicaoY() {
        return this.posicao.y;
    }

    public double getTextureWidth() {
        return this.quadrosDaAnimacao[0][0].getRegionWidth() * 0.4;
    }

    public double getTextureHeight() {
        return this.quadrosDaAnimacao[0][0].getRegionHeight() * 0.4;
    }
    
    public void setIdle(boolean idle){
        this.idle = idle;
    }

    public Barco() {
        idle = false;
        spritesheet = new Texture("boat.png");
        quadrosDaAnimacao = TextureRegion.split(spritesheet, 61, 57);
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

    public boolean update() {
        tempoDaAnimacao += Gdx.graphics.getDeltaTime();
        posicao.x += velocidade.x * Gdx.graphics.getDeltaTime();
        posicao.y += velocidade.y * Gdx.graphics.getDeltaTime();
        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            if (posicao.y < Gdx.graphics.getHeight() - this.getTextureHeight()) {
                velocidade.y = 15;
                atual = navegarPraCima;
            } else {
                velocidade.x = 0;
                velocidade.y = 0;
                atual = navegarPraFrente;
            }
        } else if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            if (posicao.x > 0) {
                velocidade.x = -15;
            }
        } else if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            if (posicao.y > 0) {
                velocidade.y = -15;
                atual = navegarPraBaixo;
            } else {
                velocidade.x = 0;
                velocidade.y = 0;
                atual = navegarPraFrente;
            }
        } else if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            if (posicao.x < Gdx.graphics.getWidth() * 0.25) {
                velocidade.x = 15;
            }
        } else {
            velocidade.x = 0;
            velocidade.y = 0;
            atual = navegarPraFrente;
            return false;
        }
        return true;
    }

    public boolean checaColisao(Obstaculo[] obstaculos) {
        for (int i = 0; i < obstaculos.length; i++) {
            if ((this.posicao.x + this.getTextureWidth() >= obstaculos[i].getPosicao().x)
                    && (this.posicao.x <= obstaculos[i].getPosicao().x + obstaculos[i].getTextureWidth())
                    && (this.posicao.y + (this.getTextureHeight() / 2) >= obstaculos[i].getPosicao().y)
                    && (this.posicao.y <= obstaculos[i].getPosicao().y + obstaculos[i].getTextureHeight())) {
                return true;
            }
        }
        return false;
    }
}
