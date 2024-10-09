package com.bullethell.game.entities.bullet.factory;

import com.badlogic.gdx.graphics.Texture;
import com.bullethell.game.entities.bullet.Bullet;
import com.bullethell.game.entities.bullet.HeroBullet;

public class HeroBulletFactory implements BulletFactory {
    @Override
    public Bullet createBullet() {
        HeroBullet bullet = new HeroBullet();
        bullet.setTexture(new Texture("heroBullet.png"));
        bullet.getSize().x = 20;
        bullet.getSize().y = 20;
        bullet.getHitbox().setSize(bullet.getSize().x, bullet.getSize().y);
        bullet.setDamage(10);
        return bullet;

    }
}
