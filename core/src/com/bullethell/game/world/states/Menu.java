package com.bullethell.game.world.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.bullethell.game.utilities.PopUp;
import com.bullethell.game.utilities.SettingPopUp;
import com.bullethell.game.utilities.Storage;
import com.bullethell.game.utils.Constants;

public class Menu {
    private Texture bg;
    private Stage stage;
	private boolean hard;
    private Image playButton;
    private Image playHardButton;
    private Image settingButton;
    private BitmapFont font;
    private Storage storage;
    private PopUp popUp;
    private boolean readyToChange;

    public Menu()
    {
        readyToChange = false;
        font = new BitmapFont();
        font.getData().setScale(1.5f);
        font.setColor(Color.YELLOW);
        storage = Storage.getInstance();
        popUp = null;
        bg = new Texture("menu.jpg");
        stage = new Stage();

		hard=false;

        playButton = new Image(new Texture("playButton.png"));
        playButton.setPosition(stage.getWidth()/2-playButton.getWidth()/2, stage.getHeight()/2-playButton.getHeight()/2);

        playHardButton = new Image(new Texture("playHardButton.png"));
        playHardButton.setPosition(stage.getWidth()/2-playButton.getWidth()/2, stage.getHeight()/2-playButton.getHeight()/2-100);
        
        settingButton = new Image(new Texture("settingButton.png"));
        settingButton.setPosition(stage.getWidth()/2-settingButton.getWidth()/2, stage.getHeight()/2-settingButton.getHeight()/2-70*2-100);

        playButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                readyToChange = true;
            }

            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                super.enter(event, x, y, pointer, fromActor);
                playButton.setPosition(playButton.getX()+3, playButton.getY()+3);
            }

            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                super.exit(event, x, y, pointer, toActor);
                playButton.setPosition(playButton.getX()-3, playButton.getY()-3);
            }
        });


        playHardButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
				hard=true;
                readyToChange = true;
            }

            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                super.enter(event, x, y, pointer, fromActor);
                playHardButton.setPosition(playHardButton.getX()+3, playHardButton.getY()+3);
            }

            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                super.exit(event, x, y, pointer, toActor);
                playHardButton.setPosition(playHardButton.getX()-3, playHardButton.getY()-3);
            }
        });

        settingButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                if (popUp != null)
                    popUp.close();
                popUp = new SettingPopUp("Setting", 0,0);
            }

            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                super.enter(event, x, y, pointer, fromActor);
                settingButton.setPosition(settingButton.getX()+3, settingButton.getY()+3);
            }

            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                super.exit(event, x, y, pointer, toActor);
                settingButton.setPosition(settingButton.getX()-3, settingButton.getY()-3);
            }
        });


        stage.addActor(playButton);
		stage.addActor(playHardButton);
        stage.addActor(settingButton);
        Gdx.input.setInputProcessor(stage);
    }

	public boolean getHard()
	{
		return hard;
	}

    public void render(SpriteBatch batch) {
        batch.begin();
        batch.draw(bg, 0, 0, Constants.GAME_WIDTH, Constants.GAME_HEIGHT);
        batch.end();
        stage.act();
        stage.draw();
    }


    public boolean readyToChange()
    {
        boolean temp = readyToChange;
        readyToChange = false;
        return temp;
    }

    public void dispose() {
        bg.dispose();
        font.dispose();
        if (popUp != null)
            popUp.dispose();
        stage.dispose();
    }
}
