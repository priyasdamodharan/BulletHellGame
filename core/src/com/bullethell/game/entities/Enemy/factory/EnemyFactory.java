package com.bullethell.game.entities.Enemy.factory;

import com.bullethell.game.entities.Enemy.Enemy;

public interface EnemyFactory {

    public Enemy createEnemy(int width, int height);

}
