package com.marchben.spiral;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private CanvasView spiralCanvasView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spiralCanvasView = (CanvasView) findViewById(R.id.spiral_canvas);
    }

    public void clearCanvas(View v){
        spiralCanvasView.clearCanvas();
    }
}
