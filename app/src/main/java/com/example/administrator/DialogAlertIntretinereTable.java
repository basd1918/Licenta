package com.example.administrator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import database.DatabaseHelper;

public class DialogAlertIntretinereTable extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    EditText editTextIndivizia_dialogIntretinere,editTextNrPers_dialogIntretinere;
    EditText editTextApa_dialogIntretinere,editTextCurent_dialogIntretinere,editTextTotal_dialogIntretinere;
    EditText editTextRestanta_dialogIntretinere,editTextLuna_dialogIntretinere,editTextAn_dialogIntretinere;
    EditText editTextStatus_dialogIntretinere;
    Button buttonDialog_cancel_dialogIntretinere,buttonDeleteIntretinere,buttonUpdateIntretinere;
    TextView editTextNrAp_dialogIntretinere;
    Spinner spinnerStatusDialog;
    DatabaseHelper myDb;

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text= parent.getItemAtPosition(position).toString();
       // Toast.makeText(parent.getContext(),text,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        String text="Eroare la spinner";
        Toast.makeText(parent.getContext(),text,Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog_alert_intretinere_table);
        myDb = new DatabaseHelper(this);
        spinnerStatusDialog=(Spinner)findViewById(R.id.spinnerStatusDialog);
        ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(this,R.array.status,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerStatusDialog.setAdapter(adapter);
        spinnerStatusDialog.setOnItemSelectedListener(this);
        buttonDeleteIntretinere=(Button)findViewById(R.id.buttonDeleteIntretinere);
        buttonUpdateIntretinere=(Button)findViewById(R.id.buttonUpdateIntretinere);

        Intent intent=getIntent();
        final String nrAp=intent.getStringExtra("nrAp_Intretinere");
        final String indivizia=intent.getStringExtra("indivizia_Intretinere");
        final String cotaNrPers=intent.getStringExtra("cotaPers_Intretinere");
        final String apa=intent.getStringExtra("apa_Intretinere");
        final String curent=intent.getStringExtra("curent_Intretinere");
        final String total=intent.getStringExtra("total_Intretinere");
        final String restanta=intent.getStringExtra("restanta_Intretinere");
        final String luna=intent.getStringExtra("luna_Intretinere");
        final String an=intent.getStringExtra("an_Intretinere");
        final String status=intent.getStringExtra("status_Intretinere");

        ArrayAdapter myAdapter=(ArrayAdapter)spinnerStatusDialog.getAdapter();
        int spinnerPosition=myAdapter.getPosition(status);
        spinnerStatusDialog.setSelection(spinnerPosition);


        editTextNrAp_dialogIntretinere=(TextView)findViewById(R.id.editTextNrAp_dialogIntretinere);
        editTextNrAp_dialogIntretinere.setText(nrAp);
        editTextIndivizia_dialogIntretinere=(EditText)findViewById(R.id.editTextIndivizia_dialogIntretinere);
        editTextIndivizia_dialogIntretinere.setText(indivizia);
        editTextNrPers_dialogIntretinere=(EditText)findViewById(R.id.editTextNrPers_dialogIntretinere);
        editTextNrPers_dialogIntretinere.setText(cotaNrPers);
        editTextApa_dialogIntretinere=(EditText)findViewById(R.id.editTextApa_dialogIntretinere);
        editTextApa_dialogIntretinere.setText(apa);
        editTextCurent_dialogIntretinere=(EditText)findViewById(R.id.editTextCurent_dialogIntretinere);
        editTextCurent_dialogIntretinere.setText(curent);
        editTextTotal_dialogIntretinere=(EditText)findViewById(R.id.editTextTotal_dialogIntretinere);
        editTextTotal_dialogIntretinere.setText(total);
        editTextRestanta_dialogIntretinere=(EditText)findViewById(R.id.editTextRestanta_dialogIntretinere);
        editTextRestanta_dialogIntretinere.setText(restanta);
        editTextLuna_dialogIntretinere=(EditText)findViewById(R.id.editTextLuna_dialogIntretinere);
        editTextLuna_dialogIntretinere.setText(luna);
        editTextAn_dialogIntretinere=(EditText)findViewById(R.id.editTextAn_dialogIntretinere);
        editTextAn_dialogIntretinere.setText(an);
        editTextStatus_dialogIntretinere=(EditText)findViewById(R.id.editTextStatus_dialogIntretinere);
        editTextStatus_dialogIntretinere.setText(status);


        buttonDialog_cancel_dialogIntretinere=(Button)findViewById(R.id.buttonDialog_cancel_dialogIntretinere);
        buttonDialog_cancel_dialogIntretinere.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        buttonUpdateIntretinere.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String nr=editTextNrAp_dialogIntretinere.getText().toString();
                if (editTextIndivizia_dialogIntretinere.length() == 0) {
                    editTextIndivizia_dialogIntretinere.setError("Introduceti cota indivizia");
                }
                else if (editTextNrPers_dialogIntretinere.length() == 0) {
                    editTextNrPers_dialogIntretinere.setError("Introduceti cota nr. pers");
                }
                else if (editTextApa_dialogIntretinere.length() == 0) {
                    editTextApa_dialogIntretinere.setError("Introduceți consumul de apă");
                }
                else if (editTextCurent_dialogIntretinere.length() == 0) {
                    editTextCurent_dialogIntretinere.setError("Câmp obligatoriu");
                }
                else if (editTextTotal_dialogIntretinere.length() == 0) {
                    editTextTotal_dialogIntretinere.setError("Câmp obligatoriu");
                }
                else if (editTextRestanta_dialogIntretinere.length() == 0) {
                    editTextRestanta_dialogIntretinere.setError("Câmp obligatoriu");
                }
                else {
                    String ind = editTextIndivizia_dialogIntretinere.getText().toString();
                    String pers = editTextNrPers_dialogIntretinere.getText().toString();
                    String apaa = editTextApa_dialogIntretinere.getText().toString();
                    String curentt = editTextCurent_dialogIntretinere.getText().toString();
                    String totall = editTextTotal_dialogIntretinere.getText().toString();
                    String rest = editTextRestanta_dialogIntretinere.getText().toString();
                    String lun = editTextLuna_dialogIntretinere.getText().toString();
                    String ann = editTextAn_dialogIntretinere.getText().toString();
                    String st = spinnerStatusDialog.getSelectedItem().toString();

                    boolean u = myDb.updateIntretinere(nr, ind, pers, apaa, curentt, totall, rest, lun, ann, st);
                    if (u == true) {
                        Toast.makeText(DialogAlertIntretinereTable.this, "Intretinere actualizata", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(DialogAlertIntretinereTable.this, TabelCheltuieliActivity.class);
                        startActivity(intent);

                    }
                }

            }
        });
        buttonDeleteIntretinere.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int d=myDb.deleteIntretinere(nrAp,luna,an);
                if(d>0)
                {
                    Toast.makeText(DialogAlertIntretinereTable.this,"Intretinere eliminata",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(DialogAlertIntretinereTable.this,TabelCheltuieliActivity.class);
                    startActivity(intent);
                }
            }
        });

    }
}
