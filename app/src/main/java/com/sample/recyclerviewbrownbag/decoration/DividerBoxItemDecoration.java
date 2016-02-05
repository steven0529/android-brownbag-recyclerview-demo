package com.sample.recyclerviewbrownbag.decoration;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.sample.recyclerviewbrownbag.R;

/**
 * Created by S. Reyes on 2/5/16.
 */
public class DividerBoxItemDecoration extends RecyclerView.ItemDecoration {

    private Paint paint1;

    public DividerBoxItemDecoration(Context context) {
        paint1 = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint1.setColor(context.getResources().getColor(R.color.light_brown));
        paint1.setStyle(Paint.Style.STROKE);
        paint1.setStrokeWidth(3);
    }

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDrawOver(c, parent, state);
        drawContainingBox(c, parent);
    }

    private void drawContainingBox(Canvas c, RecyclerView parent) {
        final int left = parent.getPaddingLeft();
        final int right = parent.getWidth() - parent.getPaddingRight();
        final int offset = 10;

        final int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            final View child = parent.getChildAt(i);
            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child
                    .getLayoutParams();
            c.drawRoundRect(left + offset, params.topMargin + offset, right - offset,
                    params.bottomMargin - offset, 25.0f, 25.0f, paint1 );
        }
    }

}
