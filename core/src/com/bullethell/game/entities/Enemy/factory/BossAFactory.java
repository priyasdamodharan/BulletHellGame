package com.bullethell.game.entities.Enemy.factory;

import com.badlogic.gdx.graphics.Texture;
import com.bullethell.game.entities.Enemy.BossA;
import com.bullethell.game.entities.Enemy.Enemy;

public class BossAFactory implements EnemyFactory {

    @Override
    public Enemy createEnemy(int width, int height) {
        Enemy enemy = new BossA(width, height);
        enemy.setTexture(new Texture("boss.png"));
        enemy.setSpeed(1);
        enemy.setHealth(1000);
        return enemy;
    }

}
