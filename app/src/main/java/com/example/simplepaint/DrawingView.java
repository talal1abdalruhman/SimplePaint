package com.example.simplepaint;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Pair;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import java.util.ArrayList;
import java.util.Vector;

import static com.example.simplepaint.MainActivity.current_shape;
import static com.example.simplepaint.MainActivity.paint;
import static com.example.simplepaint.MainActivity.txt;

public class DrawingView extends View {
    Vector<Pair<Integer, Vector<Object>>> shapes = new Vector<>();
    ArrayList<Pair<Path, Integer>> paths = new ArrayList<>();
    Path mPath;
    float mX, mY;
    Point begin = new Point();
    Point end = new Point();

    public DrawingView(Context context) {
        super(context);
    }

    public DrawingView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public DrawingView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (shapes.size() > 0) {
            for (Pair<Integer, Vector<Object>> pair : shapes) {
                switch (pair.first) {
                    case 2: {
                        Paint p = new Paint();
                        p.setColor((Integer) pair.second.get(4));
                        canvas.drawOval((int) pair.second.get(0), (int) pair.second.get(1), (int) pair.second.get(2), (int) pair.second.get(3), p);
                        break;
                    }
                    case 3: {
                        Paint p = new Paint();
                        p.setColor((Integer) pair.second.get(4));
                        canvas.drawRect((int) pair.second.get(0), (int) pair.second.get(1), (int) pair.second.get(2), (int) pair.second.get(3), p);
                        break;
                    }
                    case 4:{
                        Paint p = new Paint();
                        p.setColor((Integer) pair.second.get(3));
                        p.setTextSize(60);
                        canvas.drawText(pair.second.get(0).toString(), (int)pair.second.get(1), (int)pair.second.get(2), p);
                    }
                }
            }
            switch (current_shape) {
                case 2: {
                    canvas.drawOval(begin.x, begin.y, end.x, end.y, paint);
                    break;
                }
                case 3: {
                    canvas.drawRect(begin.x, begin.y, end.x, end.y, paint);
                }
            }
        }

        if (paths.size() > 0) {
            for (Pair<Path, Integer> pair : paths) {
                Paint p = new Paint();
                p.setColor(pair.second);
                p.setAntiAlias(true);
                p.setStyle(Paint.Style.STROKE);
                p.setStrokeCap(Paint.Cap.ROUND);
                p.setStrokeJoin(Paint.Join.ROUND);
                p.setStrokeWidth(10f);
                canvas.drawPath(pair.first, p);
            }
        }

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (current_shape) {
            case 1: {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        touchStart(event.getX(), event.getY());
                        invalidate();
                        return true;
                    }
                    case MotionEvent.ACTION_MOVE: {
                        touchMove(event.getX(), event.getY());
                        invalidate();
                        return true;
                    }
                    case MotionEvent.ACTION_UP: {
                        touchUp();
                        invalidate();
                        return true;
                    }
                    default: {
                        return false;
                    }
                }
            }
            case 2: {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        begin.x = (int) event.getX();
                        begin.y = (int) event.getY();
                        return true;
                    }
                    case MotionEvent.ACTION_MOVE: {
                        end.x = (int) event.getX();
                        end.y = (int) event.getY();
                        invalidate();
                        return true;
                    }
                    case MotionEvent.ACTION_UP: {
                        end.x = (int) event.getX();
                        end.y = (int) event.getY();
                        Vector<Object> points = new Vector<>();
                        points.add(begin.x);
                        points.add(begin.y);
                        points.add(end.x);
                        points.add(end.y);
                        points.add(paint.getColor());
                        shapes.add(new Pair<>(2, points));
                        invalidate();
                        return true;
                    }
                    default: {
                        return false;
                    }
                }
            }
            case 3: {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        begin.x = (int) event.getX();
                        begin.y = (int) event.getY();
                        return true;
                    }
                    case MotionEvent.ACTION_MOVE: {
                        end.x = (int) event.getX();
                        end.y = (int) event.getY();
                        invalidate();
                        return true;
                    }
                    case MotionEvent.ACTION_UP: {
                        end.x = (int) event.getX();
                        end.y = (int) event.getY();
                        Vector<Object> points = new Vector<>();
                        points.add(begin.x);
                        points.add(begin.y);
                        points.add(end.x);
                        points.add(end.y);
                        points.add(paint.getColor());
                        shapes.add(new Pair<>(3, points));
                        invalidate();
                        return true;
                    }
                    default: {
                        return false;
                    }
                }
            }
            case 4:{
                begin.x = (int)event.getX();
                begin.y = (int)event.getY();
                Vector<Object> points = new Vector<>();
                points.add(txt);
                points.add(begin.x);
                points.add(begin.y);
                points.add(paint.getColor());
                shapes.add(new Pair<>(4, points));
                txt = "";
                invalidate();
            }
            default:
                return false;
        }
    }

    private void touchStart(float x, float y) {
        mPath = new Path();
        mPath.reset();
        mPath.moveTo(x, y);
        mX = x;
        mY = y;
    }

    private void touchMove(float x, float y) {
        mPath.quadTo(mX, mY, x, y);
        mX = x;
        mY = y;
        paths.add(new Pair<>(mPath, paint.getColor()));
    }

    private void touchUp() {
        mPath.lineTo(mX, mY);
        paths.add(new Pair<>(mPath, paint.getColor()));
    }

    public Bitmap getBitmap() {
        this.setDrawingCacheEnabled(true);
        this.buildDrawingCache();
        Bitmap bmp = Bitmap.createBitmap(this.getDrawingCache());
        this.setDrawingCacheEnabled(false);
        return bmp;
    }

    public void Clear() {
        shapes.clear();
        paths.clear();
        invalidate();
    }
}
