package com.example.administrator.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

import com.example.administrator.R;
import com.example.administrator.claseEntity.Apartament;

public class ApartamentAdapter extends BaseAdapter {

    Context context;
    ArrayList<Apartament> arrayList;
    public ApartamentAdapter(Context context, ArrayList<Apartament>arrayList)
    {
        this.context=context;
        this.arrayList=arrayList;
    }
    @Override
    public long getItemId(int position){
        return position;
    }
    @Override
    public Object getItem(int position){
        return arrayList.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView= inflater.inflate(R.layout.apartamente_lista_adapter,null);

        TextView textViewProprietar=(TextView)convertView.findViewById(R.id.tvNume2);
        TextView textViewNrPers=(TextView)convertView.findViewById(R.id.tvNrPers2);
        TextView textViewNrApartament=(TextView)convertView.findViewById(R.id.tvApartament);

        Apartament apartament=arrayList.get(position);
        textViewProprietar.setText(apartament.getNume());
        textViewNrPers.setText(apartament.getNr_pers()+"");
        textViewNrApartament.setText(apartament.getNr_ap()+"");

        return convertView;
    }

    @Override
    public int getCount() {
        return this.arrayList.size();
    }

}
