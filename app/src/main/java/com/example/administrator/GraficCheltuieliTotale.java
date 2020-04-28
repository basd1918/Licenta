package com.example.administrator;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LegendEntry;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

import database.DatabaseHelper;

public class GraficCheltuieliTotale extends AppCompatActivity {
    private EditText editTextGetAn_graficT;
    private TextView  textViewNrApartament_graficT;
    private Button buttonPrev_graficT, buttonNext_graficT,buttonVeziConsumApaIar;
    DatabaseHelper myDb;
    BarChart stacked_BarChart;
    int[] colorClassArray=new int[]{Color.rgb(0, 121, 107),Color.rgb(39, 194, 47),Color.BLUE,Color.rgb(251, 192, 45),Color.rgb(211, 47, 47)};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grafic_cheltuieli_totale);
        myDb = new DatabaseHelper(this);



        buttonVeziConsumApaIar=(Button)findViewById(R.id.buttonVeziConsumApaIar);
        buttonVeziConsumApaIar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        buttonPrev_graficT = (Button) findViewById(R.id.buttonPrev_graficT);
        buttonNext_graficT = (Button) findViewById(R.id.buttonNext_graficT);
        buttonNext_graficT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextAp(v);
                apelGrafic(textViewNrApartament_graficT.getText().toString());
            }
        });
        buttonPrev_graficT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                prevAp(v);
                apelGrafic(textViewNrApartament_graficT.getText().toString());
            }
        });

        textViewNrApartament_graficT = (TextView) findViewById(R.id.textViewNrApartament_graficT);
        textViewNrApartament_graficT.setText("1");
        apelGrafic("1");
    }

    public void apelGrafic(String nrAp) {
        editTextGetAn_graficT = (EditText) findViewById(R.id.editTextGetAn_graficT);
        stacked_BarChart=(BarChart)findViewById(R.id.stacked_BarChart);
        BarDataSet barDataSet = new BarDataSet(dataValues1(nrAp),"Cheltuieli totale");
        barDataSet.setColors(colorClassArray);
        BarData barData=new BarData(barDataSet);
        barData.setBarWidth(0.9f);
        stacked_BarChart.setData(barData);
        stacked_BarChart.setDrawBarShadow(false);
        stacked_BarChart.setDrawValueAboveBar(true);
        stacked_BarChart.setMaxVisibleValueCount(3);
        stacked_BarChart.setPinchZoom(false);
        stacked_BarChart.setDrawGridBackground(true);
        stacked_BarChart.setDragEnabled(true);

        LegendEntry l1=new LegendEntry("Cota Indiviză",Legend.LegendForm.SQUARE,10f,2f,null,Color.rgb(0, 121, 107));
        LegendEntry l2=new LegendEntry("Cota Nr. Pers.", Legend.LegendForm.SQUARE,10f,2f,null,Color.rgb(39, 194, 47));
        LegendEntry l3=new LegendEntry("Apa",Legend.LegendForm.SQUARE,10f,2f,null,Color.BLUE);
        LegendEntry l4=new LegendEntry("Energie electrica", Legend.LegendForm.SQUARE,10f,2f,null,Color.rgb(251, 192, 45));
        LegendEntry l5=new LegendEntry("Restanta", Legend.LegendForm.SQUARE,10f,2f,null,Color.rgb(211, 47, 47));
        Legend l = stacked_BarChart.getLegend();
        l.getEntries();
        l.setYEntrySpace(10f);
        l.setWordWrapEnabled(true);
        l.setCustom(new LegendEntry[]{l1,l2,l3,l4,l5});

        String[] zi = new String[]{"", "Ian.", "Feb.", "Mar.", "Apr.", "Mai", "Iun.", "Iul.", "Aug.", "Sept.", "Oct.", "Nov.", "Dec."};
        XAxis xAxis = stacked_BarChart.getXAxis();
        xAxis.setValueFormatter(new IndexAxisValueFormatter(zi));
        stacked_BarChart.invalidate();
    }

    private ArrayList<BarEntry> dataValues1(String nrAp) {
        float indivizia_ian = Float.parseFloat(myDb.getConsumCotaIndivizia(nrAp, "Ianuarie", editTextGetAn_graficT.getText().toString()));
        myDb.close();
        float indivizia_feb = Float.parseFloat(myDb.getConsumCotaIndivizia(nrAp, "Februarie", editTextGetAn_graficT.getText().toString()));
        myDb.close();
        float indivizia_mar = Float.parseFloat(myDb.getConsumCotaIndivizia(nrAp, "Martie", editTextGetAn_graficT.getText().toString()));
        myDb.close();
        float indivizia_apr = Float.parseFloat(myDb.getConsumCotaIndivizia(nrAp, "Aprilie", editTextGetAn_graficT.getText().toString()));
        myDb.close();
        float indivizia_mai = Float.parseFloat(myDb.getConsumCotaIndivizia(nrAp, "Mai", editTextGetAn_graficT.getText().toString()));
        myDb.close();
        float indivizia_iun = Float.parseFloat(myDb.getConsumCotaIndivizia(nrAp, "Iunie", editTextGetAn_graficT.getText().toString()));
        myDb.close();
        float indivizia_iul = Float.parseFloat(myDb.getConsumCotaIndivizia(nrAp, "Iulie", editTextGetAn_graficT.getText().toString()));
        myDb.close();
        float indivizia_aug = Float.parseFloat(myDb.getConsumCotaIndivizia(nrAp, "August", editTextGetAn_graficT.getText().toString()));
        myDb.close();
        float indivizia_sept = Float.parseFloat(myDb.getConsumCotaIndivizia(nrAp, "Septembrie", editTextGetAn_graficT.getText().toString()));
        myDb.close();
        float indivizia_oct = Float.parseFloat(myDb.getConsumCotaIndivizia(nrAp, "Octombrie", editTextGetAn_graficT.getText().toString()));
        myDb.close();
        float indivizia_nov = Float.parseFloat(myDb.getConsumCotaIndivizia(nrAp, "Noiembrie", editTextGetAn_graficT.getText().toString()));
        myDb.close();
        float indivizia_dec = Float.parseFloat(myDb.getConsumCotaIndivizia(nrAp, "Decembrie", editTextGetAn_graficT.getText().toString()));
        myDb.close();

        float nrPers_ian = Float.parseFloat(myDb.getConsumNrPers(nrAp, "Ianuarie", editTextGetAn_graficT.getText().toString()));
        myDb.close();
        float nrPers_feb = Float.parseFloat(myDb.getConsumNrPers(nrAp, "Februarie", editTextGetAn_graficT.getText().toString()));
        myDb.close();
        float nrPers_mar = Float.parseFloat(myDb.getConsumNrPers(nrAp, "Martie", editTextGetAn_graficT.getText().toString()));
        myDb.close();
        float nrPers_apr = Float.parseFloat(myDb.getConsumNrPers(nrAp, "Aprilie", editTextGetAn_graficT.getText().toString()));
        myDb.close();
        float nrPers_mai = Float.parseFloat(myDb.getConsumNrPers(nrAp, "Mai", editTextGetAn_graficT.getText().toString()));
        myDb.close();
        float nrPers_iun = Float.parseFloat(myDb.getConsumNrPers(nrAp, "Iunie", editTextGetAn_graficT.getText().toString()));
        myDb.close();
        float nrPers_iul = Float.parseFloat(myDb.getConsumNrPers(nrAp, "Iulie", editTextGetAn_graficT.getText().toString()));
        myDb.close();
        float nrPers_aug = Float.parseFloat(myDb.getConsumNrPers(nrAp, "August", editTextGetAn_graficT.getText().toString()));
        myDb.close();
        float nrPers_sept = Float.parseFloat(myDb.getConsumNrPers(nrAp, "Septembrie", editTextGetAn_graficT.getText().toString()));
        myDb.close();
        float nrPers_oct = Float.parseFloat(myDb.getConsumNrPers(nrAp, "Octombrie", editTextGetAn_graficT.getText().toString()));
        myDb.close();
        float nrPers_nov = Float.parseFloat(myDb.getConsumNrPers(nrAp, "Noiembrie", editTextGetAn_graficT.getText().toString()));
        myDb.close();
        float nrPers_dec = Float.parseFloat(myDb.getConsumNrPers(nrAp, "Decembrie", editTextGetAn_graficT.getText().toString()));
        myDb.close();

        float apa_ian = Float.parseFloat(myDb.getConsumApa(nrAp, "Ianuarie", editTextGetAn_graficT.getText().toString()));
        myDb.close();
        float apa_feb = Float.parseFloat(myDb.getConsumApa(nrAp, "Februarie", editTextGetAn_graficT.getText().toString()));
        myDb.close();
        float apa_mar = Float.parseFloat(myDb.getConsumApa(nrAp, "Martie", editTextGetAn_graficT.getText().toString()));
        myDb.close();
        float apa_apr = Float.parseFloat(myDb.getConsumApa(nrAp, "Aprilie", editTextGetAn_graficT.getText().toString()));
        myDb.close();
        float apa_mai = Float.parseFloat(myDb.getConsumApa(nrAp, "Mai", editTextGetAn_graficT.getText().toString()));
        myDb.close();
        float apa_iun = Float.parseFloat(myDb.getConsumApa(nrAp, "Iunie", editTextGetAn_graficT.getText().toString()));
        myDb.close();
        float apa_iul = Float.parseFloat(myDb.getConsumApa(nrAp, "Iulie", editTextGetAn_graficT.getText().toString()));
        myDb.close();
        float apa_aug = Float.parseFloat(myDb.getConsumApa(nrAp, "August", editTextGetAn_graficT.getText().toString()));
        myDb.close();
        float apa_sept = Float.parseFloat(myDb.getConsumApa(nrAp, "Septembrie", editTextGetAn_graficT.getText().toString()));
        myDb.close();
        float apa_oct = Float.parseFloat(myDb.getConsumApa(nrAp, "Octombrie", editTextGetAn_graficT.getText().toString()));
        myDb.close();
        float apa_nov = Float.parseFloat(myDb.getConsumApa(nrAp, "Noiembrie", editTextGetAn_graficT.getText().toString()));
        myDb.close();
        float apa_dec = Float.parseFloat(myDb.getConsumApa(nrAp, "Decembrie", editTextGetAn_graficT.getText().toString()));
        myDb.close();

        float curent_ian = Float.parseFloat(myDb.getConsumCurent(nrAp, "Ianuarie", editTextGetAn_graficT.getText().toString()));
        myDb.close();
        float curent_feb = Float.parseFloat(myDb.getConsumCurent(nrAp, "Februarie", editTextGetAn_graficT.getText().toString()));
        myDb.close();
        float curent_mar = Float.parseFloat(myDb.getConsumCurent(nrAp, "Martie", editTextGetAn_graficT.getText().toString()));
        myDb.close();
        float curent_apr = Float.parseFloat(myDb.getConsumCurent(nrAp, "Aprilie", editTextGetAn_graficT.getText().toString()));
        myDb.close();
        float curent_mai = Float.parseFloat(myDb.getConsumCurent(nrAp, "Mai", editTextGetAn_graficT.getText().toString()));
        myDb.close();
        float curent_iun = Float.parseFloat(myDb.getConsumCurent(nrAp, "Iunie", editTextGetAn_graficT.getText().toString()));
        myDb.close();
        float curent_iul = Float.parseFloat(myDb.getConsumCurent(nrAp, "Iulie", editTextGetAn_graficT.getText().toString()));
        myDb.close();
        float curent_aug = Float.parseFloat(myDb.getConsumCurent(nrAp, "August", editTextGetAn_graficT.getText().toString()));
        myDb.close();
        float curent_sept = Float.parseFloat(myDb.getConsumCurent(nrAp, "Septembrie", editTextGetAn_graficT.getText().toString()));
        myDb.close();
        float curent_oct = Float.parseFloat(myDb.getConsumCurent(nrAp, "Octombrie", editTextGetAn_graficT.getText().toString()));
        myDb.close();
        float curent_nov = Float.parseFloat(myDb.getConsumCurent(nrAp, "Noiembrie", editTextGetAn_graficT.getText().toString()));
        myDb.close();
        float curent_dec = Float.parseFloat(myDb.getConsumCurent(nrAp, "Decembrie", editTextGetAn_graficT.getText().toString()));
        myDb.close();

        float restanta_ian = Float.parseFloat(myDb.getConsumRestanta(nrAp, "Ianuarie", editTextGetAn_graficT.getText().toString()));
        myDb.close();
        float restanta_feb = Float.parseFloat(myDb.getConsumRestanta(nrAp, "Februarie", editTextGetAn_graficT.getText().toString()));
        myDb.close();
        float restanta_mar = Float.parseFloat(myDb.getConsumRestanta(nrAp, "Martie", editTextGetAn_graficT.getText().toString()));
        myDb.close();
        float restanta_apr = Float.parseFloat(myDb.getConsumRestanta(nrAp, "Aprilie", editTextGetAn_graficT.getText().toString()));
        myDb.close();
        float restanta_mai = Float.parseFloat(myDb.getConsumRestanta(nrAp, "Mai", editTextGetAn_graficT.getText().toString()));
        myDb.close();
        float restanta_iun = Float.parseFloat(myDb.getConsumRestanta(nrAp, "Iunie", editTextGetAn_graficT.getText().toString()));
        myDb.close();
        float restanta_iul = Float.parseFloat(myDb.getConsumRestanta(nrAp, "Iulie", editTextGetAn_graficT.getText().toString()));
        myDb.close();
        float restanta_aug = Float.parseFloat(myDb.getConsumRestanta(nrAp, "August", editTextGetAn_graficT.getText().toString()));
        myDb.close();
        float restanta_sept = Float.parseFloat(myDb.getConsumRestanta(nrAp, "Septembrie", editTextGetAn_graficT.getText().toString()));
        myDb.close();
        float restanta_oct = Float.parseFloat(myDb.getConsumRestanta(nrAp, "Octombrie", editTextGetAn_graficT.getText().toString()));
        myDb.close();
        float restanta_nov = Float.parseFloat(myDb.getConsumRestanta(nrAp, "Noiembrie", editTextGetAn_graficT.getText().toString()));
        myDb.close();
        float restanta_dec = Float.parseFloat(myDb.getConsumRestanta(nrAp, "Decembrie", editTextGetAn_graficT.getText().toString()));
        myDb.close();

        ArrayList<BarEntry> barEntries = new ArrayList<>();
        barEntries.add(new BarEntry(1, new float[]{indivizia_ian,nrPers_ian,apa_ian,curent_ian,restanta_ian}));
        barEntries.add(new BarEntry(2,new float[]{indivizia_feb,nrPers_feb,apa_feb,curent_feb,restanta_feb} ));
        barEntries.add(new BarEntry(3,new float[]{indivizia_mar,nrPers_mar,apa_mar,curent_mar,restanta_mar} ));
        barEntries.add(new BarEntry(4,new float[]{indivizia_apr,nrPers_apr,apa_apr,curent_apr,restanta_apr} ));
        barEntries.add(new BarEntry(5,new float[]{indivizia_mai,nrPers_mai,apa_mai,curent_mai,restanta_mai} ));
        barEntries.add(new BarEntry(6,new float[]{indivizia_iun,nrPers_iun,apa_iun,curent_iun,restanta_iun} ));
        barEntries.add(new BarEntry(7,new float[]{indivizia_iul,nrPers_iul,apa_iul,curent_iul,restanta_iul} ));
        barEntries.add(new BarEntry(8,new float[]{indivizia_aug,nrPers_aug,apa_aug,curent_aug,restanta_aug} ));
        barEntries.add(new BarEntry(9,new float[]{indivizia_sept,nrPers_sept,apa_sept,curent_sept,restanta_sept} ));
        barEntries.add(new BarEntry(10,new float[]{indivizia_oct,nrPers_oct,apa_oct,curent_oct,restanta_oct} ));
        barEntries.add(new BarEntry(11,new float[]{indivizia_nov,nrPers_nov,apa_nov,curent_nov,restanta_nov} ));
        barEntries.add(new BarEntry(12,new float[]{indivizia_dec,nrPers_dec,apa_dec,curent_dec,restanta_dec} ));
        return barEntries;
    }

    public int getPrevAp() {
        String app = textViewNrApartament_graficT.getText().toString();
        int intApp = Integer.parseInt(app);
        if (intApp > 1)
            intApp = intApp - 1;
        else intApp = myDb.countApartamente();
        myDb.close();
        return intApp;
    }

    public int getNextAp() {
        String app2 = textViewNrApartament_graficT.getText().toString();
        int intApp2 = Integer.parseInt(app2);
        if (intApp2 < myDb.countApartamente())
            intApp2 = intApp2 + 1;
        else intApp2 = 1;
        myDb.close();
        return intApp2;
    }

    public void nextAp(View v) {
        textViewNrApartament_graficT.setText(Integer.toString(getNextAp()));
        myDb.close();
    }

    public void prevAp(View v) {
        textViewNrApartament_graficT.setText(Integer.toString(getPrevAp()));
        myDb.close();
    }
}
