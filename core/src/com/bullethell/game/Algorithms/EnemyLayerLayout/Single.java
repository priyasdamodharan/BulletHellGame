package com.bullethell.game.Algorithms.EnemyLayerLayout;

import com.bullethell.game.entities.Enemy.Enemy;
import com.bullethell.game.BulletHellGame;

import java.util.List;

//a single enemy only shows from center top of the game window
public class Single extends EnemyLayerLayout {

    public void applyLayout(List<Enemy> layer) {
        int x = (int)(BulletHellGame.WIDTH/2-layer.get(0).getSize().x/2);
        layer.get(0).setPos(x, BulletHellGame.HEIGHT);
    }
}
