package com.example.administrator;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
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

public class TrimiteMailActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    EditText editTextTo,editTextSubject,editTextMessage,editTextContinutTabelIntretinere;
    Button buttonSendMail;
    DatabaseHelper myDb;
    Spinner spinnerGetLunaTrimiteMail;
    EditText editTextGetAnTrimiteMail;
    Button buttonInfoEmail;
    TextView textViewInformatiiMail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trimite_mail);
        myDb=new DatabaseHelper(this);

        buttonInfoEmail=(Button)findViewById(R.id.buttonInfoEmail);
        openInfoTrimiteMail();

        editTextContinutTabelIntretinere=(EditText) findViewById(R.id.editTextContinutTabelIntretinere);
        editTextTo=(EditText) findViewById(R.id.editTextTo);
        editTextSubject=(EditText) findViewById(R.id.editTextSubject);
        editTextMessage=(EditText) findViewById(R.id.editTextMessage);
        buttonSendMail=(Button) findViewById(R.id.buttonSendMail);
        String destinatari=myDb.getAllMail();
        editTextTo.setText(destinatari);

        buttonSendMail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(editTextGetAnTrimiteMail.length()==0) {
                        editTextGetAnTrimiteMail.setError("Completati anul");}
                     else if(verificaIntretinere()==false) {
                        editTextMessage.setError("Nu s-a efectuat calculul intretinerii pentru aceasta data");}
                    else if(editTextTo.length()==0) {
                        editTextTo.setError("Completati mailul");}
                   // else if(editTextSubject.length()==0) {
                       // editTextSubject.setError("Completare obligatorie");}
                    else sendMail();
                }
            });

        spinnerGetLunaTrimiteMail = (Spinner) findViewById(R.id.spinnerGetLunaTrimiteMail);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.luni, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerGetLunaTrimiteMail.setAdapter(adapter);
        spinnerGetLunaTrimiteMail.setOnItemSelectedListener(this);
        editTextGetAnTrimiteMail=(EditText) findViewById(R.id.editTextGetAnTrimiteMail);

    }
    private void sendMail(){

        String EditText_mesaj=editTextMessage.getText().toString();//mesajul din edit text
        String destinatari=editTextTo.getText().toString();
        String[] dest=destinatari.split(",");
        String subiect=editTextSubject.getText().toString();
        String message=EditText_mesaj+"\n\n\n"+editTextContinutTabelIntretinere.getText().toString();//mesajul anteror + tabelul intretinerii

        Intent intent=new Intent(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_EMAIL,dest);
        intent.putExtra(Intent.EXTRA_SUBJECT,subiect);
        intent.putExtra(Intent.EXTRA_TEXT,message);
        intent.setType("message/rfc882");
        startActivity(Intent.createChooser(intent,"Choose an email client"));
    }

    public boolean verificaIntretinere(){
        String luna=spinnerGetLunaTrimiteMail.getSelectedItem().toString();
        String an=editTextGetAnTrimiteMail.getText().toString();
        Cursor res= myDb.getAllCheltuieliDATA_OrdDupaNrAp(luna,an);
        StringBuffer buffer= new StringBuffer();
        while(res.moveToNext()){
            buffer.append("____________________________________\n");
            buffer.append("\n");
            buffer.append("Apartament: "+ res.getString(0)+"\n");
            buffer.append("Cota Indivizia: "+ res.getString(1)+"\n");
            buffer.append("Cota Nr. Persoane: "+ res.getString(2)+"\n");
            buffer.append("Apa: "+ res.getString(3)+"\n");
            buffer.append("Energie Electrica: "+ res.getString(4)+"\n");
            buffer.append("Total: "+ res.getString(5)+"\n");
            buffer.append("Restanta: "+ res.getString(6)+"\n");
            buffer.append("Luna: "+ res.getString(7)+"\n");
            buffer.append("An: "+ res.getString(8)+"\n");
        }
        editTextContinutTabelIntretinere.setText(buffer.toString());
        if(buffer.toString().equals(""))
            return false;
        return true;
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(), text, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        String text = "Eroare la spinner";
        Toast.makeText(parent.getContext(), text, Toast.LENGTH_SHORT).show();
    }

    //------------show info-----------------------------

    public void openInfoTrimiteMail(){
        buttonInfoEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message="1) Tabelul cu întreținerea se completează automat în mail, în funcție de data aleasă." +
                        "\nFolosiți câmpul 'Mesaj' pentru adăugarea unor detalii suplimentare.\n" +
                        "\n2) Adresele de mail se iau automat din baza de date. Pentru modificarea bazei de date accesați " +
                        "setările din 'Gestiunea Apartamentelor' -> 'Modifică apartament'.\n     -În câmpul 'Către' se pot adăuga adrese noi, sau" +
                        " se pot șterge/modifica adresele existente.\n\n3) În cazul în care pentru data aleasă nu s-a efectuat" +
                        " calculul întreținerii, mailul nu va fi trimis și se va afișa un mesaj de eroare.";
                showMessage("Informații Pagină",message);
            }
        });
    }

    public void showMessage(String title,String message){
        AlertDialog.Builder builder =new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }
}
