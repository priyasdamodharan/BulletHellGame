package com.bullethell.game.AIEngine;


import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.bullethell.game.entities.Enemy.BossA;
import com.bullethell.game.entities.Enemy.BossB;
import com.bullethell.game.entities.Enemy.Enemy;
import com.bullethell.game.entities.Enemy.EnemyA;
import com.bullethell.game.entities.Enemy.EnemyB;
import com.bullethell.game.Algorithms.EnemyLayerLayout.DownDiagonal;
import com.bullethell.game.Algorithms.EnemyLayerLayout.EnemyLayerLayout;
import com.bullethell.game.Algorithms.EnemyLayerLayout.HorizontalFullWidth;
import com.bullethell.game.Algorithms.EnemyLayerLayout.HorizontalHalfWidth;
import com.bullethell.game.Algorithms.EnemyLayerLayout.RandomLayout;
import com.bullethell.game.Algorithms.EnemyLayerLayout.Single;
import com.bullethell.game.Algorithms.EnemyLayerLayout.UpDiagonal;
import com.bullethell.game.Algorithms.EnemyLayerLayout.V;
import com.bullethell.game.Algorithms.EnemyLayerLayout.Vertical;
import com.bullethell.game.Algorithms.EnemyMovePattern.DropToMiddleThenFollowHorizontally;
import com.bullethell.game.Algorithms.EnemyMovePattern.DropToMiddleThenMoveHorizontally;
import com.bullethell.game.Algorithms.EnemyMovePattern.DropToMiddleThenOscillate;
import com.bullethell.game.Algorithms.EnemyMovePattern.DropToMiddleThenStay;
import com.bullethell.game.Algorithms.EnemyMovePattern.FollowTarget;
import com.bullethell.game.Algorithms.EnemyMovePattern.EnemyMovePattern;
import com.bullethell.game.Algorithms.EnemyMovePattern.SW;
import com.bullethell.game.Algorithms.EnemyMovePattern.SE;
import com.bullethell.game.Algorithms.EnemyMovePattern.SteadyDrop;

import com.bullethell.game.entities.Enemy.factory.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class EnemyWave {

    private List<EnemyBSourceBulletManager> managers;
    private String enemyType;

    private EnemyFactory enemyFactory;

    public EnemyWave(JSONObject enemyJSON)
    {
        //get enemy info
        int numEnemies = ((Long) enemyJSON.get("num")).intValue();
        enemyType = (String) enemyJSON.get("type");
        int width = ((Long) ((JSONArray)enemyJSON.get("size")).get(0)).intValue();
        int height = ((Long) ((JSONArray)enemyJSON.get("size")).get(1)).intValue();
        String layoutStyle = (String) enemyJSON.get("layout");
        String moveStyle = (String) enemyJSON.get("moveStyle");
        JSONArray bulletsJSON = (JSONArray) enemyJSON.get("bullets");
        int attackingDuration = ((Long) enemyJSON.get("attackingDuration")).intValue();

        managers = new ArrayList<>();

        for (int j = 0; j < numEnemies; j++)
        {
            Enemy enemy = createEnemy(enemyType, width, height);
            enemy.setEnemyMovePattern(getMovePattern(moveStyle, attackingDuration));
            EnemyBSourceBulletManager manager = new EnemyBSourceBulletManager(enemy, bulletsJSON);
            managers.add(manager);
        }

        applyLayout(layoutStyle);
    }

    private EnemyMovePattern getMovePattern(String moveStyle, int attackingDuration) {
        EnemyMovePattern enemyMovePattern;
        switch (moveStyle)
        {
            case "DropToMiddleThenFollowHorizontally":
                enemyMovePattern = new DropToMiddleThenFollowHorizontally(attackingDuration);
                break;
            case "DropToMiddleThenMoveHorizontally":
                enemyMovePattern = new DropToMiddleThenMoveHorizontally(attackingDuration);
                break;
            case "DropToMiddleThenOscillate":
                enemyMovePattern = new DropToMiddleThenOscillate(attackingDuration);
                break;
            case "DropToMiddleThenStay":
                enemyMovePattern = new DropToMiddleThenStay(attackingDuration);
                break;
            case "FollowTarget":
                enemyMovePattern = new FollowTarget(attackingDuration);
                break;
            case "SW":
                enemyMovePattern = new SW(attackingDuration);
                break;
            case "SE":
                enemyMovePattern = new SE(attackingDuration);
                break;
            default:
                enemyMovePattern = new SteadyDrop(attackingDuration);
        }

        return enemyMovePattern;
    }

    private void applyLayout(String layoutStyle)
    {
        EnemyLayerLayout layout;
        switch (layoutStyle)
        {
            case "DownDiagonal":
                layout = new DownDiagonal();
                break;
            case "HorizontalHalfWidth":
                layout = new HorizontalHalfWidth();
                break;
            case "HorizontalFullWidth":
                layout = new HorizontalFullWidth();
                break;
            case "Single":
                layout = new Single();
                break;
            case "UpDiagonal":
                layout = new UpDiagonal();
                break;
            case "V":
                layout = new V();
                break;
            case "Vertical":
                layout = new Vertical();
                break;
            default:
                layout = new RandomLayout();
        }

        List<Enemy> enemyList = new ArrayList<>();
        for (int i = 0; i < managers.size(); i++)
            enemyList.add(managers.get(i).getEnemy());

        layout.applyLayout(enemyList);
    }

    private Enemy createEnemy(String type, int width, int height)
    {
        Enemy result;
        switch (type)
        {
            case "EnemyA":
                enemyFactory = new EnemyAFactory();
                break;
            case "EnemyB":
                enemyFactory = new EnemyBFactory();
                break;
            case "BossA":
                enemyFactory = new BossAFactory();
                break;
            case "BossB":
                enemyFactory = new BossBFactory();
                break;
            default:
                enemyFactory = new EnemyBFactory();
        }
        return  enemyFactory.createEnemy(width, height);
    }

    public void render(SpriteBatch batch)
    {
        for (int j = 0; j < managers.size(); j++)
            managers.get(j).render(batch);
    }

    public void update(float delta)
    {
        for (int j = 0; j < managers.size(); j++)
            managers.get(j).update(delta);
    }

    public List<EnemyBSourceBulletManager> getManagerList()
    {
        return managers;
    }

    public String getEnemyType()
    {
        return enemyType;
    }
}
