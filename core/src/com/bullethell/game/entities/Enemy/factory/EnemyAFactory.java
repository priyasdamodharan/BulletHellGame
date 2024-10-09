package com.bullethell.game.entities.Enemy.factory;

import com.badlogic.gdx.graphics.Texture;
import com.bullethell.game.entities.Enemy.Enemy;
import com.bullethell.game.entities.Enemy.EnemyA;

public class EnemyAFactory implements EnemyFactory {

    @Override
    public Enemy createEnemy(int width, int height) {
        Enemy enemy = new EnemyA(width, height);
        enemy.setTexture(new Texture("EnemyA.png"));
        enemy.setSpeed(1);
        enemy.setHealth(50);
        return enemy;
    }

}
