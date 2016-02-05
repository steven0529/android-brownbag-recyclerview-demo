package com.sample.recyclerviewbrownbag.decoration;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.sample.recyclerviewbrownbag.R;

/**
 * Created by S. Reyes on 2/2/16.
 */
public class BoxOverlayItemDecoration extends RecyclerView.ItemDecoration {

    private Paint paint1, paint2;
    private int offset;

    public BoxOverlayItemDecoration(Context context){
        offset = 5;
        paint1 = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint1.setColor(context.getResources().getColor(R.color.light_brown));
        paint1.setStyle(Paint.Style.STROKE);
        paint1.setStrokeWidth(3);

        paint2 = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint2.setColor(context.getResources().getColor(R.color.light_blue));
        paint2.setStyle(Paint.Style.STROKE);
        paint2.setStrokeWidth(1);
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        outRect.set(offset, offset, offset, offset);
    }

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDrawOver(c, parent, state);
        final RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();

        // Loop every child and draw on top
        for (int i = 0; i < parent.getChildCount(); i++) {
            final View child = parent.getChildAt(i);
            c.drawRect(layoutManager.getDecoratedLeft(child) + (offset * 2),
                    layoutManager.getDecoratedTop(child) + (offset * 2),
                    layoutManager.getDecoratedRight(child) - (offset * 2),
                    layoutManager.getDecoratedBottom(child) - (offset * 2),
                    paint1);
        }
    }
}
