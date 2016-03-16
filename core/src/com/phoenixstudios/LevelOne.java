package com.phoenixstudios;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;

/**
 * Created by jonat on 12/22/2015.
 */
public class LevelOne extends GameScreen {

    public LevelOne(TwoPlay game) {
        super(game);
        music = Gdx.audio.newMusic(Gdx.files.internal("Audio/Ouroboros.mp3"));
    }

    @Override
    public void show() {
        super.show();
        game.setCurrentLevel(1);
    }

    @Override
    public void render(float delta) {
        if(!music.isPlaying()){
            if(!movingObjects.hasChildren())
                game.setScreen(game.youWin);
        }

        else if(frames%(int)(60 - points/4) == 0){
            tempInt = rand.nextInt(1080-150);
            Gdx.app.log("Points: ", String.valueOf(points));
            movingObjects.addActor(new MovingObject(tempBool, tempInt, rightObject, leftObject, game));

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
}
