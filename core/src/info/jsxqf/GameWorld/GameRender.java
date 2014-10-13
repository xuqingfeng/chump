package info.jsxqf.GameWorld;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import info.jsxqf.GameObjects.*;
import info.jsxqf.Helper.AssetLoader;
import info.jsxqf.Screens.GameScreen;
import info.jsxqf.ui.SimpleButton;

/**
 * Created by jsxqf on 14-5-21.
 */

/*
* render ??difference with update
* */
public class GameRender {

    private GameWorld gameWorld;
    private OrthographicCamera cam;
    private ShapeRenderer shapeRenderer;
    private SpriteBatch spriteBatch;

    private BoxUp boxUp;
    private TextureRegion boxUpTR;

    private BoxDown boxDown;
    private TextureRegion boxDownTR;

    private SimpleButton play;
    private TextureRegion playTR;

    private ScrollHandler scrollHandler;

    private BarrierUp barrierUp1,barrierUp2,barrierUp3;
    private TextureRegion barrierUp1TR,barrierUp2TR,barrierUp3TR;

    private BarrierDown barrierDown1,barrierDown2,barrierDown3;
    private TextureRegion barrierDown1TR,barrierDown2TR,barrierDown3TR;

//    private TextureRegion bg;

    public GameRender(GameWorld gameWorld){
        this.gameWorld = gameWorld;

        cam = new OrthographicCamera();
//        cam.setToOrtho(true,700,400);
        cam.setToOrtho(true, GameScreen.screenWidth,GameScreen.screenHeight);

        spriteBatch = new SpriteBatch();
        spriteBatch.setProjectionMatrix(cam.combined);

        shapeRenderer = new ShapeRenderer();
        shapeRenderer.setProjectionMatrix(cam.combined);

        initGameObject();
        initAssets();
    }

    private void initGameObject(){
        boxUp = gameWorld.getBoxUp();
        boxDown = gameWorld.getBoxDown();
        play = gameWorld.getPlay();
        this.scrollHandler = gameWorld.getScrollHandler();
        barrierUp1 = scrollHandler.getBarrierUp1();
        barrierUp2 = scrollHandler.getBarrierUp2();
        barrierUp3 = scrollHandler.getBarrierUp3();
        barrierDown1 = scrollHandler.getBarrierDown1();
        barrierDown2 = scrollHandler.getBarrierDown2();
        barrierDown3 = scrollHandler.getBarrierDown3();
    }

    private void initAssets(){
        boxUpTR = AssetLoader.boxUp;
        boxDownTR = AssetLoader.boxDown;
//        playTR = AssetLoader.play;
        barrierUp1TR = AssetLoader.barrierUp1;
        barrierUp2TR = AssetLoader.barrierUp2;
        barrierUp3TR = AssetLoader.barrierUp3;
        barrierDown1TR = AssetLoader.barrierDown1;
        barrierDown2TR = AssetLoader.barrierDown2;
        barrierDown3TR = AssetLoader.barrierDown3;

//        bg = AssetLoader.bg;

    }

    private void drawBarrierUp(){
        spriteBatch.draw(barrierUp1TR,barrierUp1.getX(),barrierUp1.getY(),barrierUp1.getWidth(),barrierUp1.getHeight());
        spriteBatch.draw(barrierUp2TR,barrierUp2.getX(),barrierUp2.getY(),barrierUp2.getWidth(),barrierUp2.getHeight());
        spriteBatch.draw(barrierUp3TR,barrierUp3.getX(),barrierUp3.getY(),barrierUp3.getWidth(),barrierUp3.getHeight());

    }

    private void drawBarrierDown(){
        spriteBatch.draw(barrierDown1TR,barrierDown1.getX(),barrierDown1.getY(),barrierDown1.getWidth(),barrierDown1.getHeight());
        spriteBatch.draw(barrierDown2TR,barrierDown2.getX(),barrierDown2.getY(),barrierDown2.getWidth(),barrierDown2.getHeight());
        spriteBatch.draw(barrierDown3TR,barrierDown3.getX(),barrierDown3.getY(),barrierDown3.getWidth(),barrierDown3.getHeight());
    }

