package com.example.thiagotorres.jamverdungeon;

import android.content.Context;
import android.graphics.Canvas;
import android.os.Handler;
import android.view.View;


/**
 * Created by Thiago.Torres on 08/07/2016.
 */
public class DungeonView extends View implements Runnable {


    //Manager
    private Handler handler;

    //Test stuff
    Player character;

    public DungeonView(Context context) {

        super(context);
        handler = new Handler();
        handler.post(this);

        Start();
    }

    void Start() {
        character = new Player();
    }

    @Override
    protected void onDraw(Canvas canvas){
        character.DrawPlayer(canvas);
        super.onDraw(canvas);
    }

    @Override
    public void run() {
        handler.postDelayed(this, 30);
        //Update(); <-- Do it Later;
        invalidate();
    }
}
