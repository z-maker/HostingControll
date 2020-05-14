package com.iron.sql;

import com.iron.sql.hosting_entries.HostingEntryTable;

public class DatabaseModel {

    public static final String CREATE_TABLE_HOSTING_ENTRIES =
            "CREATE TABLE " + HostingEntryTable.Table.TABLE_NAME +
                    "(" +
                    HostingEntryTable.Table._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"+
                    HostingEntryTable.Table.ID + " TEXT NOT NULL,"+
                    HostingEntryTable.Table.USERNAME + " TEXT NOT NULL," +
                    HostingEntryTable.Table.DOMAIN + " TEXT NOT NULL," +
                    HostingEntryTable.Table.ADMIN_EMAIL + " TEXT ," +
                    HostingEntryTable.Table.START_DATE + " LONG NOT NULL," +
                    HostingEntryTable.Table.SUSPENDED + " BOOLEAN NOT NULL," +
                    HostingEntryTable.Table.NOTES + " TEXT ," +
                    " UNIQUE (" + HostingEntryTable.Table.ID + "))";
}
