package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MyGdxGame extends ApplicationAdapter {

    SpriteBatch batch;
    Texture img;
    Audio audio;
    private Texture spritesheet;
    private Barco barco;
    Obstaculo[] obstaculos = new Obstaculo[5];
    Mundo mundo;

    @Override
    public void create() {
        batch = new SpriteBatch();
        img = new Texture("badlogic.jpg");
        MyInput inputProcessor = new MyInput();
        Gdx.input.setInputProcessor(inputProcessor);
        audio = new Audio();

        spritesheet = new Texture("boat.png");

        barco = new Barco(spritesheet);
        
        for(int i = 0; i < 5; i++){
            obstaculos[i] = new Obstaculo(i);
        }
        
        mundo = new Mundo();

        Gdx.gl.glClearColor(1, 1, 1, 1);
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0, 0, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
        update(Gdx.graphics.getDeltaTime());
        batch.begin();
        //batch.draw(img, 0, 0);
        mundo.render(batch);
        for(int i = 0; i < 5; i++){
            obstaculos[i].render(batch);
        }
        barco.render(batch);
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
        if (Gdx.input.isKeyJustPressed(Keys.ESCAPE)) {
            Gdx.app.exit();
        }
        barco.update();
        for(int i = 0; i < 5; i++){
            obstaculos[i].update();
        }
        if(barco.ia){
            barco.iaa(obstaculos);
        }
        // ...
    }
}
