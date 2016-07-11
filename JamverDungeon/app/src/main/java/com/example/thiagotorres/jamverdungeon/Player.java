package com.example.thiagotorres.jamverdungeon;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

/**
 * Created by Thiago.Torres on 11/07/2016.
 */
public class Player {



    public Player(){

    }

    void DrawPlayer(Canvas canvas){
        Rect r = new Rect(0, 0, 50, 50);
        Paint p = new Paint();
        canvas.drawRect(r, p);
    }

}
