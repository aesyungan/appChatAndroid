package com.example.xl.chatandroid;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

/**
 * Created by XL on 18/10/2017.
 */

public class AdapterMessage extends ArrayAdapter<message> {
    String name ="";
    public AdapterMessage(Context context, List<message> dato,String name) {
        super(context, 0, dato);
        this.name=name;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        message item = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.row_message, parent, false);
        }

        TextView text = (TextView) convertView.findViewById(R.id.text);
        LinearLayout colorMessage = (LinearLayout) convertView.findViewById(R.id.colorMessage);
        LinearLayout linearLayoutposition = (LinearLayout) convertView.findViewById(R.id.LinearLayoutposition);
        text.setText(item.getText());
        if (item.author.equals(name)) {//color azul

            colorMessage.setBackgroundResource(R.drawable.shapeyour);
            text.setTextColor(Color.WHITE);
            linearLayoutposition.setGravity(Gravity.RIGHT);
        }


        return convertView;
    }
}