    public void render(){
        System.out.println("GameRender render");
        //fucked BoxUp boxUp;
//        boxUp = gameWorld.getBoxUp();

        Gdx.gl.glClearColor(0.063f, 0.683f,0.667f,1);
//        Gdx.gl.glClearColor(24/255.0f,159/255.0f,209/255.0f,1f);
//        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);

        shapeRenderer.setColor(255/255.0f,255/255.0f,255/255.0f,1f);

        //draw road
        int roadX = 0;
        int roadY = (int)(GameScreen.screenHeight/2)-10;
        shapeRenderer.rect(roadX,roadY,GameScreen.screenWidth,20);

//        shapeRenderer.rect(gameWorld.getRectangle().x,gameWorld.getRectangle().y,gameWorld.getRectangle().width,gameWorld.getRectangle().height);

//        shapeRenderer.rect(gameWorld.getBoxUp().getX(),gameWorld.getBoxUp().getY(),gameWorld.getBoxUp().getWidth(),gameWorld.getBoxUp().getHeight());


        shapeRenderer.end();

        spriteBatch.begin();
        spriteBatch.disableBlending();
//        spriteBatch.draw(boxUpTR,boxUp.getX(),boxUp.getY(),boxUp.getWidth(),boxUp.getHeight());
//        spriteBatch.draw(bg,0,0,GameScreen.screenWidth,GameScreen.screenHeight);

        drawBarrierUp();
        drawBarrierDown();
        //draw play
//        if(!play.isPressed()){
//            spriteBatch.draw(playTR,play.getX(),play.getY(),play.getWidth(),play.getHeight());
//        }
        spriteBatch.enableBlending();

        //tap
        if(gameWorld.isReady()){
            AssetLoader.shadow.draw(spriteBatch,"Tap To Start",GameScreen.screenWidth/2-70,GameScreen.screenHeight/4);
            AssetLoader.font.draw(spriteBatch, "Tap To Start", GameScreen.screenWidth/2 -68, GameScreen.screenHeight/4);
        }else{
            if(gameWorld.isGameOver()){
                AssetLoader.shadow.draw(spriteBatch,"Game Over",GameScreen.screenWidth/2-50,GameScreen.screenHeight/4-50);
                AssetLoader.font.draw(spriteBatch, "Game Over", GameScreen.screenWidth/2-48,GameScreen.screenHeight/4-52);

                AssetLoader.shadow.draw(spriteBatch,"Best Score:"+AssetLoader.prefs.getFloat("bestScore"),GameScreen.screenWidth/2-60,GameScreen.screenHeight/4);
                AssetLoader.font.draw(spriteBatch, "Best Score:"+AssetLoader.prefs.getFloat("bestScore"), GameScreen.screenWidth/2-58,GameScreen.screenHeight/4);

                AssetLoader.shadow.draw(spriteBatch,"Tap To Restart",GameScreen.screenWidth/2-50,GameScreen.screenHeight/4*3);
                AssetLoader.font.draw(spriteBatch, "Tap To Restart", GameScreen.screenWidth/2 -48, GameScreen.screenHeight/4*3);
            }
        }


        //rotation
        if(!boxUp.isDead()){
            spriteBatch.draw(boxUpTR, boxUp.getX(), boxUp.getY(),boxUp.getWidth()/2.0f,boxUp.getHeight()/2.0f, boxUp.getWidth(), boxUp.getHeight(),1,1,boxUp.getRotation());
        }
        if(!boxDown.isDead()){
            spriteBatch.draw(boxDownTR, boxDown.getX(), boxDown.getY(),boxDown.getWidth()/2.0f,boxDown.getHeight()/2.0f, boxDown.getWidth(), boxDown.getHeight(),1,1,boxDown.getRotation());

        }

        //score
        String score = gameWorld.getScore() + "";
        //draw shadow first
//        AssetLoader.shadow.draw(spriteBatch,"s",50,1);
//        AssetLoader.font.draw(spriteBatch,"s",52,1);

        AssetLoader.shadow.draw(spriteBatch,score,10,10);
        AssetLoader.font.draw(spriteBatch,score,8,8);

        spriteBatch.end();


    }
}
