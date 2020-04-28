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

public class MainActivity extends AppCompatActivity {
    DatabaseHelper myDb;
    private Button button_organizare;
    private Button button_veziLista;
    private Button button_intretinere;
    private Button button_achita;
    private  Button button_grafic;
    private  Button button_TabelCheltuieli;
    private  Button button_TrimiteEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myDb= new DatabaseHelper(this);


        button_organizare=(Button) findViewById(R.id.button_organizare);
        button_organizare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openOrganizare();
            }
        });

        button_intretinere=(Button) findViewById(R.id.button_calculeaza);
        button_intretinere.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openIntretinere();
            }
        });

        button_achita=(Button) findViewById(R.id.button_achita);
        button_achita.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAchita();
            }
        });

        button_veziLista=(Button)findViewById(R.id.button_veziLista);
        button_veziLista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openListaAdapter();
            }
        });

        button_grafic=(Button)findViewById(R.id.button_grafic);
        button_grafic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGrafice();
            }
        });

        button_TabelCheltuieli=(Button)findViewById(R.id.button_TabelCheltuieli);
        button_TabelCheltuieli.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openTabelCheltuieli();
            }
        });

        button_TrimiteEmail=(Button)findViewById(R.id.button_TrimiteEmail);
        button_TrimiteEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openTrimiteMail();
            }
        });
    }
    public void openOrganizare(){
        Intent intent = new Intent(this,OrganizareActivity.class);
        startActivity(intent);
    }

    public void openAchita(){
        Intent intent = new Intent(this,AchitaIntretinereActivity.class);
        startActivity(intent);
    }

    public void openIntretinere(){
        Intent intent = new Intent(this,AddIntretinere.class);
        startActivity(intent);
    }

    public void sendMessage(View v){
                Cursor res= myDb.getAllData();
                if( res.getCount()==0){
                    showMessage("ERROR","Nu s-a gasit niciun apartament");
                    return;
                }
                StringBuffer buffer= new StringBuffer();
                while(res.moveToNext()){
                    buffer.append("  Numar apartament: "+ res.getString(0)+"\n");
                    buffer.append("  Nume proprietar: "+ res.getString(1)+"\n");
                    buffer.append("  Email: "+ res.getString(2)+"\n");
                    buffer.append("  Suprafata: "+ res.getString(3)+"\n");
                    buffer.append("  Numar persoane: "+ res.getString(4)+"\n\n");
                }
                Intent intent=new Intent(this,ListaActivity.class);
                intent.putExtra("Lista_apartamentelor",buffer.toString());
                startActivity(intent);
    }

    public void showMessage(String title,String message){
        AlertDialog.Builder builder =new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }

    public void openGrafice(){
        Intent intent = new Intent(this,GraficActivity.class);
        startActivity(intent);
    }
    public void openListaAdapter(){
        Intent intent = new Intent(this,ListaApartamenteActivity.class);
        startActivity(intent);
    }

    public void openTabelCheltuieli(){
        Intent intent = new Intent(this,TabelCheltuieliActivity.class);
        startActivity(intent);
    }

    public void openTrimiteMail(){
        Intent intent = new Intent(this,TrimiteMailActivity.class);
        startActivity(intent);
    }
}
