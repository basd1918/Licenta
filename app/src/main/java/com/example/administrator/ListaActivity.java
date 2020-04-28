package com.example.administrator;

import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import database.DatabaseHelper;

public class ListaActivity extends AppCompatActivity {
    DatabaseHelper myDb;
    Button button_lista;
    TextView textViewLista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);
        myDb= new DatabaseHelper(this);
        textViewLista=(TextView)findViewById(R.id.textView_listaAp);

        Intent intent=getIntent();
        String message=intent.getStringExtra("Lista_apartamentelor");
        textViewLista.setText(message);

    }
}
