package com.example.korshreddern.a04simplenote;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Korshreddern on 22-Apr-16.
 */
public class ListViewAdapter extends BaseAdapter {
    Context mContext;
    public ArrayList<Note> noteArrayList;


    public ListViewAdapter(Context mContext, ArrayList<Note> noteArrayList) {
        this.mContext = mContext;
        this.noteArrayList = noteArrayList;
    }

    @Override
    public int getCount() {
        return noteArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater mInflater =
                (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if(convertView == null)
            convertView = mInflater.inflate(R.layout.row_note, parent, false);

        TextView textView_message = (TextView)convertView.findViewById(R.id.row_message);
        textView_message.setText(noteArrayList.get(position).message);
        TextView textView_date = (TextView)convertView.findViewById(R.id.row_date);
        textView_date.setText(noteArrayList.get(position).date);

        return convertView;
    }
}
