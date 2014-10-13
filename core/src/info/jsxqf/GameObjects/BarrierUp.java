package info.jsxqf.GameObjects;

import com.badlogic.gdx.math.Vector2;
import info.jsxqf.Screens.GameScreen;

import java.util.Random;

/**
 * Created by jsxqf on 14-5-23.
 */
public class BarrierUp {

    protected Vector2 position;
    protected Vector2 velocity;
    protected int width;
    protected int height;
    private boolean isScrolledLeft;

    private int randomGap;

    public BarrierUp(float x,float y,int width,int height,float speed){
        position = new Vector2(x,y);
        velocity = new Vector2(speed,0);
        this.width = width;
        this.height = height;
        isScrolledLeft = false;

        randomGap = (int)(Math.random()*100);

    }

    public void update(float delta){
        position.add(velocity.cpy().scl(delta));

        if(position.x+width < 0){
            isScrolledLeft = true;
        }
    }

    public void reset(float newX){
        randomGap = (int)(Math.random()*100);
//        if(newX < GameScreen.screenWidth){
//            position.x = GameScreen.screenWidth;
//        }else{
//            position.x = newX;
//        }
        position.x = newX;
        isScrolledLeft = false;


    }

    public void onRestart(float x,float speed){
        velocity.x = speed;
        reset(x);
    }

    public float getTailX(){
        return position.x + width + randomGap;
    }

//    public float getRandomGap(){
//        return randomGap;
//    }

    public void stop(){
        velocity.x = 0;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public boolean isScrolledLeft() {
        return isScrolledLeft;
    }

    public float getX(){
        return position.x;
    }

    public float getY(){
        return position.y;
    }

    public boolean collides(BoxUp boxUp){
        float x = getX();
        float screenCenterX = GameScreen.screenWidth/2;
        if((x>screenCenterX-50) && (x<screenCenterX)){
            if(boxUp.getY() < (GameScreen.screenHeight/2-40)){
                return false;
            }else{
                return true;
            }
        }
        return false;
    }

}
