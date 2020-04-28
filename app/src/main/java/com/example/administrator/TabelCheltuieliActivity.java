package com.example.administrator;

import android.content.ContentResolver;
import android.content.Intent;
import android.database.CharArrayBuffer;
import android.database.ContentObserver;
import android.database.Cursor;
import android.database.DataSetObserver;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.adapter.AdapterCheltuieli;
import com.example.administrator.adapter.ApartamentAdapter;
import com.example.administrator.claseEntity.Apartament;
import com.example.administrator.claseEntity.Intretinere;

import java.util.ArrayList;

import database.DatabaseHelper;

public class TabelCheltuieliActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    DatabaseHelper myDb;
    ListView listViewIntretinereLista;
    ArrayList<Intretinere> arrayList;
    AdapterCheltuieli myAdapter;
    Button buttonOkTabelCheltuieli,buttonLupa,buttonInchide;
    Button buttonInfoTabelCheltuieli;
    EditText editTextAnTabelCheltuieli;
    Spinner spinnerLuniTabelCheltuieli;
    LinearLayout linY;
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
        setContentView(R.layout.activity_tabel_cheltuieli);
        myDb= new DatabaseHelper(this);
        linY=(LinearLayout)findViewById(R.id.linY);
        listViewIntretinereLista=(ListView)findViewById(R.id.listViewIntretinereLista) ;
        arrayList=new ArrayList<>();
        buttonInfoTabelCheltuieli=(Button)findViewById(R.id.buttonInfoTabelCheltuieli);
        openInfoTabelCheltuieli();
        spinnerLuniTabelCheltuieli=(Spinner)findViewById(R.id.spinnerLuniTabelCheltuieli);
        ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(this,R.array.luni,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerLuniTabelCheltuieli.setAdapter(adapter);
        spinnerLuniTabelCheltuieli.setOnItemSelectedListener(this);
        buttonLupa=(Button)findViewById(R.id.buttonLupa);
        buttonLupa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linY.setVisibility(View.VISIBLE);
                buttonLupa.setVisibility(View.GONE);
            }
        });

        editTextAnTabelCheltuieli=(EditText)findViewById(R.id.editTextAnTabelCheltuieli);
        buttonOkTabelCheltuieli=(Button)findViewById(R.id.buttonOkTabelCheltuieli);
        buttonOkTabelCheltuieli.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(editTextAnTabelCheltuieli.length()==0)
                    editTextAnTabelCheltuieli.setError("Introduceti anul");
                else
                    {
                        String luna=spinnerLuniTabelCheltuieli.getSelectedItem().toString();
                        String an=editTextAnTabelCheltuieli.getText().toString();
                        loadDataInListView(luna,an);
                    }

            }
        });
        buttonInchide=(Button)findViewById(R.id.buttonInchide);
        buttonInchide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadAllDataInListView();
                linY.setVisibility(View.GONE);
                buttonLupa.setVisibility(View.VISIBLE);

            }
        });
        loadAllDataInListView();
    }
    private void loadDataInListView(String luna,String an) {
        //arrayList=myDb.getAllIntretinereArray();
       arrayList=myDb.getAllIntretinereArrayLaData(luna,an);
        myAdapter= new AdapterCheltuieli(this,arrayList);
        listViewIntretinereLista.setAdapter(myAdapter);
        listViewIntretinereLista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(getApplicationContext(),DialogAlertIntretinereTable.class);
                int nrAp=arrayList.get(position).getNr_ap();
                String indivizia=arrayList.get(position).getCotaIndivizia();
                String cotaPers=arrayList.get(position).getCotaNrPersoane();
                String apa=arrayList.get(position).getApa();
                String curent=arrayList.get(position).getEnergieElectrica();
                String total=arrayList.get(position).getTotal();
                String restanta=arrayList.get(position).getRestanta();
                String luna=arrayList.get(position).getDataLuna();
                String an=arrayList.get(position).getDataAn();
                String status=arrayList.get(position).getStatusIntretinere();

                intent.putExtra("nrAp_Intretinere",nrAp+"");
                intent.putExtra("indivizia_Intretinere",indivizia);
                intent.putExtra("cotaPers_Intretinere",cotaPers);
                intent.putExtra("apa_Intretinere",apa);
                intent.putExtra("curent_Intretinere",curent);
                intent.putExtra("total_Intretinere",total);
                intent.putExtra("restanta_Intretinere",restanta);
                intent.putExtra("luna_Intretinere",luna);
                intent.putExtra("an_Intretinere",an);
                intent.putExtra("status_Intretinere",status);
                startActivity(intent);
            }
        });

        myAdapter.notifyDataSetChanged();
    }


    private void loadAllDataInListView() {
        //arrayList=myDb.getAllIntretinereArray();   //intretinerea asa cum e ea in baza de date
        arrayList=myDb.getAllIntretinereOrdonata();
        myAdapter= new AdapterCheltuieli(this,arrayList);
        listViewIntretinereLista.setAdapter(myAdapter);
        listViewIntretinereLista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(getApplicationContext(),DialogAlertIntretinereTable.class);
                int nrAp=arrayList.get(position).getNr_ap();
                String indivizia=arrayList.get(position).getCotaIndivizia();
                String cotaPers=arrayList.get(position).getCotaNrPersoane();
                String apa=arrayList.get(position).getApa();
                String curent=arrayList.get(position).getEnergieElectrica();
                String total=arrayList.get(position).getTotal();
                String restanta=arrayList.get(position).getRestanta();
                String luna=arrayList.get(position).getDataLuna();
                String an=arrayList.get(position).getDataAn();
                String status=arrayList.get(position).getStatusIntretinere();

                intent.putExtra("nrAp_Intretinere",nrAp+"");
                intent.putExtra("indivizia_Intretinere",indivizia);
                intent.putExtra("cotaPers_Intretinere",cotaPers);
                intent.putExtra("apa_Intretinere",apa);
                intent.putExtra("curent_Intretinere",curent);
                intent.putExtra("total_Intretinere",total);
                intent.putExtra("restanta_Intretinere",restanta);
                intent.putExtra("luna_Intretinere",luna);
                intent.putExtra("an_Intretinere",an);
                intent.putExtra("status_Intretinere",status);
                startActivity(intent);
            }
        });

        myAdapter.notifyDataSetChanged();
    }

    //------------show info-----------------------------

    public void openInfoTabelCheltuieli(){
        buttonInfoTabelCheltuieli.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message="1) La apăsarea pe o celulă a tabelului se va deschide o pagină nouă, unde sunt afișate detaliile " +
                        "despre întreținerea respectivă.\n      -Se pot modifica datele prin recompletarea câmpurilor și apăsarea butonului 'Modifica', " +
                        "sau se poate șterge această întreținere prin apăsarea butonului 'Sterge'.\n" +
                        "       -Pentru revenirea la pagina principală, apasați butonul 'X'.\n" +
                        "\n2) Pentru găsirea informațiilor de la o anumită dată apăsați butonul în formă de lupă.\n" +
                        "       -Selectați luna, introduceți anul și apăsați butonul 'OK'.\n" +
                        "       -Pentru anularea filtrului de căutare apăsați butonul 'X'."
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
