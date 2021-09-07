package com.example.tp2_grupo7;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class ListadoContactos extends AppCompatActivity {

    private ListView lvContactos;

    private String [] contactos  = {};
    private ArrayList<String> listaContactos = new ArrayList<String>();
    private String [] archivos  = {};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado_contactos);

        lvContactos = (ListView) findViewById(R.id.lvContactos);
        archivos = fileList();

        Toast.makeText(this,archivos[0],Toast.LENGTH_LONG).show();
        //Hay Que cargar el arreglo contactos antes de inflar el listView
        leer();

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.list_item_contacto, contactos);
        lvContactos.setAdapter(adapter);

        lvContactos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //Una vez se selecciona un item del listView, se ejecuta este metodo
                detalleDeContacto(i);
            }
        });
    }

    //Metodo para el menu que aparezca arriba en el header de la app
    @Override public boolean onCreateOptionsMenu (Menu mimenu){
        getMenuInflater().inflate(R.menu.menu_contactos, mimenu);
        return true;
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
            Intent in = new Intent(this, ListadoContactos.class);
            startActivity(in);
            return true;
        }

        return super.onOptionsItemSelected(opcion_menu);
    }

    public void detalleDeContacto(int posicion){
        String contactoSeleccionado = contactos[posicion];

        Intent i = new Intent(this,DetalleContacto.class);
        i.putExtra("contacto",contactoSeleccionado);
        startActivity(i);
    }

    public  void leer(){
        if(ArchivoExiste(archivos,"indice.txt")){
            
            try {
                //Nose porque pero no pasa de esta linea, tira un error en: E/memtrack: Couldn't load memtrack module
                InputStreamReader indice = new InputStreamReader(openFileInput("indice.txt"));
                BufferedReader Buff = new BufferedReader(indice);
                String linea=Buff.readLine();
                int contador = 0;

                while(linea!=null){
                    //Aca cargo el vector de strings
                    contactos[contador] = linea;
                    contador++;
                    //Aca cargo el arraylist
                    listaContactos.add(linea);
                    Toast.makeText(this,linea,Toast.LENGTH_LONG).show();
                    linea = Buff.readLine();
                }

                Buff.close();
                indice.close();

            }catch(IOException ex){
                ex.printStackTrace();
            }
        }
    }

    private boolean ArchivoExiste(String[] archivos, String nombreArchivo) {
        for(int i=0;i< archivos.length;i++)
            if(nombreArchivo.equals(archivos[i]))
                return true;
        return false;
    }


}