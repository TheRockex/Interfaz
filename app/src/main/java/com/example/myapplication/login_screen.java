package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class login_screen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);

        ImageView backgroundImageView = findViewById(R.id.backgroundImageView);

        // URL de la imagen que deseas usar como fondo
        String imageUrl = "https://www.nationalgeographic.com.es/medio/2021/07/14/el-meteorito-que-creo-el-crater-de-chicxulub-media-aproximadamente-11-kilometros_4abdc471_800x800.jpg";

        // Cargar la imagen con Glide
        Glide.with(this)
                .load(imageUrl)
                .into(backgroundImageView);
    }

    public void ChangeToSesion(View view){
        Intent intent = new Intent(login_screen.this,MainActivity.class);
        startActivity(intent);
    }
}