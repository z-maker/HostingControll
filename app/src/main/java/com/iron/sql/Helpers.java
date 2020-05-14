package com.iron.sql;

import android.database.Cursor;

import com.iron.model.HostingEntry;

import java.util.ArrayList;

public interface Helpers {

    interface HostingEntryHelper{

        Cursor getAllAsCursor();
        ArrayList<HostingEntry> getAllAsList();

        Cursor searchByKeyAsCursor(String key);
        ArrayList<HostingEntry> searchByKeyAsList(String key);

        boolean add(HostingEntry obj);
        boolean update(HostingEntry obj);
        boolean delete(HostingEntry obj);

        HostingEntry getByKey(String key);
        HostingEntry getByKey(String key, Object value);
    }

}
