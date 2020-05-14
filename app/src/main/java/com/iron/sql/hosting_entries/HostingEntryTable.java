package com.iron.sql.hosting_entries;

import android.content.ContentValues;
import android.database.Cursor;
import android.provider.BaseColumns;

import com.iron.model.HostingEntry;

public class HostingEntryTable {

    public abstract class Table implements BaseColumns {

        public static  final String TABLE_NAME = "hosting";

        public static  final String ID = "id";

         public static  final String DOMAIN = "DOMAIN";
         public static  final String USERNAME = "USERNAME";
         public static  final String ADMIN_EMAIL = "ADMIN_EMAIL";
         public static  final String START_DATE = "START_DATE";
         public static  final String SUSPENDED = "SUSPENDED";
         public static  final String NOTES = "NOTES";

    }

    public static HostingEntry FromCursor(Cursor cursor){

        int _id = cursor.getColumnIndex(Table.ID);
        int _domain = cursor.getColumnIndex(Table.DOMAIN);
        int _username = cursor.getColumnIndex(Table.USERNAME);
        int _admin_email = cursor.getColumnIndex(Table.ADMIN_EMAIL);
        int _start_date = cursor.getColumnIndex(Table.START_DATE);
        int _suspended = cursor.getColumnIndex(Table.SUSPENDED);
        int _notes = cursor.getColumnIndex(Table.NOTES);

        HostingEntry obj = new HostingEntry();

        obj.setId(cursor.getString(_id));
        obj.setDomain(cursor.getString(_domain));
        obj.setUsername(cursor.getString(_username));
        obj.setAdminEmail(cursor.getString(_admin_email));
        obj.setStartDate(cursor.getLong(_start_date));
        boolean status = Boolean.parseBoolean(cursor.getString(_suspended));
        obj.setSuspended(status);
        obj.setNotes(cursor.getString(_notes));

        return obj;

    }

    public static ContentValues ObjectToContentValues(HostingEntry obj){

        ContentValues values = new ContentValues();

        values.put(Table.ID,obj.getId());
        values.put(Table.DOMAIN,obj.getDomain());
        values.put(Table.USERNAME,obj.getUsername());
        values.put(Table.ADMIN_EMAIL,obj.getAdminEmail());
        values.put(Table.START_DATE,obj.getStartDate());
        values.put(Table.SUSPENDED,obj.isSuspended());
        values.put(Table.NOTES,obj.getNotes());

        return values;

    }

}
