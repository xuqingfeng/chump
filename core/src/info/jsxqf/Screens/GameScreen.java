package info.jsxqf.Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import info.jsxqf.GameWorld.GameRender;
import info.jsxqf.GameWorld.GameWorld;
import info.jsxqf.Helper.InputHandler;

/**
 * Created by jsxqf on 14-5-21.
 */
public class GameScreen implements Screen {

    private GameWorld gameWorld;
    private GameRender gameRender;

    public static float screenWidth;
    public static float screenHeight;
    //track time
    private float runTime = 0;

    public GameScreen(){
        System.out.println("GameScreen");

        screenWidth = Gdx.graphics.getWidth();
        screenHeight = Gdx.graphics.getHeight();

        int midPointX = (int)(screenWidth/2);
        int midPointY = (int)(screenHeight/2);

        gameWorld = new GameWorld(midPointX,midPointY);
        gameRender = new GameRender(gameWorld);

        //
        Gdx.input.setInputProcessor(new InputHandler(gameWorld));
    }

    @Override
    public void render(float delta) {
//        Gdx.gl.glClearColor(24/255.0f,159/255.0f,209/255.0f,1f);
//        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        runTime += delta;
        gameWorld.update(delta);

        //runTime
        gameRender.render();
    }

    @Override
    public void resize(int width, int height) {
        System.out.println("resize");
    }

    @Override
    public void show() {
        System.out.println("show");
    }

    @Override
    public void hide() {
        System.out.println("hide");
    }

    @Override
    public void pause() {
        System.out.println("pause");
    }

    @Override
    public void resume() {
        System.out.println("resume");
    }

    @Override
    public void dispose() {
        System.out.println("dispose");
    }
}
