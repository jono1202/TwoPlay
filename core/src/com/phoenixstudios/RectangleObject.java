package com.phoenixstudios;

import com.badlogic.gdx.Gdx;
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
 * Created by jonat on 12/27/2015.
 */
public class RectangleObject extends Actor {

    ControlledObject controlledObjectLeft;
    ControlledObject controlledObjectRight;
    Circle controlledCircle;
    Rectangle movingRectangle;
    MoveByAction moveByAction;
    SequenceAction sequenceAction;
    Sprite sprite;
    boolean right;
    int frame = 0;

    TwoPlay game;

    public RectangleObject(boolean right, int y_pos, ControlledObject controlledObjectRight, ControlledObject controlledObjectLeft, TwoPlay game) {

        this.game = game;

        this.right = right;
        this.controlledObjectLeft = controlledObjectLeft;
        this.controlledObjectRight = controlledObjectRight;

        final TwoPlay game_reference = game;
        final RectangleObject object_ref = this;

        movingRectangle = new Rectangle(0,0,0,0);
        controlledCircle = new Circle(0,0,0);

        moveByAction = new MoveByAction();
        moveByAction.setDuration(3);

        sprite = new Sprite(new com.badlogic.gdx.graphics.Texture(Gdx.files.internal("ObjectSpinning.png")));
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
                game_reference.coins.play(0.4f);
                game_reference.infiniteMode.points++;
                object_ref.remove();
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
        if(Intersector.overlaps(controlledCircle, movingRectangle)){
            if(game.currentLevel == 6) {
                game.setScreen(game.score);
            }
            else
                game.setScreen(game.gameOver);
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
        movingRectangle.set(getX(), getY(), getWidth(), getHeight());
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
