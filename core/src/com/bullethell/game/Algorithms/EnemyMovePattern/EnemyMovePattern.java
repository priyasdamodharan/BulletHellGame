package com.bullethell.game.Algorithms.EnemyMovePattern;

import com.badlogic.gdx.utils.TimeUtils;
import com.bullethell.game.entities.Enemy.Enemy;

public abstract class EnemyMovePattern {
    protected long initTime;
    protected  long dTimeToRetrieve;

    public EnemyMovePattern(int attackingDuration)
    {
        initTime = TimeUtils.millis();
        dTimeToRetrieve = attackingDuration;
    }

    public abstract void applyMovePattern(Enemy entity);
    
}
