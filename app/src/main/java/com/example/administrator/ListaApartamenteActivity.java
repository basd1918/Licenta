package com.example.administrator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.administrator.adapter.ApartamentAdapter;
import com.example.administrator.claseEntity.Apartament;

import java.util.ArrayList;

import database.DatabaseHelper;

public class ListaApartamenteActivity extends AppCompatActivity {
    ListView listViewApartamenteLista;
    DatabaseHelper mydb;
    ArrayList<Apartament> arrayList;
    ApartamentAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_apartamente);
        mydb=new DatabaseHelper(this);
        listViewApartamenteLista=(ListView)findViewById(R.id.listViewApartamenteLista);
        arrayList=new ArrayList<>();
        loadDataInListView();
    }

    private void loadDataInListView() {
        arrayList=mydb.getAllApartamente();
        myAdapter= new ApartamentAdapter(this,arrayList);
        listViewApartamenteLista.setAdapter(myAdapter);
        listViewApartamenteLista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(getApplicationContext(),DialogAlertApartamenteDetail.class);
                int nrAp=arrayList.get(position).getNr_ap();
                String proprietar=arrayList.get(position).getNume();
                String email=arrayList.get(position).getEmail();
                String suprafata=arrayList.get(position).getSuprafata();
                int nrPers=arrayList.get(position).getNr_pers();

                intent.putExtra("nrAp",nrAp+"");
                intent.putExtra("proprietar",proprietar);
                intent.putExtra("email",email);
                intent.putExtra("suprafata",suprafata);
                intent.putExtra("nrPers",nrPers+"");
                startActivity(intent);
            }
        });

        myAdapter.notifyDataSetChanged();
    }
}
