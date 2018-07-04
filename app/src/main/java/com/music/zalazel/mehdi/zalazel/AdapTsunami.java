package com.music.zalazel.mehdi.zalazel;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class AdapTsunami extends ArrayAdapter<Event> {

    AdapTsunami(Context countext, ArrayList<Event> a){
        super(countext, 0, a);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final Event user = getItem(position);

        if (convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.tsaunamiii, parent, false);
        }

        TextView titleTextView =  convertView.findViewById(R.id.title);
        titleTextView.setText(user.getTitle());

        TextView dateTextView =  convertView.findViewById(R.id.date);
        dateTextView.setText(getDateString(user.getTime()));

        TextView tsunamiTextView =  convertView.findViewById(R.id.tsunami_alert);
        tsunamiTextView.setText(getTsunamiAlertString(user.getTsunamiAlert()));

        return convertView;
    }

    private String getDateString(long timeInMilliseconds) {
        @SuppressLint("SimpleDateFormat") SimpleDateFormat formatter = new SimpleDateFormat("EEE, d MMM yyyy 'at' HH:mm:ss z");
        return formatter.format(timeInMilliseconds);
    }

    private String getTsunamiAlertString(int tsunamiAlert) {
        switch (tsunamiAlert) {
            case 0:
                return String.valueOf(R.string.alert_no);
            case 1:
                return String.valueOf(R.string.alert_yes);
            default:
                return String.valueOf(R.string.alert_not_available);
        }
    }
}
