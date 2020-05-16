package com.iron.utils;

import android.os.AsyncTask;
import android.util.Log;

import com.iron.data.entities.HostingConstants;
import com.iron.data.entities.HostingEntity;

import org.apache.commons.net.whois.WhoisClient;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DomainChecker {

    private static WhoisClient whoisClient;
    private List<HostingEntity> list = new ArrayList<>();
    private static DomainChecker instance;

    private OnDomainCheckProgress onDomainCheckProgress;
    private OnDomainCheckResultFromList onDomainCheckResultFromList;
    private OnDomainCheckResult onDomainCheckResult;

    public static synchronized DomainChecker getInstance(){
        if (instance==null){
            instance = new DomainChecker();
        }
        whoisClient = new WhoisClient();
        return instance;
    }

//    public DomainChecker fromList(List<HostingEntity> list){
//        this.list = list;
//        return instance;
//    }
//
//    public DomainChecker singleCheck(String domain){
//        return instance;
//    }

    public DomainChecker singleCheck(HostingEntity list){
        return instance;
    }

    public DomainChecker setOnProgressListener(OnDomainCheckProgress o){
        this.onDomainCheckProgress=o;
        return instance;
    }

    public DomainChecker setOnResultListener(OnDomainCheckResultFromList l){
        this.onDomainCheckResultFromList = l;
        return instance;
    }

    public DomainChecker setOnResultListener(OnDomainCheckResult r){
        this.onDomainCheckResult = r;
        return instance;
    }

    public void Starts(List<HostingEntity> list){
        new CheckAsyncFromList().execute(list);
    }

    public void Starts(HostingEntity entity){

    }

    private class CheckAsyncFromList extends AsyncTask<List<HostingEntity>,Integer,List<HostingEntity>>{

        private List<HostingEntity> resultList = new ArrayList<>();

        @Override
        protected List<HostingEntity> doInBackground(List<HostingEntity>... lists) {
            List<HostingEntity> scanList = lists[0];
            int progress = 0;

            for (HostingEntity obj:scanList) {
                try {

                    String whoIsResult = whoIs(obj.getDomainUrl());

                    //only parse matched results
                    if (!whoIsResult.startsWith("No match")){
                        Map<String, String> whoIsClean = resultToMap(whoIsResult);

                        //now update data for each matched object
                        obj.setDomainStatus(HostingConstants.DOMAIN_ACTIVE);
                        obj.setHostingStatus(HostingConstants.HOSING_ACTIVE);
                        resultList .add(updateHostingValues(obj,whoIsClean));

                    }else {
                        obj.setDomainStatus(HostingConstants.DOMAIN_FREE);
                        obj.setHostingStatus(HostingConstants.HOSTING_SUSPENDED);
                        resultList .add(obj);
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
                progress++;
                publishProgress(progress);
            }

            return resultList;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            if (onDomainCheckProgress!=null){
                onDomainCheckProgress.onProgressUpdate(values[0]);
            }
        }

        @Override
        protected void onPostExecute(List<HostingEntity> list) {
            super.onPostExecute(list);
            if (onDomainCheckResultFromList!=null){
                onDomainCheckResultFromList.onResult(list);
            }
        }
    }

    private String whoIs(String domain) throws IOException {
        String whoIsResult = "";

        whoisClient.connect(WhoisClient.DEFAULT_HOST);
        whoIsResult = whoisClient.query("="+domain);

        return whoIsResult;
    }

    private Map<String, String> resultToMap(String result){
        Map<String, String> mapResult = new HashMap<>();

        //first clean step: get important info
        String tmpClean = result.split(">>>")[0];

        //Second step get result lines
        String[] lines = tmpClean.split("\r\n");

        //third step obtain key value set
        for (String line:lines) {
            //divide result line into key value
            String[] keyValue = line.split(": ");

            String key = "undefined";
            String value = "undefined";

            //may be some stack overflow happen
            try {
                key = keyValue[0].trim().toLowerCase().replace(" ","_");
                value = keyValue[1].toLowerCase();
            }catch (Exception ignored){
                //for conventions make undefined by default value
                key = keyValue[0].trim().toLowerCase().replace(" ","_");
                value = "undefined";
            }
            mapResult.put(key,value);

        }

        return mapResult;
    }

    private HostingEntity updateHostingValues( HostingEntity obj, Map<String, String> values){

        String domainUrl = values.get("domain_name");

        String domainCreated = values.get("creation_date");
        String domainUpdated = values.get("updated_date");
        String domainExpires = values.get("registry_expire_date");

        obj.setDomainUrl(domainUrl);
        obj.setDomainCreated(domainCreated);
        obj.setDomainUpdated(domainUpdated);
        obj.setDomainExpires(domainExpires);

        return obj;
    }

    public static interface OnDomainCheckProgress{
        void onProgressUpdate(int progress);
    }

    public static interface OnDomainCheckResultFromList{
        void onResult(List<HostingEntity> resultList);
    }

    public interface OnDomainCheckResult{
        void onResult(HostingEntity obj);
    }
}
