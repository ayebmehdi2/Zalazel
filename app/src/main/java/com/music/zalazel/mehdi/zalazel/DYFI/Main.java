package com.music.zalazel.mehdi.zalazel.DYFI;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.app.LoaderManager;
import android.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import com.music.zalazel.mehdi.zalazel.R;

public class Main extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Evnt> {

    private static final String USGS_REQUEST_URL =
            "https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&starttime=2016-01-01&endtime=2016-05-02&minfelt=50&minmagnitude=5";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dyfi);

        getLoaderManager().initLoader(0, null, this).forceLoad();

        }

    @NonNull
    @Override
    public Loader<Evnt> onCreateLoader(int id, @Nullable Bundle args) {
        return new Utils.dyAsyncTask(Main.this, USGS_REQUEST_URL);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<Evnt> loader, Evnt data) {
        updateUii(data);
    }

    @Override
    public void onLoaderReset(@NonNull Loader<Evnt> loader) {

    }





    private void updateUii(Evnt earthquake) {
        TextView titleTextView =  findViewById(R.id.titl);
        titleTextView.setText(earthquake.title);

        TextView tsunamiTextView =  findViewById(R.id.number_of_people);
        tsunamiTextView.setText(getString(R.string.num_people_felt_it, earthquake.numOfPeople));

        TextView magnitudeTextView =  findViewById(R.id.perceived_magnitude);
        magnitudeTextView.setText(earthquake.perceivedStrength);
    }
}