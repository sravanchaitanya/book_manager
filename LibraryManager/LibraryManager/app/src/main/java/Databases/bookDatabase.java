package Databases;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class bookDatabase extends SQLiteOpenHelper {

    public static final String databaseName = "bookDatabase";
    public static final String tableName = "bookTable";
    public static int DB_Version = 1;

    public bookDatabase(Context context){
        super(context,databaseName,null,DB_Version);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE "+tableName+" (id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "NAME TEXT,AUTHOR TEXT, GENRE TEXT,PRICE INTEGER);");
    }
    public void add(String name,String author,String genre,int price){
        ContentValues contentValues = new ContentValues();
        contentValues.put("NAME",name);
        contentValues.put("AUTHOR",author);
        contentValues.put("GENRE",genre);
        contentValues.put("PRICE",price);
        SQLiteDatabase db = getReadableDatabase();
        db.insert(tableName,null,contentValues);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}
