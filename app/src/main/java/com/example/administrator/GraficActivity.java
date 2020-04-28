package com.example.administrator;

import android.content.Intent;
import android.graphics.Color;
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

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;

import java.util.ArrayList;

import database.DatabaseHelper;

public class GraficActivity extends AppCompatActivity {

    private TextView textViewIanuarie, textViewFebruarie, textViewMartie, textViewAprilie, textViewMai, textViewIunie;
    private TextView textViewIulie, textViewAugust, textViewSeptembrie, textViewOctombrie, textViewNoiembrie, textViewDecembrie;
    private EditText editTextGetAn_grafic;
    private TextView textViewConsumApa_grafic, textViewNrApartament_grafic;
    private Button buttonPrev_grafic, buttonNext_grafic,buttonVeziCheltTotaleGrafic;
    DatabaseHelper myDb;
    BarChart barChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grafic);
        myDb = new DatabaseHelper(this);

        buttonVeziCheltTotaleGrafic=(Button)findViewById(R.id.buttonVeziCheltTotaleGrafic);
        buttonVeziCheltTotaleGrafic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GraficActivity.this, GraficCheltuieliTotale.class);
                startActivity(intent);
            }
        });
        buttonPrev_grafic = (Button) findViewById(R.id.buttonPrev_grafic);
        buttonNext_grafic = (Button) findViewById(R.id.buttonNext_grafic);
        buttonNext_grafic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextAp(v);
                apelGrafic(textViewNrApartament_grafic.getText().toString());
            }
        });
        buttonPrev_grafic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                prevAp(v);
                apelGrafic(textViewNrApartament_grafic.getText().toString());
            }
        });

        textViewNrApartament_grafic = (TextView) findViewById(R.id.textViewNrApartament_grafic);
        textViewNrApartament_grafic.setText("1");
        apelGrafic("1");

    }

    public void apelGrafic(String nrAp) {
        editTextGetAn_grafic = (EditText) findViewById(R.id.editTextGetAn_grafic);
        barChart = (BarChart) findViewById(R.id.bargraph);
        barChart.setDrawBarShadow(false);
        barChart.setDrawValueAboveBar(true);
        barChart.setMaxVisibleValueCount(3);
        barChart.setPinchZoom(false);
        barChart.setDrawGridBackground(true);
        barChart.setDragEnabled(true);


        BarDataSet barDataSet = new BarDataSet(getv(nrAp), "Consum apa");
        barDataSet.setColors(Color.BLUE);
        BarData data = new BarData(barDataSet);
        data.setBarWidth(0.9f);
        String[] zi = new String[]{"", "Ian.", "Feb.", "Mar.", "Apr.", "Mai", "Iun.", "Iul.", "Aug.", "Sept.", "Oct.", "Nov.", "Dec."};
        XAxis xAxis = barChart.getXAxis();
        xAxis.setValueFormatter(new IndexAxisValueFormatter(zi));
        barChart.setData(data);
        barChart.invalidate();
    }

    private ArrayList<BarEntry> getv(String nrAp) {
        String consumIanuarie = myDb.getConsumApa(nrAp, "Ianuarie", editTextGetAn_grafic.getText().toString());
        String consumFebruarie = myDb.getConsumApa(nrAp, "Februarie", editTextGetAn_grafic.getText().toString());
        String consumMartie = myDb.getConsumApa(nrAp, "Martie", editTextGetAn_grafic.getText().toString());
        String consumAprilie = myDb.getConsumApa(nrAp, "Aprilie", editTextGetAn_grafic.getText().toString());
        String consumMai = myDb.getConsumApa(nrAp, "Mai", editTextGetAn_grafic.getText().toString());
        String consumIunie = myDb.getConsumApa(nrAp, "Iunie", editTextGetAn_grafic.getText().toString());
        String consumIulie = myDb.getConsumApa(nrAp, "Iulie", editTextGetAn_grafic.getText().toString());
        String consumAugust = myDb.getConsumApa(nrAp, "August", editTextGetAn_grafic.getText().toString());
        String consumSeptembrie = myDb.getConsumApa(nrAp, "Septembrie", editTextGetAn_grafic.getText().toString());
        String consumOctombrie = myDb.getConsumApa(nrAp, "Octombrie", editTextGetAn_grafic.getText().toString());
        String consumNoiembrie = myDb.getConsumApa(nrAp, "Noiembrie", editTextGetAn_grafic.getText().toString());
        String consumDecembrie = myDb.getConsumApa(nrAp, "Decembrie", editTextGetAn_grafic.getText().toString());

        float ian = Float.parseFloat(consumIanuarie);
        float feb = Float.parseFloat(consumFebruarie);
        float mar = Float.parseFloat(consumMartie);
        float apr = Float.parseFloat(consumAprilie);
        float mai = Float.parseFloat(consumMai);
        float iun = Float.parseFloat(consumIunie);
        float iul = Float.parseFloat(consumIulie);
        float aug = Float.parseFloat(consumAugust);
        float sept = Float.parseFloat(consumSeptembrie);
        float oct = Float.parseFloat(consumOctombrie);
        float nov = Float.parseFloat(consumNoiembrie);
        float dec = Float.parseFloat(consumDecembrie);

        ArrayList<BarEntry> barEntries = new ArrayList<>();
        barEntries.add(new BarEntry(1, ian));
        barEntries.add(new BarEntry(2, feb));
        barEntries.add(new BarEntry(3, mar));
        barEntries.add(new BarEntry(4, apr));
        barEntries.add(new BarEntry(5, mai));
        barEntries.add(new BarEntry(6, iun));
        barEntries.add(new BarEntry(7, iul));
        barEntries.add(new BarEntry(8, aug));
        barEntries.add(new BarEntry(9, sept));
        barEntries.add(new BarEntry(10, oct));
        barEntries.add(new BarEntry(11, nov));
        barEntries.add(new BarEntry(12, dec));
        return barEntries;
    }


    public int getPrevAp() {
        String app = textViewNrApartament_grafic.getText().toString();
        int intApp = Integer.parseInt(app);
        if (intApp > 1)
            intApp = intApp - 1;
        else intApp = myDb.countApartamente();
        return intApp;
    }

    public int getNextAp() {
        String app2 = textViewNrApartament_grafic.getText().toString();
        int intApp2 = Integer.parseInt(app2);
        if (intApp2 < myDb.countApartamente())
            intApp2 = intApp2 + 1;
        else intApp2 = 1;
        return intApp2;
    }

    public void nextAp(View v) {
        textViewNrApartament_grafic.setText(Integer.toString(getNextAp()));
//        apelGrafic(textViewNrApartament_grafic.getText().toString());

    }

    public void prevAp(View v) {
        textViewNrApartament_grafic.setText(Integer.toString(getPrevAp()));
  //      apelGrafic(textViewNrApartament_grafic.getText().toString());

    }


}
