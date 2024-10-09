package com.bullethell.game.Algorithms.EnemyLayerLayout;

import com.bullethell.game.entities.Enemy.Enemy;
import com.bullethell.game.BulletHellGame;

import java.util.List;

//a vertical line
//the enemies come from top of the window
public class Vertical extends EnemyLayerLayout {
    public void applyLayout(List<Enemy> layer) {
        //calculation
        int entityWidth = (int)layer.get(0).getSize().x;
        int entityHeight = (int)layer.get(0).getSize().y;
        int numEntities = layer.size();
        int dy = entityWidth;

        for (int i = 0; i < numEntities; i++) {
            float x = BulletHellGame.WIDTH/2-layer.get(0).getSize().x/2;
            float y = BulletHellGame.HEIGHT + dy*i;
            layer.get(i).setPos(x, y);
        }
    }
}

