package com.bullethell.game.entities.Enemy.factory;

import com.badlogic.gdx.graphics.Texture;
import com.bullethell.game.entities.Enemy.BossB;
import com.bullethell.game.entities.Enemy.Enemy;

public class BossBFactory implements EnemyFactory {

    @Override
    public Enemy createEnemy(int width, int height) {
        Enemy enemy = new BossB(width, height);
        enemy.setTexture(new Texture("bossb.png"));
        enemy.setSpeed(1);
        enemy.setHealth(2000);
        return enemy;
    }

}
