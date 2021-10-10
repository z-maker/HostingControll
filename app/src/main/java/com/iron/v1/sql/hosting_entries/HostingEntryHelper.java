package com.iron.v1.sql.hosting_entries;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.iron.v1.model.HostingEntry;
import com.iron.v1.sql.DatabaseHelper;
import com.iron.v1.sql.Helpers;

import java.util.ArrayList;

public class HostingEntryHelper implements Helpers.HostingEntryHelper {

    public final String TABLE_NAME = HostingEntryTable.Table.TABLE_NAME;

    public final String[] COLUMNS ={
            HostingEntryTable.Table.ID,
            HostingEntryTable.Table.DOMAIN,
            HostingEntryTable.Table.USERNAME,
            HostingEntryTable.Table.ADMIN_EMAIL,
            HostingEntryTable.Table.START_DATE,
            HostingEntryTable.Table.SUSPENDED,
            HostingEntryTable.Table.NOTES
    };

    private Context context;
    private SQLiteDatabase database;
    private DatabaseHelper helper;

    public HostingEntryHelper(Context context) {
        this.context = context;
        this.helper = new DatabaseHelper(context);
        this.database = helper.getWritableDatabase();
    }

    @Override
    public Cursor getAllAsCursor() {
        return database.query(HostingEntryTable.Table.TABLE_NAME,COLUMNS,null,null,null,null,null);
    }

    @Override
    public ArrayList<HostingEntry> getAllAsList() {
        return toList(getAllAsCursor());
    }

    @Override
    public Cursor searchByKeyAsCursor(String key) {
        return null;
    }

    @Override
    public ArrayList<HostingEntry> searchByKeyAsList(String key) {
        return null;
    }

    @Override
    public boolean add(HostingEntry obj) {
        if (obj==null)
            return false;

        ContentValues contentValues = HostingEntryTable.ObjectToContentValues(obj);

        return database.insert(HostingEntryTable.Table.TABLE_NAME,null,contentValues) > 0;
    }

    @Override
    public boolean update(HostingEntry obj) {
        return false;
    }

    @Override
    public boolean delete(HostingEntry obj) {
        return false;
    }

    @Override
    public HostingEntry getByKey(String key) {
        return null;
    }

    @Override
    public HostingEntry getByKey(String key, Object value) {
        return null;
    }

    private ArrayList<HostingEntry> toList(Cursor cursor){
        ArrayList<HostingEntry> list = new ArrayList<>();
        if (cursor!=null && cursor.moveToFirst()){
            do {
                HostingEntry obj = HostingEntryTable.FromCursor(cursor);
                list.add(obj);
            }while (cursor.moveToNext());
        }
        return list;
    }
}
