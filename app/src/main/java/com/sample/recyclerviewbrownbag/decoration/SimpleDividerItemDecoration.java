package com.sample.recyclerviewbrownbag.decoration;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by S. Reyes on 2/2/16.
 */
public class SimpleDividerItemDecoration extends RecyclerView.ItemDecoration {

    private Paint paintBlue, paintRed;
    private int offset;

    public SimpleDividerItemDecoration(){
        offset = 10;
        paintBlue = new Paint(Paint.ANTI_ALIAS_FLAG);
        paintBlue.setColor(Color.BLUE);
        paintBlue.setStyle(Paint.Style.STROKE);
        paintBlue.setStrokeWidth(5);

        paintRed = new Paint(Paint.ANTI_ALIAS_FLAG);
        paintRed.setColor(Color.RED);
        paintRed.setStyle(Paint.Style.STROKE);
        paintRed.setStrokeWidth(1);
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        outRect.set(offset, offset, offset, offset);
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);

        final RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();

        for(int i=0; i<parent.getChildCount(); i++){
            final View child = parent.getChildAt(i);
            c.drawLine(layoutManager.getDecoratedLeft(child),
                    layoutManager.getDecoratedBottom(child),
                    layoutManager.getDecoratedRight(child),
                    layoutManager.getDecoratedBottom(child),
                    paintBlue);
        }
    }

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDrawOver(c, parent, state);
        final RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();

//        // Loop every child and draw on top
        for (int i = 0; i < parent.getChildCount(); i++) {
            final View child = parent.getChildAt(i);
            c.drawLine(layoutManager.getDecoratedLeft(child),
                    layoutManager.getDecoratedBottom(child),
                    layoutManager.getDecoratedRight(child),
                    layoutManager.getDecoratedBottom(child),
                    paintBlue);
//            c.drawRect(layoutManager.getDecoratedLeft(child) + (offset * 2),
//                    layoutManager.getDecoratedTop(child) + (offset * 2),
//                    layoutManager.getDecoratedRight(child) - (offset * 2),
//                    layoutManager.getDecoratedBottom(child) - (offset * 2),
//                    paintRed);
//            c.drawLine(layoutManager.getDecoratedLeft(child),
//                    layoutManager.getDecoratedBottom(child),
//                    layoutManager.getDecoratedRight(child),
//                    layoutManager.getDecoratedBottom(child),
//                    paintBlue);
        }
    }


}
