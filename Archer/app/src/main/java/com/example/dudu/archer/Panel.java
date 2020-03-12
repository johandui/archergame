package com.example.dudu.archer;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.v4.view.MotionEventCompat;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dudu on 12/18/17.
 */

public class Panel extends SurfaceView implements SurfaceHolder.Callback {
    public static final int WIDTH = 856, HEIGHT = 480;
    private MainThread thread;
    private Archer archer;
    private Background bg;
    private int numofarrow = 14;
    private int numberofsun = 7;
    private int Radius = 100;
    private List<Sun> suns;
    final CharSequence[] choice = {"Тийм","Үгүй"};
    public Panel(Context context) {
        super(context);
        getHolder().addCallback(this);
        thread = new MainThread(getHolder(), this);
        setFocusable(true);
    }



    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        bg = new Background(BitmapFactory.decodeResource(getResources(), R.drawable.bg));
        Bitmap im = BitmapFactory.decodeResource(getResources(), R.drawable.arc);
        archer = new Archer(im, (int)(getWidth() * 0.2), getHeight() - im.getHeight() + 200);
        archer.setFoot(new Foot(BitmapFactory.decodeResource(getResources(), R.drawable.foot),
                archer.getX(), archer.getY(), archer.getWidth(), archer.getHeight()));
        archer.setHand(new Hand(BitmapFactory.decodeResource(getResources(), R.drawable.hand),
                archer.getX(), archer.getY(), archer.getWidth(), archer.getHeight()));
        archer.setArrows(getArrows());
        getSuns();
        thread = new MainThread(getHolder(), this);
        thread.setRunning(true);
        thread.start();
    }
    public List<Arrow> getArrows(){
        List<Arrow> arrows = new ArrayList();
        for(int i = 0; i < numofarrow; i++){
            arrows.add(new Arrow(BitmapFactory.decodeResource(getResources(), R.drawable.arrow),
                    BitmapFactory.decodeResource(getResources(), R.drawable.arrow1),
                    archer.getX(), archer.getY(),archer.getWidth(), archer.getHeight()));
        }
        return arrows;
    }
    public List<Sun> getSuns(){
        suns = new ArrayList<Sun>();
        int k = 40;
        for(int i = 0; i < numberofsun; i++){
            suns.add(new Sun(BitmapFactory.decodeResource(getResources(), R.drawable.sun), 50, 20, getWidth() + 100, k));
            k += 40;
        }
        return suns;
    }
    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        boolean retry = true;
        while(true){
            try {
                thread.setRunning(false);
                thread.join();
            }catch (Exception e) {
                e.printStackTrace();

            }
            retry = false;
        }
    }
    @Override
    public boolean onTouchEvent(MotionEvent event){

        int action = MotionEventCompat.getActionMasked(event);
        if(event.getAction() == MotionEvent.ACTION_DOWN){
            int x = (int) event.getX();
            int y = (int) event.getY();
            int vectorX = x - this.archer.getX();
            int vectorY = y - this.archer.getY();
            this.archer.setVector(vectorX, vectorY);
            return true;

        }
        if(event.getAction() == MotionEvent.ACTION_MOVE){
            Log.i("dada", "da");
            int x = (int) event.getX();
            int y = (int) event.getY();
            int vectorX = x - this.archer.getX();
            int vectorY = y - this.archer.getY();
            this.archer.setVector(vectorX, vectorY);
            return true;
        }
        if(event.getAction() == MotionEvent.ACTION_UP){
            int x = (int) event.getX();
            int y = (int) event.getY();
            int vectorX = x - this.archer.getX();
            int vectorY = y - this.archer.getY();
            this.archer.setVector(vectorX, vectorY);
            this.archer.setShoot(true);
            return true;

        }
        return false;

    }

    public void update(){
        archer.update();
        suns.get(suns.size() - 1).update();
        if(suns.get(suns.size() - 1).shootingTarget(archer.getArrow())){
            Log.i("bzdrah", "dadadadada");
            suns.remove(suns.size() - 1);

        }

    }
    @Override
    public void draw(Canvas canvas){
        if(archer.getArrows().size() == 0){
            Paint paint = new Paint();
            paint.setTextSize(100);
            drawCenterText(canvas, paint, "GAME OVER");
        }
        else {
            if (suns.size() == 0) {
                Paint paint = new Paint();
                paint.setTextSize(100);
                drawCenterText(canvas, paint, "You WIN");
            }
            else {
                float scaleX = (float) WIDTH / (getWidth() * 1.f);
                float scaleY = (float) HEIGHT / (getHeight() * 1.f);
                super.draw(canvas);
                final int savedState = canvas.save();
                canvas.scale(scaleX, scaleY);
                bg.draw(canvas);
                archer.draw(canvas);
                suns.get(suns.size() - 1).draw(canvas);
                canvas.restoreToCount(savedState);
            }
        }
    }

    private void drawCenterText(Canvas canvas, Paint paint, String s) {
        paint.setTextAlign(Paint.Align.LEFT);
        canvas.getClipBounds();
        canvas.drawText(s, getWidth() / 2 - s.length(), getHeight() / 2, paint);
        thread.setRunning(false);

    }
}
