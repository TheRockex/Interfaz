package com.example.myapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView textView = findViewById(R.id.olvidar);

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAlertDialog();
            }
        });

        ImageView backgroundImageView = findViewById(R.id.backgroundImageView);

        // URL de la imagen que deseas usar como fondo
        String imageUrl = "https://www.nationalgeographic.com.es/medio/2021/07/14/el-meteorito-que-creo-el-crater-de-chicxulub-media-aproximadamente-11-kilometros_4abdc471_800x800.jpg";

        // Cargar la imagen con Glide
        Glide.with(this)
                .load(imageUrl)
                .into(backgroundImageView);

    }




    private void showAlertDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.AlertDialogCustom);
        builder.setTitle("INGRESE CORREO ELECTRÓNICO");

        // Inflar el diseño personalizado que contiene el EditText
        View viewInflated = LayoutInflater.from(this).inflate(R.layout.edit_text_layout, null);
        final EditText inputEditText = viewInflated.findViewById(R.id.editText);

        // Aplicar el estilo personalizado al texto
        inputEditText.setTextAppearance(this, R.style.AlertDialogCustomText);

        builder.setView(viewInflated);

        // Configurar el botón "OK"
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Obtener el texto ingresado en el EditText
                String userInput = inputEditText.getText().toString();
                // Aquí puedes manejar el texto ingresado
            }
        });

        // Configurar el botón "Cancelar"
        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Aquí puedes manejar el clic en el botón Cancelar, si es necesario
            }
        });

        // Crear y mostrar el diálogo
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void ChangeToLogin(View view){
        Intent intent = new Intent(MainActivity.this,login_screen.class);
        startActivity(intent);
    }

}
