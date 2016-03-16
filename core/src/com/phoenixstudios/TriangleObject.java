package com.phoenixstudios;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.PolygonRegion;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.*;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.*;

import java.util.ResourceBundle;
import java.util.TimerTask;

/**
 * Created by jonat on 12/21/2015.
 */
public class TriangleObject extends Actor{

    ControlledObject controlledObjectLeft;
    ControlledObject controlledObjectRight;
    Circle controlledCircle;
    Polygon movingTriangle;
    MoveByAction moveByAction;
    SequenceAction sequenceAction;
    Sprite sprite;
    boolean right;
    int frame = 0;
    float[] vertices;

    TwoPlay game;

    public TriangleObject(boolean right, int y_pos, ControlledObject controlledObjectRight, ControlledObject controlledObjectLeft, TwoPlay game) {

        this.game = game;

        this.right = right;
        this.controlledObjectLeft = controlledObjectLeft;
        this.controlledObjectRight = controlledObjectRight;

        final TwoPlay game_reference = game;
        final TriangleObject object_ref = this;

        vertices = new float[6];

        movingTriangle = new Polygon();
        controlledCircle = new Circle(0,0,0);

        moveByAction = new MoveByAction();
        moveByAction.setDuration(0.67f);
        moveByAction.setInterpolation(Interpolation.pow4In);

        sprite = new Sprite(new com.badlogic.gdx.graphics.Texture(Gdx.files.internal("TriangleOrange.png")));

        if(right){
            sprite.setPosition(1920/2, y_pos);
            moveByAction.setAmount(1920/2, 0);
        }
        else{
            sprite.setFlip(true, false);
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
        if(isCollision(movingTriangle, controlledCircle)){
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
        if(right){
            vertices[0] = getX();
            vertices[1] = getY();
            vertices[2] = getX()+getWidth();
            vertices[3] = getY()+getHeight()/2;
            vertices[4] = getX();
            vertices[5] = getY()+getHeight();
        }
        else{
            vertices[0] = getX();
            vertices[1] = getY()+getHeight()/2;
            vertices[2] = getX()+getWidth();
            vertices[3] = getY();
            vertices[4] = getX()+getWidth();
            vertices[5] = getY()+getHeight();
        }
        movingTriangle.setVertices(vertices);
        setBounds(getX(), getY(), getWidth(), getHeight());
        sprite.setPosition(getX(),getY());
        super.positionChanged();
    }

    @Override
    public boolean remove() {
        sprite.getTexture().dispose();
        return super.remove();
    }

    private boolean isCollision(Polygon p, Circle c) {
        float[] vertices = p.getTransformedVertices();
        Vector2 center = new Vector2(c.x, c.y);
        float squareRadius = c.radius * c.radius;
        for (int i = 0; i < vertices.length; i += 2) {
            if (i == 0) {
                if (Intersector.intersectSegmentCircle(new Vector2(
                        vertices[vertices.length - 2],
                        vertices[vertices.length - 1]), new Vector2(
                        vertices[i], vertices[i + 1]), center, squareRadius))
                    return true;
            } else {
                if (Intersector.intersectSegmentCircle(new Vector2(
                        vertices[i - 2], vertices[i - 1]), new Vector2(
                        vertices[i], vertices[i + 1]), center, squareRadius))
                    return true;
            }
        }
        return false;
    }
}
