package com.example.gadda_000.newproj;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.sql.SQLException;

/**
 * Created by gadda_000 on 1/31/2016.
 */
public class DBcontent {

    public static final String KEY_ROWID ="_id";
    public static final String KEY_BusID ="_bus";
    public static final String KEY_AREA="_area";
    public static final String KEY_LANDMARK="_landmark";
    public static final String KEY_TIME="_time";



    private static final String DATABASE_NAME1="CbitBusInfo2";
    private static final String DATABASE_TABLE="BusTimingTable1";
    private static final int DATABASE_VERSION = 1;

    private DbHelper ourHelper;
    private final Context ourContext;
    private SQLiteDatabase ourDatabase;

    public String getdata() {

        String[] coloums=new String[]{KEY_ROWID,KEY_BusID,KEY_AREA,KEY_LANDMARK,KEY_TIME};
        Cursor c=ourDatabase.query(DATABASE_TABLE,coloums,null,null,null,null,null);

        String result="";

        int irowid=c.getColumnIndex(KEY_ROWID);
        int ibo=c.getColumnIndex(KEY_BusID);
        int irowname=c.getColumnIndex(KEY_AREA);
        int irowlandmark=c.getColumnIndex(KEY_LANDMARK);
        int irowtime=c.getColumnIndex(KEY_TIME);

        for(c.moveToFirst();!c.isAfterLast();c.moveToNext())
            result=result+" "+c.getString(irowid)+" \t\t\t "+c.getString(ibo)+" \t\t\t "+c.getString(irowname)+"\t\t\t  "+c.getString(irowlandmark)+"  \t\t\t"+c.getString(irowtime)+"\n";

        return result;
    }

    public String[][] getcaldata(String ndata) {

        String[] coloums1=new String[]{KEY_ROWID,KEY_BusID,KEY_AREA,KEY_LANDMARK,KEY_TIME};
        // Cursor c1=ourDatabase.query(DATABASE_TABLE,coloums1,KEY_AREA+"=",new String[]{"abids"},null,null,null);
        //String selectqwery="SELECT * FROM "+DATABASE_TABLE+" WHERE "+KEY_AREA+"=\""+"abids"+"\";";

        String selectqwery="SELECT * FROM "+DATABASE_TABLE+" WHERE "+KEY_AREA+" LIKE "+"\""+"%"+ndata+"%"+"\";";

        Cursor c1=ourDatabase.rawQuery(selectqwery, null);

        String[][] result=new String[200][5];


        int irowid=c1.getColumnIndex(KEY_ROWID);
        int ibo=c1.getColumnIndex(KEY_BusID);
        int irowname=c1.getColumnIndex(KEY_AREA);
        int irowlandmark=c1.getColumnIndex(KEY_LANDMARK);
        int irowtime=c1.getColumnIndex(KEY_TIME);
        int i=0;
        for(c1.moveToFirst();!c1.isAfterLast();c1.moveToNext()) {
            result[i][0] = c1.getString(irowid) ;
            result[i][1]= c1.getString(ibo);
            result[i][2]= c1.getString(irowname);
            result[i][3]= c1.getString(irowlandmark) ;
            result[i][4]= c1.getString(irowtime);
            i++;
        }
        return result;
    }

    public String[][] getbusdata(String busnum) {


        String selectqwery="SELECT * FROM "+DATABASE_TABLE+" WHERE "+KEY_BusID+"=\""+busnum+"\";";
        // String selectqwery="SELECT * FROM "+DATABASE_TABLE+" WHERE "+KEY_AREA+" LIKE "+"\""+"%"+busnum+"%"+"\";";

        Cursor c1=ourDatabase.rawQuery(selectqwery, null);

        String[][] result=new String[200][5];


        int irowid=c1.getColumnIndex(KEY_ROWID);
        // int ibo=c1.getColumnIndex(KEY_BusID);
        int irowname=c1.getColumnIndex(KEY_AREA);
        int irowlandmark=c1.getColumnIndex(KEY_LANDMARK);
        int irowtime=c1.getColumnIndex(KEY_TIME);
        int i=0;
        for(c1.moveToFirst();!c1.isAfterLast();c1.moveToNext()) {
            result[i][0] = c1.getString(irowid);
            //   result[i][1] = c1.getString(ibo);
            result[i][1] = c1.getString(irowname);
            result[i][2] = c1.getString(irowlandmark);
            result[i][3] = c1.getString(irowtime);
            i++;
        }
        return  result;
    }

    public String[] getautoString() {
        String selectqwery="SELECT * FROM "+DATABASE_TABLE;
        Cursor c1=ourDatabase.rawQuery(selectqwery, null);

        String[] result=new String[448];


        int irowname=c1.getColumnIndex(KEY_AREA);
        int i=0;
        for(c1.moveToFirst();!c1.isAfterLast();c1.moveToNext()) {
            result[i] = c1.getString(irowname);
            i++;
        }
        return  result;

    }


    private  static class DbHelper extends SQLiteOpenHelper{

        public DbHelper(Context context) {
            super(context, DATABASE_NAME1, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {

            db.execSQL("CREATE TABLE "+DATABASE_TABLE+" ("+ KEY_ROWID +" INTEGER PRIMARY KEY AUTOINCREMENT, "+KEY_BusID+" TEXT NOT NULL, "+KEY_AREA +

                            " TEXT NOT NULL, "+KEY_LANDMARK +" TEXT NOT NULL, "+KEY_TIME+" TEXT NOT NULL);"
            );
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

            db.execSQL("DROP TABLE IF EXISTS "+DATABASE_TABLE);
            onCreate(db);
        }
    }

    public DBcontent(Context c)
    {
        ourContext=c;
    }
    public DBcontent open() throws SQLException
    {
        ourHelper=new DbHelper(ourContext);
        ourDatabase=ourHelper.getWritableDatabase();
        return this;
    }
    public  void close()
    {
        ourHelper.close();
    }


    public long createEntry(String bno4,String area4, String landmark4, String time4) {

        ContentValues cv=new ContentValues();
        cv.put(KEY_BusID,bno4);
        cv.put(KEY_AREA,area4);
        cv.put(KEY_LANDMARK,landmark4);
        cv.put(KEY_TIME, time4);
        return ourDatabase.insert(DATABASE_TABLE,null,cv);

    }
}
