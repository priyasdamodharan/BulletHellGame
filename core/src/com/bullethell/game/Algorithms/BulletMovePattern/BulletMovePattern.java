package com.bullethell.game.Algorithms.BulletMovePattern;

import com.badlogic.gdx.utils.TimeUtils;
import com.bullethell.game.entities.Enemy.Enemy;
import com.bullethell.game.entities.bullet.*;
import com.bullethell.game.entities.bullet.factory.BigBulletFactory;
import com.bullethell.game.entities.bullet.factory.BulletFactory;
import com.bullethell.game.entities.bullet.factory.MediumBulletFactory;
import com.bullethell.game.entities.bullet.factory.SmallBulletFactory;

import java.util.List;

public abstract class BulletMovePattern {

    protected List<BulletSource> bulletSources;
    protected List<List<Bullet>> bulletsList;
    protected int speed;
    protected Enemy enemy;
    protected int countBwBullets;
    protected int maxNumBullets;

    private String bulletType;
    private long existingTime;
    private int count;

    BulletFactory factory;

    public BulletMovePattern(List<BulletSource> bulletSources,
                             List<List<Bullet>> bulletsList,
                             String bulletType, Enemy enemy){
        this.bulletSources = bulletSources;
        this.bulletsList = bulletsList;
        count = 0;
        countBwBullets = 10;
        this.bulletType = bulletType;
        speed = 4;
        this.enemy = enemy;
        existingTime = 6000;
        maxNumBullets = 9999;
    };

    protected Bullet createBullet()
    {
        switch (bulletType)
        {
            case "SmallBullet":
                factory = new SmallBulletFactory();
                break;
            case "BigBullet":
                factory = new BigBulletFactory();
                break;
            default:
                factory = new MediumBulletFactory();
                break;
        }
        return factory.createBullet();
    }

    public void update(float delta, boolean enemyDie)
    {
        if(!enemyDie)
            if (count % countBwBullets == 0 && bulletsList.size()>0 &&
                    bulletsList.get(0).size()< maxNumBullets)
                addBullets();

        cleanUpBullets();

        count++;
    }

    private void addBullets()
    {
        for (int i = 0; i < bulletSources.size(); i++) {
            Bullet newBullet = createBullet();
            float x = bulletSources.get(i).getPos().x;
            float y = bulletSources.get(i).getPos().y;
            newBullet.setPos(x, y);
            bulletsList.get(i).add(newBullet);
        }
    }

    private void cleanUpBullets()
    {
        for (int i = 0; i < bulletsList.size(); i++)
            for (int j = 0; j < bulletsList.get(i).size(); j++) {
                if (bulletsList.get(i).get(j).outOfBorder(0) ||
                        TimeUtils.millis() - bulletsList.get(i).get(j).getInitTime() > existingTime)
                    bulletsList.get(i).remove(j);
            }
    }
}
