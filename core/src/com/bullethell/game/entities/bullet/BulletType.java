package com.bullethell.game.entities.bullet;

public enum BulletType {

    MID_BOSS_B("midBossbBullet.png"),
    FINAL_BOSS("FinalBossFire.png"),
    MID_BOSS_A("midBossABullet.png"),
    HERO("midBossbBullet.png"),
    ENEMY_1("enemyBullet.png"),
    ENEMY_2("Herodie.png");

    private String image;

    BulletType(String image) {
        this.image = image;
    }

    public String getImage() {
        return image;
    }

}
