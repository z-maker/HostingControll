package com.iron.v1.utils;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.text.TextUtils;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.iron.v1.data.entities.HostingEntity;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CsvParser {

    private OnCsvParserProgress onCsvParserProgress;
    private OnCsvParserResult onCsvParserResult;

    private CSVFormat format;

    private static CsvParser instance;

    CsvParser() {
    }

    public static synchronized CsvParser getInstance() {
        if (instance==null){
            instance = new CsvParser();
        }
        return instance;
    }

    public CsvParser withParams(boolean withHeader, char delimiter){
        if (withHeader)
            format = CSVFormat.RFC4180.withHeader().withDelimiter(delimiter);
        else
            format = CSVFormat.RFC4180.withDelimiter(delimiter);
        return instance;
    }

    public CsvParser addProgressListener(OnCsvParserProgress listener){
        this.onCsvParserProgress = listener;
        return instance;
    }

    public CsvParser addResultListener(OnCsvParserResult listener){
        this.onCsvParserResult = listener;
        return instance;
    }

    public void parse(File file){
        new ParseAsync(format).execute(file);
    }

    @SuppressLint("StaticFieldLeak")
    private class ParseAsync extends AsyncTask<File,String,List<HostingEntity>>{

        private CSVFormat format;

        public ParseAsync(CSVFormat format){
            this.format = format;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected List<HostingEntity> doInBackground(File... files) {
            File file = files[0];

            List<HostingEntity> hostingEntities = new ArrayList<>();
            Gson gson = new Gson();
            int progress = 0;
            try {
                CSVParser parser = new CSVParser(new FileReader(file), format);

                for (CSVRecord record:parser.getRecords()){
                    JsonObject j = new JsonObject();
                    for (Map.Entry<String, String> map: record.toMap().entrySet()){
                        String key = map.getKey();
                        String value = map.getValue();
                        if (TextUtils.isEmpty(value))
                            value = "0";
                        j.addProperty(key,value);
                    }

//                }
//                for (CSVRecord record: parser){
//                    JsonObject j = new JsonObject();
//                    for (Map.Entry<String, Integer> map : parser.getHeaderMap().entrySet()){
//                        String key = map.getKey();
//                        String value = record.get(map.getValue());
//                        if (TextUtils.isEmpty(value))
//                            value = "0";
//                        j.addProperty(key,value);
//                    }
                    HostingEntity h = (HostingEntity)gson.fromJson(j,HostingEntity.class);
                    hostingEntities.add(h);
                    progress++;
                    publishProgress(h.getClientName());
                }
                parser.close();

            } catch (IOException ex) {
                Log.e("KK",ex.getLocalizedMessage().toString());
                return hostingEntities;
            }

            return hostingEntities;
        }

        @Override
        protected void onProgressUpdate(String... values) {
            super.onProgressUpdate(values);
            if (onCsvParserProgress!=null)
                onCsvParserProgress.onCsvParserProgress(values[0]);
        }

        @Override
        protected void onPostExecute(List<HostingEntity> list) {
            super.onPostExecute(list);
            if (onCsvParserResult!=null)
                onCsvParserResult.onCsvParserResult(list);
        }
    }

    public interface OnCsvParserResult{
        void onCsvParserResult(List<HostingEntity> list);
    }

    public interface OnCsvParserProgress{
        void onCsvParserProgress(String progress);
    }

}
