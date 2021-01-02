package com.example.simplepaint;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;


public class MainActivity extends AppCompatActivity {

    public static Paint paint = new Paint();
    public static int current_shape = 1;
    public static String txt;
    ImageButton pencil, circle, rectangle, text, red, green, blue, yellow, cyan, purple;
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
        text = findViewById(R.id.text);
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
                text.setBackground(getDrawable(R.drawable.color_bg));
                current_shape = 3;
                break;
            }
            case R.id.circle: {
                pencil.setBackground(getDrawable(R.drawable.color_bg));
                circle.setBackground(getDrawable(R.drawable.shape_selected));
                rectangle.setBackground(getDrawable(R.drawable.color_bg));
                text.setBackground(getDrawable(R.drawable.color_bg));
                current_shape = 2;
                break;
            }
            case R.id.text: {
                pencil.setBackground(getDrawable(R.drawable.color_bg));
                circle.setBackground(getDrawable(R.drawable.color_bg));
                rectangle.setBackground(getDrawable(R.drawable.color_bg));
                text.setBackground(getDrawable(R.drawable.shape_selected));
                current_shape = 4;
                showDialog();
                break;
            }
            default: {
                pencil.setBackground(getDrawable(R.drawable.shape_selected));
                circle.setBackground(getDrawable(R.drawable.color_bg));
                rectangle.setBackground(getDrawable(R.drawable.color_bg));
                text.setBackground(getDrawable(R.drawable.color_bg));
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

    public void Save(View view) {

        File folder = new File(Environment.getExternalStorageDirectory().toString());
        boolean success = false;
        if (!folder.exists()) {
            success = folder.mkdirs();
        }

        System.out.println(success + "folder");

        File file = new File(this.getFilesDir() + "/sample" + new Date().getTime() + ".png");
        Log.d("save", file.getPath());
        if (!file.exists()) {
            try {
                success = file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        FileOutputStream ostream = null;
        try {
            ostream = new FileOutputStream(file);

            View targetView = canvas;

            Bitmap well = canvas.getBitmap();
            Bitmap save = Bitmap.createBitmap(320, 480, Bitmap.Config.ARGB_8888);
            Paint paint = new Paint();
            paint.setColor(Color.WHITE);
            Canvas now = new Canvas(save);
            now.drawRect(new Rect(0, 0, 320, 480), paint);
            now.drawBitmap(well, new Rect(0, 0, well.getWidth(), well.getHeight()), new Rect(0, 0, 320, 480), null);

            if (save == null) {
                System.out.println("NULL bitmap save\n");
            }
            save.compress(Bitmap.CompressFormat.PNG, 100, ostream);

        } catch (NullPointerException e) {
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), "Null error", Toast.LENGTH_SHORT).show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            Log.d("save", e.getMessage());
            Toast.makeText(getApplicationContext(), "File error", Toast.LENGTH_SHORT).show();
        }

    }

    public void showDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Add Text");
        final View customLayout = getLayoutInflater().inflate(R.layout.text_dialog, null);
        builder.setView(customLayout);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                EditText editText = customLayout.findViewById(R.id.editText);
                txt = editText.getText().toString();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
