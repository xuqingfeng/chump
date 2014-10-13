package info.jsxqf.Helper;

import com.badlogic.gdx.InputProcessor;
import info.jsxqf.GameObjects.BoxDown;
import info.jsxqf.GameObjects.BoxUp;
import info.jsxqf.GameWorld.GameWorld;
import info.jsxqf.Screens.GameScreen;
import info.jsxqf.ui.SimpleButton;

/**
 * Created by jsxqf on 14-5-22.
 */
public class InputHandler implements InputProcessor {

    private GameWorld gameWorld;
    private BoxUp boxUp;
    private BoxDown boxDown;
    private SimpleButton play;

    public InputHandler(GameWorld gameWorld){
        this.gameWorld = gameWorld;
        this.boxUp = this.gameWorld.getBoxUp();
        this.boxDown = this.gameWorld.getBoxDown();
        this.play = this.gameWorld.getPlay();
    }

    @Override
    public boolean keyDown(int keycode) {
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

        if(gameWorld.isReady()){
            gameWorld.start(screenX,screenY);
        }

        if(gameWorld.isTapped()){
            if(screenY < GameScreen.screenHeight/2){
                boxUp.onClick();
            }else if(screenY > GameScreen.screenHeight/2){
                boxDown.onClick();
            }
        }

        if(screenX > 0 && screenY > 0){
            gameWorld.setTapped(true);
        }


//        if(play.isPressed()){
//            if(screenY < GameScreen.screenHeight/2){
//                boxUp.onClick();
//            }else if(screenY > GameScreen.screenHeight/2){
//                boxDown.onClick();
//            }
//        }


        if(gameWorld.isGameOver()){
            gameWorld.restart();
        }

        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
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
