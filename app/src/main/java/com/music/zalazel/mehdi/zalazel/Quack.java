package com.music.zalazel.mehdi.zalazel;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.NonNull;
import android.app.LoaderManager;
import android.content.Loader;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class Quack extends AppCompatActivity implements LoaderManager.LoaderCallbacks<ArrayList<Data>> {

    private static final String LOG_TAG = Quack.class.getSimpleName();
    private static final String USGS = "https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&starttime&limit=30";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);setContentView(R.layout.quack);

        View v = findViewById(R.id.loading_spinner);
        v.setVisibility(View.GONE);
        ConnectivityManager connect = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo net = connect.getActiveNetworkInfo();
        if (net != null && net.isConnected()){
            v.setVisibility(View.VISIBLE);
            LoaderManager nn = getLoaderManager();
            nn.initLoader(0, null, this);
        } else {
            TextView tt = findViewById(R.id.loading);
            tt.setText(R.string.no_internet_connection);
        }
    }

    @NonNull
    @Override
    public Loader<ArrayList<Data>> onCreateLoader(int i, Bundle bundle) {
        Log.e(LOG_TAG, " TEST: onCreateLoader");
        return new QueryUtils.ErathQuackLoder(Quack.this, USGS);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<ArrayList<Data>> loader, ArrayList<Data> data) {
        View v = findViewById(R.id.loading_spinner);
        v.setVisibility(View.GONE);
        if (data == null){
            TextView tt = findViewById(R.id.loading);
            tt.setText(R.string.no_erath);
        }
        CostumAdapetr cos = new CostumAdapetr(Quack.this, data);
        ListView li = findViewById(R.id.list);
        li.setAdapter(cos);
    }

    @Override
    public void onLoaderReset(@NonNull Loader<ArrayList<Data>> loader) {
        Log.e(LOG_TAG, " TEST: onLoadReset");
    }



}


