package com.example.administrator;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import database.DatabaseHelper;
public class StergeActivity extends AppCompatActivity {

    DatabaseHelper myDb;
    EditText editText_numarAp3,editText_numeProprietar3;
    Button buttonDelete3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sterge);
        myDb= new DatabaseHelper(this);

        editText_numarAp3=(EditText)findViewById(R.id.editText_numarAp3);
        editText_numeProprietar3=(EditText)findViewById(R.id.editText_numeProprietar3);
        buttonDelete3=(Button)findViewById(R.id.buttonDelete3);
        DeleteData();
    }

    public void DeleteData(){
        buttonDelete3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer isDeleted= myDb.deleteData(editText_numarAp3.getText().toString(),editText_numeProprietar3.getText().toString());
                if(isDeleted > 0)
                {
                    Toast.makeText(StergeActivity.this, "Apartament sters", Toast.LENGTH_LONG).show();
                    finish();
                }
                else
                    Toast.makeText(StergeActivity.this, "Apartamentul nu a putut fi sters...", Toast.LENGTH_LONG).show();


            }
        });
    }
}
