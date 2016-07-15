package com.example.thiagotorres.jamverdungeon;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

/**
 * Created by Thiago on 15/07/2016.
 */
public class Background {

    public Paint paint = new Paint();

    public Background(){

    }

    void DrawButton(Canvas canvas) {
        Rect r = new Rect();
        r.set(canvas.getWidth() / 2 - 200, 50, canvas.getWidth() / 2 + 200, 150);
        canvas.drawRect(r, paint);
    }
}
