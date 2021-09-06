package com.example.tp2_grupo7;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.Switch;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class DatosExtra extends AppCompatActivity {

    private String nombre,apellido,telefono,email,direccion,fechaNac;
    private RadioButton rbPrimIncomp,rbPrimCompleto,rbSecunIncom,rbSecunComp,rbOtros;
    private CheckBox chkDeporte,chkMusica,chkArte,chkTecnologia;
    private Switch DeseoInfo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos_extra);

        nombre=getIntent().getStringExtra("nombre");
        apellido=getIntent().getStringExtra("apellido");
        telefono=getIntent().getStringExtra("telefono");
        email=getIntent().getStringExtra("email");
        direccion=getIntent().getStringExtra("direccion");
        fechaNac=getIntent().getStringExtra("fechaNac");
        //matcheo el front con el back
        rbPrimIncomp=(RadioButton) findViewById(R.id.RbPrimarioIncompleto);
        rbPrimCompleto=(RadioButton) findViewById(R.id.RbPrimarioCompleto);
        rbSecunComp=(RadioButton) findViewById(R.id.RbSecundarioCompleto);
        rbSecunIncom=(RadioButton) findViewById(R.id.RbSecundarioIncompleto);
        rbOtros=(RadioButton) findViewById(R.id.RbOtros);
        chkDeporte=(CheckBox) findViewById(R.id.chkDeporte);
        chkMusica=(CheckBox) findViewById(R.id.chkMusica);
        chkArte=(CheckBox) findViewById(R.id.chkArte);
        chkTecnologia=(CheckBox) findViewById(R.id.chkTecnologia);
        DeseoInfo=(Switch) findViewById(R.id.switch1);
    }

    public void Guardar(View vw){
        String rbCheck="";
        String check="";
        String DeseaRecibirInfo="No";

        if(rbPrimIncomp.isChecked())rbCheck="Primario incompleto";
        if(rbPrimCompleto.isChecked())rbCheck="Primario completo";
        if(rbSecunIncom.isChecked())rbCheck="Secundario incompleto";
        if(rbSecunComp.isChecked())rbCheck="Primario completo";
        if(rbOtros.isChecked())rbCheck="Otros";
        if(chkTecnologia.isChecked())check=check+" Tecnologia";
        if(chkArte.isChecked())check=check+" Arte";
        if(chkMusica.isChecked())check=check+" Musica";
        if(chkDeporte.isChecked())check=check+" Deporte";
        if(DeseoInfo.isChecked())DeseaRecibirInfo="Si";
        try {
            //Guardo el contacto
            OutputStreamWriter archivoContact= new OutputStreamWriter(openFileOutput(nombre+apellido+" - "+email+".txt", Activity.MODE_PRIVATE));
            archivoContact.write("Nombre:"+nombre+"\n"+"Apellido:"+apellido+"\n"+"Telefono:"+telefono+"\n"+"Email:"+email+"\n"+"Direccion:"+direccion+"\n"+"Fecha de nacimiento:"+fechaNac+"Nivel de estudio alcanzado:"+rbCheck+"\n"+"Intereses:"+check+"\n"+"Deseo recibir info:"+DeseaRecibirInfo);
            archivoContact.flush();
            archivoContact.close();
        }catch(IOException ex1){
            ex1.printStackTrace();
        }
        try {
            //Guardo en el archivo indice el archivo nuevp
            OutputStreamWriter archivo= new OutputStreamWriter(openFileOutput("Indice.txt", Activity.MODE_PRIVATE));
            archivo.write(nombre+apellido+" - "+email+".txt");
            archivo.flush();
            archivo.close();
        }catch(IOException ex){
            ex.printStackTrace();
        }
        Toast.makeText(this,"guardado",Toast.LENGTH_LONG).show();
        finish();
    }
    /*
    public  void leer(){
     if(ArchivoExiste(archivos,"bitacora.txt")){
        try {
            InputStreamReader archivo=new InputStreamReader(openFileInput("bitacora.txt"));
            BufferedReader Buff=new BufferedReader(archivo);
            String linea=Buff.readLine();
            String textoCompleto="";

            while(linea!=null){
                textoCompleto=textoCompleto+ linea+"\n";
                linea= Buff.readLine();
            }
            Buff.close();
            archivo.close();
            et.setText(textoCompleto);
        }catch(IOException ex){

        }

    }
    }

        private boolean ArchivoExiste(String[] archivos, String nombreArchivo) {
        for(int i=0;i< archivos.length;i++)
            if(nombreArchivo.equals(archivos[i]))
                return true;
        return false;
    }
    */




}