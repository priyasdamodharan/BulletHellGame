package com.bullethell.game.Algorithms.EnemyLayerLayout;

import com.bullethell.game.entities.Enemy.Enemy;
import com.bullethell.game.BulletHellGame;

import java.util.List;

//make a V shape
//the enemies come from top of the window
public class V extends EnemyLayerLayout {
    private int entityWidth;
    private int numEntities;
    private int dx = 0;
    private int dy = 40;
    private float x, y;
    private List<Enemy> layer;

    public void applyLayout(List<Enemy> layer) {
        //calculation
        entityWidth = (int)layer.get(0).getSize().x;
        numEntities = layer.size();
        this.layer = layer;

        if (numEntities > 1)
            dx = (int)(BulletHellGame.WIDTH - entityWidth)/(numEntities - 1);

        //set positions for each enemy in the layer
        if (numEntities == 1)
            layer.get(0).setPos(BulletHellGame.WIDTH/2-entityWidth/2, BulletHellGame.HEIGHT);
        else { //more than 1 item => V shape
            if (numEntities % 2 == 1)
                Vodd();
            else
                Veven();
        }
    }

    private void Vodd()
    {
        for (int i = 0; i < numEntities; i++) {
            int offset_y = Math.abs(numEntities / 2 - i) * dy;
            x = i * dx;
            y = BulletHellGame.HEIGHT + offset_y;
            layer.get(i).setPos(x, y);
        }
    }

    private void Veven()
    {
        for (int i = 0; i < numEntities/2; i++) {
            x = i * dx;
            y = BulletHellGame.HEIGHT + dy * (numEntities / 2 - 1 - i);
            layer.get(i).setPos(x, y); //left items
            x = (numEntities - i-1) * dx;
            y = BulletHellGame.HEIGHT + dy * (numEntities / 2 - 1 - i);
            layer.get(numEntities - i-1).setPos(x, y); //right items
        }
    }
}
