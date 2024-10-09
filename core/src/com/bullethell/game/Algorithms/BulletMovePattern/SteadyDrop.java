package com.bullethell.game.Algorithms.BulletMovePattern;


import com.bullethell.game.entities.Enemy.Enemy;
import com.bullethell.game.entities.bullet.Bullet;
import com.bullethell.game.entities.bullet.BulletSource;

import java.util.List;

public class SteadyDrop extends BulletMovePattern {

    public SteadyDrop(List<BulletSource> bulletSources,
                      List<List<Bullet>> bulletsList,
                      String bulletType, Enemy enemy) {
        super(bulletSources, bulletsList, bulletType, enemy);
    }

    public void update(float delta, boolean enemyDie) {
        super.update(delta, enemyDie);

        float curX, curY, newX, newY;
        //move bullets
        for (int i = 0; i < bulletsList.size(); i++)
            for (int j = 0; j < bulletsList.get(i).size(); j++) {
                curX = bulletsList.get(i).get(j).getPos().x;
                curY = bulletsList.get(i).get(j).getPos().y;
                newX = curX;
                newY = curY-speed;
                bulletsList.get(i).get(j).setPos(newX, newY);
            }
    }
}