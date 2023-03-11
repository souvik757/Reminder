package com.example.reminder;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SQLiteManager extends SQLiteOpenHelper {

    private static SQLiteManager sqLiteManager ;
    private static final String DATABASE_NAME = "NotesDB" ;
    private static final int DATABASE_VERSION  = 1 ;
    private static final String TABLE_NAME = "Notes" ;
    private static final String COUNTER = "Counter" ;

    private static final String ID_FIELD = "id" ;
    private static final String TITLE_FIELD = "title" ;
    private static final String DESC_FIELD = "desc" ;
    private static final String DELETED_FIELD = "deleted" ;

    @SuppressLint("SimpleDateFormat")
    private static final DateFormat dateformat = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss");

    public SQLiteManager(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION) ;
    }

    public static SQLiteManager instanceofDB(Context context){
        if(sqLiteManager == null){
            sqLiteManager = new SQLiteManager(context) ;
        }
        return sqLiteManager ;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        StringBuilder sql ;
        sql = new StringBuilder().append("CREATE TABLE ")
                .append(TABLE_NAME)
                .append("(")
                .append(COUNTER)
                .append(" INTEGER PRIMARY KEY AUTOINCREMENT, ")
                .append(ID_FIELD)
                .append(" INT, ")
                .append(TITLE_FIELD)
                .append(" TEXT, ")
                .append(DESC_FIELD)
                .append(" TEXT, ")
                .append(DELETED_FIELD)
                .append(" TEXT)") ;
        db.execSQL(sql.toString());
    }

    public void DeleteNote(ModelNotes modelNotes){
        SQLiteDatabase db = this.getWritableDatabase() ;
        db.delete(TABLE_NAME , ID_FIELD+" =? ", new String[]{String.valueOf(modelNotes.getId())}) ;
        db.close();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " +TABLE_NAME);
        onCreate(db);
    }

    public void AddNoteToDB(ModelNotes modelNotes){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase() ;
        ContentValues contentValues = new ContentValues() ;
        contentValues.put(ID_FIELD , modelNotes.getId());
        contentValues.put(TITLE_FIELD , modelNotes.getTile());
        contentValues.put(DESC_FIELD , modelNotes.getDescription());
        contentValues.put(DELETED_FIELD , getStringFromDate(modelNotes.getDeleted()));

        sqLiteDatabase.insert(TABLE_NAME , null , contentValues) ;
        sqLiteDatabase.close();
    }
    public void populateListDB(){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase() ;
        try{
            Cursor result = sqLiteDatabase.rawQuery("SELECT * FROM "+TABLE_NAME , null) ;
            if(result.getCount() != 0){
                while (result.moveToNext()){
                    int id = result.getInt(1) ;
                    String title = result.getString(2) ;
                    String desc = result.getString(3) ;
                    String stringDeleted = result.getString(4) ;
                    Date deleted = getDateFromString(stringDeleted) ;
                    ModelNotes modelNotes = new ModelNotes(id , title , desc , deleted) ;
                    ModelNotes._list_NOTE_.add(modelNotes) ;
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        sqLiteDatabase.close();
    }

    public void updateNotesDB(ModelNotes modelNotes){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase() ;
        ContentValues contentValues = new ContentValues() ;
        contentValues.put(ID_FIELD , modelNotes.getId());
        contentValues.put(TITLE_FIELD , modelNotes.getTile());
        contentValues.put(DESC_FIELD , modelNotes.getDescription());
        contentValues.put(DELETED_FIELD , getStringFromDate(modelNotes.getDeleted()));

        sqLiteDatabase.update(TABLE_NAME , contentValues , ID_FIELD+" =? " ,
                new String[]{String.valueOf(modelNotes.getId())}) ;
        sqLiteDatabase.close();
    }
    private String getStringFromDate(Date date){
        if(date == null)
            return null ;
        return dateformat.format(date) ;
    }
    private Date getDateFromString(String string){
        try {
            return dateformat.parse(string) ;
        }
        catch (ParseException | NullPointerException ex){
            return null ;
        }
    }
}
