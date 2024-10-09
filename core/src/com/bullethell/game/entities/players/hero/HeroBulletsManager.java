package com.bullethell.game.entities.players.hero;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.bullethell.game.entities.bullet.Bullet;
import com.bullethell.game.entities.bullet.HeroBullet;
import com.bullethell.game.entities.bullet.factory.BulletFactory;
import com.bullethell.game.entities.bullet.factory.HeroBulletFactory;

import java.util.ArrayList;
import java.util.List;

public class HeroBulletsManager {
    private List<Bullet> bullets;
    private int bulletSpeed;
    private Sound shootSound;

    public HeroBulletsManager()
    {
        bullets = new ArrayList<>();
        bulletSpeed = 350;
        shootSound = Gdx.audio.newSound(Gdx.files.internal("playerShoot.mp3"));
    }

    public void render(SpriteBatch batch)
    {
        for (int i = 0; i < bullets.size(); i++) {
            bullets.get(i).render(batch);
        }
    }

    public void updateBullets(float delta) {
        cleanUpGoneBullets();

        for (int i = 0; i < bullets.size(); i++) {
            bulletMove(bullets.get(i), delta);
        }
    }

    private void cleanUpGoneBullets() {
        for (int i = 0; i < bullets.size(); i++) {
            if (bullets.get(i).outOfBorder(0))
                bullets.remove(i);
        }
    }

    private void bulletMove(Bullet bullet, float delta) {
        bullet.setPos(bullet.getPos().x, bullet.getPos().y + bulletSpeed * delta);
    }

    public void addBullet(Vector2 pos, Vector2 size) {
        BulletFactory bulletFactory = new HeroBulletFactory();
        Bullet newBullet = bulletFactory.createBullet();
        newBullet.setPos(pos.x + size.x / 2, pos.y + size.y);
        bullets.add(newBullet);
        shootSound.play();
    }

    public List<Bullet> getBullets()
    {
        return bullets;
    }

    public void dispose()
    {
        shootSound.dispose();
    }
}
