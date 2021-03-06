package com.example.administrator;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import database.DatabaseHelper;

public class ModificaActivity extends AppCompatActivity {

    DatabaseHelper myDb;
    EditText editText_numarAp,editText_numeProprietar,editText_email,editText_ocupatie,editText_numarPersoane;
    Button buttonUpdate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modifica);

        myDb= new DatabaseHelper(this);
        editText_numarAp=(EditText)findViewById(R.id.editText_numarAp2);
        editText_numeProprietar=(EditText)findViewById(R.id.editText_numeProprietar2);
        editText_email=(EditText)findViewById(R.id.editText_email2);
        editText_ocupatie=(EditText)findViewById(R.id.editText_ocupatie2);
        editText_numarPersoane=(EditText)findViewById(R.id.editText_numarPersoane2);
        buttonUpdate=(Button) findViewById(R.id.buttonUpdate);
        UpdateData();
    }

    public void UpdateData(){
        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (editText_numarAp.length() == 0) {
                    editText_numarAp.setError("Introduceti numarul apartamentului");
                } else if (editText_numeProprietar.length() == 0) {
                    editText_numeProprietar.setError("Introduceti proprietarul");
                } else if (editText_email.length() == 0) {
                    editText_email.setError("Introduceti email");
                } else if (editText_ocupatie.length() == 0) {
                    editText_ocupatie.setError("Introduceti suprafata");
                } else if (editText_numarPersoane.length() == 0) {
                    editText_numarPersoane.setError("Introduceti numarul de persoane");
                } else {
                    boolean isUpdated = myDb.updateData(editText_numarAp.getText().toString(), editText_numeProprietar.getText().toString(),
                            editText_email.getText().toString(), editText_ocupatie.getText().toString(), editText_numarPersoane.getText().toString());
                    if (isUpdated == true)
                    {
                        Toast.makeText(ModificaActivity.this, "Apartament modificat", Toast.LENGTH_LONG).show();
                        finish();
                    }
                    else
                        Toast.makeText(ModificaActivity.this, "Apartamentul nu s-a putut modifica...", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
