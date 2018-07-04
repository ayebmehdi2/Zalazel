package com.music.zalazel.mehdi.zalazel;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.app.LoaderManager;
import android.content.Loader;
import android.support.v7.app.AppCompatActivity;


import java.util.ArrayList;

public class tsaunamii extends AppCompatActivity implements LoaderManager.LoaderCallbacks<ArrayList<Event>>{

    private static final String USGS_REQUEST_URL = "https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&starttime=2014-01-01&endtime=2014-12-01&minmagnitude=7";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_tsunamii); }

    @NonNull
    @Override
    public Loader<ArrayList<Event>> onCreateLoader(int id, @Nullable Bundle args) {
        return new QueryUtils.TsunamiAsyncTask(this, USGS_REQUEST_URL);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<ArrayList<Event>> loader, ArrayList<Event> data) {
        }

    @Override
    public void onLoaderReset(@NonNull Loader<ArrayList<Event>> loader) {

    }


}
