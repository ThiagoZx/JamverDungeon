package com.example.thiagotorres.jamverdungeon;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.media.MediaPlayer;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


/**
 * Created by Thiago.Torres on 08/07/2016
 */
public class SongView extends View implements Runnable {

    //Context
    Context context;

    //Manager
    private Handler handler;

    //Time measure
    long lastTime;
    long startTime;

    //Bitmaps
    Bitmap arrow;

    //GameOver button
    Background background = new Background(BitmapFactory.decodeResource(getResources(), R.drawable.chickenlegsart));
    boolean songOver = false;

    //Media Stuff
    MediaPlayer song;

    //Listas
    List<Controller> controllers;
    List<Grid> bar;
    List<Command> commands;

    //Canvas width, height
    int canvasWidth, canvasHeight;

    public SongView(Context context) {

        super(context);
        handler = new Handler();
        handler.post(this);

        this.context = context;

        Start();

    }

    void gameOver() {
        Intent sendIntent = new Intent();
        sendIntent.setAction("JAMV");
        sendIntent.putExtra("letter", "Q0");
        context.startActivity(sendIntent);
    }

    void sendNote() {
        long currTime = System.currentTimeMillis();
        Random r = new Random();
        int note = r.nextInt(4);
        if (currTime - lastTime > 742.89){
            commands.add(commands.size(), new Command(arrow, note));
            lastTime = currTime;
        }
    }

    void destroyNote(int note) {
        for (int i = 0; i < commands.size(); i++) {
            if (commands.get(i) != null && commands.get(i).body != null && Rect.intersects(commands.get(i).body, bar.get(note).body) && commands.get(i).getDirection() == note) {
                commands.remove(i);
            }
        }
    }

    void songProgress(Canvas canvas) {
        Paint progressPaint = new Paint();

        //Draw Background
        progressPaint.setColor(Color.rgb(16, 16, 16));
        Rect progressBackground = new Rect((4 * canvas.getWidth() / 10) - 3, ((canvas.getHeight() / 35 * 8) - 50) - 3, (canvas.getWidth() - canvas.getWidth() / 20) + 3, (canvas.getHeight() / 35 * 8) + 3);
        canvas.drawRect(progressBackground, progressPaint);

        //Draw Progress
        progressPaint.setColor(Color.rgb(255, 151, 2));
        float songPosition = (4 * canvas.getWidth() / 10) + (6 * canvas.getWidth() / 10  - canvas.getWidth() / 20) * song.getCurrentPosition() / song.getDuration();
        Rect progress = new Rect(4 * canvas.getWidth() / 10, (canvas.getHeight() / 35 * 8) - 50, (int)songPosition, canvas.getHeight() / 35 * 8);
        canvas.drawRect(progress, progressPaint);
    }

    @Override
    protected void onDraw(Canvas canvas){

        canvasHeight = canvas.getHeight();
        canvasWidth = canvas.getWidth();

        background.DrawBackground(canvas);
        background.DrawGridBackground(canvas, arrow);
        background.DrawAuthorName(canvas);

        songProgress(canvas);


        if (song.isPlaying()){
            sendNote();
        } else {
            background.DrawButton(canvas);
            songOver = true;
        }

        for (int i = 0; i < 4; i++){
            controllers.get(i).drawController(canvas);
            bar.get(i).drawGrid(canvas);
        }

        for (int i = 0; i < commands.size(); i++) {
            commands.get(i).drawCommand(canvas);
        }

        super.onDraw(canvas);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event){
        int action = event.getAction();
        int x = (int) event.getRawX();
        int y = (int) event.getRawY();

        switch(action){
            case MotionEvent.ACTION_DOWN:

                for (int i = 0; i < controllers.size(); i++){
                    if (x >= controllers.get(i).getAxis("x") && x < (controllers.get(i).getAxis("x") + controllers.get(i).getSize("width"))
                            && y >= controllers.get(i).getAxis("y") && y < (controllers.get(i).getAxis("y") + controllers.get(i).getSize("height"))) {
                        destroyNote(i);
                    }
                }

                if (songOver && x > 4 * canvasWidth / 10 && x < canvasWidth - canvasWidth / 20 && y > (canvasHeight / 35 * 8) - 50 && y < (canvasHeight / 35 * 8)){
                    gameOver();
                }

                break;
        }

        return true;
    }

    void Update(){

        for (int i = 0; i < bar.size(); i++) {
            bar.get(i).updateGrid();
        }

        for (int i = 0; i < commands.size(); i++) {
            commands.get(i).updateCommand();
            if (commands.get(i).deleteCommand()){
                commands.remove(i);
            }
        }
    }

    void Start() {
        controllers = new ArrayList<>();
        bar = new ArrayList<>();
        commands = new ArrayList<>();

        arrow = BitmapFactory.decodeResource(getResources(), R.drawable.arrow);

        song = MediaPlayer.create(context, R.raw.chickenlegs);
        startTime = System.currentTimeMillis();
        song.start();

        for (int i = 0; i < 4; i++) {
            controllers.add(controllers.size(), new Controller(arrow, i));
            bar.add(bar.size(), new Grid(arrow, i));
        }
    }

    @Override
    public void run() {
        handler.postDelayed(this, 30);
        Update();
        invalidate();
    }
}
