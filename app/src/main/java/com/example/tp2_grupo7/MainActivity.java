package com.example.tp2_grupo7;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText et_nombres, et_apellidos, et_telefono, et_email, et_direccion,fechaNac;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //nombre y casteo de variables de la parte grafica a codigo
        et_nombres = (EditText) findViewById(R.id.txt_nombres);
        et_apellidos = (EditText) findViewById(R.id.txt_apellidos);
        et_telefono = (EditText) findViewById(R.id.txt_telefono);
        et_email = (EditText) findViewById(R.id.txt_email);
        et_direccion = (EditText) findViewById(R.id.txt_direccion);
        fechaNac = (EditText) findViewById(R.id.date_fechanac);
    }


    //Metodo para el menu que aparezca arriba en el header de la app
    @Override public boolean onCreateOptionsMenu (Menu mimenu){
        getMenuInflater().inflate(R.menu.menu_contactos, mimenu);
        return true;
    }


    /*
    //Metodo boton guardar
    public void Guardar(View view){
        String nombre = et_nombres.getText.toString();
        String apellido = et_apellidos.getText.toString();
        String telefono = et_telefono.getText.toString();
        String email = et_email.getText.toString();
        String direccion = et_direccion.getText.toString();

        SharedPreferences preferencias = getSharedPreferences("agenda", Context.MODE_PRIVATE);
        SharedPreferences.Editor obj_editor = preferencias.edit();
        obj_editor.putString(nombre, apellido);
    }
    */


    //metodo para agregar contactos a la agenda

    public void agregarContactos(View vw){
    Intent i = new Intent(this,DatosExtra.class);
    i.putExtra("nombre",et_nombres.getText().toString());
    i.putExtra("apellido",et_apellidos.getText().toString());
    i.putExtra("telefono",et_telefono.getText().toString());
    i.putExtra("email",et_email.getText().toString());
    i.putExtra("direccion",et_direccion.getText().toString());
    i.putExtra("fechaNac",fechaNac.getText().toString());
    startActivity(i);
    }


    //Metodo para poder seleccionar y direccionar a los diferentes items del menu
    @Override public boolean onOptionsItemSelected (MenuItem opcion_menu){

        int id=opcion_menu.getItemId();

        if (id==R.id.agregar_contactos){
            Intent i = new Intent(this,MainActivity.class);
            startActivity(i);
            return true;
        }

        if (id==R.id.listado_contactos){
            return true;
        }

        return super.onOptionsItemSelected(opcion_menu);
    }
}