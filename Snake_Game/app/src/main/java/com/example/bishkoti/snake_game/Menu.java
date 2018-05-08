package com.example.bishkoti.snake_game;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Menu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
    }

    public void closeButton(View view) {
        finish();
    }

    public void startMain(View view) {
        startActivity(new Intent(Menu.this, MainActivity.class));
    }

    public void readAbout(View view) {
        startActivity(new Intent(Menu.this, About.class));
    }
}
