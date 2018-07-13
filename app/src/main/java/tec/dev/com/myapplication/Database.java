package tec.dev.com.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Database {

    public static final String DB_NAME = "kamatchi";
    public static final int VER = 2;
    public static final String STUDENT = "student";
    public static final String ST_ID = "_id";
    public static final String ST_NAME = "st_name";

    public static final String STAFF = "staff";
    public static final String STUDENT_CREATE_TBL = "create table "+STUDENT+" " +
            "("+ST_ID+" integer primary key autoincrement,"+ST_NAME+" text not null)";
    public static final String STUDENT_DROP_TBL = "drop table "+STUDENT;
    public DBOpenHelper dbOpenHelper;
    public SQLiteDatabase db;

    public Database(Context context){
        dbOpenHelper = new DBOpenHelper(context);
    }

    public void openDb(){
        db = dbOpenHelper.getWritableDatabase();
    }

    public void closeDb(){
        db.close();
        dbOpenHelper = null;
    }

    public void addStudent(String studentName){
        //INSERT INTO table
        ContentValues contentValues = new ContentValues();
        contentValues.put(ST_NAME,studentName);
        db.insert(STUDENT,null,contentValues);
    }



    public Cursor getAllStudents(){
        Cursor cursor = db.query(STUDENT,new String[]{ST_ID,ST_NAME},null,null,null,null,null);
        return cursor;
    }


    class DBOpenHelper extends SQLiteOpenHelper{

        public DBOpenHelper(Context context) {
            super(context, DB_NAME, null, VER);
        }

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {
            sqLiteDatabase.execSQL(STUDENT_CREATE_TBL);
        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
           sqLiteDatabase.execSQL(STUDENT_DROP_TBL);
           onCreate(sqLiteDatabase);
        }
    }
}
