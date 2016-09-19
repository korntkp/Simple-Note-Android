package com.example.korshreddern.a04simplenote.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.korshreddern.a04simplenote.R;
import com.example.korshreddern.a04simplenote.model.Note;

import java.util.ArrayList;

/**
 * Created by Korshreddern on 22-Apr-16.
 */
public class ListViewAdapter extends BaseAdapter {
    Context mContext;
    public ArrayList<Note> noteArrayList;
    LayoutInflater mInflater;


    public ListViewAdapter(Context mContext, ArrayList<Note> noteArrayList) {
        this.mContext = mContext;
        this.noteArrayList = noteArrayList;
        mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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
        if(convertView == null)
            convertView = mInflater.inflate(R.layout.row_note, parent, false);

        TextView textView_message = (TextView)convertView.findViewById(R.id.row_message);
        textView_message.setText(noteArrayList.get(position).message);
        TextView textView_date = (TextView)convertView.findViewById(R.id.row_date);
        textView_date.setText(noteArrayList.get(position).date);

        /**
         * Add Animation to each view
         */
        /*
        textView_message.setAnimation(AnimationUtils.loadAnimation(mContext, R.anim.row_anim));
        textView_date.setAnimation(AnimationUtils.loadAnimation(mContext, R.anim.row_anim));
        */

        return convertView;
    }
}
