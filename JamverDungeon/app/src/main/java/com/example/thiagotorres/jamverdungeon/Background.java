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

/**
 * Created by Thiago on 15/07/2016
 */
public class Background {

    private boolean albumSizeSet = false;
    Paint paint;
    Bitmap album;

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
        Shader shader = new LinearGradient(0, 0, 0, canvas.getHeight() / 4, Color.BLACK, Color.WHITE, Shader.TileMode.CLAMP);
        Paint paint = new Paint();
        paint.setShader(shader);
        canvas.drawRect(new RectF(0, 0, canvas.getWidth(), canvas.getHeight()), paint); // <== Fix this;
        canvas.drawBitmap(album, canvas.getWidth() / 20, canvas.getHeight() / 35, null);
    }

    void DrawGridBackground(Canvas canvas, Bitmap bitmap) {

        //Don't get me wrong, I AM going to optimize this function, I just wanted to see it working.

        Rect rect_01 = new Rect(0,(canvas.getHeight() / 2) + bitmap.getHeight(), canvas.getWidth(), (canvas.getHeight() / 2) + bitmap.getHeight() * 2);
        Rect rect_02 = new Rect(0, (canvas.getHeight() / 2), canvas.getWidth(), (canvas.getHeight() / 2) + bitmap.getHeight());
        Rect rect_03 = new Rect(0, (canvas.getHeight() / 2) - bitmap.getHeight() * 2, canvas.getWidth(), (canvas.getHeight() / 2) - bitmap.getHeight());
        Rect rect_04 = new Rect(0, (canvas.getHeight() / 2) - bitmap.getHeight(), canvas.getWidth(), (canvas.getHeight() / 2));

        Paint paint_01 = new Paint();
        paint_01.setColor(Color.rgb(244, 100, 100));
        Paint paint_02 = new Paint();
        paint_02.setColor(Color.rgb(86, 186, 236));
        Paint paint_03 = new Paint();
        paint_03.setColor(Color.rgb(176, 229, 124));
        Paint paint_04 = new Paint();
        paint_04.setColor(Color.rgb(255, 236, 148));

        canvas.drawRect(rect_01, paint_01);
        canvas.drawRect(rect_02, paint_02);
        canvas.drawRect(rect_03, paint_03);
        canvas.drawRect(rect_04, paint_04);
    }

    void DrawButton(Canvas canvas) {
        Rect r = new Rect();
        paint.setColor(Color.LTGRAY);
        r.set(canvas.getWidth() / 2, 100, canvas.getWidth() / 2 + 200, 150);
        canvas.drawRect(r, paint);
    }
}
