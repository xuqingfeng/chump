package info.jsxqf.GameWorld;

import com.badlogic.gdx.math.Rectangle;
import info.jsxqf.GameObjects.BoxDown;
import info.jsxqf.GameObjects.BoxUp;
import info.jsxqf.GameObjects.ScrollHandler;
import info.jsxqf.Helper.AssetLoader;
import info.jsxqf.Screens.GameScreen;
import info.jsxqf.ui.SimpleButton;

import java.math.BigDecimal;

/**
 * Created by jsxqf on 14-5-21.
 */

/*
* update
* */
public class GameWorld {

//    private Rectangle rectangle = new Rectangle(0,0,20,20);
    private BoxUp boxUp;
    private int boxUpX;
    private int boxUpY;
    private BoxDown boxDown;
    private int boxDownX;
    private int boxDownY;
    private ScrollHandler scrollHandler;

    private float score;

    private GameState currentState;

    private boolean isTapped;

    //play button
    SimpleButton play;

    public enum GameState{
        READY,RUNNING,GAMEOVER
    }

    public void setTapped(boolean isTapped) {
        this.isTapped = isTapped;
    }

    public boolean isTapped() {

        return isTapped;
    }

    public GameWorld(int midPointX,int midPointY){
        currentState = GameState.READY;

        boxUpX = midPointX - 20;
        boxUpY = midPointY - 30;
        boxUp = new BoxUp(boxUpX,boxUpY,20,20);

        boxDownX = midPointX;
        boxDownY = midPointY + 10;
        boxDown = new BoxDown(boxDownX,boxDownY,20,20);

        scrollHandler = new ScrollHandler();

        score = 0;

        float playButtonX = GameScreen.screenWidth/4*3;
        float playButtonY = GameScreen.screenHeight/4*3;
        play = new SimpleButton(playButtonX,playButtonY,50,50);

        isTapped = false;

    }

    public void update(float delta){
        switch(currentState){
            case READY:
                updateReady(delta);
                break;
            case RUNNING:
                updateRunning(delta);
                break;
        }
    }

    private void updateReady(float delta){
        //do noting for now
    }

    public void updateRunning(float delta){
        System.out.println("GameWorld update");
        boxUp.update(delta);
        boxDown.update(delta);
        //stop first jump
        play.setIsPressed(true);


        scrollHandler.update(delta);

        if(!boxUp.isDead()||!boxDown.isDead()){
            score += delta;
        }

        //collide
        if(!boxUp.isDead() && !boxDown.isDead() && scrollHandler.collides(boxUp,boxDown)){
            scrollHandler.stop();
            boxUp.stop();
            boxDown.stop();
            AssetLoader.dead.play();
            AssetLoader.background.stop();
//            AssetLoader.background.stop(id);
            boxUp.setDead(true);
            boxDown.setDead(true);
            currentState = GameState.GAMEOVER;

            if(getScore() > AssetLoader.prefs.getFloat("bestScore")){
                AssetLoader.prefs.putFloat("bestScore",getScore());
                AssetLoader.prefs.flush();
            }
        }


//        rectangle.x++;
//        if(rectangle.x > 700){
//            rectangle.x = 0;
//        }
    }

//    public Rectangle getRectangle(){
//        return rectangle;
//    }

    public BoxUp getBoxUp(){
        return boxUp;
    }

    public BoxDown getBoxDown(){
        return boxDown;
    }

    public ScrollHandler getScrollHandler(){
        return scrollHandler;
    }

    public SimpleButton getPlay() {
        return play;
    }

    public float getScore(){
        BigDecimal bigDecimal = new BigDecimal(score);
        return bigDecimal.setScale(1,BigDecimal.ROUND_HALF_UP).floatValue();
    }

    public boolean isReady(){
        return currentState == GameState.READY;
    }

    public void start(float screenX,float screenY){
        currentState = GameState.RUNNING;
        AssetLoader.background.play();
//        this.id = AssetLoader.background.loop();
//        if(play.isPressed(screenX,screenY)){
//            currentState = GameState.RUNNING;
//            AssetLoader.background.play();
//        }
    }

    public void restart(){
        currentState = GameState.READY;
        score = 0;
        isTapped = false;
        boxUp.onRestart(boxUpX,boxUpY);
        boxDown.onRestart(boxDownX,boxDownY);
        scrollHandler.onRestart();
    }

    public boolean isGameOver(){
        return currentState == GameState.GAMEOVER;
    }

}
