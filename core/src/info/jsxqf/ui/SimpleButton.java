package info.jsxqf.ui;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

/**
 * Created by jsxqf on 14-5-26.
 */
public class SimpleButton {

    private float x,y,width,height;
//    private TextureRegion buttonStart;

    private Rectangle bounds;

    private boolean isPressed;

    public SimpleButton(float x,float y,float width,float height){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
//        this.buttonStart = buttonStart;

        this.isPressed = false;

        bounds = new Rectangle(x,y,width,height);
    }

    public boolean isPressed(float screenX,float screenY){
        return bounds.contains(screenX,screenY);
    }
    public boolean isPressed(){
        return isPressed;
    }

    public void setIsPressed(boolean isPressed){
        this.isPressed = isPressed;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }

    //    public void draw(SpriteBatch spriteBatch){
//        spriteBatch.draw(buttonStart,x,y,width,height);
//    }
}
