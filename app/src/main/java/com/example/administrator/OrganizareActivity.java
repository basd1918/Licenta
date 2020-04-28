package com.example.administrator;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import database.DatabaseHelper;

public class OrganizareActivity extends AppCompatActivity {

    private Button button_adauga;
    private Button button_modifica;
    private Button button_sterge;
    private Button button_lista;
    DatabaseHelper myDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_organizare);
        myDb= new DatabaseHelper(this);

        button_adauga=(Button)findViewById(R.id.button_add);
        button_adauga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openAdaugaa();
            }
        });

        button_modifica=(Button)findViewById(R.id.button_modifica);
        button_modifica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view2) {
                openModifica();
            }
        });

        button_sterge=(Button)findViewById(R.id.button_sterge);
        button_sterge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view3) {
                openSterge();
            }
        });

        button_lista=(Button)findViewById(R.id.button_lista);
        button_lista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view4) {
                openLista();
            }
        });


    }

    public void openAdaugaa() {
        Intent intentAdauga = new Intent(this, AddActivity.class);
        startActivity(intentAdauga);
    }

    public void openModifica(){
        Intent intentModifica = new Intent(this,ModificaActivity.class);
        startActivity(intentModifica);
    }

    public void openSterge(){
        Intent intentSterge = new Intent(this,StergeActivity.class);
        startActivity(intentSterge);

    }

    public void openLista(){
        Intent intentLista = new Intent(this,ListaApartamenteActivity.class);
        startActivity(intentLista);
    }

 /*   public void openLista(){
            button_lista.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Cursor res= myDb.getAllData();
                    if( res.getCount()==0){
                        showMessage("ERROR","Baza de date este goala");
                        return;
                    }
                    StringBuffer buffer= new StringBuffer();
                    while(res.moveToNext()){
                        buffer.append("Numar apartament: "+ res.getString(0)+"\n");
                        buffer.append("Nume proprietar: "+ res.getString(1)+"\n");
                        buffer.append("Email: "+ res.getString(2)+"\n");
                        buffer.append("Suprafata: "+ res.getString(3)+"\n");
                        buffer.append("Numar persoane: "+ res.getString(4)+"\n\n");
                    }
                    showMessage("Lista apartamentelor",buffer.toString());
                }
            });
        }*/

    public void showMessage(String title,String message){
        AlertDialog.Builder builder =new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }

}
