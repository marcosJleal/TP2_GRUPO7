package com.example.tp2_grupo7;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class DetalleContacto extends AppCompatActivity {

    private TextView tvDetalle;
    private Button btnVolver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_contacto);

        tvDetalle = (TextView) findViewById(R.id.tvDetalleContacto);
        btnVolver = (Button) findViewById(R.id.btnVolver);

        cargarDetalle();

    }

    public void cargarDetalle(){
        String nombreArchivo = getIntent().getStringExtra("contacto");
        leer(nombreArchivo);
    }


    public  void leer(String nombreArchivo){

        try {
            //Nose porque pero no pasa de esta linea, tira un error en: E/memtrack: Couldn't load memtrack module
            InputStreamReader archivo = new InputStreamReader(openFileInput(nombreArchivo));
            BufferedReader Buff = new BufferedReader(archivo);
            String linea=Buff.readLine();
            String contacto = "";

            while(linea!=null){

                contacto = contacto + linea + "\n";
                linea = Buff.readLine();
            }
            Buff.close();
            archivo.close();
            tvDetalle.setText(contacto);

        }catch(IOException ex){
            ex.printStackTrace();
        }
    }


    public void Volver(View vw){
        finish();
    }
}