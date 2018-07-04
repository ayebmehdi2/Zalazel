package com.music.zalazel.mehdi.zalazel;

import android.annotation.SuppressLint;
import android.content.AsyncTaskLoader;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;

class QueryUtils {
    private static final String LOG_TAG = QueryUtils.class.getSimpleName();

    QueryUtils(){}

    @SuppressLint("StaticFieldLeak")
    public static class ErathQuackLoder extends AsyncTaskLoader<ArrayList<Data>> {

        private String ur;
        ErathQuackLoder(@NonNull Context context, String ur) {
            super(context);
            this.ur = ur;
        }

        @Override
        protected void onStartLoading() {
            forceLoad();
            Log.e(LOG_TAG, " TEST: onStartLoading");

        }

        @Nullable
        @Override
        public ArrayList<Data> loadInBackground() {

            String json = "";
            URL url = creatUrl(ur);
            try {
                json = makeHttpReqeust(url);
            } catch (IOException ignored){ }

            return extractEarthquakes(json);
        }
    }

    private static ArrayList<Data> extractEarthquakes(String a) {

        if (TextUtils.isEmpty(a)){
            return null;
        }

        ArrayList<Data> earthquakes = new ArrayList<>();

         try {

             JSONObject jo = new JSONObject(a);
             JSONArray ja = jo.optJSONArray("features");

             for (int i = 0; i < ja.length(); i++ ){

                 JSONObject p = ja.optJSONObject(i);
                 JSONObject PP = p.optJSONObject("properties");

                 String place = PP.optString("place");
                 long timeUnix = PP.optLong("time");
                 Double mag = PP.optDouble("mag");
                 String type = PP.optString("type");
                 String title = PP.optString("title");
                 int tsunami = PP.optInt("tsunami");
                 String detail = PP.optString("url");


                 String titre = title.substring(title.indexOf("of") + 3, title.length() - 1);
                 String placev = place.substring(0, place.indexOf("f") + 1);
                 String placekm = place.substring(place.indexOf("f") + 1, place.length() - 1);

                 Date timeUnixNumber = new Date(timeUnix);
                 @SuppressLint("SimpleDateFormat") SimpleDateFormat Datee = new SimpleDateFormat("LLL dd, yyyy");
                 @SuppressLint("SimpleDateFormat") SimpleDateFormat Time = new SimpleDateFormat("h:mm a");
                 String date = Datee.format(timeUnixNumber);
                 String time = Time.format(timeUnixNumber);

                 earthquakes.add(new Data(
                         mag,
                         placev,
                         placekm,
                         String.valueOf(time),
                         String.valueOf(date),
                         type,
                         titre,
                         tsunami,
                         detail));
                    }

        } catch (JSONException e) {
               Log.e("QueryUtils", "Problem parsing the earthquake JSON results", e);
        }
        return earthquakes;
    }
    private static URL creatUrl(String a){
        URL url = null;
        try {
            url = new URL(a);
        } catch (MalformedURLException ignored){ }
        return url;
    }
    private static String makeHttpReqeust(URL url) throws IOException {

        String output = "";

        if (url == null){
            return output;
        }

        InputStream inputStream = null;
        HttpURLConnection urlConnection = null;

        try {
        urlConnection = (HttpURLConnection) url.openConnection();
        urlConnection.setRequestMethod("GET");
        urlConnection.setConnectTimeout(3000);
        urlConnection.setReadTimeout(3000);
        urlConnection.setDoInput(true);
        urlConnection.connect();

        if (urlConnection.getResponseCode() == 200){
            inputStream = urlConnection.getInputStream();
            output = readStream(inputStream);
        } else { Log.e(LOG_TAG, "ERROE RESPONSE CODE: "+ urlConnection.getResponseCode()); }
        } catch (IOException e){
            Log.e(LOG_TAG, "Problem JSON", e);
        }
        finally {
            if (urlConnection != null){ urlConnection.disconnect(); }

            if (inputStream != null){
                inputStream.close();
            }
        }
        return output;


    }
    private static String readStream(InputStream in) throws IOException {

        StringBuilder out = new StringBuilder();

        InputStreamReader inputStreamReader = new InputStreamReader(in, Charset.forName("UTF-8"));
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

        String line = bufferedReader.readLine();
        while (line != null){
            out.append(line);
            line = bufferedReader.readLine();
        }

        return out.toString();

    }


    @SuppressLint("StaticFieldLeak")
    static class TsunamiAsyncTask extends AsyncTaskLoader<ArrayList<Event>> {

        private String s;
        TsunamiAsyncTask(@NonNull Context context, String s) {
            super(context);
            this.s = s;
        }

        @Override
        protected void onStartLoading() {
            forceLoad();
        }

        @Nullable
        @Override
        public ArrayList<Event> loadInBackground() {
            URL url = creatUrl(s);
            String jsonResponse = "";
            try {
                assert url != null;
                jsonResponse = makeHttpReqeust(url);

            } catch (IOException ignored) { }

            return extractFeatureFromJson(jsonResponse);
        }
    }

    private static ArrayList<Event> extractFeatureFromJson(String earthquakeJSON) {

        if (TextUtils.isEmpty(earthquakeJSON)){
            return null;
        }

        ArrayList<Event> it = new ArrayList<>();

        try {
            JSONObject baseJsonResponse = new JSONObject(earthquakeJSON);
            JSONArray featureArray = baseJsonResponse.optJSONArray("features");

            for (int i = 0;  i < featureArray.length(); i++) {

                JSONObject firstFeature = featureArray.optJSONObject(i);
                JSONObject properties = firstFeature.optJSONObject("properties");

                String title = properties.optString("title");
                long time = properties.optLong("time");
                int tsunamiAlert = properties.optInt("tsunami");

                it.add(new Event(title, time, tsunamiAlert));
            }
        } catch (JSONException e) {
            Log.e(LOG_TAG, "Problem parsing the earthquake JSON results", e);
        }
        return it;
    }


}