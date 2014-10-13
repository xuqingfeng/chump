package info.jsxqf.Helper;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by jsxqf on 14-5-22.
 */
public class AssetLoader {

    public static Texture boxUpT,boxDownT;
    public static TextureRegion boxUp,boxDown;

    public static Texture barrierUpT,barrierDownT;
    public static TextureRegion barrierUp1,barrierUp2,barrierUp3,barrierDown1,barrierDown2,barrierDown3;

//    public static Texture playT;
//    public static TextureRegion play;

    public static Texture logoT;
    public static TextureRegion logo;

//    public static Texture bgT;
//    public static TextureRegion bg;

    public static Sound dead;
//    public static Sound background;
    public static Music background;

    public static BitmapFont font,shadow;

    public static Preferences prefs;

    public static void load(){
        boxUpT = new Texture(Gdx.files.internal("data/boxUp.png"));
        boxUpT.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);
        boxUp = new TextureRegion(boxUpT,0,0,20,20);
        //y+
        boxUp.flip(false,true);

        boxDownT = new Texture(Gdx.files.internal("data/boxDown.png"));
        boxDownT.setFilter(Texture.TextureFilter.Nearest,Texture.TextureFilter.Nearest);
        boxDown = new TextureRegion(boxDownT,0,0,20,20);
        boxDown.flip(false,true);

        barrierUpT = new Texture(Gdx.files.internal("data/barrierUp.png"));
        barrierUpT.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);
        barrierUp1 = new TextureRegion(barrierUpT,0,0,30,30);
        barrierUp2 = new TextureRegion(barrierUpT,0,0,30,30);
        barrierUp3 = new TextureRegion(barrierUpT,0,0,30,30);

        barrierUp1.flip(false,true);
        barrierUp2.flip(false,true);
        barrierUp3.flip(false,true);

        barrierDownT = new Texture(Gdx.files.internal("data/barrierDown.png"));
        barrierDownT.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);
        barrierDown1 = new TextureRegion(barrierDownT,0,0,30,30);
        barrierDown2 = new TextureRegion(barrierDownT,0,0,30,30);
        barrierDown3 = new TextureRegion(barrierDownT,0,0,30,30);
        barrierDown1.flip(false,true);
        barrierDown2.flip(false,true);
        barrierDown3.flip(false,true);

//        bgT = new Texture(Gdx.files.internal("data/bg.jpg"));
//        bgT.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);
//        bg = new TextureRegion(bgT,0,0,600,600);

//        playT = new Texture(Gdx.files.internal("data/play.png"));
//        barrierDownT.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);
//        play = new TextureRegion(playT,0,0,200,200);
//        play.flip(false,true);

        dead = Gdx.audio.newSound(Gdx.files.internal("data/rockman.mp3"));
//        background = Gdx.audio.newSound(Gdx.files.internal("data/bg.mp3"));
        background = Gdx.audio.newMusic(Gdx.files.internal("data/bg.mp3"));
//        background.loop(.5f);

        font = new BitmapFont(Gdx.files.internal("data/text.fnt"));
        font.setScale(.3f,-.3f);
        shadow = new BitmapFont(Gdx.files.internal("data/shadow.fnt"));
        shadow.setScale(.3f,-.3f);

        prefs = Gdx.app.getPreferences("ZBGame");
        if(!prefs.contains("bestScore")){
            prefs.putFloat("bestScore",0);
        }
    }

    public static void loadLogo(){

        logoT = new Texture(Gdx.files.internal("data/logo.png"));
        logoT.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

        logo = new TextureRegion(logoT,0,0,600,600);

    }

    public static void dispose(){
        boxUpT.dispose();
        boxDownT.dispose();
//        playT.dispose();
        barrierUpT.dispose();
        barrierDownT.dispose();
        logoT.dispose();
//        bgT.dispose();
        dead.dispose();
        background.dispose();
        font.dispose();
        shadow.dispose();
    }
}
