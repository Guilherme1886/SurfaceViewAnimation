package com.gui.toledo.surfaceviewanimation;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new AnimatedSurfaceView(this));

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
    }
}
