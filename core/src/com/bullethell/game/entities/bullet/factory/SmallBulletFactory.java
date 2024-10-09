package com.bullethell.game.entities.bullet.factory;

import com.badlogic.gdx.graphics.Texture;
import com.bullethell.game.entities.bullet.Bullet;
import com.bullethell.game.entities.bullet.SmallBullet;

public class SmallBulletFactory implements BulletFactory {
    @Override
    public Bullet createBullet() {
        SmallBullet bullet = new SmallBullet();
        bullet.setTexture(new Texture("enemyABullet.png"));
        bullet.getSize().x = 10;
        bullet.getSize().y = 10;
        bullet.getHitbox().setSize(bullet.getSize().x, bullet.getSize().y);
        bullet.setDamage(2);
        return bullet;

    }
}
