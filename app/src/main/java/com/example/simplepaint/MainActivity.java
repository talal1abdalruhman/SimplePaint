package com.example.simplepaint;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;


public class MainActivity extends AppCompatActivity {
    
    public static Paint paint = new Paint();
    public static int current_shape = 1;
    ImageButton pencil, circle, rectangle, red, green, blue, yellow, cyan, purple;
    DrawingView canvas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        InitializeFields();
    }

    public void InitializeFields() {
        pencil = findViewById(R.id.pencil);
        circle = findViewById(R.id.circle);
        rectangle = findViewById(R.id.rectangle);
        red = findViewById(R.id.red);
        green = findViewById(R.id.green);
        blue = findViewById(R.id.blue);
        yellow = findViewById(R.id.yellow);
        cyan = findViewById(R.id.cyan);
        purple = findViewById(R.id.purple);
        canvas = findViewById(R.id.canvas);

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void Pencil(View view) {
        switch (view.getId()) {
            case R.id.rectangle: {
                pencil.setBackground(getDrawable(R.drawable.color_bg));
                circle.setBackground(getDrawable(R.drawable.color_bg));
                rectangle.setBackground(getDrawable(R.drawable.shape_selected));
                current_shape = 3;
                break;
            }
            case R.id.circle: {
                pencil.setBackground(getDrawable(R.drawable.color_bg));
                circle.setBackground(getDrawable(R.drawable.shape_selected));
                rectangle.setBackground(getDrawable(R.drawable.color_bg));
                current_shape = 2;
                break;
            }
            default: {
                pencil.setBackground(getDrawable(R.drawable.shape_selected));
                circle.setBackground(getDrawable(R.drawable.color_bg));
                rectangle.setBackground(getDrawable(R.drawable.color_bg));
                current_shape = 1;
            }
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void Red(View view) {
        SelectColor(view);
        paint.setARGB(255, 255, 0, 0);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void Green(View view) {
        SelectColor(view);
        paint.setARGB(255, 0, 255, 0);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void Blue(View view) {
        SelectColor(view);
        paint.setARGB(255, 0, 0, 255);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void Yellow(View view) {
        SelectColor(view);
        paint.setARGB(255, 255, 255, 0);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void Cyan(View view) {
        SelectColor(view);
        paint.setARGB(255, 0, 255, 255);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void Purple(View view) {
        SelectColor(view);
        paint.setARGB(255, 255, 0, 255);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void SelectColor(View view) {
        switch (view.getId()) {
            case R.id.red: {
                red.setBackground(getDrawable(R.drawable.selected_color_red));
                green.setBackground(getDrawable(R.drawable.color_green));
                blue.setBackground(getDrawable(R.drawable.color_blue));
                yellow.setBackground(getDrawable(R.drawable.color_yellow));
                cyan.setBackground(getDrawable(R.drawable.color_cyan));
                purple.setBackground(getDrawable(R.drawable.color_purple));
                break;
            }
            case R.id.green: {
                red.setBackground(getDrawable(R.drawable.color_red));
                green.setBackground(getDrawable(R.drawable.selected_color_green));
                blue.setBackground(getDrawable(R.drawable.color_blue));
                yellow.setBackground(getDrawable(R.drawable.color_yellow));
                cyan.setBackground(getDrawable(R.drawable.color_cyan));
                purple.setBackground(getDrawable(R.drawable.color_purple));
                break;
            }
            case R.id.blue: {
                red.setBackground(getDrawable(R.drawable.color_red));
                green.setBackground(getDrawable(R.drawable.color_green));
                blue.setBackground(getDrawable(R.drawable.selected_color_blue));
                yellow.setBackground(getDrawable(R.drawable.color_yellow));
                cyan.setBackground(getDrawable(R.drawable.color_cyan));
                purple.setBackground(getDrawable(R.drawable.color_purple));
                break;
            }
            case R.id.yellow: {
                red.setBackground(getDrawable(R.drawable.color_red));
                green.setBackground(getDrawable(R.drawable.color_green));
                blue.setBackground(getDrawable(R.drawable.color_blue));
                yellow.setBackground(getDrawable(R.drawable.selected_color_yellow));
                cyan.setBackground(getDrawable(R.drawable.color_cyan));
                purple.setBackground(getDrawable(R.drawable.color_purple));
                break;
            }
            case R.id.cyan: {
                red.setBackground(getDrawable(R.drawable.color_red));
                green.setBackground(getDrawable(R.drawable.color_green));
                blue.setBackground(getDrawable(R.drawable.color_blue));
                yellow.setBackground(getDrawable(R.drawable.color_yellow));
                cyan.setBackground(getDrawable(R.drawable.selected_color_cyan));
                purple.setBackground(getDrawable(R.drawable.color_purple));
                break;
            }
            case R.id.purple: {
                red.setBackground(getDrawable(R.drawable.color_red));
                green.setBackground(getDrawable(R.drawable.color_green));
                blue.setBackground(getDrawable(R.drawable.color_blue));
                yellow.setBackground(getDrawable(R.drawable.color_yellow));
                cyan.setBackground(getDrawable(R.drawable.color_cyan));
                purple.setBackground(getDrawable(R.drawable.selected_color_purple));
                break;
            }
        }
    }

    public void Erase(View view) {
        canvas.Clear();
    }
}