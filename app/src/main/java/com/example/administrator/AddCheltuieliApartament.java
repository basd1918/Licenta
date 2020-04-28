package com.example.administrator;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import database.DatabaseHelper;

public class AddCheltuieliApartament extends AppCompatActivity {
    DatabaseHelper myDb;
    TextView textViewLuna, textViewAn;
    EditText editTextApometru;
    TextView textViewNrApartament, textViewIndiviza, textViewCheltuieliNrPers, textViewEnergieElectrica, textViewTotal, textViewRestanta, textViewConsumApa;
    TextView textViewNumeProprietarAp, textViewSuprafataAp, textViewNumarPersoaneAp;
    Button buttonPrev, buttonNext, button_AddIntretinere, buttonOk, buttonAscunde, buttonCalculeazaConsumApa;
    Button butonDialogInfoCheltApartament;
    LinearLayout lynearLayoutIndivizia, lynearLayoutCheltNrPers, lynearLayoutEnergieElectrica;
    LinearLayout lynearLayoutNumeProprietarAp, lynearLayoutSuprafataAp, lynearLayoutNumarPersoaneAp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_cheltuieli_apartament);

        myDb = new DatabaseHelper(this);
        textViewLuna = (TextView) findViewById(R.id.textViewLuna);
        textViewAn = (TextView) findViewById(R.id.textViewAn);
        textViewNrApartament = (TextView) findViewById(R.id.textViewNrApartament);
        editTextApometru = (EditText) findViewById(R.id.editTextApometru);
        editTextApometru.setText("0.0");
        butonDialogInfoCheltApartament=(Button)findViewById(R.id.butonDialogInfoCheltApartament);
        openInfoCheltApartament();

        textViewIndiviza = (TextView) findViewById(R.id.textViewIndiviza);
        textViewCheltuieliNrPers = (TextView) findViewById(R.id.textViewCheltuieliNrPers);
        textViewEnergieElectrica = (TextView) findViewById(R.id.textViewEnergieElectrica);
        textViewTotal = (TextView) findViewById(R.id.textViewTotal);
        textViewRestanta = (TextView) findViewById(R.id.textViewRestanta);
        textViewNumeProprietarAp = (TextView) findViewById(R.id.textViewNumeProprietarAp);
        textViewSuprafataAp = (TextView) findViewById(R.id.textViewSuprafataAp);
        textViewNumarPersoaneAp = (TextView) findViewById(R.id.textViewNumarPersoaneAp);
        textViewConsumApa = (TextView) findViewById(R.id.textViewConsumApa);

        lynearLayoutIndivizia = (LinearLayout) findViewById(R.id.lynearLayoutIndivizia);
        lynearLayoutCheltNrPers = (LinearLayout) findViewById(R.id.lynearLayoutCheltNrPers);
        lynearLayoutEnergieElectrica = (LinearLayout) findViewById(R.id.lynearLayoutEnergieElectrica);
        lynearLayoutNumeProprietarAp = (LinearLayout) findViewById(R.id.lynearLayoutNumeProprietarAp);
        lynearLayoutSuprafataAp = (LinearLayout) findViewById(R.id.lynearLayoutSuprafataAp);
        lynearLayoutNumarPersoaneAp = (LinearLayout) findViewById(R.id.lynearLayoutNumarPersoaneAp);

        buttonCalculeazaConsumApa = (Button) findViewById(R.id.buttonCalculeazaConsumApa);
        button_AddIntretinere = (Button) findViewById(R.id.button_AddIntretinere);
        buttonOk = (Button) findViewById(R.id.buttonOk);
        buttonAscunde = (Button) findViewById(R.id.buttonAscunde);
        buttonOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonAscunde.setVisibility(View.VISIBLE);
                buttonOk.setVisibility(View.GONE);
                lynearLayoutNumeProprietarAp.setVisibility(View.VISIBLE);
                lynearLayoutSuprafataAp.setVisibility(View.VISIBLE);
                lynearLayoutNumarPersoaneAp.setVisibility(View.VISIBLE);
            }
        });
        buttonAscunde.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonAscunde.setVisibility(View.GONE);
                buttonOk.setVisibility(View.VISIBLE);
                lynearLayoutNumeProprietarAp.setVisibility(View.GONE);
                lynearLayoutSuprafataAp.setVisibility(View.GONE);
                lynearLayoutNumarPersoaneAp.setVisibility(View.GONE);
            }
        });


        buttonPrev = (Button) findViewById(R.id.buttonPrev);
        buttonNext = (Button) findViewById(R.id.buttonNext);
        //------------------ Setez continutul initial pt butonul DETALII
        textViewNrApartament.setText("1");
        String numeProprietar = myDb.getNumeProprietarApartament(textViewNrApartament.getText().toString());
        textViewNumeProprietarAp.setText(numeProprietar);
        int nrPers = myDb.getNrPersoaneApartament(textViewNrApartament.getText().toString());
        textViewNumarPersoaneAp.setText(Integer.toString(nrPers));
        String suprafata = myDb.getSuprafataApartament(textViewNrApartament.getText().toString());
        textViewSuprafataAp.setText(suprafata);

        buttonCalculeazaConsumApa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double consumApaApartament = CalculeazaConsumApa(textViewNrApartament.getText().toString());
                double roundConsumApaApartament = Math.round(consumApaApartament * 100.0) / 100.0;
                textViewConsumApa.setText(Double.toString(roundConsumApaApartament));
                CalculeazaTotal(textViewNrApartament.getText().toString());
            }
        });
        CalculeazaTotal(textViewNrApartament.getText().toString());

        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextAp(v);
            }
        });
        buttonPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                prevAp(v);
            }
        });


        Intent intent = getIntent();
        String messageLuna = intent.getStringExtra("luna");
        textViewLuna.setText(messageLuna);
        String messageAn = intent.getStringExtra("an");
        textViewAn.setText(messageAn);

        AddIntretinere();
    }

    public int getPrevAp() {
        textViewNrApartament.invalidate();//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!1
        String app = textViewNrApartament.getText().toString();
        int intApp = Integer.parseInt(app);
        int nrApartamente= myDb.countApartamente();
        myDb.close();//!!!!!!!!!!++++++++++++++++++++
        if (intApp > 1)
            intApp = intApp - 1;
        else intApp =nrApartamente;
        return intApp;
    }

    public int getNextAp() {
        textViewNrApartament.invalidate();//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!1
        String app2 = textViewNrApartament.getText().toString();
        int intApp2 = Integer.parseInt(app2);
        int nrApartamente= myDb.countApartamente();
        myDb.close();//!!!!!!!!!!++++++++++++++++++++
        if (intApp2 < nrApartamente)
            intApp2 = intApp2 + 1;
        else intApp2 = 1;
        return intApp2;
    }

    public double CalculeazaCheltuieliDupaNrPers(String apartament) {
        Intent intent_nrPers = getIntent();
        String salubritate = intent_nrPers.getStringExtra("salubritate");
        // String curentHol=intent_nrPers.getStringExtra("curentHol");
        String curatenieHol = intent_nrPers.getStringExtra("curatenieHol");

        double doubleSalubritate = Double.parseDouble(salubritate);
        //  double doubleCurentHol=Double.parseDouble(curentHol);
        double doubleCuratenieHol = Double.parseDouble(curatenieHol);

        double totalCheltuieliDupaNrPers = doubleSalubritate + doubleCuratenieHol;//+doubleCurentHol;
        int nrTotalPersoane = 0;
        int nrApartamente=myDb.countApartamente();
        myDb.close();//!!!!!!!!!!++++++++++++++++++++
        for (int i = 1; i <=nrApartamente;  i++) {
            nrTotalPersoane = nrTotalPersoane + myDb.getNrPersoaneApartament(Integer.toString(i));
            myDb.close();//!!!!!!!!!!++++++++++++++++++++
            //apartament=Integer.toString(i);
        }
        double rezultat = (totalCheltuieliDupaNrPers / nrTotalPersoane) * myDb.getNrPersoaneApartament(apartament);
        myDb.close();//!!!!!!!!!!++++++++++++++++++++
        double roundOffRezultat = Math.round(rezultat * 100.0) / 100.0;
        return roundOffRezultat;
    }

    public double CalculeazaEnergieElectricaHolPePersoana(String apartament) {
        Intent intent_EnergieElectrica_nrPers = getIntent();
        String curentHol = intent_EnergieElectrica_nrPers.getStringExtra("curentHol");
        double doubleCurentHol = Double.parseDouble(curentHol);
        int nrTotalPersoane = 0;
        int nrApartamente= myDb.countApartamente();
        for (int i = 1; i <= nrApartamente; i++) {
            nrTotalPersoane = nrTotalPersoane + myDb.getNrPersoaneApartament(Integer.toString(i));
            myDb.close();//!!!!!!!!!!++++++++++++++++++++
        }
        double rezultat = (doubleCurentHol / nrTotalPersoane) * myDb.getNrPersoaneApartament(apartament);
        myDb.close();//!!!!!!!!!!++++++++++++++++++++
        double roundOffRezultat = Math.round(rezultat * 100.0) / 100.0;
        return roundOffRezultat;
    }


    public double CalculeazaConsumApa(String apartament) {
        Intent intent_consumApa = getIntent();
        String pretApa = intent_consumApa.getStringExtra("pretApa");
        double doublePretApa = Double.parseDouble(pretApa);
        String apometru = editTextApometru.getText().toString();
        double doubleApometru = Double.parseDouble(apometru);
        double rezultat = doubleApometru * doublePretApa;
        double roundOffRezultat = Math.round(rezultat * 100.0) / 100.0;
        return roundOffRezultat;
    }

    public double CalculeazaCheltuieliDupaCotaIndiviza(String apartament) {
        Intent intent_indiviza = getIntent();
        String cheltuieliAdministrative = intent_indiviza.getStringExtra("cheltAdmin");
        String intretinereAdscensor = intent_indiviza.getStringExtra("ascensor");
        String salariuAdministrator = intent_indiviza.getStringExtra("salariuAdmin");
        double doubleCheltAdministrative = Double.parseDouble(cheltuieliAdministrative);
        double doubleIntretinereAdscensor = Double.parseDouble(intretinereAdscensor);
        double doubleSalariuAdministrator = Double.parseDouble(salariuAdministrator);
        double totalCheltuieliDupaCotaIndiviza = doubleCheltAdministrative + doubleIntretinereAdscensor + doubleSalariuAdministrator;
        double totalSuprafata = 0.0;
        int nrApartamente= myDb.countApartamente();
        myDb.close();//!!!!!!!!!!++++++++++++++++++++
        for (int i = 1; i <=nrApartamente; i++) {
            String suprafataAp = myDb.getSuprafataApartament(Integer.toString(i));
            myDb.close();//!!!!!!!!!!++++++++++++++++++++
            double doubleSuprafataAp = Double.parseDouble(suprafataAp);
            totalSuprafata = totalSuprafata + doubleSuprafataAp;
            //apartament=Integer.toString(i);
        }
        String supApartamentCurent = myDb.getSuprafataApartament(apartament);
        myDb.close();//!!!!!!!!!!++++++++++++++++++++
        double doubleSupApartamentCurent = Double.parseDouble(supApartamentCurent);
        double rezultat = (totalCheltuieliDupaCotaIndiviza / totalSuprafata) * doubleSupApartamentCurent;
        double roundOffRezultat = Math.round(rezultat * 100.0) / 100.0;
        return roundOffRezultat;

    }

    public double CalculeazaTotal(String apartament) {
        double total = 0.0;
        //---chelt dupa nr de pers
        double cheltDupaNrPers = CalculeazaCheltuieliDupaNrPers(textViewNrApartament.getText().toString());
        String stringCheltDupaNrPers = Double.toString(cheltDupaNrPers);
        textViewCheltuieliNrPers.setText(stringCheltDupaNrPers);
        //---celt dupa cota indiviza
        double cheltDupaCotaIndiviza = CalculeazaCheltuieliDupaCotaIndiviza(textViewNrApartament.getText().toString());
        double roundCheltDupaCotaIndiviza = Math.round(cheltDupaCotaIndiviza * 100.0) / 100.0;
        String stringCheltDupaCotaIndiviza = Double.toString(roundCheltDupaCotaIndiviza);
        textViewIndiviza.setText(stringCheltDupaCotaIndiviza);
        //---energie electrica pe casa scarii
        double energieElectricaHol = CalculeazaEnergieElectricaHolPePersoana(textViewNrApartament.getText().toString());
        double roundEnergieElectricaHol = Math.round(energieElectricaHol * 100.0) / 100.0;
        String stringEnergieElectricaHol = Double.toString(roundEnergieElectricaHol);
        textViewEnergieElectrica.setText(stringEnergieElectricaHol);
        //---calcul consum apa textViewConsumApa
        double consumApaApartament = CalculeazaConsumApa(textViewNrApartament.getText().toString());
        double roundConsumApaApartament = Math.round(consumApaApartament * 100.0) / 100.0;
        //---calcul total
        total = cheltDupaNrPers + roundCheltDupaCotaIndiviza + roundEnergieElectricaHol + consumApaApartament;
        double roundTotal = Math.round(total * 100.0) / 100.0;
        String stringTotal = Double.toString(roundTotal);
        textViewTotal.setText(stringTotal);
        return roundTotal;
    }


    public void nextAp(View v) {
        //---detalii apartament.
        textViewNrApartament.invalidate();//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!1
        textViewNrApartament.setText(Integer.toString(getNextAp()));
        String numeProprietar = myDb.getNumeProprietarApartament(textViewNrApartament.getText().toString());
        myDb.close();//!!!!!!!!!!++++++++++++++++++++

        textViewNumeProprietarAp.setText(numeProprietar);
        int nrPers = myDb.getNrPersoaneApartament(textViewNrApartament.getText().toString());
        myDb.close();//!!!!!!!!!!++++++++++++++++++++

        textViewNumarPersoaneAp.setText(Integer.toString(nrPers));
        String suprafata = myDb.getSuprafataApartament(textViewNrApartament.getText().toString());
        myDb.close();//!!!!!!!!!!++++++++++++++++++++

        //---despre cheltuieli
        textViewSuprafataAp.setText(suprafata);
        textViewConsumApa.setText("0.0");
        editTextApometru.setText("0.0");
        double rez = CalculeazaTotal(textViewNrApartament.getText().toString());
    }

    public void prevAp(View v) {
        //---detalii apartament.
        textViewNrApartament.invalidate();//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!1
        textViewNrApartament.setText(Integer.toString(getPrevAp()));
        String numeProprietar = myDb.getNumeProprietarApartament(textViewNrApartament.getText().toString());
        myDb.close();//!!!!!!!!!!++++++++++++++++++++

        textViewNumeProprietarAp.setText(numeProprietar);
        int nrPers = myDb.getNrPersoaneApartament(textViewNrApartament.getText().toString());
        myDb.close();//!!!!!!!!!!++++++++++++++++++++

        textViewNumarPersoaneAp.setText(Integer.toString(nrPers));
        String suprafata = myDb.getSuprafataApartament(textViewNrApartament.getText().toString());
        myDb.close();//!!!!!!!!!!++++++++++++++++++++

        textViewSuprafataAp.setText(suprafata);
        //---despre cheltuieli
        textViewConsumApa.setText("0.0");
        editTextApometru.setText("0.0");
        double rez = CalculeazaTotal(textViewNrApartament.getText().toString());
    }

    public String Restanta(String ap, String luna, String an) {
        String lunaN = luna;
        int intAn = Integer.parseInt(an);
        if (luna.equals("Ianuarie")) {
            lunaN = "Decembrie";
            intAn = Integer.parseInt(an) - 1;
        } else if (luna.equals("Februarie")) {
            lunaN = "Ianuarie";
        } else if (luna.equals("Martie")) {
            lunaN = "Februarie";
        } else if (luna.equals("Aprilie")) {
            lunaN = "Martie";
        } else if (luna.equals("Mai")) {
            lunaN = "Aprilie";
        } else if (luna.equals("Iunie")) {
            lunaN = "Mai";
        } else if (luna.equals("Iulie")) {
            lunaN = "Iunie";
        } else if (luna.equals("August")) {
            lunaN = "Iulie";
        } else if (luna.equals("Septembrie")) {
            lunaN = "August";
        } else if (luna.equals("Octombrie")) {
            lunaN = "Septembrie";
        } else if (luna.equals("Noiembrie")) {
            lunaN = "Octombrie";
        } else if (luna.equals("Decembrie")) {
            lunaN = "Noiembrie";
        }
        String StrIntAn = Integer.toString(intAn);
        String RestantaAnterioarea = myDb.getRestantaIntretinere(ap, lunaN, StrIntAn);
        String totalAnterior = myDb.getTotalIntretinere(ap, lunaN, StrIntAn);
        myDb.close();//!!!!!!!!!!++++++++++++++++++++
        double doubleRestantaTotala = Double.parseDouble(RestantaAnterioarea) + Double.parseDouble(totalAnterior);
        double roundOffRestantaTotala = Math.round(doubleRestantaTotala * 100.0) / 100.0;
        String restantaTotala = Double.toString(roundOffRestantaTotala);
        return restantaTotala;
    }

    public String StatusLunaTrecuta(String ap, String luna, String an) {
        String lunaN = luna;
        int intAn = Integer.parseInt(an);
        if (luna.equals("Ianuarie")) {
            lunaN = "Decembrie";
            intAn = Integer.parseInt(an) - 1;
        } else if (luna.equals("Februarie")) {
            lunaN = "Ianuarie";
        } else if (luna.equals("Martie")) {
            lunaN = "Februarie";
        } else if (luna.equals("Aprilie")) {
            lunaN = "Martie";
        } else if (luna.equals("Mai")) {
            lunaN = "Aprilie";
        } else if (luna.equals("Iunie")) {
            lunaN = "Mai";
        } else if (luna.equals("Iulie")) {
            lunaN = "Iunie";
        } else if (luna.equals("August")) {
            lunaN = "Iulie";
        } else if (luna.equals("Septembrie")) {
            lunaN = "August";
        } else if (luna.equals("Octombrie")) {
            lunaN = "Septembrie";
        } else if (luna.equals("Noiembrie")) {
            lunaN = "Octombrie";
        } else if (luna.equals("Decembrie")) {
            lunaN = "Noiembrie";
        }
        String StrIntAn = Integer.toString(intAn);
        return myDb.getStatusPlata(ap, lunaN, StrIntAn);
    }

    public void AddIntretinere() {
        button_AddIntretinere.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editTextApometru.length() == 0) {
                    editTextApometru.setError("Acest camp este obligatoriu");
                } else {

                    double rezTotal = CalculeazaTotal(textViewNrApartament.getText().toString());
                    String stausLunaTrecuta = StatusLunaTrecuta(textViewNrApartament.getText().toString(), textViewLuna.getText().toString(), textViewAn.getText().toString());
                    String restanta = "0.0";
                    if (stausLunaTrecuta.equals("neachitat")) {
                        restanta = Restanta(textViewNrApartament.getText().toString(), textViewLuna.getText().toString(), textViewAn.getText().toString());
                    }
                    textViewRestanta.setText(restanta);
                    double consumApaApartament = CalculeazaConsumApa(textViewNrApartament.getText().toString());
                    textViewConsumApa.setText(consumApaApartament+"");
                    boolean isInserted = myDb.insertIntretinere(textViewNrApartament.getText().toString(),
                            textViewIndiviza.getText().toString(), textViewCheltuieliNrPers.getText().toString(), consumApaApartament+"",
                            textViewEnergieElectrica.getText().toString(), textViewTotal.getText().toString(), restanta
                            , textViewLuna.getText().toString(), textViewAn.getText().toString(), "status");
                    if (isInserted == true)
                        Toast.makeText(AddCheltuieliApartament.this, "Intretinere adaugata", Toast.LENGTH_LONG).show();
                    else
                        Toast.makeText(AddCheltuieliApartament.this, "Datele au fost deja introduse pentru acest apartament...", Toast.LENGTH_LONG).show();


                }
            }
        });
    }


    //------------show info-----------------------------

    public void openInfoCheltApartament(){
        butonDialogInfoCheltApartament.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message="Întreținerea se calculează astfel: (1)+(2)+(3) ,unde:\n\n" +
                        "       1) Cheltuieli personale = volumul de apă declarat (mc), înmulțit cu valoarea apei (lei/mc) din luna respectivă.\n" +
                        "\n     2) Cheltuieli după cota indiviză = Suma: Cheltuieli administrative + Întreținere Ascensor + Salariu administrator, " +
                        "raportată la suprafața fiecarui apartament.\n" +
                        "\n        3) Cheltuieli după numărul de persoane = Suma: Salubritate + Curățenie + Electrica, raportată la numărul de persoane per aparatment. ";

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