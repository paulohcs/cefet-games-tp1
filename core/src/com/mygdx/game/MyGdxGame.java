package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MyGdxGame extends ApplicationAdapter {

    // Define a quantidade de obstáculos
    static final int qntdObstaculos = 3; 

    // Indicador de background e modo emoção
    boolean emotion;
    boolean idle;

    // Tempo para usar o modo background
    long tempoInicial;

    // Objetos do jogo
    private Barco barco;
    Obstaculo[] obstaculos = new Obstaculo[qntdObstaculos];
    Mundo mundo;
    Iu iu;
    SpriteBatch batch;
    Audio audio;

    @Override
    // Instancia batch, audio, obstáculos, barco, iu e mundo
    public void create() { 
        Gdx.graphics.setTitle("Titanic 2");
        
        emotion = false;
        idle = false;

        tempoInicial = System.currentTimeMillis();

        batch = new SpriteBatch();
        audio = new Audio();
        audio.play(emotion);

        iu = new Iu();

        barco = new Barco();

        for (int i = 0; i < qntdObstaculos; i++) {
            obstaculos[i] = new Obstaculo(i + 1);
        }

        mundo = new Mundo();

        Gdx.gl.glClearColor(1, 1, 1, 1);
    }

    // Reseta o jogo, quando há colisão
    public void reset() {
        tempoInicial = System.currentTimeMillis();
        barco = new Barco();
        idle = false;
        for (int i = 0; i < qntdObstaculos; i++) {
            obstaculos[i] = new Obstaculo(i + 1);
        }
        iu.reset();
        mundo = new Mundo();
    }

    @Override
    // Renderiza mundo, barco, iu e obstáculo
    public void render() {
        super.render();
        Gdx.gl.glClearColor(0, 0, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        update(Gdx.graphics.getDeltaTime());
        batch.begin();
        mundo.render(batch);
        for (int i = 0; i < qntdObstaculos; i++) {
            obstaculos[i].render(batch);
        }
        barco.render(batch);
        iu.render(batch, emotion);
        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
    }

    public void update(float delta) {
        // Checa o tempo sem input, para ligar ou não o modo background
        if ((System.currentTimeMillis() - tempoInicial) / 1000 > 5 && !idle) {
            System.out.println("idle");
            barco.setIdle(true);
            for (int i = 0; i < qntdObstaculos; i++) {
                obstaculos[i].setIdle(true);
            }
            idle = true;
        }
        // Sai do jogo
        if (Gdx.input.isKeyJustPressed(Keys.ESCAPE)) {
            Gdx.app.exit();
        }
        // Liga/Desliga o modo emoção
        if (Gdx.input.isKeyJustPressed(Keys.Q)) {
            if (emotion) {
                emotion = false;
                audio.pause();
            } else {
                emotion = true;
                audio.play(emotion);
            }
        }
        // Reseta a contagem caso haja input do jogador
        if (barco.update()) {
            tempoInicial = System.currentTimeMillis();
            // Se estava em background, desliga o modo background
            if (idle) {
                barco.setIdle(false);
                for (int i = 0; i < qntdObstaculos; i++) {
                    obstaculos[i].setIdle(false);
                }
                idle = false;
            }
        }
        // Atualiza os obstáculos
        for (int i = 0; i < qntdObstaculos; i++) {
            obstaculos[i].update();
        }
        // Checa colisão do barco com obstáculos
        if (barco.checaColisao(obstaculos)) {
            reset();
        }
        // Atualiza IU
        iu.update();
    }
}
