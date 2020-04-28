package database;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.view.View;
import android.widget.TextView;

import com.example.administrator.claseEntity.Apartament;
import com.example.administrator.claseEntity.Cheltuieli;
import com.example.administrator.claseEntity.Intretinere;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final  String DATABASE_NAME="Apartament.db";
    public static final  String TABLE_NAME="apartament_table";
    public static final  String TABLE_NAME_Intretinere="intretinere_table";
    public static final  String TABLE_NAME_cheltuieliAsociatie="cheltuieliAdministrative_table";
    public static final  String COL_1="Nr_ap";
    public static final  String COL_2="Nume";
    public static final  String COL_3="Email";
    public static final  String COL_4="Suprafata";
    public static final  String COL_5="Nr_pers";

    public static final  String COLO_0="nr_ap";
    public static final  String COLO_1="cotaIndivizia";
    public static final  String COLO_2="cotaNrPersoane";
    public static final  String COLO_3="apa";
    public static final  String COLO_4="energieElectrica";
    public static final  String COLO_5="total";
    public static final  String COLO_6="restanta";
    public static final  String COLO_7="dataLuna";
    public static final  String COLO_8="dataAn";
    public static final String COLO_9="statusIntretinere";

    public static final  String COLOMN_0="pretApa";
    public static final  String COLOMN_1="cheltAdministrative";
    public static final  String COLOMN_2="salubritate";
    public static final  String COLOMN_3="energieElectricaHol";
    public static final  String COLOMN_4="salariuAdmin";
    public static final  String COLOMN_5="intretinereAscensor";
    public static final  String COLOMN_6="curatenieScara";
    public static final  String COLOMN_7="luna";
    public static final  String COLOMN_8="an";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + "( Nr_ap INTEGER PRIMARY KEY , Nume text , Email text , Suprafata text , Nr_pers integer )");
        db.execSQL("create table "+ TABLE_NAME_Intretinere + "( Nr_ap INTEGER, cotaIndivizia text , cotaNrPersoane text , apa text, energieElectrica text , total text ,restanta text, dataLuna text, dataAn text,statusIntretinere,primary key(Nr_ap,dataLuna,dataAn));");
        db.execSQL("create table "+ TABLE_NAME_cheltuieliAsociatie + "(pretApa text , cheltAdministrative text , salubritate text, energieElectricaHol text,salariuAdmin text , intretinereAscensor text ,curatenieScara text, luna text, an text,primary key(luna,an));");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(" drop table if exists " + TABLE_NAME);
        db.execSQL(" drop table if exists "+ TABLE_NAME_Intretinere);
        db.execSQL(" drop table if exists " + TABLE_NAME_cheltuieliAsociatie);
        onCreate(db);
    }

    public  boolean insertData(String Nr_ap, String Nume,String Email, String Suprafata, String Nr_pers){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put(COL_1, Nr_ap);
        contentValues.put(COL_2, Nume);
        contentValues.put(COL_3, Email);
        contentValues.put(COL_4, Suprafata);
        contentValues.put(COL_5, Nr_pers);
        long result=db.insert(TABLE_NAME,null,contentValues);
        if(result==-1)
            return  false;
        else
                return true;
    }

   public  boolean insertIntretinere(String Nr_ap,String cotaIndivizia, String cotaNrPersoane, String apa , String energieElectrica,String total,String restanta,String dataLuna, String dataAn,String statusIntretinere){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put(COLO_0, Nr_ap);
        contentValues.put(COLO_1, cotaIndivizia);
        contentValues.put(COLO_2, cotaNrPersoane);
        contentValues.put(COLO_3, apa);
        contentValues.put(COLO_4, energieElectrica);
        contentValues.put(COLO_5, total);
        contentValues.put(COLO_6, restanta);
        contentValues.put(COLO_7, dataLuna);
        contentValues.put(COLO_8, dataAn);
        contentValues.put(COLO_9,statusIntretinere);
        long result=db.insert(TABLE_NAME_Intretinere,null,contentValues);
        if(result==-1)
            return  false;
        else
            return true;
    }



    public  boolean insertCheltuieliAsociatie(String pretApa, String cheltAdmin, String salubritate , String curentHol,String salariuAdmin,String lift,String curatenie,String luna, String an){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put(COLOMN_0, pretApa);
        contentValues.put(COLOMN_1, cheltAdmin);
        contentValues.put(COLOMN_2, salubritate);
        contentValues.put(COLOMN_3, curentHol);
        contentValues.put(COLOMN_4, salariuAdmin);
        contentValues.put(COLOMN_5, lift);
        contentValues.put(COLOMN_6, curatenie);
        contentValues.put(COLOMN_7, luna);
        contentValues.put(COLOMN_8, an);

        long result=db.insert(TABLE_NAME_cheltuieliAsociatie,null,contentValues);
        if(result==-1)
            return  false;
        else
            return true;
    }

   public int adaugaRestanta(String valoareRestanta,String ap,String luna,String an){
       //UPDATE 'intretinere_table' SET restanta =valoare WHERE Nr_ap=ap and dataLuna=luna and dataAn=an
       SQLiteDatabase db=this.getWritableDatabase();
       ContentValues contentValues=new ContentValues();
       contentValues.put(DatabaseHelper.COLO_6,valoareRestanta);
       String[]whereArgs={ap,luna,an};
       int count=db.update(DatabaseHelper.TABLE_NAME_Intretinere,contentValues,DatabaseHelper.COLO_0 + " =? and "+DatabaseHelper.COLO_7 +"=? and "+DatabaseHelper.COLO_8+"=?",whereArgs);
       return count;
   }


    public int statusAchita(String ap,String luna,String an,String status){
        //UPDATE 'intretinere_table' SET status =valoare WHERE Nr_ap=ap and dataLuna=luna and dataAn=an
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(DatabaseHelper.COLO_9,status);
        String[]whereArgs={ap,luna,an};
        int count=db.update(DatabaseHelper.TABLE_NAME_Intretinere,contentValues,DatabaseHelper.COLO_0 + " =? and "+DatabaseHelper.COLO_7 +"=? and "+DatabaseHelper.COLO_8+"=?",whereArgs);
        return count;
    }

    public Cursor getAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res= db.rawQuery("select * from " + TABLE_NAME,null);
        return res;
    }

    public int countApartamente(){
        int nr=0;
        String sql=" SELECT COUNT(*) FROM " + TABLE_NAME;
        Cursor cursor=getReadableDatabase().rawQuery(sql,null);
        while( cursor.moveToNext()){
            nr=cursor.getInt(0);
        }
        cursor.close();
        return nr;
    }

    public int countApartamente2(){
        int nr=0;
        String sql=" SELECT COUNT(*) FROM " + TABLE_NAME;
        Cursor cursor=getReadableDatabase().rawQuery(sql,null);
        if(cursor.getCount()>0){
            cursor.moveToNext();
            nr=cursor.getInt(0);
        }
        cursor.close();
        return nr;
    }

    public String getNumeProprietarApartament(String ap){
        String numeP="0";
        String sql=" SELECT Nume FROM "+TABLE_NAME+" where Nr_ap="+"'"+ap+"'";
        Cursor cursor=getReadableDatabase().rawQuery(sql,null);
        if(cursor.getCount()>0){
            cursor.moveToNext();
            numeP=cursor.getString(0);
        }
        return numeP;
    }

    public String getSuprafataApartament(String ap){
        String suprafata="0";
        String sql=" SELECT Suprafata FROM "+TABLE_NAME+" where Nr_ap="+"'"+ap+"'";
        Cursor cursor=getReadableDatabase().rawQuery(sql,null);
        if(cursor.getCount()>0){
            cursor.moveToNext();
            suprafata=cursor.getString(0);
        }
        return suprafata;
    }


    public int getNrPersoaneApartament(String ap){
        int nrPers=0;
        String sql=" SELECT Nr_pers FROM "+TABLE_NAME+" where Nr_ap="+"'"+ap+"'";
        Cursor cursor=getReadableDatabase().rawQuery(sql,null);
        if(cursor.getCount()>0){
            cursor.moveToNext();
            nrPers=cursor.getInt(0);
        }
        return nrPers;
    }


    public String getTotalIntretinere(String ap, String luna, String an){
        String total="0";
        String sql=" SELECT total FROM "+TABLE_NAME_Intretinere+" where Nr_ap="+"'"+ap+"'"+" and dataLuna="+"'"+luna+"'" +" and dataAn="+"'"+an+"'";
        Cursor cursor=getReadableDatabase().rawQuery(sql,null);
        if(cursor.getCount()>0){
            cursor.moveToNext();
            total=cursor.getString(0);
        }
        return total;
    }

    public String getRestantaIntretinere(String ap, String luna, String an){
        String total="0";
        String sql=" SELECT restanta FROM "+TABLE_NAME_Intretinere+" where Nr_ap="+"'"+ap+"'"+" and dataLuna="+"'"+luna+"'" +" and dataAn="+"'"+an+"'";
        Cursor cursor=getReadableDatabase().rawQuery(sql,null);
        if(cursor.getCount()>0){
            cursor.moveToNext();
            total=cursor.getString(0);
        }
        return total;
    }

   public String getStatusPlata(String ap, String luna, String an){
        String status="status";
        String sql=" SELECT statusIntretinere FROM "+TABLE_NAME_Intretinere+" where Nr_ap="+"'"+ap+"'"+" and dataLuna="+"'"+luna+"'" +" and dataAn="+"'"+an+"'";
        Cursor cursor=getReadableDatabase().rawQuery(sql,null);
        if(cursor.getCount()>0){
            cursor.moveToNext();
            status=cursor.getString(0);
        }
        return status;
    }

    public  boolean updateData(String Nr_ap, String Nume,String Email, String Suprafata, String Nr_pers){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put(COL_1, Nr_ap);
        contentValues.put(COL_2, Nume);
        contentValues.put(COL_3, Email);
        contentValues.put(COL_4, Suprafata);
        contentValues.put(COL_5, Nr_pers);
        long result=db.update(TABLE_NAME, contentValues,"Nr_ap = ?",new String[]{Nr_ap});
        return true;

    }

    public  boolean updateheltuieliAsociatie(String pretApa, String cheltAdmin, String salubritate , String curentHol,String salariuAdmin,String lift,String curatenie,String luna, String an){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put(COLOMN_0, pretApa);
        contentValues.put(COLOMN_1, cheltAdmin);
        contentValues.put(COLOMN_2, salubritate);
        contentValues.put(COLOMN_3, curentHol);
        contentValues.put(COLOMN_4, salariuAdmin);
        contentValues.put(COLOMN_5, lift);
        contentValues.put(COLOMN_6, curatenie);
        contentValues.put(COLOMN_7, luna);
        contentValues.put(COLOMN_8, an);
        long result=db.update(TABLE_NAME_cheltuieliAsociatie,contentValues,"luna=? and an=?",new String[]{luna,an});
        return true;
    }

    public  boolean updateIntretinere(String Nr_ap,String cotaIndivizia, String cotaNrPersoane, String apa , String energieElectrica,String total,String restanta,String dataLuna, String dataAn,String statusIntretinere){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put(COLO_0, Nr_ap);
        contentValues.put(COLO_1, cotaIndivizia);
        contentValues.put(COLO_2, cotaNrPersoane);
        contentValues.put(COLO_3, apa);
        contentValues.put(COLO_4, energieElectrica);
        contentValues.put(COLO_5, total);
        contentValues.put(COLO_6, restanta);
        contentValues.put(COLO_7, dataLuna);
        contentValues.put(COLO_8, dataAn);
        contentValues.put(COLO_9,statusIntretinere);
        long result=db.update(TABLE_NAME_Intretinere,contentValues,"nr_ap=? and dataLuna=? and dataAn=?",new String[]{Nr_ap,dataLuna,dataAn});
        if(result==-1)
            return  false;
        else
            return true;
    }

    public Integer deleteIntretinere(String Nr_ap, String luna,String an){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME_Intretinere,COLO_0+"=? and "+COLO_7+"=? and "+COLO_8+"=?",new String[]{Nr_ap, luna,an});
    }

    public Integer deleteData(String Nr_ap, String Nume){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME,"Nr_ap=? and Nume=?",new String[]{Nr_ap, Nume});
    }

    //--------------------------grafic apa---------------------
    public String getConsumApa(String ap, String luna, String an){
            String apa="0.0";
            String sql=" SELECT apa FROM "+TABLE_NAME_Intretinere+" where Nr_ap="+"'"+ap+"'"+" and dataLuna="+"'"+luna+"'" +" and dataAn="+"'"+an+"'";
            Cursor cursor=getReadableDatabase().rawQuery(sql,null);
            if(cursor.getCount()>0){
                cursor.moveToNext();
                apa=cursor.getString(0);
            }
            return apa;
        }



        //========================grafic cheltuieli totale=====================================


    public ArrayList<Intretinere> getAllIntretinereArrayGrafcTotal(String ap,String luna, String an)
    {
        ArrayList<Intretinere> arrayList=new ArrayList<>();
        SQLiteDatabase db= this.getReadableDatabase();
        Cursor cursor= db.rawQuery(" SELECT * FROM "+TABLE_NAME_Intretinere+" where dataLuna="+"'"+luna+"'" +" and dataAn="+"'"+an+"'",null);
        while (cursor.moveToNext())
        {
            int nrAp=cursor.getInt(0);
            String indivizia=cursor.getString(1);
            String cotaPers=cursor.getString(2);
            String apa=cursor.getString(3);
            String curent=cursor.getString(4);
            String total=cursor.getString(5);
            String restanta=cursor.getString(6);
            String lun=cursor.getString(7);
            String aan=cursor.getString(8);
            String status=cursor.getString(9);
            Intretinere intretinere=new Intretinere(nrAp,indivizia,cotaPers,apa,curent,total,restanta,lun,aan,status);
            arrayList.add(intretinere);

        }
        return arrayList;
    }
