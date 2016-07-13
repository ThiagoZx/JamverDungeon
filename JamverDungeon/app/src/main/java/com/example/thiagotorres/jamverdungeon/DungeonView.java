package com.example.thiagotorres.jamverdungeon;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Thiago.Torres on 08/07/2016.
 */
public class DungeonView extends View implements Runnable {


    //Context oooh
    Context context;

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

        this.context = context;

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
    public boolean onTouchEvent(MotionEvent event){
        int action = event.getAction();
        int x = (int) event.getRawX();
        int y = (int) event.getRawY();

        switch(action){
            case MotionEvent.ACTION_DOWN:

                for (int i = 0; i < controllers.size(); i++){
                    if (x >= controllers.get(i).getAxis("x") && x < (controllers.get(i).getAxis("x") + controllers.get(i).getSize("width"))
                            && y >= controllers.get(i).getAxis("y") && y < (controllers.get(i).getAxis("y") + controllers.get(i).getSize("height"))) {
                        controllers.get(i).movePlayerTo(character);

                        //Use this to show to the player that doors are locked, that he got the key, to look the map itself, etc.
                        CharSequence text = "Hello toast!";
                        int duration = Toast.LENGTH_LONG;
                        Toast toast = Toast.makeText(context, text, duration);
                        toast.show();

                        return true;
                    }
                }

                break;
        }

        return false;
    }

    @Override
    public void run() {
        handler.postDelayed(this, 30);
        //Update(); <-- Do it Later;
        invalidate();
    }
}
