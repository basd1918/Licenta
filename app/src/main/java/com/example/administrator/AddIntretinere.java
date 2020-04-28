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

import com.example.administrator.claseEntity.Cheltuieli;
import com.example.administrator.claseEntity.Intretinere;

import java.util.ArrayList;

import database.DatabaseHelper;

public class AddIntretinere extends AppCompatActivity  implements AdapterView.OnItemSelectedListener {
    DatabaseHelper myDb;
    EditText editTextAn;
    EditText editTextCheltAdministrative,editTextPretApa,editTextCurent,editTextSalubritate,editTextSalariuAdmin,editTextAscensor,editTextCuratenie;
    Spinner spinnerLuna;
    Button buttonNextCheltPersonale,butonDialogInfoCalculeaza;
    ArrayList<Cheltuieli> arrayList;

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text= parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(),text,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        String text="Eroare la spinner";
        Toast.makeText(parent.getContext(),text,Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_intretinere);
        myDb =new DatabaseHelper(this);

        spinnerLuna=(Spinner) findViewById(R.id.spinnerLuna);
        ArrayAdapter<CharSequence>adapter=ArrayAdapter.createFromResource(this,R.array.luni,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerLuna.setAdapter(adapter);
        spinnerLuna.setOnItemSelectedListener(this);

        butonDialogInfoCalculeaza=(Button)findViewById(R.id.butonDialogInfoCalculeaza);
        openInfoCalculeaza();

        editTextAn=(EditText)findViewById(R.id.editTextAn);
        editTextCheltAdministrative=(EditText)findViewById(R.id.editTextCheltAdmin);
        editTextPretApa=(EditText)findViewById(R.id.editTextPretMetruApa);
        editTextCurent=(EditText)findViewById(R.id.editTextCurent);
        editTextSalubritate=(EditText)findViewById(R.id.editTextSalubritate);
        editTextSalariuAdmin=(EditText)findViewById(R.id.editTextSalariuAdmin);
        editTextAscensor=(EditText)findViewById(R.id.editTextAscensor);
        editTextCuratenie=(EditText)findViewById(R.id.editTextCuratenie);
        buttonNextCheltPersonale=(Button)findViewById(R.id.buttonNextCheltPersonale);

    }


    public String Restanta(String ap, String luna, String an){
        String lunaN=luna;
        int intAn=Integer.parseInt(an);
        if(luna.equals("Ianuarie")){
            lunaN="Decembrie";
            intAn=Integer.parseInt(an)-1;
        }
        else if(luna.equals("Februarie")){
            lunaN="Ianuarie";
        }
        else if(luna.equals("Martie")){
            lunaN="Februarie";
        }
        else if(luna.equals("Aprilie")){
            lunaN="Martie";
        }
        else if(luna.equals("Mai")){
            lunaN="Aprilie";
        }
        else if(luna.equals("Iunie")){
            lunaN="Mai";
        }
        else if(luna.equals("Iulie")){
            lunaN="Iunie";
        }
        else if(luna.equals("August")){
            lunaN="Iulie";
        }
        else if(luna.equals("Septembrie")){
            lunaN="August";
        }
        else if(luna.equals("Octombrie")){
            lunaN="Septembrie";
        }
        else if(luna.equals("Noiembrie")){
            lunaN="Octombrie";
        }
        else if(luna.equals("Decembrie")){
            lunaN="Noiembrie";
        }
        String StrIntAn=Integer.toString(intAn);
        String RestantaAnterioarea=myDb.getRestantaIntretinere(ap,lunaN,StrIntAn);
        String totalAnterior=myDb.getTotalIntretinere(ap, lunaN, StrIntAn);
        double doubleRestantaTotala=Double.parseDouble(RestantaAnterioarea)+Double.parseDouble(totalAnterior);
        double roundOffRestantaTotala = Math.round(doubleRestantaTotala * 100.0) / 100.0;
        String restantaTotala=Double.toString(roundOffRestantaTotala);
        return restantaTotala;
    }

    public String StatusLunaTrecuta(String ap, String luna, String an){
        String lunaN=luna;
        int intAn=Integer.parseInt(an);
        if(luna.equals("Ianuarie")){
            lunaN="Decembrie";
            intAn=Integer.parseInt(an)-1;
        }
        else if(luna.equals("Februarie")){
            lunaN="Ianuarie";
        }
        else if(luna.equals("Martie")){
            lunaN="Februarie";
        }
        else if(luna.equals("Aprilie")){
            lunaN="Martie";
        }
        else if(luna.equals("Mai")){
            lunaN="Aprilie";
        }
        else if(luna.equals("Iunie")){
            lunaN="Mai";
        }
        else if(luna.equals("Iulie")){
            lunaN="Iunie";
        }
        else if(luna.equals("August")){
            lunaN="Iulie";
        }
        else if(luna.equals("Septembrie")){
            lunaN="August";
        }
        else if(luna.equals("Octombrie")){
            lunaN="Septembrie";
        }
        else if(luna.equals("Noiembrie")){
            lunaN="Octombrie";
        }
        else if(luna.equals("Decembrie")){
            lunaN="Noiembrie";
        }
        String StrIntAn=Integer.toString(intAn);
        return myDb.getStatusPlata(ap, lunaN, StrIntAn);
    }


