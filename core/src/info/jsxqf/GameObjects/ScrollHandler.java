package info.jsxqf.GameObjects;

import info.jsxqf.Screens.GameScreen;

/**
 * Created by jsxqf on 14-5-23.
 */
public class ScrollHandler {

    private BarrierUp barrierUp1, barrierUp2, barrierUp3;
    private BarrierDown barrierDown1,barrierDown2,barrierDown3;

    public static final int SPEED_UP = -150;
    public static final int SPEED_DOWN = 150;
    public static final int BARRIER_GAP = 150;

    public ScrollHandler(){
        int barrierUpY = (int)(GameScreen.screenHeight/2)-40;
        barrierUp1 = new BarrierUp(GameScreen.screenWidth,barrierUpY,30,30,SPEED_UP);
        barrierUp2 = new BarrierUp(barrierUp1.getTailX()+BARRIER_GAP,barrierUpY,30,30,SPEED_UP);
        barrierUp3 = new BarrierUp(barrierUp2.getTailX()+BARRIER_GAP,barrierUpY,30,30,SPEED_UP);

        int barrierDownY = (int)(GameScreen.screenHeight/2)+10;
        barrierDown1 = new BarrierDown(-80,barrierDownY,30,30,SPEED_DOWN);
        barrierDown2 = new BarrierDown(-80-barrierDown1.getRandomGap()-BARRIER_GAP,barrierDownY,30,30,SPEED_DOWN);
        barrierDown3 = new BarrierDown(-280-barrierDown2.getTailX()-BARRIER_GAP,barrierDownY,30,30,SPEED_DOWN);
    }

    public void update(float delta){
        barrierUp1.update(delta);
        barrierUp2.update(delta);
        barrierUp3.update(delta);

        barrierDown1.update(delta);
        barrierDown2.update(delta);
        barrierDown3.update(delta);

        if(barrierUp1.isScrolledLeft()){
            barrierUp1.reset(GameScreen.screenWidth+barrierUp3.getTailX());
//            barrierUp1.reset(barrierUp3.getTailX()+BARRIER_GAP);
        }else if(barrierUp2.isScrolledLeft()){
//            barrierUp2.reset(barrierUp1.getTailX()+BARRIER_GAP);
            barrierUp2.reset(barrierUp1.getTailX()+BARRIER_GAP);

        }else if(barrierUp3.isScrolledLeft()){
//            barrierUp3.reset(barrierUp2.getTailX()+BARRIER_GAP);
            barrierUp3.reset(barrierUp2.getTailX()+BARRIER_GAP);

        }

        if(barrierDown1.isScrolledRight()){
            barrierDown1.reset(-80-BARRIER_GAP);
        }else if(barrierDown2.isScrolledRight()){
            barrierDown2.reset(-80-barrierDown1.getRandomGap()-BARRIER_GAP);
        }else if(barrierDown3.isScrolledRight()){
            barrierDown3.reset(-280-barrierDown2.getTailX()-BARRIER_GAP);
        }

    }

    public void onRestart(){
        barrierUp1.onRestart(GameScreen.screenWidth,SPEED_UP);
        barrierUp2.onRestart(barrierUp1.getTailX()+BARRIER_GAP,SPEED_UP);
        barrierUp3.onRestart(barrierUp2.getTailX()+BARRIER_GAP,SPEED_UP);

        barrierDown1.onRestart(-80,SPEED_DOWN);
        barrierDown2.onRestart(-80-barrierDown1.getRandomGap()-BARRIER_GAP,SPEED_DOWN);
        barrierDown3.onRestart(-280-barrierDown2.getTailX()-BARRIER_GAP,SPEED_DOWN);

    }

    public void stop(){
        barrierUp1.stop();
        barrierUp2.stop();
        barrierUp3.stop();

        barrierDown1.stop();
        barrierDown2.stop();
        barrierDown3.stop();

    }

    public boolean collides(BoxUp boxUp,BoxDown boxDown){
        return barrierUp1.collides(boxUp)||barrierUp2.collides(boxUp)||barrierUp3.collides(boxUp)||barrierDown1.collides(boxDown)||barrierDown2.collides(boxDown)||barrierDown3.collides(boxDown);
    }

    public BarrierUp getBarrierUp1() {
        return barrierUp1;
    }

    public BarrierUp getBarrierUp2() {
        return barrierUp2;
    }

    public BarrierUp getBarrierUp3() {
        return barrierUp3;
    }

    public BarrierDown getBarrierDown1() {
        return barrierDown1;
    }

    public BarrierDown getBarrierDown2() {
        return barrierDown2;
    }

    public BarrierDown getBarrierDown3() {
        return barrierDown3;
    }
}
