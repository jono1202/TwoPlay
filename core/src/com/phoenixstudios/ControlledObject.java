package com.phoenixstudios;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.sun.prism.Texture;

/**
 * Created by jonat on 12/21/2015.
 */
public class ControlledObject extends Actor{

    Sprite sprite;
    float x_pos;

    public ControlledObject(int x_pos, boolean right) {
        this.x_pos = x_pos;
        if(right){
            sprite = new Sprite(new com.badlogic.gdx.graphics.Texture(Gdx.files.internal("CircleBlue.png")));
        }
        else
            sprite = new Sprite(new com.badlogic.gdx.graphics.Texture(Gdx.files.internal("CircleBlue.png")));
        sprite.setPosition(x_pos - sprite.getWidth()/2, 1080/2 - sprite.getHeight()/2);
        setBounds(sprite.getX(),sprite.getY(), sprite.getWidth(),sprite.getHeight());
    }

    @Override
    public void act(float delta) {
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
}
