package com.phoenixstudios;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
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
 * Created by jonat on 12/27/2015.
 */
public class GameOver implements Screen {

    Sound gameOver;

    TwoPlay game;

    Stage stage;
    Image board;
    Button retry;
    Button mainMenu;
    Label title;
    Label retry_label;
    Label mainMenu_label;
    BitmapFont titleFont;
    BitmapFont font;

    int frames;
    boolean tempBool = true;
    int tempInt;
    Random rand;


    public GameOver(final TwoPlay game) {

        gameOver = Gdx.audio.newSound(Gdx.files.internal("Audio/GameOver.wav"));

        font = new BitmapFont(Gdx.files.internal("Font2/Font.fnt"));
        titleFont = new BitmapFont(Gdx.files.internal("Font/TitleFont.fnt"));

        final GameOver this_ref = this;
        rand = new Random();
        this.game = game;

        stage = new Stage(new FitViewport(1920,1080));
        stage.addActor(new Image(new Texture(Gdx.files.internal("GameBackground.png"))));

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
                Gdx.app.log("Current Level: ", String.valueOf(game.getCurrentLevel()));
                game.buttonPress.play(3.0f);
                switch (game.getCurrentLevel()){
                    case 1:
                        game.setScreen(game.levelOne);
                        break;
                    case 2:
                        game.setScreen(game.levelTwo);
                        break;
                    case 3:
                        game.setScreen(game.levelThree);
                        break;
                    case 4:
                        game.setScreen(game.levelFour);
                        break;
                    case 5:
                        game.setScreen(game.levelFive);
                        break;
                }
            }
        });

        title = new Label("GAME\nOVER", new Label.LabelStyle(titleFont, Color.BLACK));
        title.setAlignment(Align.center);
        title.setPosition(1920/2 - title.getWidth()/2, 520);

        mainMenu_label = new Label("Main\nMenu", new Label.LabelStyle(font, Color.BLACK));
        mainMenu_label.setAlignment(Align.center);
        mainMenu_label.setPosition(mainMenu.getX()+mainMenu.getWidth()/2-mainMenu_label.getWidth()/2, mainMenu.getY()+mainMenu.getHeight()+20);

        retry_label = new Label("Try\nAgain?", new Label.LabelStyle(font, Color.BLACK));
        retry_label.setAlignment(Align.center);
        retry_label.setPosition(retry.getX()+retry.getWidth()/2-retry_label.getWidth()/2, retry.getY()+retry.getHeight()+20);

        stage.addActor(title);
        stage.addActor(retry);
        stage.addActor(mainMenu);
        stage.addActor(mainMenu_label);
        stage.addActor(retry_label);
    }

    @Override
    public void show() {
        gameOver.play(0.4f);
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
