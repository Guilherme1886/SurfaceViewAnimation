package com.gui.toledo.surfaceviewanimation;

import static com.gui.toledo.surfaceviewanimation.Utils.getRandomIndex;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.os.Looper;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.ArrayList;
import java.util.Random;

public class AnimatedSurfaceView extends SurfaceView implements SurfaceHolder.Callback {

    private SurfaceHolder surfaceHolder;
    private Paint paint;
    private float circleX, circleY;
    private int circleRadius = 50;
    private float velocityX = 15f, velocityY = 15f;
    private Handler handler;
    private ArrayList<Integer> colors;
    private Random random;
    private int min = 0;
    private int max = 0;

    public AnimatedSurfaceView(Context context) {
        super(context);

        surfaceHolder = getHolder();
        surfaceHolder.addCallback(this);

        paint = new Paint();
        paint.setColor(Color.RED);

        handler = new Handler(Looper.getMainLooper());

        colors = new ArrayList<>();
        colors.add(Color.BLUE);
        colors.add(Color.CYAN);
        colors.add(Color.DKGRAY);
        colors.add(Color.GREEN);
        colors.add(Color.LTGRAY);
        colors.add(Color.MAGENTA);
        colors.add(Color.RED);
        colors.add(Color.YELLOW);

        random = new Random();

        max = colors.size();

        setFocusable(true);

        circleX = 100;
        circleY = 100;
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        DrawingThread drawingThread = new DrawingThread();
        drawingThread.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }

    private void updateCircle() {
        circleX += velocityX;
        circleY += velocityY;

        if (circleX - circleRadius <= 0 || circleX + circleRadius >= getWidth()) {
            velocityX = -velocityX;
            setCircleColor();
        }

        if (circleY - circleRadius <= 0 || circleY + circleRadius >= getHeight()) {
            velocityY = -velocityY;
            setCircleColor();
        }
    }

    private void setCircleColor() {
        paint.setColor(getRandomColor());
    }

    private int getRandomColor() {
        return colors.get(getRandomIndex(colors));
    }

    private void drawCircle() {
        Canvas canvas = null;
        try {
            canvas = surfaceHolder.lockCanvas();

            if (canvas != null) {
                synchronized (surfaceHolder) {
                    canvas.drawColor(Color.BLACK);
                    canvas.drawCircle(circleX, circleY, circleRadius, paint);
                }
            }
        } finally {
            if (canvas != null) {
                surfaceHolder.unlockCanvasAndPost(canvas);
            }
        }
    }

    private class DrawingThread extends Thread {
        @Override
        public void run() {
            while (true) {
                updateCircle();
                handler.post(AnimatedSurfaceView.this::drawCircle);
                try {
                    Thread.sleep(16);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

