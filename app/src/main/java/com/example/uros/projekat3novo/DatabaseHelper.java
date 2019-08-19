package com.example.uros.projekat3novo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class DatabaseHelper  extends  SQLiteOpenHelper{

    public static  final int DATABASE_VERSION = 2;
    public static  final String DATABASE_NAME = "contacts.db";
    public static  final String TABLE_NAME = "contacts";
    public static  final String COLUMN_ID = "id";
    public static  final String COLUMN_NAME = "name";
    public static  final String COLUMN_NUMBER = "number";
    public static  final String COLUMN_FAV = "favorite";


    private final String TABLE_CREATE = "create table contacts (id integer primary key autoincrement," + "name text not null, number text not null, favorite integer not null)";



    public DatabaseHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE);
        //this.db = db;
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String query = "DROP TABLE IF EXISTS " + TABLE_NAME;
        db.execSQL(query);
        this.onCreate(db);
    }


    public long insertContact(Contact contact) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, contact.getNameContact());
        values.put(COLUMN_NUMBER, contact.getNumberContact());
        values.put(COLUMN_FAV, 0);

        return db.insert(TABLE_NAME, null, values);
    }

    public void deleteContact(long id) {
        SQLiteDatabase db = getWritableDatabase();
        String query = "DELETE FROM contacts WHERE id = ?";
        db.execSQL(query, new Long[] {id});
    }

    public List<Contact> getAllContacts() {
        SQLiteDatabase db = getWritableDatabase();

        List<Contact> contacts = new LinkedList<>();

        String query = "SELECT * FROM contacts";
        Cursor cursor = db.rawQuery(query, null);

        if(cursor.moveToFirst()) {
            while(!cursor.isAfterLast()) {
                contacts.add(getContactFromCursor(cursor));

                cursor.moveToNext();
            }
        }

        cursor.close();


        return contacts;
    }

    public List<Contact> getAllFavContacts() {

        SQLiteDatabase db = getWritableDatabase();

        List<Contact> contacts = new LinkedList<>();

        String query = "SELECT * FROM contacts WHERE favorite = 1";
        Cursor cursor = db.rawQuery(query, null);

        if(cursor.moveToFirst()) {
            while(!cursor.isAfterLast()) {
                contacts.add(getContactFromCursor(cursor));

                cursor.moveToNext();
            }
        }

        cursor.close();


        return contacts;
    }

    public Contact getContactID(long id) {

        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM contacts WHERE id = ?";
        Cursor cursor = db.rawQuery(query, new String[] {id + ""});
        Contact contact = null;

        if(cursor.moveToFirst()) {
            contact = getContactFromCursor(cursor);
        }

        cursor.close();
        return contact;
    }

    public void updateContact(Contact contact) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, contact.getNameContact());
        values.put(COLUMN_NUMBER, contact.getNumberContact());

        db.update(TABLE_NAME, values, "id = ?", new String[] {contact.getId() + ""});
    }

   /* public boolean updateOmiljeniKontakt(Contact contact) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues args = new ContentValues();
        args.put(COLUMN_FAV, contact.getFav());

        return db.update(TABLE_NAME, args, COLUMN_ID + "=" + contact.getId(), null) > 0;
    }*/

    public void setContactFavorite(long contactId, boolean isFavorite) {
        SQLiteDatabase db = getWritableDatabase();
        int newValue = isFavorite ? 1 : 0;

        ContentValues values = new ContentValues();
        values.put(COLUMN_FAV, newValue);

        db.update(TABLE_NAME, values, "id = ?", new String[] {contactId + ""});
    }


    private Contact getContactFromCursor(Cursor cursor) {
        int id = cursor.getInt(cursor.getColumnIndex("id"));
        String ime = cursor.getString(cursor.getColumnIndex("name"));
        String broj = cursor.getString(cursor.getColumnIndex("number"));

        Contact contact = new Contact();
        contact.setId(id);
        contact.setNameContact(ime);
        contact.setNumberContact(broj);

        return contact;
    }

}

