package com.bullethell.game.entities.Enemy.factory;

import com.badlogic.gdx.graphics.Texture;
import com.bullethell.game.entities.Enemy.Enemy;
import com.bullethell.game.entities.Enemy.EnemyB;

public class EnemyBFactory implements EnemyFactory {

    @Override
    public Enemy createEnemy(int width, int height) {
        Enemy enemy = new EnemyB(width, height);
        enemy.setTexture(new Texture("enemyTexture1.png"));
        enemy.setSpeed(1);
        enemy.setHealth(50);
        return enemy;
    }

}
