package com.mattias.economics;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

/**
 * Created by Mattias on 2015-01-06.
 */
public class EconomicsAdapter extends CursorAdapter {
    public EconomicsAdapter(Context context, Cursor c, boolean autoRequery) {
        super(context, c, autoRequery);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent){
        View view = LayoutInflater.from(context).inflate(R.layout.economicsrow, parent, false);
        ViewHolder holder = new ViewHolder();
        holder.id = (TextView) view.findViewById(R.id.economics_id);
        holder.title = (TextView) view.findViewById(R.id.economics_title);
        holder.amount = (TextView) view.findViewById(R.id.economics_amount);
        holder.date = (TextView) view.findViewById(R.id.economics_date);
        view.setTag(holder);
        return view;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        ViewHolder holder = (ViewHolder) view.getTag();

        holder.id.setText("ID: " + cursor.getString(0));
        holder.title.setText("Title: " + cursor.getString(3));
        holder.amount.setText("Amount: " + cursor.getString(2));
        holder.date.setText("Date: " + cursor.getString(1));
    }

    private class ViewHolder {
        TextView id;
        TextView title;
        TextView amount;
        TextView date;
    }
}
