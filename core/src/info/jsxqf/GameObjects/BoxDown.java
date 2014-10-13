package info.jsxqf.GameObjects;

import com.badlogic.gdx.math.Vector2;

/**
 * Created by jsxqf on 14-5-23.
 */
public class BoxDown {

    private Vector2 position;
    private float x;
    private float y;

    private Vector2 velocity;
    private Vector2 acceleration;

    private float rotation;
    private int width;
    private int height;

    private boolean isClickable;
    private boolean isDead;

    public BoxDown(float x,float y,int width,int height){
        this.width = width;
        this.height = height;
        position = new Vector2(x,y);
        this.x = x;
        this.y = y;

        velocity = new Vector2(0,0);
        acceleration = new Vector2(0,-450);

        rotation = 0;
        isClickable = true;
        isDead = false;
    }

    public void update(float delta){
        velocity.add(acceleration.cpy().scl(delta));

        position.add(velocity.cpy().scl(delta));

        if(position.y < this.y){
            position.y = this.y;
            velocity.y = 0;
            if(!isDead){
                isClickable = true;
            }else{
                isClickable = false;
            }
        }

        if(velocity.y > 0){
            rotation += 600 * delta;
            if(rotation > 180){
                rotation = 180;
            }
        }
        if(velocity.y < 0){
            rotation += 600 * delta;
            if(rotation > 360){
                rotation = 360;
            }
        }
    }

    public void onClick(){
        if(isClickable){
            rotation = 0;
            velocity.y = 300;
            isClickable = false;
        }
    }

    public void onRestart(int boxDownX,int boxDownY){
        rotation = 0;
        position.x = boxDownX;
        position.y = boxDownY;
        velocity.x = 0;
        velocity.y = 0;
        acceleration.x = 0;
        acceleration.y = -450;
        isDead = false;
    }

    public void stop(){
        isDead = true;
    }

    public boolean isDead(){
        return isDead;
    }

    public void setDead(boolean isDead){
        this.isDead = isDead;
    }


    public float getX(){
        return position.x;
    }

    public float getY(){
        return position.y;
    }

    public float getWidth(){
        return width;
    }

    public float getHeight(){
        return height;
    }

    public float getRotation(){
        return rotation;
    }
}
