package com.bullethell.game.world.states;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.TimeUtils;
import com.bullethell.game.AIEngine.RunAIEngine;
import com.bullethell.game.entities.players.hero.Hero;
import com.bullethell.game.entities.players.hero.observer.ScoreObserver;
import com.bullethell.game.utils.Constants;
import com.bullethell.game.world.InteractionManager;

import java.util.ArrayList;
import java.util.List;

public class StageOneMap implements ScoreObserver {

    private RunAIEngine AIEnemy;
    private Texture background1, background2;
    private int bgYOffset1, bgYOffset2;
    private BitmapFont font;
    private InteractionManager iManager;
    private boolean isWin, isLose;
    private int score;
	private int bombNum;
    private String tempEnemyType;
    private Hero hero;
    private long startTime;
    private String curHealthBar;
    private String totalHealthBar;
    private Sound collisionSound;
    private Sound bombSound;
	private Sound awardSound;

    private List<ScoreObserver> scoreObservers;


    public StageOneMap(boolean hard) {
		hero = Hero.getInstance();
		hero.reInitialize();
		if(hard==false)
			AIEnemy = RunAIEngine.getInstance("assets/easy.json");
		else
			AIEnemy = RunAIEngine.getInstance("assets/hard.json");
        AIEnemy.reInitialize();
        background1 = new Texture("background.png");
        background2 = new Texture("background.png");
        bgYOffset1 = 0;
        bgYOffset2 = Constants.GAME_HEIGHT;
        font = new BitmapFont();
        iManager = new InteractionManager(AIEnemy);
        isWin = isLose = false;
        score = 0;
		bombNum=3;
        startTime = TimeUtils.millis();
        totalHealthBar = "|||||||||||||||";
        curHealthBar = totalHealthBar;
        collisionSound = Gdx.audio.newSound(Gdx.files.internal("explode.mp3"));
		bombSound = Gdx.audio.newSound(Gdx.files.internal("explode2.mp3"));
		awardSound = Gdx.audio.newSound(Gdx.files.internal("award.mp3"));
        scoreObservers = new ArrayList<>();
    }

    public void render(OrthographicCamera camera, SpriteBatch batch) {
        batch.setProjectionMatrix(camera.combined);
        batch.begin();

        drawBackGround(batch);

        if (!isLose) {
            hero.render(batch);
        }

        AIEnemy.render(batch);

        if(hero.getPressBomb() && bombNum>0){
            hero.renderBomb(batch);
            hero.setPressBomb(false);
			bombSound.play();
			bombNum-=1;
			iManager.BombHitEnemy();
        }
		else
		{
			hero.setPressBomb(false);
		}


        drawStats(batch);

        drawEndGame(batch);

        batch.end();
    }

    private void drawBackGround(SpriteBatch batch)
    {
        batch.draw(background1, 0, bgYOffset1, (int) (Constants.GAME_WIDTH), (int) (Constants.GAME_HEIGHT));
        batch.draw(background2, 0, bgYOffset2, (int) (Constants.GAME_WIDTH), (int) (Constants.GAME_HEIGHT));
    }

    private void drawStats(SpriteBatch batch)
    {
        font.getData().setScale(1.1f);
        font.draw(batch, "Health: " + curHealthBar, Constants.GAME_WIDTH-150, Constants.GAME_HEIGHT-20);
        font.draw(batch, "Lives: " + hero.getLives(), Constants.GAME_WIDTH-100, Constants.GAME_HEIGHT-50);
        font.draw(batch, "Score: " + score, Constants.GAME_WIDTH-100, Constants.GAME_HEIGHT-80);
        font.draw(batch, "Bomb: " + bombNum, Constants.GAME_WIDTH-100, Constants.GAME_HEIGHT-110);
		font.getData().setScale(1.5f);
    }

    private void drawEndGame(SpriteBatch batch)
    {
        if (isLose)
        {
            hero.setHitbox(new Rectangle(-1000,-1000,0,0));
            font.draw(batch, "Game Over!", Constants.GAME_WIDTH/2-40, Constants.GAME_HEIGHT/2);
        }
        else if (isWin)
        {
            font.draw(batch, "WIN!", Constants.GAME_WIDTH/2-30, Constants.GAME_HEIGHT/2);
        }
    }

    public void update(float delta) {
        AIEnemy.update(delta);

        hero.update(delta);

        runBackground();

        handleInteractions();

//        updateScore(tempEnemyType);

//        updateHealthBar();

        updateWinningLosing();
    }

    private void updateHealthBar()
    {
        if (hero.getTotalHeath() > 0) {
            double ratio = totalHealthBar.length()/(double)hero.getTotalHeath();
            curHealthBar = "";
            for (int i = 0; i < (int)(ratio*hero.getCurHealth()); i++)
                curHealthBar = curHealthBar + "|";
        }
    }

    private void runBackground()
    {
        bgYOffset1 -=1;
        if ( bgYOffset1*(-1) >= Constants.GAME_HEIGHT) {
            bgYOffset1 = Constants.GAME_HEIGHT;
        }
        bgYOffset2 -=1;
        if ( bgYOffset2*(-1) >= Constants.GAME_HEIGHT) {
            bgYOffset2 = Constants.GAME_HEIGHT;
        }
    }

    private void handleInteractions()
    {
        boolean EHHit = iManager.EnemyHitHero();
        boolean BHHit =  iManager.BulletHitHero();
        tempEnemyType = iManager.BulletHitEnemy();

        if ( EHHit || BHHit || tempEnemyType != "") {
            collisionSound.play();
            updateScore(tempEnemyType);
            updateHealthBar();
        }

    }

    private void updateScore(String enemyType)
    {
        int scoreChange = 0;
        switch (enemyType)
        {
            case "BossA":
                scoreChange += 10;
                break;
            case "EnemyA":
                scoreChange += 5;
                break;
            case "BossB":
                scoreChange += 20;
                break;
            case "EnemyB":
                scoreChange += 7;
                break;
            default:
                scoreChange += 0;
        }
		if(score>1000 && score%1000<50)
		{
			bombNum +=1;
			score+=50;
			awardSound.play();
		}
        if (scoreChange != 0) {
            score += scoreChange;
            notifyScoreObservers(scoreChange);
        }
    }
	public int getBombNum()
	{
		return this.bombNum;
	}
	public void setBombNum(int bombNum)
	{
		this.bombNum=bombNum;
	}
    private void updateWinningLosing()
    {
        isWin =  AIEnemy.lastEnemyRetrieve() || AIEnemy.lastEnemyDie();
        isLose = hero.getLives() <= 0;
    }

    public void dispose() {
        background1.dispose();
        background2.dispose();
    }

    public boolean isWin()
    {
        return isWin;
    }

    public boolean isLose()
    {
        return isLose;
    }

    public int getScore()
    {
        return score;
    }

    public void addObserver(ScoreObserver observer) {
        scoreObservers.add(observer);
    }

    public void removeObserver(ScoreObserver observer) {
        scoreObservers.remove(observer);
    }

    private void notifyScoreObservers(int scoreChange) {
        for (ScoreObserver observer : scoreObservers) {
            observer.updateScore(scoreChange);
        }
    }

    @Override
    public void updateScore(int scoreChange) {
        score += scoreChange;
    }
}
