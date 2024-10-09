package com.bullethell.game.entities.bullet.factory;

import com.badlogic.gdx.graphics.Texture;
import com.bullethell.game.entities.bullet.Bullet;
import com.bullethell.game.entities.bullet.MediumBullet;

public class MediumBulletFactory implements BulletFactory {
    @Override
    public Bullet createBullet() {
        MediumBullet bullet = new MediumBullet();
        bullet.setTexture(new Texture("enemyBBullet.png"));
        bullet.getSize().x = 20;
        bullet.getSize().y = 20;
        bullet.getHitbox().setSize(bullet.getSize().x, bullet.getSize().y);
        bullet.setDamage(5);
        return bullet;
    }
}
