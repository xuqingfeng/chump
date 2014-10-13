package info.jsxqf.GameObjects;

import com.badlogic.gdx.math.Vector2;
import info.jsxqf.Screens.GameScreen;

/**
 * Created by jsxqf on 14-5-26.
 */
public class BarrierDown {

    private Vector2 position;
    private Vector2 velocity;
    private int width;
    private int height;
    private boolean isScrolledRight;

    private int randomGap;

    public BarrierDown(float x,float y,int width,int height,float speed){
        position = new Vector2(x,y);
        velocity = new Vector2(speed,0);
        this.width = width;
        this.height = height;
        isScrolledRight = false;

        randomGap = (int)(Math.random()*100) + 50;
    }

    public void update(float delta){
        position.add(velocity.cpy().scl(delta));
        if(position.x > GameScreen.screenWidth){
            isScrolledRight = true;
        }
    }

    public void reset(float newX){
        randomGap = (int)(Math.random()*100) + 50;

        position.x = newX;
        isScrolledRight = false;
    }

    public void onRestart(float x,float speed){
        velocity.x = speed;
        reset(x);
    }

    public float getTailX(){
        return -(position.x + width + randomGap);
    }

    public void stop(){
        velocity.x = 0;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public boolean isScrolledRight(){
        return isScrolledRight;
    }

    public float getX(){
        return position.x;
    }

    public float getY(){
        return position.y;
    }

    public boolean collides(BoxDown boxDown){
        float x = getX()+30;
        float screenCenterX = GameScreen.screenWidth/2;
        if((x>screenCenterX)&&(x<screenCenterX+50)){
            if(boxDown.getY() > (GameScreen.screenHeight/2+40)){
                return false;
            }else{
                return true;
            }
        }
        return false;
    }

    public float getRandomGap(){
        return randomGap;
    }
}
