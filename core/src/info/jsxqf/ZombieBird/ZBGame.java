package info.jsxqf.ZombieBird;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import info.jsxqf.Helper.AssetLoader;
import info.jsxqf.Screens.GameScreen;
import info.jsxqf.Screens.SplashScreen;

public class ZBGame extends Game {

	@Override
	public void create () {
        System.out.println("ZBGame");
        //don't work?
        AssetLoader.loadLogo();
        setScreen(new SplashScreen(this));
        AssetLoader.load();
    }

    public void dispose(){
        super.dispose();
        AssetLoader.dispose();
    }


}
