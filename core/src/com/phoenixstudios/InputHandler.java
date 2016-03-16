package com.phoenixstudios;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by jonat on 12/21/2015.
 */
public class InputHandler implements InputProcessor {

    GameScreen gameScreen;
    float horizMultiplier;
    float vertMultiplier;

    public InputHandler(GameScreen gameScreen) {
        this.gameScreen = gameScreen;
        Gdx.input.setCatchBackKey(true);
        horizMultiplier = (float)Gdx.graphics.getWidth()/1920f;
        vertMultiplier = (float)Gdx.graphics.getHeight()/1080f;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        if(!gameScreen.menuOpen){
            if(screenX > Gdx.graphics.getWidth()-500*horizMultiplier &&
                    screenY > gameScreen.rightObject.getHeight()*vertMultiplier/2 &&
                    screenY < Gdx.graphics.getHeight() - gameScreen.rightObject.getHeight()*vertMultiplier/2)
                gameScreen.rightObject.setPosition(gameScreen.rightObject.getX() , Gdx.graphics.getHeight()/vertMultiplier - screenY/vertMultiplier - gameScreen.rightObject.getHeight()/2);
            else if(screenX < 500*horizMultiplier &&
                    screenY > gameScreen.leftObject.getHeight()*vertMultiplier/2 &&
                    screenY < Gdx.graphics.getHeight() - gameScreen.leftObject.getHeight()*vertMultiplier/2)
                gameScreen.leftObject.setPosition(gameScreen.leftObject.getX() , Gdx.graphics.getHeight()/vertMultiplier - screenY/vertMultiplier - gameScreen.leftObject.getHeight()/2);
            Gdx.app.log("YPosition", String.valueOf(Gdx.graphics.getHeight()/vertMultiplier - screenY/vertMultiplier - gameScreen.leftObject.getHeight()/2));
            Gdx.app.log("Y_Position", String.valueOf(screenY/vertMultiplier));
            Gdx.app.log("ScreenY", String.valueOf(screenY));
        }
        return false;
    }

    @Override
    public boolean keyDown(int keycode) {
        if(keycode == Input.Keys.BACK){
            gameScreen.game.buttonPress.play(3.0f);
            Gdx.app.log("Back: ", "Pressed");
            gameScreen.Menu();
        }
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        if(!gameScreen.menuOpen){
            if(screenX > Gdx.graphics.getWidth()-500*horizMultiplier){
                if(screenY < gameScreen.rightObject.getHeight()*vertMultiplier/2)
                    gameScreen.rightObject.setPosition(gameScreen.rightObject.getX() , Gdx.graphics.getHeight()/vertMultiplier - gameScreen.rightObject.getHeight());
                else if(screenY > Gdx.graphics.getHeight() - gameScreen.rightObject.getHeight()*vertMultiplier/2)
                    gameScreen.rightObject.setPosition(gameScreen.rightObject.getX() , 0);
                else
                    gameScreen.rightObject.setPosition(gameScreen.rightObject.getX() , Gdx.graphics.getHeight()/vertMultiplier - screenY/vertMultiplier - gameScreen.rightObject.getHeight()/2);
            }
            if(screenX < 500*horizMultiplier){
                if(screenY < gameScreen.leftObject.getHeight()*vertMultiplier/2)
                    gameScreen.leftObject.setPosition(gameScreen.leftObject.getX() , Gdx.graphics.getHeight()/vertMultiplier - gameScreen.leftObject.getHeight());
                else if(screenY > Gdx.graphics.getHeight() - gameScreen.leftObject.getHeight()*vertMultiplier/2)
                    gameScreen.leftObject.setPosition(gameScreen.leftObject.getX() , 0);
                else
                    gameScreen.leftObject.setPosition(gameScreen.leftObject.getX() , Gdx.graphics.getHeight()/vertMultiplier - screenY/vertMultiplier - gameScreen.leftObject.getHeight()/2);
            }}
        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
