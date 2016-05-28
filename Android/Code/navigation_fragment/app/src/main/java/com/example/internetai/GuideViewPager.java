package com.example.internetai;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;

/**
 * Created by joho on 2016/4/24.
 */
public class GuideViewPager extends ViewPager {

    private Bitmap bg;
    private Paint b = new Paint(1);

    public GuideViewPager(Context context) {
        super(context);
    }

    public GuideViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        if(this.bg != null) {
            int width = this.bg.getWidth();
            int height = this.bg.getHeight();
            int count = getAdapter().getCount();
            int x = getScrollX();

            int n = height * getWidth() / getHeight();

            int w = x * ((width - n) / (count - 1)) / getWidth();
            canvas.drawBitmap(this.bg, new Rect(w, 0, n + w, height), new Rect(x, 0, x + getWidth(),getHeight()), this.b);
        }
        super.dispatchDraw(canvas);
    }

    public void setBackGround(Bitmap paramBitmap) {
        this.bg = paramBitmap;
        this.b.setFilterBitmap(true);
    }
}