    public void AddIntretinere(View v) {

        String cheltuieliExistente=myDb.getCheltuieli(spinnerLuna.getSelectedItem().toString(),editTextAn.getText().toString());
        if(cheltuieliExistente.equals("")==false)
        { String[] chelt=cheltuieliExistente.split(",");
            String pretApa=chelt[0];
            String cheltAdministrative=chelt[1];
            String salubritate=chelt[2];
            String energieElectricaHol=chelt[3];
            String salariuAdmin=chelt[4];
            String intretinereAscensor=chelt[5];
            String curatenieScara=chelt[6];
            String lunaa=chelt[7];
            String ann=chelt[8];

            Intent intent = new Intent(this, AddCheltuieliApartament.class);
            intent.putExtra("luna", lunaa);
            intent.putExtra("an", ann);
            intent.putExtra("pretApa", pretApa);
            intent.putExtra("salubritate", salubritate);
            intent.putExtra("curentHol",energieElectricaHol);
            intent.putExtra("curatenieHol", curatenieScara);
            //---salubritate+curentHol+curatenieHol se platesc pe nr de persoane
            intent.putExtra("cheltAdmin", cheltAdministrative);
            intent.putExtra("salariuAdmin", salariuAdmin);
            intent.putExtra("ascensor", intretinereAscensor);
            // Toast.makeText(AddIntretinere.this, pretApa+" "+cheltAdministrative+" "+salubritate+" "+energieElectricaHol+" "+salariuAdmin+" "+intretinereAscensor+" "+curatenieScara, Toast.LENGTH_LONG).show();
            startActivity(intent);}


        else if (editTextAn.length() == 0) {
            editTextAn.setError("Trebuie sa completati anul");
        } else if (editTextPretApa.length() == 0) {
            editTextPretApa.setError("Acest camp este obligatoriu");
        } else if (editTextCheltAdministrative.length() == 0) {
            editTextCheltAdministrative.setError("Acest camp este obligatoriu");
        } else if (editTextSalubritate.length() == 0) {
            editTextSalubritate.setError("Acest camp este obligatoriu");
        } else if (editTextSalariuAdmin.length() == 0) {
            editTextSalariuAdmin.setError("Introduceti apa");
        } else if (editTextCurent.length() == 0) {
            editTextCurent.setError("Acest camp este obligatoriu");
        } else if (editTextAscensor.length() == 0) {
            editTextAscensor.setError("Acest camp este obligatoriu");
        } else if (editTextCuratenie.length() == 0) {
            editTextCuratenie.setError("Acest camp este obligatoriu");
        } else {
            boolean isInserted = myDb.insertCheltuieliAsociatie(editTextPretApa.getText().toString(),
                    editTextCheltAdministrative.getText().toString(), editTextSalubritate.getText().toString(), editTextCurent.getText().toString(),
                    editTextSalariuAdmin.getText().toString(), editTextAscensor.getText().toString(), editTextCuratenie.getText().toString()
                    , spinnerLuna.getSelectedItem().toString(), editTextAn.getText().toString());
            if (isInserted == true) {
                Toast.makeText(AddIntretinere.this, "Cheltuieli administrative salvate", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(this, AddCheltuieliApartament.class);
                intent.putExtra("luna", spinnerLuna.getSelectedItem().toString());
                intent.putExtra("an", editTextAn.getText().toString());
                intent.putExtra("pretApa", editTextPretApa.getText().toString());
                intent.putExtra("salubritate", editTextSalubritate.getText().toString());
                intent.putExtra("curentHol", editTextCurent.getText().toString());
                intent.putExtra("curatenieHol", editTextCuratenie.getText().toString());
                //---salubritate+curentHol+curatenieHol se platesc pe nr de persoane
                intent.putExtra("cheltAdmin", editTextCheltAdministrative.getText().toString());
                intent.putExtra("salariuAdmin", editTextSalariuAdmin.getText().toString());
                intent.putExtra("ascensor", editTextAscensor.getText().toString());
                startActivity(intent);
            }

        }
    }
//------------show info-----------------------------

    public void openInfoCalculeaza(){
        butonDialogInfoCalculeaza.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String message="Se setează data dorită.\n     -Dacă cheltuielile administrative au fost deja " +
                            "introduse în sistem pentru data setată, atunci se poate trece direct la pagina următoare " +
                            "prin apăsarea butonului 'Pagina Urmatoare'.\n      -Altfel, sistemul va cere completarea" +
                            " obligatorie pentru fiecare câmp.";

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
