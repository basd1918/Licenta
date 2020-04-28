package com.example.administrator.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.administrator.R;
import com.example.administrator.claseEntity.Apartament;
import com.example.administrator.claseEntity.Intretinere;

import java.util.ArrayList;

public class AdapterCheltuieli extends BaseAdapter{
    Context context;
    ArrayList<Intretinere> arrayList;
    public AdapterCheltuieli(Context context, ArrayList<Intretinere>arrayList)
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
        convertView= inflater.inflate(R.layout.intretinere_lista_adapter,null);

        TextView tvApartament_Cheltuieli=(TextView)convertView.findViewById(R.id.tvApartament_Cheltuieli);
        TextView tvTotal2=(TextView)convertView.findViewById(R.id.tvTotal2);
        TextView tvRestanta2=(TextView)convertView.findViewById(R.id.tvRestanta2);
        TextView tvLuna2=(TextView)convertView.findViewById(R.id.tvLuna2);
        TextView tvAn2=(TextView)convertView.findViewById(R.id.tvAn2);

        Intretinere intretinere=arrayList.get(position);
        tvApartament_Cheltuieli.setText(intretinere.getNr_ap()+"");
        tvTotal2.setText(intretinere.getTotal());
        tvRestanta2.setText(intretinere.getRestanta());
        tvLuna2.setText(intretinere.getDataLuna());
        tvAn2.setText(intretinere.getDataAn());
        return convertView;
    }

    @Override
    public int getCount() {
        return this.arrayList.size();
    }
}