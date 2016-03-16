package com.phoenixstudios;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.FitViewport;

import java.util.Random;

/**
 * Created by jonat on 12/28/2015.
 */
public class Score implements Screen {

    TwoPlay game;

    Stage stage;
    Image board;
    Button retry;
    Button mainMenu;
    Label score;
    Label title1;
    Label title2;
    Label highScore;
    Label retry_label;
    Label mainMenu_label;
    BitmapFont titleFont;
    BitmapFont font;

    int frames;
    boolean tempBool = true;
    int tempInt;
    Random rand;


    public Score(final TwoPlay game) {

        font = new BitmapFont(Gdx.files.internal("Font2/Font.fnt"));
        titleFont = new BitmapFont(Gdx.files.internal("Font3/ScoreFont.fnt"));

        final Score this_ref = this;
        rand = new Random();
        this.game = game;

        stage = new Stage(new FitViewport(1920,1080));
        stage.addActor(new Image(new Texture("GameBackground.png")));
        frames = 0;

        board = new Image(new Texture(Gdx.files.internal("TItleBoard.png")));
        board.setPosition(1920/2-board.getWidth()/2, 1080-board.getHeight()-60);
        stage.addActor(board);

        retry = new Button(new SpriteDrawable(new Sprite(new Texture(Gdx.files.internal("Buttons/RetryButton.png")))),
                new SpriteDrawable(new Sprite(new Texture(Gdx.files.internal("Buttons/RetryButtonDown.png")))));
        retry.setPosition(1920/2 + 1006/2 - retry.getWidth() - 120, 40);
        retry.setOrigin(retry.getWidth()/2, retry.getHeight()/2);

        mainMenu = new Button(new SpriteDrawable(new Sprite(new Texture(Gdx.files.internal("Buttons/MainMenu.png")))),
                new SpriteDrawable(new Sprite(new Texture(Gdx.files.internal("Buttons/MainMenuDown.png")))));
        mainMenu.setPosition(1920/2 - 1006/2 + 120, 40);
        mainMenu.setOrigin(mainMenu.getWidth()/2, mainMenu.getHeight()/2);

        mainMenu.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.buttonPress.play(3.0f);
                this_ref.game.setScreen(this_ref.game.mainMenuScreen);
            }
        });

        retry.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.buttonPress.play(3.0f);
                this_ref.game.setScreen(this_ref.game.infiniteMode);
            }
        });

        score = new Label("", new Label.LabelStyle(titleFont, Color.BLACK));
        score.setAlignment(Align.center);
        score.setPosition(1920/2 + 250, 800+75/2);

        highScore = new Label("", new Label.LabelStyle(titleFont, Color.BLACK));
        highScore.setAlignment(Align.center);
        highScore.setPosition(1920/2 + 250, 550+75/2);

        title1 = new Label("Score:", new Label.LabelStyle(titleFont, Color.BLACK));
        title1.setPosition(1920/2 - 450, 800);

        title2  = new Label("High\nScore:", new Label.LabelStyle(titleFont, Color.BLACK));
        title2.setPosition(1920/2 - 450, 550);

        mainMenu_label = new Label("Main\nMenu", new Label.LabelStyle(font, Color.BLACK));
        mainMenu_label.setAlignment(Align.center);
        mainMenu_label.setPosition(mainMenu.getX()+mainMenu.getWidth()/2-mainMenu_label.getWidth()/2, mainMenu.getY()+mainMenu.getHeight()+20);

        retry_label = new Label("Try\nAgain?", new Label.LabelStyle(font, Color.BLACK));
        retry_label.setAlignment(Align.center);
        retry_label.setPosition(retry.getX()+retry.getWidth()/2-retry_label.getWidth()/2, retry.getY()+retry.getHeight()+20);

        stage.addActor(title1);
        stage.addActor(title2);
        stage.addActor(highScore);
        stage.addActor(score);
        stage.addActor(retry);
        stage.addActor(mainMenu);
        stage.addActor(mainMenu_label);
        stage.addActor(retry_label);
    }

    @Override
    public void show() {
        game.gameOver.gameOver.play(0.4f);
        score.setText(String.valueOf(game.infiniteMode.points));
        if(game.infiniteMode.points > game.preferences.getInteger("HighScore", 0)){
            game.preferences.putInteger("HighScore", game.infiniteMode.points);
            game.preferences.flush();
            highScore.setText(String.valueOf(game.preferences.getInteger("HighScore", 0)));
        }
        else{
            highScore.setText(String.valueOf(game.preferences.getInteger("HighScore", 0)));
        }
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {

        if(frames%60 == 0){
            tempInt = rand.nextInt(1080-150);
            stage.addActor(new RandomObject(tempBool, 550, tempInt, game));
            stage.addActor(new RandomObject(!tempBool, 550, rand.nextInt(1080-150), game));

            tempBool = !tempBool;
        }

        Gdx.gl.glClearColor(0.7f,0.7f,0.7f,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        frames++;

        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
