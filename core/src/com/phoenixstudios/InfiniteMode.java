package com.phoenixstudios;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;

/**
 * Created by jonat on 12/27/2015.
 */
public class InfiniteMode extends GameScreen {

    Label score;
    int choice;
    int x;
    int levels;
    boolean tempBool2;

    public InfiniteMode(TwoPlay game) {
        super(game);
        tempBool2 = true;
        music = game.funkGameLoop;
        titleFont = new BitmapFont(Gdx.files.internal("Font3/ScoreFont.fnt"));
        score = new Label("0", new Label.LabelStyle(titleFont, Color.BLACK));
        movingObjects.toBack();
        x = 600;
    }

    @Override
    public void show() {
        super.show();
        levels = 0;
        stage.addActor(score);
        game.setCurrentLevel(6);
    }

    @Override
    public void render(float delta) {
        score.setText(String.valueOf(points));
        score.setPosition(1920/2, 1080 - score.getHeight(), Align.center);

        if(frames%(int)(40) == 0){
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
        else if(frames%(int)(138) == 0){
            Gdx.app.log("Points: ", String.valueOf(points));
            tempInt = rand.nextInt(1080-150);
            movingObjects.addActor(new TriangleObject(tempBool, rand.nextInt(1080-150), rightObject, leftObject, game));

            tempBool = !tempBool;
        }
        /*if(frames < 30*20){
            switch (choice){
                case 0:if(frames%(int)(60 - levels) == 0){
                    tempInt = rand.nextInt(1080-150);
                    Gdx.app.log("Points: ", String.valueOf(points));
                    movingObjects.addActor(new MovingObject(tempBool, tempInt, rightObject, leftObject, game));

                    tempBool = !tempBool;
                }
                    break;
                case 1:if(frames%(int)(90 - levels) == 0){
                    Gdx.app.log("Points: ", String.valueOf(points));
                    tempInt = rand.nextInt(1080-150);
                    movingObjects.addActor(new TriangleObject(tempBool, rand.nextInt(1080-150), rightObject, leftObject, game));

                    tempBool = !tempBool;
                }
                else if(frames%(int)(70 - levels) == 0){
                    tempInt = rand.nextInt(1080-150);
                    Gdx.app.log("Points: ", String.valueOf(points));
                    movingObjects.addActor(new MovingObject(tempBool, tempInt, rightObject, leftObject, game));

                    tempBool = !tempBool;
                }
                    break;
                case 2:if(frames%(int)(45 - levels/3) == 0){
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
                else if(frames%(int)(150 - levels) == 0){
                    Gdx.app.log("Points: ", String.valueOf(points));
                    tempInt = rand.nextInt(1080-150);
                    movingObjects.addActor(new TriangleObject(tempBool, rand.nextInt(1080-150), rightObject, leftObject, game));

                    tempBool = !tempBool;
                }
                    break;
                case 3:
                    if(frames%30 == 0){
                        movingObjects.addActor(new CircleObject(false, (int)((MathUtils.cos(frames*2*MathUtils.PI/x)+1)*(1080-150)/2), rightObject, leftObject, game));
                        movingObjects.addActor(new CircleObject(true, (int)((MathUtils.cos(frames*2*MathUtils.PI/x)+1)*(1080-150)/2), rightObject, leftObject, game));
                    }
                    break;
                case 5:
                    if(frames%30 == 0){
                        movingObjects.addActor(new MovingObject(false, (int)((MathUtils.cos(frames*2*MathUtils.PI/x)+1)*(1080-150)/2), rightObject, leftObject, game));
                        movingObjects.addActor(new MovingObject(true, (int)((MathUtils.cos(frames*2*MathUtils.PI/x)+1)*(1080-150)/2), rightObject, leftObject, game));
                    }
                    break;
                case 4:if(frames%(int)(45 - levels/3) == 0){
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
                    break;
                default:
                    break;
            }
        }
        else{
            choice = rand.nextInt(6);
            if(levels < 25){
                levels++;
            }
            frames = 0;
        }*/

        /*if (frames % (int) (90) == 0) {
            tempInt = rand.nextInt(1080 - 150);
            movingObjects.addActor(new CircleObject(tempBool, rand.nextInt(1080 - 150), rightObject, leftObject, game));

            tempBool = !tempBool;
        } else if (frames % (int) (80) == 0) {
            tempInt = rand.nextInt(1080 - 150);
            Gdx.app.log("Points: ", String.valueOf(points));
            movingObjects.addActor(new MovingObject(tempBool, tempInt, rightObject, leftObject, game));

            tempBool = !tempBool;
        } else if (frames % (int) (150) == 0) {
            Gdx.app.log("Points: ", String.valueOf(points));
            tempInt = rand.nextInt(1080 - 150);
            movingObjects.addActor(new TriangleObject(tempBool, rand.nextInt(1080 - 150), rightObject, leftObject, game));

            tempBool = !tempBool;
        }*/

        Gdx.gl.glClearColor(.7f, .7f, .7f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        if (!menuOpen) {
            frames++;
            stage.act(Gdx.graphics.getDeltaTime());
        }

        backStage.draw();
    }

    @Override
    public void hide() {
        music.pause();
        game.funkGameLoop.play();
        movingObjects.clear();
    }
}
