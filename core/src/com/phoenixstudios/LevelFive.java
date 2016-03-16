package com.phoenixstudios;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;

/**
 * Created by jonat on 12/24/2015.
 */
public class LevelFive extends GameScreen {
    public LevelFive(TwoPlay game) {
        super(game);
        music = Gdx.audio.newMusic(Gdx.files.internal("Audio/Severe Tire Damage.mp3"));
    }

    @Override
    public void show() {
        super.show();
        game.setCurrentLevel(5);
    }

    @Override
    public void render(float delta) {
        if(!music.isPlaying()){
            if(!movingObjects.hasChildren())
                game.setScreen(game.youWin);
        }

        else if(frames%(int)(45 - points/8) == 0){
            switch(cycler){
                case 0:
                    tempInt = rand.nextInt(1080-150);
                    movingObjects.addActor(new CircleObject(tempBool, rand.nextInt(1080-150), rightObject, leftObject, game));

                    tempBool = !tempBool;
                    cycler = rand.nextInt(cycler2);
                    cycler2 = 3;
                    break;
                case 1:
                    tempInt = rand.nextInt(1080-150);
                    Gdx.app.log("Points: ", String.valueOf(points));
                    movingObjects.addActor(new MovingObject(tempBool, tempInt, rightObject, leftObject, game));

                    tempBool = !tempBool;
                    cycler = rand.nextInt(cycler2);
                    cycler2 = 3;
                    break;
                case 2:
                    Gdx.app.log("Points: ", String.valueOf(points));
                    tempInt = rand.nextInt(1080-150);
                    if(rand.nextBoolean())
                        movingObjects.addActor(new RectangleObject(tempBool, 0, rightObject, leftObject, game));
                    else
                        movingObjects.addActor(new RectangleObject(tempBool, 1080 - 712, rightObject, leftObject, game));

                    tempBool = !tempBool;
                    cycler = rand.nextInt(cycler2);
                    cycler2 = 2;
                    break;
                default:
                    Gdx.app.log("Points: ", String.valueOf(points));
                    if(rand.nextBoolean())
                        movingObjects.addActor(new RectangleObject(tempBool, 0, rightObject, leftObject, game));
                    else
                        movingObjects.addActor(new RectangleObject(tempBool, 1080 - 718, rightObject, leftObject, game));

                    tempBool = !tempBool;
                    cycler = rand.nextInt(3);
                    break;
            }
        }
        /*if(frames%(int)(90 - points/8) == 0){
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
            if(rand.nextBoolean())
                movingObjects.addActor(new RectangleObject(tempBool, rand.nextInt(0), rightObject, leftObject, game));
            else
                movingObjects.addActor(new RectangleObject(tempBool, rand.nextInt(1080 - 712), rightObject, leftObject, game));

            tempBool = !tempBool;
        }*/

        Gdx.gl.glClearColor(.7f,.7f,.7f,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        if(!menuOpen){
            frames++;
            stage.act(Gdx.graphics.getDeltaTime());
        }

        backStage.draw();
    }
}
