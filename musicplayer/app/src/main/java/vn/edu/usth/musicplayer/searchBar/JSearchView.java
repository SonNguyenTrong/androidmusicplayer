package vn.edu.usth.musicplayer.searchBar;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

import vn.edu.usth.musicplayer.searchBar.Controller;
import vn.edu.usth.musicplayer.searchBar.IconToSimpleLine;

public class JSearchView extends View {
    private Paint mPaint;
    private Path mPath;
    private Controller mController = new IconToSimpleLine();

    public JSearchView(Context context) {
        this(context, null);
    }

    public JSearchView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public JSearchView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStrokeWidth(4);

        mPath = new Path();
    }

    public void setController(Controller controller) {
        this.mController = controller;
        mController.setSearchView(this);
        invalidate();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mController.draw(canvas, mPaint);
    }

    public void startAnim() {
        if (mController != null)
            mController.startAnim();

    }

    public void resetAnim() {
        if (mController != null)
            mController.resetAnim();
    }

}
