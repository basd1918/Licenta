package com.example.administrator;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
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

public class AchitaIntretinereActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    DatabaseHelper myDb;
    Button buttonGetTotal, buttonAchitaIntretinere, buttonRest;
    EditText editTextGetAp, editTextGetAn;
    TextView textViewGetTotal, textViewRest,textViewStatusPlata,textViewTotalTotal,textViewGetRestanta;
    Spinner spinnerGetLunaAchita;
    EditText editTextIntroducetiNumerar;
    Button butonDialogInfoAchita;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_achita_intretinere);
        myDb = new DatabaseHelper(this);
        butonDialogInfoAchita=(Button)findViewById(R.id.butonDialogInfoAchita);
        openInfoAchita();

        editTextGetAp = (EditText) findViewById(R.id.editTextGetAp);
        editTextGetAn = (EditText) findViewById(R.id.editTextGetAn);
        textViewGetTotal = (TextView) findViewById(R.id.textViewGetTotal);
        textViewTotalTotal = (TextView) findViewById(R.id.textViewTotalTotal);
        textViewGetRestanta = (TextView) findViewById(R.id.textViewGetRestanta);
        buttonGetTotal = (Button) findViewById(R.id.buttonGet);
        editTextIntroducetiNumerar = (EditText) findViewById(R.id.editTextIntroducetiNumerar);
        buttonAchitaIntretinere = (Button) findViewById(R.id.buttonAchitaIntretinere);
        textViewRest = (TextView) findViewById(R.id.textViewRest);
        textViewStatusPlata = (TextView) findViewById(R.id.textViewStatusPlata);

        spinnerGetLunaAchita = (Spinner) findViewById(R.id.spinnerGetLunaAchita);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.luni, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerGetLunaAchita.setAdapter(adapter);
        spinnerGetLunaAchita.setOnItemSelectedListener(this);
        Rest();
        Total();

        buttonRest = (Button) findViewById(R.id.buttonRest);
        buttonRest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editTextGetAn.length() == 0) {
                    editTextGetAn.setError("Introduceti Anul");
                } else {
                String res = Rest();
                textViewRest.setText(res);}
            }
        });

        buttonGetTotal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editTextGetAn.length() == 0)
                    {editTextGetAn.setError("Introduceti Anul");}
                else if (editTextGetAp.length() == 0)
                    {editTextGetAp.setError("Introduceti Apartament");}
                 else {
                    textViewStatusPlata.setVisibility(View.VISIBLE);
                    String total = Total();
                    String restanta="0.0";
                   // String stausLunaTrecuta=StatusLunaTrecuta(editTextGetAp.getText().toString(), spinnerGetLunaAchita.getSelectedItem().toString(), editTextGetAn.getText().toString());
                    //if(stausLunaTrecuta.equals("neachitat"))
                        restanta = Restanta(editTextGetAp.getText().toString(), spinnerGetLunaAchita.getSelectedItem().toString(), editTextGetAn.getText().toString());
                    textViewGetTotal.setText(total);
                    textViewGetRestanta.setText(restanta);
                    textViewStatusPlata.setText(myDb.getStatusPlata(editTextGetAp.getText().toString(), spinnerGetLunaAchita.getSelectedItem().toString(), editTextGetAn.getText().toString()));
                    double t = Double.parseDouble(total) + Double.parseDouble(restanta);
                    double roundTotal = Math.round(t * 100.0) / 100.0;
                    textViewTotalTotal.setText(Double.toString(roundTotal));

                }
            }
        });

      /* buttonAchitaIntretinere.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String luna = spinnerGetLunaAchita.getSelectedItem().toString();
                String ap = editTextGetAp.getText().toString();
                String an = editTextGetAn.getText().toString();
                if (editTextGetAn.length() == 0) {
                    editTextGetAn.setError("Introduceti Anul");
                }
                else if (editTextGetAp.length() == 0)
                    {editTextGetAp.setError("Introduceti Apartament");}
                else if (editTextIntroducetiNumerar.length() == 0)
                    {editTextIntroducetiNumerar.setError("Introduceti Numerar");}
                else {
                    if(textViewStatusPlata.getText().toString().equals("status")){
                        String rest = Rest();
                        String total = Total();//aici ai putea pune si o achitare partiala...
                                                    // total=total luna curenta....aparent,aici nu il folosesc la nimic
                                                    //rest=diferenta de bani: numerar-(restanta totala de luna trecuta+ total luna curenta)
                                                    //restul asta ar trebui pus in restanta daca e negativ...
                                                    //

                        double doubleRest = Double.parseDouble(rest);
                        double doubleRestConvert=doubleRest*(-1);
                        String strinDoubleRest=Double.toString(doubleRestConvert);

                        if (doubleRest < 0) {
                           //myDb.adaugaRestanta(strinDoubleRest, ap, luna, an);
                            myDb.statusAchita(ap,luna,an,"neachitat");
                            Toast.makeText(AchitaIntretinereActivity.this, "Restanta adaugata: " + rest, Toast.LENGTH_LONG).show();
                        } else {
                            myDb.statusAchita(ap,luna,an,"achitat");
                           // myDb.adaugaRestanta("0.0", ap, luna, an);
                            Toast.makeText(AchitaIntretinereActivity.this, "Intretinere achitata", Toast.LENGTH_LONG).show(); }

                    }
                    else {
                        Toast.makeText(AchitaIntretinereActivity.this, "Aceasta intretinere a fost deja calculata", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });

    }*/



        buttonAchitaIntretinere.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String luna = spinnerGetLunaAchita.getSelectedItem().toString();
                String ap = editTextGetAp.getText().toString();
                String an = editTextGetAn.getText().toString();
                String status=myDb.getStatusPlata(ap,luna,an);
                if (editTextGetAn.length() == 0) {
                    editTextGetAn.setError("Introduceti Anul");
                }
                else if (editTextGetAp.length() == 0)
                {editTextGetAp.setError("Introduceti Apartament");}
                else if (editTextIntroducetiNumerar.length() == 0)
                {editTextIntroducetiNumerar.setError("Introduceti Numerar");}
                else {
                    if(status.equals("status")){
                        String stringRestantaTotalaTrecuta=Restanta(ap,luna,an);
                        String stringStatusLunaTrecuta=StatusLunaTrecuta(ap,luna,an);
                        String stringTotalLunaCurenta = Total();
                        String stringNumerar=editTextIntroducetiNumerar.getText().toString();
                        //----------------------------------------------------------------------
                        double doubleRestantaTotalaTrecuta=0.0;
                        double doubleTotalLunaCurenta=Double.parseDouble(stringTotalLunaCurenta);
                        double doubleNumerar=Double.parseDouble(stringNumerar);
                        if(stringStatusLunaTrecuta.equals("neachitat"))
                            doubleRestantaTotalaTrecuta=Double.parseDouble(stringRestantaTotalaTrecuta);
                        double rest=doubleNumerar-(doubleRestantaTotalaTrecuta+doubleTotalLunaCurenta);
                        if(rest>=0){
                            myDb.statusAchita(ap,luna,an,"achitat");
                            Toast.makeText(AchitaIntretinereActivity.this, "Intretinere achitata", Toast.LENGTH_LONG).show();
                        }
                        else if(doubleNumerar-(doubleRestantaTotalaTrecuta+doubleTotalLunaCurenta)<0 && doubleNumerar==0){
                            myDb.statusAchita(ap,luna,an,"neachitat");
                            Toast.makeText(AchitaIntretinereActivity.this, "Restanta adaugata"+rest, Toast.LENGTH_LONG).show();
                        }
                        else editTextIntroducetiNumerar.setError("Nu puteti achita partial intretinerea");

                    }
                    else {
                        Toast.makeText(AchitaIntretinereActivity.this, "Aceasta intretinere a fost deja calculata", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });

    }

  /*  public String Restanta(String ap, String luna, String an){
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
        return RestantaAnterioarea;
    }*/




    public String Restanta(String ap, String luna, String an){
        String RestantaAnterioarea=myDb.getRestantaIntretinere(ap,luna,an);
        return RestantaAnterioarea;
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


    public String Rest() {
        String luna = spinnerGetLunaAchita.getSelectedItem().toString();
        String ap = editTextGetAp.getText().toString();
        String an = editTextGetAn.getText().toString();
        String total = myDb.getTotalIntretinere(ap, luna, an);
        String status=StatusLunaTrecuta(ap,luna,an);
       // String restanta="0.0";
      //  if(status.equals("neachitat"))
        String  restanta = Restanta(ap, luna, an);//Restanta totala de pe luna trecuta--->totalAntrior+restantaAnterioara
       // myDb.adaugaRestanta(restanta, ap, luna, an);//o pun in baza de date. E 0.0 daca statusul pe luna trecuta e "achitat" ,sau restantaTotala daca statusul ant. e "neachitat"
        String stringRest="0.0";
        double doubleTotal = Double.parseDouble(total) + Double.parseDouble(restanta);
        double roundOffTotal = Math.round(doubleTotal * 100.0) / 100.0;
        if(editTextIntroducetiNumerar.length() == 0) {
            editTextIntroducetiNumerar.setError("Introduceti plata"); }
        else {
                String numerar = editTextIntroducetiNumerar.getText().toString();
                double doubleRest = Double.parseDouble(numerar) - roundOffTotal;
                double roundOffRest = Math.round(doubleRest * 100.0) / 100.0;
                stringRest = Double.toString(roundOffRest);
            }
            return stringRest;
        }

    public String Total(){
        String luna = spinnerGetLunaAchita.getSelectedItem().toString();
        String ap = editTextGetAp.getText().toString();
        String an = editTextGetAn.getText().toString();
        String total = myDb.getTotalIntretinere(ap, luna, an);
        double doubleTotal = Double.parseDouble(total);
        double roundOffTotal = Math.round(doubleTotal * 100.0) / 100.0;
        String stringTotal = Double.toString(roundOffTotal);
        return stringTotal;
    }

    //------------show info-----------------------------

    public void openInfoAchita(){
        butonDialogInfoAchita.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message="Selectați data dorită și numărul apartamentului.\n     - Prin apăsarea " +
                        "butonului 'OK' se vor afișa informațiile despre întreținerea corespunzătoare parametrilor selectați." +
                        "\n      - O întreținere nu se poate achita parțial, adică valoarea introdusă în câmpul Numerar " +
                        "nu poate fi mai mică decât valoarea totală a întreținerii." +
                         "\n      - O întreținere neachitată presupune introducerea valorii 0 în câmpul Numerar."+
                        "\n      - Pentru modificarea unei întrețineri a cărui status a fost deja setat la 'achitat' sau 'neachitat' " +
                        "trebuie să intrați în meniul principal -> Lista Cheltuielilor. Selectați întreținerea dorită, actualizați datele" +
                        "și apăsați butonul 'Modifică'. "
                        ;

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