//folosesc functia de sus pt ca e mai rapid asa

    public String getConsumCotaIndivizia(String ap, String luna, String an){
        String consum="0.0";
        String sql=" SELECT cotaIndivizia FROM "+TABLE_NAME_Intretinere+" where Nr_ap="+"'"+ap+"'"+" and dataLuna="+"'"+luna+"'" +" and dataAn="+"'"+an+"'";
        Cursor cursor=getReadableDatabase().rawQuery(sql,null);
        if(cursor.getCount()>0){
            cursor.moveToNext();
            consum=cursor.getString(0);
        }
        return consum;
    }

    public String getConsumNrPers(String ap, String luna, String an){
        String consum="0.0";
        String sql=" SELECT cotaNrPersoane FROM "+TABLE_NAME_Intretinere+" where Nr_ap="+"'"+ap+"'"+" and dataLuna="+"'"+luna+"'" +" and dataAn="+"'"+an+"'";
        Cursor cursor=getReadableDatabase().rawQuery(sql,null);
        if(cursor.getCount()>0){
            cursor.moveToNext();
            consum=cursor.getString(0);
        }
        return consum;
    }

    public String getConsumCurent(String ap, String luna, String an){
        String consum="0.0";
        String sql=" SELECT energieElectrica FROM "+TABLE_NAME_Intretinere+" where Nr_ap="+"'"+ap+"'"+" and dataLuna="+"'"+luna+"'" +" and dataAn="+"'"+an+"'";
        Cursor cursor=getReadableDatabase().rawQuery(sql,null);
        if(cursor.getCount()>0){
            cursor.moveToNext();
            consum=cursor.getString(0);
        }
        return consum;
    }

    public String getConsumRestanta(String ap, String luna, String an){
        String consum="0.0";
        String sql=" SELECT restanta FROM "+TABLE_NAME_Intretinere+" where Nr_ap="+"'"+ap+"'"+" and dataLuna="+"'"+luna+"'" +" and dataAn="+"'"+an+"'";
        Cursor cursor=getReadableDatabase().rawQuery(sql,null);
        if(cursor.getCount()>0){
            cursor.moveToNext();
            consum=cursor.getString(0);
        }
        return consum;
    }
    //==================================================================================


        //--------------------------adapterApartamente------------

        public ArrayList<Apartament> getAllApartamente()
        {
            ArrayList<Apartament> arrayList=new ArrayList<>();
            SQLiteDatabase db= this.getReadableDatabase();
            Cursor cursor= db.rawQuery("Select * from apartament_table order by Nr_ap",null);

            while (cursor.moveToNext())
            {
                int nrAp=cursor.getInt(0);
                String proprietar=cursor.getString(1);
                String email=cursor.getString(2);
                String suprafata=cursor.getString(3);
                int nrPers=cursor.getInt(4);
                Apartament apartament=new Apartament(nrAp,proprietar,email,suprafata,nrPers);
                arrayList.add(apartament);

            }
            return arrayList;
        }

        //---------------------------------filtrari---------
        public Cursor getAllIntretinere(){
            SQLiteDatabase db = this.getWritableDatabase();
            Cursor res= db.rawQuery("select * from " +TABLE_NAME_Intretinere ,null);
            return res;
        }

    public Cursor getAllCheltuieliDATA_OrdDupaNrAp(String luna, String an){
        String sql=" SELECT * FROM "+TABLE_NAME_Intretinere+" where dataLuna="+"'"+luna+"'" +" and dataAn="+"'"+an+"'" +" ORDER BY Nr_ap";
        Cursor cursor=getReadableDatabase().rawQuery(sql,null);
        return cursor;
    }

    //---adapter intretinere-----
    public ArrayList<Intretinere> getAllIntretinereArray()
    {
        ArrayList<Intretinere> arrayList=new ArrayList<>();
        SQLiteDatabase db= this.getReadableDatabase();
        Cursor cursor= db.rawQuery("Select * from "+TABLE_NAME_Intretinere,null);
        while (cursor.moveToNext())
        {
            int nrAp=cursor.getInt(0);
            String indivizia=cursor.getString(1);
            String cotaPers=cursor.getString(2);
            String apa=cursor.getString(3);
            String curent=cursor.getString(4);
            String total=cursor.getString(5);
            String restanta=cursor.getString(6);
            String luna=cursor.getString(7);
            String an=cursor.getString(8);
            String status=cursor.getString(9);
            Intretinere intretinere=new Intretinere(nrAp,indivizia,cotaPers,apa,curent,total,restanta,luna,an,status);
            arrayList.add(intretinere);

        }
        return arrayList;
    }


    public ArrayList<Intretinere> getAllIntretinereArrayLaData(String luna, String an)
    {
        ArrayList<Intretinere> arrayList=new ArrayList<>();
        SQLiteDatabase db= this.getReadableDatabase();
        Cursor cursor= db.rawQuery(" SELECT * FROM "+TABLE_NAME_Intretinere+" where dataLuna="+"'"+luna+"'" +" and dataAn="+"'"+an+"'" +" ORDER BY Nr_ap",null);
        while (cursor.moveToNext())
        {
            int nrAp=cursor.getInt(0);
            String indivizia=cursor.getString(1);
            String cotaPers=cursor.getString(2);
            String apa=cursor.getString(3);
            String curent=cursor.getString(4);
            String total=cursor.getString(5);
            String restanta=cursor.getString(6);
            String lun=cursor.getString(7);
            String aan=cursor.getString(8);
            String status=cursor.getString(9);
            Intretinere intretinere=new Intretinere(nrAp,indivizia,cotaPers,apa,curent,total,restanta,lun,aan,status);
            arrayList.add(intretinere);

        }
        return arrayList;
    }

    public ArrayList<Intretinere> getAllIntretinereOrdonata()
    {
        ArrayList<Intretinere> arrayList=new ArrayList<>();
        SQLiteDatabase db= this.getReadableDatabase();
        Cursor cursor= db.rawQuery(" SELECT * FROM "+TABLE_NAME_Intretinere+" where dataLuna='Ianuarie' order by dataAn,Nr_ap ;",null);
        while (cursor.moveToNext()) {
            int nrAp = cursor.getInt(0);
            String indivizia = cursor.getString(1);
            String cotaPers = cursor.getString(2);
            String apa = cursor.getString(3);
            String curent = cursor.getString(4);
            String total = cursor.getString(5);
            String restanta = cursor.getString(6);
            String lun = cursor.getString(7);
            String aan = cursor.getString(8);
            String status = cursor.getString(9);
            Intretinere intretinere = new Intretinere(nrAp, indivizia, cotaPers, apa, curent, total, restanta, lun, aan, status);
            arrayList.add(intretinere);
        }
            Cursor cursor2= db.rawQuery(" SELECT * FROM "+TABLE_NAME_Intretinere+" where dataLuna='Februarie' order by dataAn,Nr_ap ;",null);
            while (cursor2.moveToNext())
            {
                int nrAp2=cursor2.getInt(0);
                String indivizia2=cursor2.getString(1);
                String cotaPers2=cursor2.getString(2);
                String apa2=cursor2.getString(3);
                String curent2=cursor2.getString(4);
                String total2=cursor2.getString(5);
                String restanta2=cursor2.getString(6);
                String lun2=cursor2.getString(7);
                String aan2=cursor2.getString(8);
                String status2=cursor2.getString(9);
                Intretinere intretinere2=new Intretinere(nrAp2,indivizia2,cotaPers2,apa2,curent2,total2,restanta2,lun2,aan2,status2);
                arrayList.add(intretinere2);

            }

        Cursor cursor3= db.rawQuery(" SELECT * FROM "+TABLE_NAME_Intretinere+" where dataLuna='Martie' order by dataAn,Nr_ap ;",null);
        while (cursor3.moveToNext())
        {
            int nrAp3=cursor3.getInt(0);
            String indivizia3=cursor3.getString(1);
            String cotaPers3=cursor3.getString(2);
            String apa3=cursor3.getString(3);
            String curent3=cursor3.getString(4);
            String total3=cursor3.getString(5);
            String restanta3=cursor3.getString(6);
            String lun3=cursor3.getString(7);
            String aan3=cursor3.getString(8);
            String status3=cursor3.getString(9);
            Intretinere intretinere3=new Intretinere(nrAp3,indivizia3,cotaPers3,apa3,curent3,total3,restanta3,lun3,aan3,status3);
            arrayList.add(intretinere3);
        }

        Cursor cursor4= db.rawQuery(" SELECT * FROM "+TABLE_NAME_Intretinere+" where dataLuna='Aprilie' order by dataAn,Nr_ap ;",null);
        while (cursor4.moveToNext())
        {
            int nrAp3=cursor4.getInt(0);
            String indivizia3=cursor4.getString(1);
            String cotaPers3=cursor4.getString(2);
            String apa3=cursor4.getString(3);
            String curent3=cursor4.getString(4);
            String total3=cursor4.getString(5);
            String restanta3=cursor4.getString(6);
            String lun3=cursor4.getString(7);
            String aan3=cursor4.getString(8);
            String status3=cursor4.getString(9);
            Intretinere intretinere4=new Intretinere(nrAp3,indivizia3,cotaPers3,apa3,curent3,total3,restanta3,lun3,aan3,status3);
            arrayList.add(intretinere4);
        }
        Cursor cursor5= db.rawQuery(" SELECT * FROM "+TABLE_NAME_Intretinere+" where dataLuna='Mai' order by dataAn,Nr_ap ;",null);
        while (cursor5.moveToNext())
        {
            int nrAp3=cursor5.getInt(0);
            String indivizia3=cursor5.getString(1);
            String cotaPers3=cursor5.getString(2);
            String apa3=cursor5.getString(3);
            String curent3=cursor5.getString(4);
            String total3=cursor5.getString(5);
            String restanta3=cursor5.getString(6);
            String lun3=cursor5.getString(7);
            String aan3=cursor5.getString(8);
            String status3=cursor5.getString(9);
            Intretinere intretinere5=new Intretinere(nrAp3,indivizia3,cotaPers3,apa3,curent3,total3,restanta3,lun3,aan3,status3);
            arrayList.add(intretinere5);
        }
        Cursor cursor6= db.rawQuery(" SELECT * FROM "+TABLE_NAME_Intretinere+" where dataLuna='Iunie' order by dataAn,Nr_ap ;",null);
        while (cursor6.moveToNext())
        {
            int nrAp3=cursor6.getInt(0);
            String indivizia3=cursor6.getString(1);
            String cotaPers3=cursor6.getString(2);
            String apa3=cursor6.getString(3);
            String curent3=cursor6.getString(4);
            String total3=cursor6.getString(5);
            String restanta3=cursor6.getString(6);
            String lun3=cursor6.getString(7);
            String aan3=cursor6.getString(8);
            String status3=cursor6.getString(9);
            Intretinere intretinere6=new Intretinere(nrAp3,indivizia3,cotaPers3,apa3,curent3,total3,restanta3,lun3,aan3,status3);
            arrayList.add(intretinere6);
        }
        Cursor cursor7= db.rawQuery(" SELECT * FROM "+TABLE_NAME_Intretinere+" where dataLuna='Iulie' order by dataAn,Nr_ap ;",null);
        while (cursor7.moveToNext())
        {
            int nrAp3=cursor7.getInt(0);
            String indivizia3=cursor7.getString(1);
            String cotaPers3=cursor7.getString(2);
            String apa3=cursor7.getString(3);
            String curent3=cursor7.getString(4);
            String total3=cursor7.getString(5);
            String restanta3=cursor7.getString(6);
            String lun3=cursor7.getString(7);
            String aan3=cursor7.getString(8);
            String status3=cursor7.getString(9);
            Intretinere intretinere7=new Intretinere(nrAp3,indivizia3,cotaPers3,apa3,curent3,total3,restanta3,lun3,aan3,status3);
            arrayList.add(intretinere7);
        }
        Cursor cursor8= db.rawQuery(" SELECT * FROM "+TABLE_NAME_Intretinere+" where dataLuna='August' order by dataAn,Nr_ap ;",null);
        while (cursor8.moveToNext())
        {
            int nrAp3=cursor8.getInt(0);
            String indivizia3=cursor8.getString(1);
            String cotaPers3=cursor8.getString(2);
            String apa3=cursor8.getString(3);
            String curent3=cursor8.getString(4);
            String total3=cursor8.getString(5);
            String restanta3=cursor8.getString(6);
            String lun3=cursor8.getString(7);
            String aan3=cursor8.getString(8);
            String status3=cursor8.getString(9);
            Intretinere intretinere8=new Intretinere(nrAp3,indivizia3,cotaPers3,apa3,curent3,total3,restanta3,lun3,aan3,status3);
            arrayList.add(intretinere8);
        }
        Cursor cursor9= db.rawQuery(" SELECT * FROM "+TABLE_NAME_Intretinere+" where dataLuna='Septembrie' order by dataAn,Nr_ap ;",null);
        while (cursor9.moveToNext())
        {
            int nrAp3=cursor9.getInt(0);
            String indivizia3=cursor9.getString(1);
            String cotaPers3=cursor9.getString(2);
            String apa3=cursor9.getString(3);
            String curent3=cursor9.getString(4);
            String total3=cursor9.getString(5);
            String restanta3=cursor9.getString(6);
            String lun3=cursor9.getString(7);
            String aan3=cursor9.getString(8);
            String status3=cursor9.getString(9);
            Intretinere intretinere9=new Intretinere(nrAp3,indivizia3,cotaPers3,apa3,curent3,total3,restanta3,lun3,aan3,status3);
            arrayList.add(intretinere9);
        }
        Cursor cursor10= db.rawQuery(" SELECT * FROM "+TABLE_NAME_Intretinere+" where dataLuna='Octombrie' order by dataAn,Nr_ap ;",null);
        while (cursor10.moveToNext())
        {
            int nrAp3=cursor10.getInt(0);
            String indivizia3=cursor10.getString(1);
            String cotaPers3=cursor10.getString(2);
            String apa3=cursor10.getString(3);
            String curent3=cursor10.getString(4);
            String total3=cursor10.getString(5);
            String restanta3=cursor10.getString(6);
            String lun3=cursor10.getString(7);
            String aan3=cursor10.getString(8);
            String status3=cursor10.getString(9);
            Intretinere intretinere10=new Intretinere(nrAp3,indivizia3,cotaPers3,apa3,curent3,total3,restanta3,lun3,aan3,status3);
            arrayList.add(intretinere10);
        }
        Cursor cursor11= db.rawQuery(" SELECT * FROM "+TABLE_NAME_Intretinere+" where dataLuna='Noiembrie' order by dataAn,Nr_ap ;",null);
        while (cursor11.moveToNext())
        {
            int nrAp3=cursor11.getInt(0);
            String indivizia3=cursor11.getString(1);
            String cotaPers3=cursor11.getString(2);
            String apa3=cursor11.getString(3);
            String curent3=cursor11.getString(4);
            String total3=cursor11.getString(5);
            String restanta3=cursor11.getString(6);
            String lun3=cursor11.getString(7);
            String aan3=cursor11.getString(8);
            String status3=cursor11.getString(9);
            Intretinere intretinere11=new Intretinere(nrAp3,indivizia3,cotaPers3,apa3,curent3,total3,restanta3,lun3,aan3,status3);
            arrayList.add(intretinere11);
        }
        Cursor cursor12= db.rawQuery(" SELECT * FROM "+TABLE_NAME_Intretinere+" where dataLuna='Decembrie' order by dataAn,Nr_ap ;",null);
        while (cursor12.moveToNext())
        {
            int nrAp3=cursor12.getInt(0);
            String indivizia3=cursor12.getString(1);
            String cotaPers3=cursor12.getString(2);
            String apa3=cursor12.getString(3);
            String curent3=cursor12.getString(4);
            String total3=cursor12.getString(5);
            String restanta3=cursor12.getString(6);
            String lun3=cursor12.getString(7);
            String aan3=cursor12.getString(8);
            String status3=cursor12.getString(9);
            Intretinere intretinere12=new Intretinere(nrAp3,indivizia3,cotaPers3,apa3,curent3,total3,restanta3,lun3,aan3,status3);
            arrayList.add(intretinere12);
        }
        return arrayList;
    }




    //-------------------------sendMail-------------
    public String getAllMail(){
        String emails="";
        String sql=" SELECT Email FROM "+TABLE_NAME;
        Cursor cursor=getReadableDatabase().rawQuery(sql,null);
        while(cursor.moveToNext()){
            emails=emails+cursor.getString(0)+",";
        }
        return emails;
    }




    //---------------------Cheltuieli lunare ale administratiei--------

    public String getCheltuieli(String luna, String an)
    {
        String chelt="";
        String sql=" SELECT * FROM "+TABLE_NAME_cheltuieliAsociatie+" where luna="+"'"+luna+"'" +" and an="+"'"+an+"'";
        Cursor cursor=getReadableDatabase().rawQuery(sql,null);

        if(cursor.getCount()>0){
            cursor.moveToNext();
            String pretApa=cursor.getString(0);
            String cheltAdministrative=cursor.getString(1);
            String salubritate=cursor.getString(2);
            String energieElectricaHol=cursor.getString(3);
            String salariuAdmin=cursor.getString(4);
            String intretinereAscensor=cursor.getString(5);
            String curatenieScara=cursor.getString(6);
            String lunaa=cursor.getString(7);
            String ann=cursor.getString(8);
           chelt=pretApa+","+cheltAdministrative+","+salubritate+","+energieElectricaHol+","+salariuAdmin+","+intretinereAscensor+","+curatenieScara+","+lunaa+","+ann;
        }
        return chelt;
    }


}
