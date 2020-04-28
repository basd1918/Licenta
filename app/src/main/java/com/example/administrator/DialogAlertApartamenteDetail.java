package com.example.administrator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import database.DatabaseHelper;

public class DialogAlertApartamenteDetail extends AppCompatActivity {

    DatabaseHelper myDb;
    private Button buttonDialog_cancel;
    private TextView textViewNrAp_dialog,textViewNumeProp_dialog,textViewEmail_dialog,textViewSuprafata_dialog,textViewNrPers_dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog_alert_apartamente_detail);
        myDb = new DatabaseHelper(this);

        Intent intent=getIntent();
        final String nrAp=intent.getStringExtra("nrAp");
        final String prop=intent.getStringExtra("proprietar");
        final String email=intent.getStringExtra("email");
        final String suprafata=intent.getStringExtra("suprafata");
        final String nrPers=intent.getStringExtra("nrPers");
        textViewNrAp_dialog=(TextView) findViewById(R.id.textViewNrAp_dialog);
        textViewNrAp_dialog.setText(nrAp);
        textViewNumeProp_dialog=(TextView) findViewById(R.id.textViewNumeProp_dialog);
        textViewNumeProp_dialog.setText(prop);
        textViewEmail_dialog=(TextView) findViewById(R.id.textViewEmail_dialog);
        textViewEmail_dialog.setText(email);
        textViewSuprafata_dialog=(TextView) findViewById(R.id.textViewSuprafata_dialog);
        textViewSuprafata_dialog.setText(suprafata);
        textViewNrPers_dialog=(TextView) findViewById(R.id.textViewNrPers_dialog);
        textViewNrPers_dialog.setText(nrPers);

        buttonDialog_cancel=(Button)findViewById(R.id.buttonDialog_cancel);
        buttonDialog_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}

