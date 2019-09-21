package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MyGdxGame extends ApplicationAdapter {

    static final int qntdObstaculos = 2;

    SpriteBatch batch;
    Texture img;
    Audio audio;
    boolean idle;

    long tempoInicial;

    private Barco barco;
    Obstaculo[] obstaculos = new Obstaculo[qntdObstaculos];
    Mundo mundo;
    Iu iu;
    boolean emotion;

    @Override
    public void create() {
        emotion = false;
        idle = false;

        tempoInicial = System.currentTimeMillis();

        batch = new SpriteBatch();
        img = new Texture("badlogic.jpg");
        MyInput inputProcessor = new MyInput();
        Gdx.input.setInputProcessor(inputProcessor);
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
    public void render() {
        super.render();
        Gdx.gl.glClearColor(0, 0, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        update(Gdx.graphics.getDeltaTime());
        batch.begin();
        //batch.draw(img, 0, 0);
        mundo.render(batch);
        for (int i = 0; i < qntdObstaculos; i++) {
            obstaculos[i].render(batch);
        }
        barco.render(batch);
        iu.render(batch, emotion);
        batch.end();
        //System.out.println(Gdx.input.getX());
        //System.out.println(Gdx.input.getY());
    }

    @Override
    public void dispose() {
        batch.dispose();
        //img.dispose();
    }

    public void update(float delta) {
        if ((System.currentTimeMillis() - tempoInicial) / 1000 > 5 && !idle) {
            System.out.println("idle");
            barco.setIdle(true);
            mundo.setIdle(true);
            for (int i = 0; i < qntdObstaculos; i++) {
                obstaculos[i].setIdle(true);
            }
            idle = true;
        }
        if (Gdx.input.isKeyJustPressed(Keys.ESCAPE)) {
            Gdx.app.exit();
        }
        if (Gdx.input.isKeyJustPressed(Keys.Q)) {
            if (emotion) {
                emotion = false;
                audio.pause();
            } else {
                emotion = true;
                audio.play(emotion);
            }
        }
        if (barco.update()) {
            tempoInicial = System.currentTimeMillis();
            if (idle) {
                barco.setIdle(false);
                mundo.setIdle(false);
                for (int i = 0; i < qntdObstaculos; i++) {
                    obstaculos[i].setIdle(false);
                }
                idle = false;
            }
        }
        for (int i = 0; i < qntdObstaculos; i++) {
            obstaculos[i].update();
        }
        if (barco.checaColisao(obstaculos)) {
            reset();
        }
        iu.update();
        // ...
    }
}
