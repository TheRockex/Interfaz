package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.Spatializer;
import android.os.Bundle;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class Splash_screen extends AppCompatActivity {

    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        ImageView backgroundImageView = findViewById(R.id.backgroundImageView2);

        // URL de la imagen que deseas usar como fondo
        String imageUrl = "https://www.nationalgeographic.com.es/medio/2021/07/14/el-meteorito-que-creo-el-crater-de-chicxulub-media-aproximadamente-11-kilometros_4abdc471_800x800.jpg";

        // Cargar la imagen con Glide
        Glide.with(this)
                .load(imageUrl)
                .into(backgroundImageView);

        final ImageView logoImageView = findViewById(R.id.logo2);
        final ImageView garrasImageView = findViewById(R.id.garras);

        // Configura la rotación de la imagen
        ObjectAnimator rotation = ObjectAnimator.ofFloat(logoImageView, "rotationY", 0.0f, -30.0f);
        rotation.setDuration(1000);
        rotation.setInterpolator(new LinearInterpolator());
        rotation.setRepeatCount(1);
        rotation.setRepeatMode(ValueAnimator.REVERSE);

        // Reproduce el sonido al finalizar la rotación del logo
        final MediaPlayer rugidoMediaPlayer = MediaPlayer.create(this, R.raw.rugido);

        // Configura la animación de desplazamiento de las garras
        ObjectAnimator translationX = ObjectAnimator.ofFloat(garrasImageView, "translationX", 0f, 1000f); // Ajusta los valores según sea necesario
        ObjectAnimator translationY = ObjectAnimator.ofFloat(garrasImageView, "translationY", 0f, 1000f); // Ajusta los valores según sea necesario
        translationX.setDuration(800); // Duración de la animación de desplazamiento
        translationY.setDuration(800); // Duración de la animación de desplazamiento

        // Configura la visibilidad de las garras
        garrasImageView.setVisibility(View.INVISIBLE);

        // Inicia la animación de rotación y rugido al mismo tiempo
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(rotation);

        // Agrega un listener para saber cuándo la animación ha finalizado
        animatorSet.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                rugidoMediaPlayer.start();
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                rugidoMediaPlayer.release();

                // Configura la visibilidad de las garras y luego inicia la animación de desplazamiento de las garras
                garrasImageView.setVisibility(View.VISIBLE);
                AnimatorSet garrasAnimatorSet = new AnimatorSet();
                garrasAnimatorSet.playTogether(translationX, translationY);

                // Agrega un listener para saber cuándo la animación de las garras ha finalizado
                garrasAnimatorSet.addListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {
                        // Reproduce el sonido cuando aparecen las garras
                        MediaPlayer garrasMediaPlayer = MediaPlayer.create(Splash_screen.this, R.raw.sonidogarras);
                        garrasMediaPlayer.start();
                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        // Puedes realizar otras acciones aquí si es necesario
                        Intent intent = new Intent(Splash_screen.this, MainActivity.class);
                        startActivity(intent);
                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {
                        // No se utiliza en este ejemplo
                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {
                        // No se utiliza en este ejemplo
                    }
                });

                // Inicia la animación de las garras
                garrasAnimatorSet.start();
            }

            @Override
            public void onAnimationCancel(Animator animation) {
                // No se utiliza en este ejemplo
            }

            @Override
            public void onAnimationRepeat(Animator animation) {
                // No se utiliza en este ejemplo
            }
        });

        // Inicia la animación de rotación
        animatorSet.start();
    }


}

