package hs.project.watermark.customview;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;

import androidx.annotation.IntRange;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class WaterMarkBgJava extends Drawable {

    private Paint paint = new Paint();
    private List<String> labels;
    private Context context;
    private int angle;
    private int fontSize;

    public WaterMarkBgJava(Context context, List<String> labels, int angle, int fontSize) {
        this.labels = labels;
        this.context = context;
        this.angle = angle;
        this.fontSize = fontSize;
    }

    @Override
    public void draw(@NonNull Canvas canvas) {

        int width = getBounds().right;
        int height = getBounds().bottom * 2;

        canvas.drawColor(Color.parseColor("#40F3F5F9"));
        paint.setColor(Color.parseColor("#000000"));

        paint.setAntiAlias(true);
        paint.setTextSize(sp2px(context, fontSize));
        canvas.save();
        canvas.rotate(angle);
        float textWidth = paint.measureText(labels.get(0));
        int index = 0;
        for (int positionY = height / 10; positionY <= height; positionY += height / 10 + 80) {
            float fromX = -width + (index++ % 2) * textWidth;
            for (float positionX = fromX; positionX < width; positionX += textWidth * 2) {
                int spacing = 0;//间距
                for (String label : labels) {
                    canvas.drawText(label, positionX, positionY + spacing, paint);
                    spacing = spacing + 50;
                }

            }
        }
        canvas.restore();
    }

    @Override
    public void setAlpha(@IntRange(from = 0, to = 255) int alpha) {

    }

    @Override
    public void setColorFilter(@Nullable ColorFilter colorFilter) {

    }

    @Override
    public int getOpacity() {
        return PixelFormat.UNKNOWN;
    }


    public static int sp2px(Context context, float spValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }
}