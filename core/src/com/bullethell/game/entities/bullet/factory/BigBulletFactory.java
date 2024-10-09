package com.bullethell.game.entities.bullet.factory;

import com.badlogic.gdx.graphics.Texture;
import com.bullethell.game.entities.bullet.BigBullet;
import com.bullethell.game.entities.bullet.Bullet;

public class BigBulletFactory implements BulletFactory {
    @Override
    public Bullet createBullet() {
        BigBullet bullet = new BigBullet();
        bullet.setTexture(new Texture("cloudBullet.png"));
        bullet.getSize().x = 30;
        bullet.getSize().y = 30;
        bullet.getHitbox().setSize(bullet.getSize().x, bullet.getSize().y);
        bullet.setDamage(10);
        return bullet;

    }
}
