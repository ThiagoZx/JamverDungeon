package com.example.thiagotorres.jamverdungeon;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by Thiago on 15/07/2016
 */
public class Background {

    //Bar Background
    private boolean barBackgroundSet = false;
    private Rect[] rects = new Rect[4];
    private Paint[] paints = new Paint[4];

    //Album
    private boolean albumSizeSet = false;
    private Paint paint = new Paint();
    private Bitmap album;

    public Background(Bitmap bitmap){
        album = bitmap;
    }

    public Bitmap setAlbumSize(Bitmap bm, int newHeight, int newWidth) {
        int width = bm.getWidth();
        int height = bm.getHeight();
        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;

        Matrix matrix = new Matrix();
        matrix.postScale(scaleWidth, scaleHeight);

        Bitmap resizedBitmap = Bitmap.createBitmap(bm, 0, 0, width, height, matrix, false);
        return resizedBitmap;
    }

    void DrawBackground(Canvas canvas) {
        if (!albumSizeSet) { album = setAlbumSize(album, canvas.getHeight() / 5, canvas.getWidth() / 3); }
        paint.setColor(Color.rgb(64,64,64));
        canvas.drawRect(new Rect(0, 0, canvas.getWidth(), canvas.getHeight()), paint);

        paint.setColor(Color.rgb(32, 32, 32));
        canvas.drawRect(new Rect(canvas.getWidth() / 20 - 10, canvas.getHeight() / 35 - 10, (canvas.getWidth() - canvas.getWidth() / 20) + 13, (canvas.getHeight() / 35 * 8) + 13), paint);
        canvas.drawBitmap(album, canvas.getWidth() / 20, canvas.getHeight() / 35, null);
    }

    void DrawAuthorName() {
        
    }

    void setBarBackground(Canvas canvas, Bitmap bitmap){
        barBackgroundSet = true;
        for (int i = -2; i < 2; i++) {
            rects[i + 2] = new Rect(0, (canvas.getHeight() / 2) + (bitmap.getHeight() * i), canvas.getWidth(), (canvas.getHeight() / 2) + bitmap.getHeight() * (i + 1));
        }
        paints[0] = new Paint();
        paints[0].setColor(Color.rgb(244, 100, 100));
        paints[1] = new Paint();
        paints[1].setColor(Color.rgb(86, 186, 236));
        paints[2] = new Paint();
        paints[2].setColor(Color.rgb(176, 229, 124));
        paints[3] = new Paint();
        paints[3].setColor(Color.rgb(255, 236, 148));
    }

    void DrawGridBackground(Canvas canvas, Bitmap bitmap) {

        if (!barBackgroundSet){ setBarBackground(canvas, bitmap); }

        canvas.drawRect(rects[0], paints[2]);
        canvas.drawRect(rects[1], paints[3]);
        canvas.drawRect(rects[2], paints[1]);
        canvas.drawRect(rects[3], paints[0]);
    }

    void DrawButton(Canvas canvas) {
        Rect r = new Rect();
        paint.setColor(Color.rgb(52, 73, 94));
        r.set(canvas.getWidth() / 2, (canvas.getHeight() / 35 * 8) - 50, canvas.getWidth() - canvas.getWidth() / 20, (canvas.getHeight() / 35 * 8));
        canvas.drawRect(r, paint);
        paint.setColor(Color.WHITE);
        paint.setTextSize(36);
        canvas.drawText("Letra Q!", 6 * canvas.getWidth() / 10, (canvas.getHeight() / 35 * 8) - 10, paint);
    }
}
