package earl.com.assignment2;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.View;


public class Canvas extends View {

    Paint paint;
    Rect rect;

    public Canvas(Context context) {
        super(context);
    }

    @Override
    protected void onDraw(android.graphics.Canvas canvas) {
        super.onDraw(canvas);

        rect = new Rect();
        rect.set(0,0,canvas.getWidth(),canvas.getHeight()/2);
        Paint blue = new Paint();
        blue.setColor(Color.BLUE);
        blue.setStyle(Paint.Style.FILL);

        canvas.drawRect(rect,blue);
    }
}

//attempted to use canvas but didn't work.