package com.bullethell.game.Algorithms.EnemyLayerLayout;

import com.bullethell.game.entities.Enemy.Enemy;
import com.bullethell.game.BulletHellGame;

import java.util.List;

//a horizontal line that covers half of the width of the game window
//the enemies come from top of the window
public class HorizontalHalfWidth extends EnemyLayerLayout {
    public void applyLayout(List<Enemy> layer) {
        //calculation
        int entityWidth = (int)layer.get(0).getSize().x;
        int numEntities = layer.size();
        int dx = 0;

        if (numEntities > 1)
            dx = (int)(BulletHellGame.WIDTH - entityWidth)/(numEntities - 1)/2;

        //set positions for each enemy in the layer
        if (numEntities == 1)
            layer.get(0).setPos(BulletHellGame.WIDTH/2-entityWidth/2, BulletHellGame.HEIGHT);
        else {
            for (int i = 0; i < numEntities; i++) {
                layer.get(i).setPos(i * dx + BulletHellGame.WIDTH / 4, BulletHellGame.HEIGHT);
            }
        }
    }
}
