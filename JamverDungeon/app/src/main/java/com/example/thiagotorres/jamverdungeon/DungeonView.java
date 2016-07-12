package com.example.thiagotorres.jamverdungeon;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.os.Handler;
import android.view.View;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Thiago.Torres on 08/07/2016.
 */
public class DungeonView extends View implements Runnable {


    //Manager
    private Handler handler;

    //Test stuff
    Player character;

    //Listas
    List<Controller> controllers;

    public DungeonView(Context context) {

        super(context);
        handler = new Handler();
        handler.post(this);

        Start();
    }

    void Start() {
        controllers = new ArrayList<>();

        controllers.add(controllers.size(), new Controller(BitmapFactory.decodeResource(getResources(), R.drawable.arrow), "up"));
        controllers.add(controllers.size(), new Controller(BitmapFactory.decodeResource(getResources(), R.drawable.arrow), "down"));
        controllers.add(controllers.size(), new Controller(BitmapFactory.decodeResource(getResources(), R.drawable.arrow), "left"));
        controllers.add(controllers.size(), new Controller(BitmapFactory.decodeResource(getResources(), R.drawable.arrow), "right"));

        character = new Player();
    }

    @Override
    protected void onDraw(Canvas canvas){
        character.DrawPlayer(canvas);
        for (int i = 0; i < controllers.size(); i++){
            controllers.get(i).drawController(canvas);
        }
        super.onDraw(canvas);
    }

    @Override
    public void run() {
        handler.postDelayed(this, 30);
        //Update(); <-- Do it Later;
        invalidate();
    }
}
