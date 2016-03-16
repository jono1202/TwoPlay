package com.phoenixstudios;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.math.MathUtils;

/**
 * Created by jonat on 12/24/2015.
 */
public class LevelFour extends GameScreen {

    int x;

    public LevelFour(TwoPlay game) {
        super(game);
        music = Gdx.audio.newMusic(Gdx.files.internal("Audio/Exit the Premises.mp3"));
        x = 600;
    }

    @Override
    public void show() {
        super.show();
        game.setCurrentLevel(4);
    }

    @Override
    public void render(float delta) {
        if(!music.isPlaying()){
            if(!movingObjects.hasChildren())
                game.setScreen(game.youWin);
        }
        else if(frames>=0*30 && frames<20*30){
            if(frames%30 == 0){
                movingObjects.addActor(new CircleObject(false, (int)((MathUtils.cos(frames*2*MathUtils.PI/x)+1)*(1080-150)/2), rightObject, leftObject, game));
                movingObjects.addActor(new CircleObject(true, (int)((MathUtils.cos(frames*2*MathUtils.PI/x)+1)*(1080-150)/2), rightObject, leftObject, game));
            }
        }
        else if(frames>40*30 && frames <60*30){
            if(frames%30 == 0){
                movingObjects.addActor(new MovingObject(false, (int)((MathUtils.cos(frames*2*MathUtils.PI/x)+1)*(1080-150)/2), rightObject, leftObject, game));
                movingObjects.addActor(new MovingObject(true, (int)((MathUtils.cos(frames*2*MathUtils.PI/x)+1)*(1080-150)/2), rightObject, leftObject, game));
            }
        }
        else if(frames>=80*30 && frames < 120*30){
            if(frames%30 == 0){
                movingObjects.addActor(new CircleObject(true, (int)((MathUtils.cos(frames*2*MathUtils.PI/x)+1)*(1080-150)/2), rightObject, leftObject, game));
                movingObjects.addActor(new MovingObject(false, (int)((MathUtils.cos(frames*2*MathUtils.PI/x)+1)*(1080-150)/2), rightObject, leftObject, game));
            }
        }
        else if(frames>=140*30 && frames<180*30){
            if(frames%30 == 0){
                movingObjects.addActor(new MovingObject(true, (int)((MathUtils.cos(frames*2*MathUtils.PI/x)+1)*(1080-150)/2), rightObject, leftObject, game));
                movingObjects.addActor(new CircleObject(false, (int)((MathUtils.cos(frames*2*MathUtils.PI/x)+1)*(1080-150)/2), rightObject, leftObject, game));
            }
        }
        else if(frames%(int)(90 - points/8) == 0){
            tempInt = rand.nextInt(1080-150);
            movingObjects.addActor(new CircleObject(tempBool, rand.nextInt(1080-150), rightObject, leftObject, game));

            tempBool = !tempBool;
        }
        else if(frames%(int)(80 - points/16) == 0){
            tempInt = rand.nextInt(1080-150);
            Gdx.app.log("Points: ", String.valueOf(points));
            movingObjects.addActor(new MovingObject(tempBool, tempInt, rightObject, leftObject, game));

            tempBool = !tempBool;
        }
        else if(frames%(int)(150 - points/8) == 0){
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
