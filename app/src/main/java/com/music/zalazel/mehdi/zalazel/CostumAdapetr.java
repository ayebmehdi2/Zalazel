package com.music.zalazel.mehdi.zalazel;

import android.content.Context;
import java.text.DecimalFormat;
import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import java.util.ArrayList;

public class CostumAdapetr extends ArrayAdapter<Data> {

    private Context cntxt;
    CostumAdapetr(Context countext, ArrayList<Data> a){
        super(countext, 0, a);
        this.cntxt = countext;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final Data user = getItem(position);

        if (convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item, parent, false);
        }




        DecimalFormat Formatter = new DecimalFormat();

        TextView mag = convertView.findViewById(R.id.magnitude);

        assert user != null;
        mag.setText(Formatter.format(user.getMag()));



        GradientDrawable magnitudeCircle = (GradientDrawable) mag.getBackground();

        int magnitudeColor = getMagnitudeColor(user.getMag());

        magnitudeCircle.setColor(magnitudeColor);



        TextView placeKm = convertView.findViewById(R.id.placekm);
        placeKm.setText(user.getPlaceKm());

        TextView placeV = convertView.findViewById(R.id.placeV);
        placeV.setText(user.getPlaceV());

        TextView time = convertView.findViewById(R.id.time);
        time.setText(user.getTime());

        TextView date = convertView.findViewById(R.id.date);
        date.setText(user.getDate());

        TextView type = convertView.findViewById(R.id.type);
        type.setText(user.getType());

        TextView title = convertView.findViewById(R.id.title);
        title.setText(user.getTitle());

        View tsunami = convertView.findViewById(R.id.tsunami);
        if (user.getTsunami() == 1){
            tsunami.setBackgroundResource(R.drawable.tsaunami_ye);
        } else {
            tsunami.setBackgroundResource(R.drawable.raduis_simple_gray);
        }

        Button detail = convertView.findViewById(R.id.more);
        detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final MediaPlayer m = MediaPlayer.create(cntxt, R.raw.aa);
                m.start();
                cntxt.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(user.getDetail())));
                m.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mediaPlayer) { m.release(); }});
            }
        });


        return convertView;
    }

    private int getMagnitudeColor(double magnitude) {
        int magnitudeColorResourceId;
        int magnitudeFloor = (int) Math.floor(magnitude);
        switch (magnitudeFloor) {
            case 0:
            case 1:
                magnitudeColorResourceId = R.color.magnitude1;
                break;
            case 2:
                magnitudeColorResourceId = R.color.magnitude2;
                break;
            case 3:
                magnitudeColorResourceId = R.color.magnitude3;
                break;
            case 4:
                magnitudeColorResourceId = R.color.magnitude4;
                break;
            case 5:
                magnitudeColorResourceId = R.color.magnitude5;
                break;
            case 6:
                magnitudeColorResourceId = R.color.magnitude6;
                break;
            case 7:
                magnitudeColorResourceId = R.color.magnitude7;
                break;
            case 8:
                magnitudeColorResourceId = R.color.magnitude8;
                break;
            case 9:
                magnitudeColorResourceId = R.color.magnitude9;
                break;
            default:
                magnitudeColorResourceId = R.color.magnitude10plus;
                break;
        }

        return ContextCompat.getColor(getContext(), magnitudeColorResourceId);
    }


}
