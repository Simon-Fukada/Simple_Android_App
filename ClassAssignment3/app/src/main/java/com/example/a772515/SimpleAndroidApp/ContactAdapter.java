package com.example.a772515.SimpleAndroidApp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
/*
Custom layout for listview items
 */

public class ContactAdapter extends ArrayAdapter<Agent> {
    public ContactAdapter(Context context, ArrayList<Agent> agents) {
        super(context, 0, agents);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // Get object at this positio in list
        Agent agent = getItem(position);
        // Inflate custom view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.listviewlayout, parent, false);
        }

        TextView tvAgent = (TextView) convertView.findViewById(R.id.ListLayout);
        tvAgent.setText(agent.getAgtFirstName());
        return convertView;
    }
}