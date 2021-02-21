package com.example.bba;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    private String TABLE_NAME = "user_table";
    private String TABLE_NAME1 = "transfers_table";

    public DatabaseHelper(@Nullable Context context) {
        super(context, "User.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME +" (PHONENUMBER INTEGER PRIMARY KEY ,NAME TEXT,BALANCE DECIMAL,EMAIL VARCHAR,ACCOUNT_NO VARCHAR,IFSC_CODE VARCHAR)");
        db.execSQL("create table " + TABLE_NAME1 +" (TRANSACTIONID INTEGER PRIMARY KEY AUTOINCREMENT,DATE TEXT,FROMNAME TEXT,TONAME TEXT,AMOUNT DECIMAL,STATUS TEXT)");
        db.execSQL("insert into user_table values(9632587412,'Mohan',4864.00,'mohan@gmail.com','XXXXXXXXXXXX153','CHAN00987')");
        db.execSQL("insert into user_table values(7894561236,'Shreyas',4582.00,'shreyas@gmail.com','XXXXXXXXXXXX154','CHAN00987')");
        db.execSQL("insert into user_table values(8527419632,'Manoj',3452.05,'manoj@gmail.com','XXXXXXXXXXXX155','CHAN00987')");
        db.execSQL("insert into user_table values(7418529632,'Sukesh',7426.09,'sukesh@gmail.com','XXXXXXXXXXXX156','CHAN00987')");
        db.execSQL("insert into user_table values(7531594862,'Darshan',723650.02,'darshan@gmail.com','XXXXXXXXXXXX157','CHAN00987')");
        db.execSQL("insert into user_table values(6547893214,'Keerthi',12364.15,'keerthi@gmail.com','XXXXXXXXXXXX158','CHAN00987')");
        db.execSQL("insert into user_table values(9514782630,'Tejas',5936.00,'tejas@gmail.com','XXXXXXXXXXXX159','CHAN00987')");
        db.execSQL("insert into user_table values(8642315972,'Gagan',6325.22,'gagan@gmail.com','XXXXXXXXXXXX160','CHAN00987')");
        db.execSQL("insert into user_table values(7182936540,'Hemanth',4398.46,'hemanth@gmail.com','XXXXXXXXXXXX161','CHAN00987')");
        db.execSQL("insert into user_table values(9173684250,'Tarun',8273.90,'tarun@gmail.com','XXXXXXXXXXXX162','CHAN00987')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME1);
        onCreate(db);
    }

    public Cursor readalldata(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from user_table", null);
        return cursor;
    }

    public Cursor readparticulardata(String phonenumber){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from user_table where phonenumber = " +phonenumber, null);
        return cursor;
    }

    public Cursor readselectuserdata(String phonenumber) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from user_table except select * from user_table where phonenumber = " +phonenumber, null);
        return cursor;
    }

    public void updateAmount(String phonenumber, String amount){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("update user_table set balance = " + amount + " where phonenumber = " +phonenumber);
    }

    public Cursor readtransferdata(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from transfers_table", null);
        return cursor;
    }

    public boolean insertTransferData(String date,String from_name, String to_name, String amount, String status){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("DATE", date);
        contentValues.put("FROMNAME", from_name);
        contentValues.put("TONAME", to_name);
        contentValues.put("AMOUNT", amount);
        contentValues.put("STATUS", status);
        Long result = db.insert(TABLE_NAME1, null, contentValues);
        if(result == -1){
            return false;
        }else{
            return true;
        }
    }
}