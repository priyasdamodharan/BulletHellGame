package com.bullethell.game.entities.bullet;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.TimeUtils;
import com.bullethell.game.utils.Constants;

public abstract class Bullet {
    protected Texture texture;
    protected Vector2 size;
    protected Vector2 pos;
    protected long initTime;
    protected Rectangle hitbox;
    protected int damage;

    public Bullet()
    {
        pos = new Vector2();
        size = new Vector2();
        initTime = TimeUtils.millis();
        hitbox = new Rectangle();
        damage = 5;
    }

    public void render(SpriteBatch batch)
    {
        batch.draw(texture, pos.x, pos.y, size.x, size.y);
    }

    public void setPos(float x, float y)
    {
        this.pos.x = x;
        this.pos.y = y;
        hitbox.setPosition(x,y);
    }

    public Vector2 getPos()
    {
        return pos;
    }

    public void dispose()
    {
        texture.dispose();
    }

    public boolean outOfBorder(int pixels)
    {
        return (pos.x+size.x < -pixels || pos.y+size.y < -pixels ||
                pos.x > Constants.GAME_WIDTH + pixels ||
                pos.y > Constants.GAME_HEIGHT + pixels);
    }

    public long getInitTime()
    {
        return initTime;
    }

    public Texture getTexture() {
        return texture;
    }

    public void setTexture(Texture texture) {
        this.texture = texture;
    }

    public Vector2 getSize() {
        return size;
    }

    public void setSize(Vector2 size) {
        this.size = size;
    }

    public void setPos(Vector2 pos) {
        this.pos = pos;
    }

    public void setInitTime(long initTime) {
        this.initTime = initTime;
    }

    public void setHitbox(Rectangle hitbox) {
        this.hitbox = hitbox;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public Rectangle getHitbox()
    {
        return hitbox;
    }

    public int getDamage()
    {
        return damage;
    }
}
