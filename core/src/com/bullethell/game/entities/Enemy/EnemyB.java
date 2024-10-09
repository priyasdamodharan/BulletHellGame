package com.bullethell.game.entities.Enemy;

import com.badlogic.gdx.graphics.Texture;

public class EnemyB extends Enemy {
    public EnemyB(int width, int height)
    {
        super(width, height);
        texture = new Texture("enemyTexture1.png");
        speed = 2;
        health = 100;
    }
}

