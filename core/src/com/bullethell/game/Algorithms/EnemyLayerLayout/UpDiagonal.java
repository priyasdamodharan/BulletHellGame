package com.bullethell.game.Algorithms.EnemyLayerLayout;

import com.bullethell.game.entities.Enemy.Enemy;
import com.bullethell.game.BulletHellGame;

import java.util.List;

//a diagonal line / that covers half of the width of the game window
//the enemies come from top right corner of the window
public class UpDiagonal extends EnemyLayerLayout {
    public void applyLayout(List<Enemy> layer) {
        //calculation
        int entityWidth = (int)layer.get(0).getSize().x;
        int numEntities = layer.size();
        int dx = 0;
        int dy = 30;

        if (numEntities > 1)
            dx = (int)(BulletHellGame.WIDTH - entityWidth)/(numEntities - 1)/2;

        //set positions for each enemy in the layer
        if (numEntities == 1)
            layer.get(0).setPos(-entityWidth, BulletHellGame.HEIGHT);
        else {
            for (int i = 0; i < numEntities; i++) {
                float x = i * dx + BulletHellGame.WIDTH;
                float y = BulletHellGame.HEIGHT + i*dy;
                layer.get(i).setPos(x, y);
            }
        }
    }
}
