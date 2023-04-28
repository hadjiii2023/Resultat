package com.example.resultat;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class DataBaseHelper extends SQLiteOpenHelper {
    private Context context ;
    private static final  String DATABASE_NAME = "Result.db" ;
    private static final int DATABASE_VERSION = 1 ;

    private static final String TABLE_NAME = "mes_moyenne" ;
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_CIN = "cin" ;
    private static final String COLUMN_MOYEN = "moyen";
    private static final String COLUMN_Result = "resultat" ;


    DataBaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION );
        this.context = context ;
    }

    @Override
    /*OneCreate is a part of SQLiteOpenHelper it is used to create Tables and its content*/
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME + " (" + COLUMN_ID +
                " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_CIN + " TEXT, " +
                COLUMN_MOYEN + " TEXT, " +
                COLUMN_Result + " TEXT);";
        db.execSQL(query);
    }

    @Override
    /* onUpgrade is used the table*/
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL(" DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
    void addMoyen (String cin , String moyen , String result) {
        SQLiteDatabase db  = this.getWritableDatabase() ;
        ContentValues cv = new ContentValues() ;

        cv.put(COLUMN_CIN, cin );
        cv.put(COLUMN_MOYEN, moyen );
        cv.put(COLUMN_Result, result );
        long res =   db.insert(TABLE_NAME , null ,cv) ;
        if (res == -1) {
            Toast.makeText(context, "failed process", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Successfully added", Toast.LENGTH_SHORT).show();
        }
    }

    void getMoyenne(String mycin) {
        String moy = null;
        String res = null;
        String query = "SELECT * FROM "+TABLE_NAME+" WHERE cin =?" ;
        SQLiteDatabase db = this.getReadableDatabase() ;
        Cursor c = db.rawQuery(query , new String[]{mycin}) ; ;
        if(db != null) {
            if (c.getCount() == 0 ){
                Toast.makeText(context, "No data", Toast.LENGTH_SHORT).show();
            }else{
                c.moveToFirst();
                moy = c.getString(2) ;
                res = c.getString(3) ;
                AlertDialog.Builder msg = new AlertDialog.Builder(context);
                msg.setTitle("Resultat");
                msg.setMessage("Le titulaire de CIN:"+mycin+" Vous êtes "+res+" Avec Moyenne :"+moy );
                msg.show();
                }
        }
    }
    Cursor readAllData() {
        String query = "SELECT * FROM " + TABLE_NAME ;
        SQLiteDatabase db = this.getReadableDatabase() ;
        Cursor cursor = null ;
        if(db != null) {
            cursor = db.rawQuery(query , null) ;
        }
        return cursor ;
    }
    void deleteById(String id) {
        SQLiteDatabase db = this.getWritableDatabase() ;
        long res = db.delete(TABLE_NAME, "id=" + id, null);
        if (res == -1) {
            Toast.makeText(context, "Failed to Delete", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Sucessfully Delete", Toast.LENGTH_SHORT).show();
        }        db.close();
    }
    void UpdateData(String row_id ,String cin , String moyen , String result){
        SQLiteDatabase db = this.getWritableDatabase() ;
        ContentValues cv = new ContentValues() ;
        cv.put(COLUMN_CIN , cin);
        cv.put(COLUMN_MOYEN , moyen);
        cv.put(COLUMN_Result , result);


        long res = db.update(TABLE_NAME , cv , "id=?",  new String[]{row_id}) ;
        if (res == -1) {
            Toast.makeText(context, "Failed to update", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Sucessfully updated", Toast.LENGTH_SHORT).show();
        }
    }
}
