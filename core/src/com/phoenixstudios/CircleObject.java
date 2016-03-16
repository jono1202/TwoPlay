package com.phoenixstudios;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.MoveByAction;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;

/**
 * Created by jonat on 12/25/2015.
 */
public class CircleObject extends Actor {

    ControlledObject controlledObjectLeft;
    ControlledObject controlledObjectRight;
    Circle controlledCircle;
    Circle movingCircle;
    MoveByAction moveByAction;
    SequenceAction sequenceAction;
    Sprite sprite;
    boolean right;
    int frame = 0;

    TwoPlay game;

    public CircleObject(boolean right, int y_pos, ControlledObject controlledObjectRight, ControlledObject controlledObjectLeft, TwoPlay game) {

        this.game = game;

        this.right = right;
        this.controlledObjectLeft = controlledObjectLeft;
        this.controlledObjectRight = controlledObjectRight;

        final TwoPlay game_reference = game;
        final CircleObject object_ref = this;

        movingCircle = new Circle(0,0,0);
        controlledCircle = new Circle(0,0,0);

        moveByAction = new MoveByAction();
        moveByAction.setDuration(3);

        sprite = new Sprite(new com.badlogic.gdx.graphics.Texture(Gdx.files.internal("CircleGreen.png")));
        if(right){
            sprite.setPosition(1920/2, y_pos);
            moveByAction.setAmount(1920/2, 0);
        }
        else{
            sprite.setPosition(1920/2 - sprite.getWidth(), y_pos);
            moveByAction.setAmount(-1920/2, 0);
        }

        sequenceAction = new SequenceAction();
        sequenceAction.addAction(moveByAction);
        sequenceAction.addAction(new Action() {
            @Override
            public boolean act(float delta) {
                if(game_reference.currentLevel == 6) {
                    game_reference.setScreen(game_reference.score);
                }
                else
                    game_reference.setScreen(game_reference.gameOver);
                return false;
            }
        });
        sequenceAction.addAction(Actions.removeActor());

        setBounds(sprite.getX(),sprite.getY(), sprite.getWidth(),sprite.getHeight());
    }

    @Override
    public void act(float delta) {

        if(right){
            controlledCircle.set(controlledObjectRight.getX() + controlledObjectRight.getWidth()/2,
                    controlledObjectRight.getY() + controlledObjectRight.getHeight()/2,
                    (controlledObjectRight.getHeight()+controlledObjectRight.getWidth())/4);
        }
        else {
            controlledCircle.set(controlledObjectLeft.getX() + controlledObjectLeft.getWidth()/2,
                    controlledObjectLeft.getY() + controlledObjectLeft.getWidth()/2,
                    (controlledObjectLeft.getHeight()+controlledObjectLeft.getWidth())/4);
        }
        if(Intersector.overlaps(controlledCircle, movingCircle)){
            game.coins.play(0.4f);
            game.infiniteMode.points++;
            this.remove();
        }

        if(frame == 30){
            frame = 31;
            sprite.setAlpha(1f);
            addAction(sequenceAction);
        }
        else if(frame<30){
            frame++;
            sprite.setAlpha((frame/60.0f));
        }
        super.act(delta);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        sprite.draw(batch);
    }

    @Override
    protected void positionChanged() {
        movingCircle.set(getX()+getWidth()/2, getY()+getHeight()/2, (float)(getWidth()+getHeight())/4);
        setBounds(getX(), getY(), getWidth(), getHeight());
        sprite.setPosition(getX(),getY());
        super.positionChanged();
    }

    @Override
    public boolean remove() {
        sprite.getTexture().dispose();
        return super.remove();
    }
}
