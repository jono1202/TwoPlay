package com.phoenixstudios;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import java.util.Random;

/**
 * Created by jonat on 12/21/2015.
 */
public class GameScreen implements Screen{

    int cycler2;
    int cycler;
    int tempFrames;

    Music music;

    InputMultiplexer inputMultiplexer;

    TwoPlay game;

    Stage backStage;
    Group stage;
    Image background;
    ControlledObject rightObject;
    ControlledObject leftObject;
    Group movingObjects;
    int frames = 0;
    int tempInt = 0;
    Random rand = new Random();

    Image board;
    Button retry;
    Button mainMenu;
    Label title;
    Label mainMenu_label;
    Label retry_label;
    BitmapFont font;
    BitmapFont titleFont;
    boolean menuOpen;

    Group pauseMenu;

    int points = 0;

    InputHandler inputHandler;

    Boolean tempBool = true;

    public GameScreen(final TwoPlay game) {
        final GameScreen this_ref = this;
        this.game = game;

        font = new BitmapFont(Gdx.files.internal("Font2/Font.fnt"));
        titleFont = new BitmapFont(Gdx.files.internal("Font/TitleFont.fnt"));

        backStage = new Stage(new FitViewport(1920,1080));
        stage = new Group();
        backStage.addActor(stage);
        inputHandler = new InputHandler(this);
        rightObject = new ControlledObject(1920 - 200, true);
        leftObject = new ControlledObject(200, false);
        movingObjects = new Group();
        frames = 0;

        background = new Image(new Texture(Gdx.files.internal("GameBackground.png")));
        backStage.addActor(background);
        background.toBack();

        board = new Image(new Texture(Gdx.files.internal("TItleBoard.png")));
        board.setPosition(1920/2-board.getWidth()/2, 1080-board.getHeight()-60);

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
                game.setScreen(game.mainMenuScreen);
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
                    case 6:
                        game.setScreen(game.infiniteMode);
                        break;
                }
            }
        });

        title = new Label("PAUSED", new Label.LabelStyle(titleFont, Color.BLACK));
        title.setAlignment(Align.center);
        title.setPosition(1920/2 - title.getWidth()/2, 620);

        mainMenu_label = new Label("Main\nMenu", new Label.LabelStyle(font, Color.BLACK));
        mainMenu_label.setAlignment(Align.center);
        mainMenu_label.setPosition(mainMenu.getX()+mainMenu.getWidth()/2-mainMenu_label.getWidth()/2, mainMenu.getY()+mainMenu.getHeight()+20);

        retry_label = new Label("Try\nAgain?", new Label.LabelStyle(font, Color.BLACK));
        retry_label.setAlignment(Align.center);
        retry_label.setPosition(retry.getX()+retry.getWidth()/2-retry_label.getWidth()/2, retry.getY()+retry.getHeight()+20);

        pauseMenu = new Group();
        pauseMenu.setName("pauseMenu");

        pauseMenu.addActor(board);
        pauseMenu.addActor(title);
        pauseMenu.addActor(retry);
        pauseMenu.addActor(mainMenu);
        pauseMenu.addActor(mainMenu_label);
        pauseMenu.addActor(retry_label);

        inputMultiplexer = new InputMultiplexer(backStage, inputHandler);
    }

    @Override
    public void show() {
        game.funkGameLoop.pause();

        menuOpen = false;

        leftObject.setPosition(leftObject.x_pos - leftObject.getWidth()/2, 1080/2 - leftObject.getHeight()/2);
        rightObject.setPosition(rightObject.x_pos - rightObject.getWidth()/2, 1080/2 - rightObject.getHeight()/2);

        movingObjects.clear();
        stage.clear();

        cycler2 = 1;
        cycler = 0;
        frames = 0;
        points = 0;

        Gdx.input.setInputProcessor(inputHandler);

        stage.addActor(rightObject);
        stage.addActor(leftObject);
        stage.addActor(movingObjects);
        music.play();
    }

    @Override
    public void render(float delta) {

        if(points == 150)
            game.setScreen(game.youWin);

        else if(frames%(int)(60 - points/4) == 0){
            tempInt = rand.nextInt(1080-150);
            movingObjects.addActor(new MovingObject(tempBool, tempInt, rightObject, leftObject, game));
            movingObjects.addActor(new TriangleObject(tempBool, rand.nextInt(1080-150), rightObject, leftObject, game));

           tempBool = !tempBool;
        }

        Gdx.gl.glClearColor(.7f,.7f,.7f,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        if(!menuOpen){
            frames++;
            stage.act(Gdx.graphics.getDeltaTime());
        }

        backStage.draw();
    }

    void Menu(){
        if(menuOpen){
            this.resume();
            music.play();
            frames = tempFrames;
            menuOpen = false;
            pauseMenu.remove();
        }
        else if(!menuOpen){
            this.pause();
            music.pause();
            tempFrames = frames;
            frames = 1;
            menuOpen = true;
            stage.addActor(pauseMenu);
        }
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {
        Gdx.input.setInputProcessor(inputMultiplexer);
    }

    @Override
    public void resume() {
        Gdx.input.setInputProcessor(inputMultiplexer);
    }

    @Override
    public void hide() {
        music.stop();
        game.funkGameLoop.play();
        movingObjects.clear();
    }

    @Override
    public void dispose() {

    }
}
