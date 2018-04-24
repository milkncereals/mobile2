package earl.com.assignment2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;



public class DatabaseHelper extends SQLiteOpenHelper {

    //database name
    public static final String db_name= "quiz.db";
    private static final int DATABASE_VERSION = 1;

    //table names
    public static final String RESULT = "RESULT";

    //table columns
    //employee table columns
    public static final String NAME_COL = "NAME";
    public static final String COUNTRY_COL = "COUNTRY";
    public static final String AGE_COL = "AGE";
    public static final String GENDER_COL = "GENDER";
    public static final String RESULT_COL = "RESULT";

    @Override
    public void onCreate(SQLiteDatabase db) {


        //Result table
        db.execSQL("create table " + RESULT + "( _id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "NAME TEXT," +
                "COUNTRY TEXT," +
                "AGE TEXT," +
                "GENDER TEXT," +
                "RESULT TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS "+ RESULT);
    }

    public DatabaseHelper(Context context) {
        super(context, db_name, null, DATABASE_VERSION);
    }

    //add new result
    void addNewResult(String name,String country,String age,String gender, String result){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(NAME_COL,name);
        contentValues.put(COUNTRY_COL,country);
        contentValues.put(AGE_COL,age);
        contentValues.put(GENDER_COL,gender);
        contentValues.put(RESULT_COL,result);
        db.insert( RESULT,null,contentValues);

    }

    //get result list
    Cursor getAllHighscores() {

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor c = db.query(RESULT,
                new String[] {"_id", NAME_COL,COUNTRY_COL,AGE_COL,GENDER_COL,RESULT_COL},
                null,
                null,
                null,
                null,
                null);

        return c;
    }

    //clear highscore
    void clearHighscore(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM "+ RESULT);
    }

}
