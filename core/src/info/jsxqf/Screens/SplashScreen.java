package info.jsxqf.Screens;

import aurelienribon.tweenengine.*;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import info.jsxqf.Helper.AssetLoader;
import info.jsxqf.TweenAccessors.SpriteAccessor;
import info.jsxqf.ZombieBird.ZBGame;

/**
 * Created by jsxqf on 14-5-26.
 */
public class SplashScreen implements Screen {

    private TweenManager manager;
    private SpriteBatch spriteBatch;
    private Sprite sprite;
    private ZBGame game;

    public SplashScreen(ZBGame game){
        this.game = game;
    }


    @Override
    public void show() {
        sprite = new Sprite(AssetLoader.logo);
        sprite.setColor(1,1,1,0);

        float width = Gdx.graphics.getWidth();
        float height = Gdx.graphics.getHeight();

        float desireWidth = width * .5f;
        float scale = desireWidth/sprite.getWidth();
        sprite.setSize(sprite.getWidth()*scale,sprite.getHeight()*scale);

        sprite.setPosition((width/2)-(sprite.getWidth()/2),(height/2)-(sprite.getHeight()/2));
        setupTween();
        spriteBatch = new SpriteBatch();
    }
    private void setupTween(){
        Tween.registerAccessor(Sprite.class,new SpriteAccessor());
        manager = new TweenManager();

        TweenCallback cb = new TweenCallback() {
            @Override
            public void onEvent(int type, BaseTween<?> baseTween) {
                game.setScreen(new GameScreen());
            }
        };
        Tween.to(sprite,SpriteAccessor.ALPHA,2.0f).target(1).ease(TweenEquations.easeInOutQuad).repeatYoyo(1,.4f).setCallback(cb).setCallbackTriggers(TweenCallback.COMPLETE).start(manager);

    }

    @Override
    public void render(float delta) {
        manager.update(delta);
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        spriteBatch.begin();
        sprite.draw(spriteBatch);
        spriteBatch.end();
    }

    @Override
    public void resize(int width, int height) {

    }


    @Override
    public void hide() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {

    }
}
