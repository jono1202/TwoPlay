package com.phoenixstudios;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;

import java.util.Random;

/**
 * Created by jonat on 12/24/2015.
 */
public class LevelsScreen implements Screen {

    InputMultiplexer inputMultiplexer;
    BackHandler inputProcessor;

    BitmapFont font;
    TwoPlay game;
    Stage backStage;
    Group stage;
    Button levelOne;
    Label levelOneLabel;
    Button levelTwo;
    Label levelTwoLabel;
    Button levelThree;
    Label levelThreeLabel;
    Button levelFour;
    Label levelFourLabel;
    Button levelFive;
    Label levelFiveLabel;

    Image board;

    BitmapFont titleFont;
    Label title;

    int tempInt;
    boolean tempBool = true;
    Random rand;
    int frames = 0;

    RandomObject tempObject;

    public LevelsScreen(final TwoPlay game) {
        inputMultiplexer = new InputMultiplexer();
        Gdx.input.setCatchBackKey(true);
        inputProcessor = new BackHandler(game);

        board = new Image(new Texture(Gdx.files.internal("LevelBoard.png")));

        titleFont = new BitmapFont(Gdx.files.internal("Font/TitleFont.fnt"));
        title = new Label("LEVELS", new Label.LabelStyle(titleFont, Color.BLACK));

        rand = new Random();
        final TwoPlay game_ref = game;

        font = new BitmapFont(Gdx.files.internal("Font2/Font.fnt"));

        backStage = new Stage(new FitViewport(1920,1080));
        stage = new Group();
        backStage.addActor(stage);
        Image background = new Image(new Texture(Gdx.files.internal("GameBackground.png")));
        backStage.addActor(background);
        background.toBack();

        //Level 1
        levelOne = new Button(new SpriteDrawable(new Sprite(new Texture(Gdx.files.internal("Buttons/LevelOne.png")))),
                new SpriteDrawable(new Sprite(new Texture(Gdx.files.internal("Buttons/LevelOneDown.png")))));
        levelOne.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.buttonPress.play(3.0f);
                game_ref.setScreen(game_ref.levelOne);
            }
        });
        levelOneLabel = new Label("Level 1", new Label.LabelStyle(font, Color.BLACK));

        //Level 2
        levelTwo = new Button(new SpriteDrawable(new Sprite(new Texture(Gdx.files.internal("Buttons/LevelTwo.png")))),
                new SpriteDrawable(new Sprite(new Texture(Gdx.files.internal("Buttons/LevelTwoDown.png")))));
        levelTwo.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.buttonPress.play(3.0f);
                game_ref.setScreen(game_ref.levelTwo);
            }
        });
        levelTwoLabel = new Label("Level 2", new Label.LabelStyle(font, Color.BLACK));

        //Level 3
        levelThree = new Button(new SpriteDrawable(new Sprite(new Texture(Gdx.files.internal("Buttons/LevelThree.png")))),
                new SpriteDrawable(new Sprite(new Texture(Gdx.files.internal("Buttons/LevelThreeDown.png")))));
        levelThree.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.buttonPress.play(3.0f);
                game_ref.setScreen(game_ref.levelThree);
            }
        });
        levelThreeLabel = new Label("Level 3", new Label.LabelStyle(font, Color.BLACK));

        //Level 4
        levelFour = new Button(new SpriteDrawable(new Sprite(new Texture(Gdx.files.internal("Buttons/LevelFour.png")))),
                new SpriteDrawable(new Sprite(new Texture(Gdx.files.internal("Buttons/LevelFourDown.png")))));
        levelFour.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.buttonPress.play(3.0f);
                game_ref.setScreen(game_ref.levelFour);
            }
        });
        levelFourLabel = new Label("Level 4", new Label.LabelStyle(font, Color.BLACK));

        //Level 5
        levelFive = new Button(new SpriteDrawable(new Sprite(new Texture(Gdx.files.internal("Buttons/LevelFive.png")))),
                new SpriteDrawable(new Sprite(new Texture(Gdx.files.internal("Buttons/LevelFiveDown.png")))));
        levelFive.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.buttonPress.play(3.0f);
                game_ref.setScreen(game_ref.levelFive);
            }
        });
        levelFiveLabel = new Label("Level 5", new Label.LabelStyle(font, Color.BLACK));

        levelOne.setPosition(1920/2 - 210 - levelOne.getWidth(), 350);
        levelOneLabel.setPosition(levelOne.getX() + levelOne.getWidth()/2 - levelOneLabel.getWidth()/2, levelOne.getY() + levelOne.getHeight() + 20);
        stage.addActor(levelOne);
        stage.addActor(levelOneLabel);

        levelTwo.setPosition(1920/2 - levelTwo.getWidth()/2, 350);
        levelTwoLabel.setPosition(levelTwo.getX() + levelTwo.getWidth()/2 - levelTwoLabel.getWidth()/2, levelTwo.getY() + levelTwo.getHeight() + 20);
        stage.addActor(levelTwo);
        stage.addActor(levelTwoLabel);

        levelThree.setPosition(1920/2 + 210, 350);
        levelThreeLabel.setPosition(levelThree.getX() + levelThree.getWidth()/2 - levelThreeLabel.getWidth()/2, levelThree.getY() + levelThree.getHeight() + 20);
        stage.addActor(levelThree);
        stage.addActor(levelThreeLabel);

        levelFour.setPosition(1920/2 -levelFour.getWidth() - 75, 30);
        levelFourLabel.setPosition(levelFour.getX() + levelFour.getWidth()/2 - levelFourLabel.getWidth()/2, levelFour.getY() + levelFour.getHeight() + 20);
        stage.addActor(levelFour);
        stage.addActor(levelFourLabel);

        levelFive.setPosition(1920/2 + 75, 30);
        levelFiveLabel.setPosition(levelFive.getX() + levelFive.getWidth()/2 - levelFiveLabel.getWidth()/2, levelFive.getY() + levelFive.getHeight() + 20);
        stage.addActor(levelFive);
        stage.addActor(levelFiveLabel);

        stage.addActor(board);
        board.setPosition(1920/2 - board.getWidth()/2, 1080 - board.getHeight() - 10);

        stage.addActor(title);
        title.setPosition(1920/2 - title.getWidth()/2, 1080 - title.getHeight() - 90);

        this.game = game;
    }

    @Override
    public void render(float delta) {
        if(frames%60 == 0){
            tempInt = rand.nextInt(1080-150);
            tempObject = new RandomObject(tempBool, 550, tempInt, game);
            stage.addActor(tempObject);
            tempObject.toBack();
            tempObject = new RandomObject(!tempBool, 550, rand.nextInt(1080-150), game);
            stage.addActor(tempObject);
            tempObject.toBack();

            tempBool = !tempBool;
        }
        Gdx.gl.glClearColor(0.7f,0.7f,0.7f,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act(Gdx.graphics.getDeltaTime());
        backStage.draw();
        frames++;
    }

    @Override
    public void show() {
        inputMultiplexer.addProcessor(backStage);
        inputMultiplexer.addProcessor(inputProcessor);
        Gdx.input.setInputProcessor(inputMultiplexer);
    }

    @Override
    public void dispose() {

    }

    @Override
    public void hide() {
    }

    @Override
    public void pause() {

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void resume() {

    }
}
