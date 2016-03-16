package com.phoenixstudios;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;

/**
 * Created by jonat on 12/24/2015.
 */
public class LevelThree extends GameScreen {
    boolean tempBool2;

    public LevelThree(TwoPlay game) {
        super(game);
        tempBool2 = true;
        music = Gdx.audio.newMusic(Gdx.files.internal("Audio/Rhinoceros.mp3"));
    }

    @Override
    public void show() {
        super.show();
        game.setCurrentLevel(3);
    }

    @Override
    public void render(float delta) {
        if(!music.isPlaying()){
            if(!movingObjects.hasChildren())
                game.setScreen(game.youWin);
        }

        else if(frames%(int)(45 - points/8) == 0){
            if(tempBool2){
                tempInt = rand.nextInt(1080-150);
                movingObjects.addActor(new CircleObject(tempBool, rand.nextInt(1080-150), rightObject, leftObject, game));
            }
            else{
                tempInt = rand.nextInt(1080-150);
                movingObjects.addActor(new MovingObject(tempBool, tempInt, rightObject, leftObject, game));
            }
            tempBool = !tempBool;
            tempBool2 = rand.nextBoolean();
        }
        else if(frames%(int)(150 - points/4) == 0){
            Gdx.app.log("Points: ", String.valueOf(points));
            tempInt = rand.nextInt(1080-150);
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
}
