package com.phoenixstudios;

import com.badlogic.gdx.*;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class TwoPlay extends Game{


	Music funkGameLoop;
	Sound buttonPress;
	Sound coins;

	int currentLevel;

	Preferences preferences;
	MainMenuScreen mainMenuScreen;
	LevelOne levelOne;
	LevelTwo levelTwo;
	LevelThree levelThree;
	LevelFour levelFour;
	LevelFive levelFive;
	LevelsScreen levelsScreen;
	YouWin youWin;
	GameOver gameOver;
	InfiniteMode infiniteMode;
	Score score;

	@Override
	public void create () {
		coins = Gdx.audio.newSound(Gdx.files.internal("Audio/Coins.mp3"));
		buttonPress = Gdx.audio.newSound(Gdx.files.internal("Audio/ButtonClick.mp3"));
		funkGameLoop = Gdx.audio.newMusic(Gdx.files.internal("Audio/Funk Game Loop.mp3"));
		preferences = Gdx.app.getPreferences("Score");
		mainMenuScreen = new MainMenuScreen(this);
		this.setScreen(mainMenuScreen);
		infiniteMode = new InfiniteMode(this);
		score = new Score(this);
		youWin = new YouWin(this);
		levelOne = new LevelOne(this);
		levelTwo = new LevelTwo(this);
		levelThree = new LevelThree(this);
		levelFour = new LevelFour(this);
		levelFive = new LevelFive(this);
		levelsScreen = new LevelsScreen(this);
		gameOver = new GameOver(this);
	}

	public int getCurrentLevel() {
		return currentLevel;
	}

	public void setCurrentLevel(int currentLevel) {
		this.currentLevel = currentLevel;
	}
}
