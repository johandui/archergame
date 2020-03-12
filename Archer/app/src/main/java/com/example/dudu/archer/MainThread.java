package com.example.dudu.archer;

import android.graphics.Canvas;
import android.util.Log;
import android.view.SurfaceHolder;

/**
 * Created by Dudu on 12/18/17.
 */

public class MainThread extends Thread {
    public static final int MAX_FPS = 30;
    private double averageFPS;
    private SurfaceHolder surfaceHolder;
    private Panel gamePanel;
    private boolean running;
    public static Canvas canvas;
    public void setRunning(boolean b){
        this.running = b;
    }
    public MainThread(SurfaceHolder surfaceHolder, Panel panel){
        super();
        this.surfaceHolder = surfaceHolder;
        this.gamePanel = panel;
    }
    @Override
    public void run(){
        long start;
        long timeMills = 1000 / MAX_FPS;
        long waitTime;
        int frameCount = 0;
        long totalTime = 0;
        long targetTime = 1000 / MAX_FPS;
        while(running){
            start = System.nanoTime();
            canvas = null;
            try{
                canvas = this.surfaceHolder.lockCanvas();
                synchronized (surfaceHolder){
                    this.gamePanel.update();
                    this.gamePanel.draw(canvas);
                }
            }catch (Exception e){e.printStackTrace();}
            finally {
                if(canvas != null){
                    try {
                        surfaceHolder.unlockCanvasAndPost(canvas);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }
            timeMills = (System.nanoTime() - start) / 1000000;
            waitTime = targetTime - timeMills;
            try {
                if(waitTime > 0)
                    this.sleep(waitTime);
            }catch (Exception e){
                e.printStackTrace();
            }
            totalTime += System.nanoTime() - start;
            frameCount++;
            if(frameCount == MAX_FPS){
                averageFPS = 1000 / ((totalTime/frameCount) / 1000000);
                frameCount = 0;
                totalTime = 0;
                Log.i("TIME",waitTime + "");
                Log.i("FPS",averageFPS + "");
            }
        }
    }
}
