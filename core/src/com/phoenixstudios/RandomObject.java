package com.phoenixstudios;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
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

import java.util.Random;

/**
 * Created by jonat on 12/24/2015.
 */
public class RandomObject extends Actor {

    MoveByAction moveByAction;
    SequenceAction sequenceAction;
    Sprite sprite;
    boolean right;
    int frame = 0;
    Random rand;

    TwoPlay game;

    public RandomObject(boolean right, int x_pos, int y_pos, TwoPlay game) {

        rand = new Random();

        this.game = game;

        this.right = right;

        final TwoPlay game_reference = game;
        final RandomObject object_ref = this;

        moveByAction = new MoveByAction();
        moveByAction.setDuration(3);

        switch (rand.nextInt(3)){
            case 0:
                sprite = new Sprite(new com.badlogic.gdx.graphics.Texture(Gdx.files.internal("SquareRed.png")));
                break;
            case 1:
                sprite = new Sprite(new com.badlogic.gdx.graphics.Texture(Gdx.files.internal("TriangleOrange.png")));
                if(!right)
                    sprite.flip(true, false);
                break;
            case 2:
                sprite = new Sprite(new com.badlogic.gdx.graphics.Texture(Gdx.files.internal("CircleGreen.png")));
                break;
            default:
                sprite = new Sprite(new Texture(Gdx.files.internal("SquareRed.png")));
        }

        if(right){
            sprite.setPosition(1920/2+x_pos, y_pos);
            moveByAction.setAmount(1920/2, 0);
        }
        else{
            sprite.setPosition(1920/2 - sprite.getWidth()-x_pos, y_pos);
            moveByAction.setAmount(-1920/2, 0);
        }

        sequenceAction = new SequenceAction();
        sequenceAction.addAction(moveByAction);
        sequenceAction.addAction(new Action() {
            @Override
            public boolean act(float delta) {
                game_reference.levelOne.points++;
                game_reference.levelTwo.points++;
                object_ref.remove();
                return false;
            }
        });
        sequenceAction.addAction(Actions.removeActor());

        setBounds(sprite.getX(),sprite.getY(), sprite.getWidth(),sprite.getHeight());
    }

    @Override
    public void act(float delta) {

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
