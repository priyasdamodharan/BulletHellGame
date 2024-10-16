package com.bullethell.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.bullethell.game.world.GameStateManager;


public class BulletHellGame extends ApplicationAdapter {

    public static final int WIDTH = 480;
    public static final int HEIGHT = 800;
    public static final String TITLE = "Bullet Hell";

    private OrthographicCamera camera;
    private SpriteBatch batch;
    private GameStateManager gsm;

    @Override
    public void create () {
        batch = new SpriteBatch();
        camera = new OrthographicCamera();
        camera.setToOrtho(false, WIDTH, HEIGHT);
        camera.update();
        gsm = GameStateManager.getInstance();
    }

    @Override
    public void render () {

        Gdx.gl.glClearColor(0,0,0.2f,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();

        batch.setProjectionMatrix(camera.combined);

        gsm.update();
        gsm.render(camera, batch);
    }

    @Override
    public void dispose () {
        batch.dispose();
    }
}
