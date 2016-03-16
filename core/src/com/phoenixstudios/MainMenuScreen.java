package com.phoenixstudios;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
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
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import javafx.scene.text.Text;

import java.util.Random;

/**
 * Created by jonat on 12/21/2015.
 */
public class MainMenuScreen implements Screen {

    TwoPlay game;

    Stage stage;
    Image board;
    Button infinite;
    Button levels;
    Label title;
    Label credits;
    Label infinite_label;
    Label levels_label;
    BitmapFont titleFont;
    BitmapFont font;

    int frames;
    boolean tempBool = true;
    int tempInt;
    Random rand;


    public MainMenuScreen(TwoPlay game) {
        font = new BitmapFont(Gdx.files.internal("Font2/Font.fnt"));
        titleFont = new BitmapFont(Gdx.files.internal("Font/TitleFont.fnt"));

        final MainMenuScreen this_ref = this;
        rand = new Random();
        this.game = game;

        stage = new Stage(new FitViewport(1920,1080));
        stage.addActor(new Image(new Texture(Gdx.files.internal("GameBackground.png"))));

        frames = 0;

        board = new Image(new Texture(Gdx.files.internal("TItleBoard.png")));
        board.setPosition(1920/2-board.getWidth()/2, 1080-board.getHeight()-60);
        stage.addActor(board);

        infinite = new Button(new SpriteDrawable(new Sprite(new Texture(Gdx.files.internal("Buttons/InfinityMode.png")))),
                new SpriteDrawable(new Sprite(new Texture(Gdx.files.internal("Buttons/InfinityModeDown.png")))));
        infinite.setPosition(1920/2 + 1006/2 - infinite.getWidth() - 120, 40);
        infinite.setOrigin(infinite.getWidth()/2, infinite.getHeight()/2);

        infinite.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                this_ref.game.buttonPress.play(3.0f);
                this_ref.game.setScreen(this_ref.game.infiniteMode);
            }
        });

        levels = new Button(new SpriteDrawable(new Sprite(new Texture(Gdx.files.internal("Buttons/Levels.png")))),
                new SpriteDrawable(new Sprite(new Texture(Gdx.files.internal("Buttons/LevelsDown.png")))));
        levels.setPosition(1920/2 - 1006/2 + 120, 40);
        levels.setOrigin(levels.getWidth()/2, levels.getHeight()/2);

        levels.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                this_ref.game.buttonPress.play(3.0f);
                this_ref.game.setScreen(this_ref.game.levelsScreen);
            }
        });

        title = new Label("SHAPES", new Label.LabelStyle(titleFont, Color.BLACK));
        title.setPosition(1920/2 - title.getWidth()/2, 700);

        credits = new Label("By Jonathan Yang\nand Jack Deng", new Label.LabelStyle(font, Color.BLACK));
        credits.setAlignment(Align.center);
        credits.setPosition(1920/2 + 1006/2 - credits.getWidth() -40, 1080 - 500 + 40);

        levels_label = new Label("Levels\nMenu", new Label.LabelStyle(font, Color.BLACK));
        levels_label.setAlignment(Align.center);
        levels_label.setPosition(levels.getX()+levels.getWidth()/2-levels_label.getWidth()/2, levels.getY()+levels.getHeight()+20);

        infinite_label = new Label("Infinite\nMode", new Label.LabelStyle(font, Color.BLACK));
        infinite_label.setAlignment(Align.center);
        infinite_label.setPosition(infinite.getX()+infinite.getWidth()/2-infinite_label.getWidth()/2, infinite.getY()+infinite.getHeight()+20);

        stage.addActor(title);
        stage.addActor(infinite);
        stage.addActor(levels);
        stage.addActor(credits);
        stage.addActor(levels_label);
        stage.addActor(infinite_label);

        game.funkGameLoop.play();
        game.funkGameLoop.setLooping(true);
    }

    @Override
    public void show() {
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
