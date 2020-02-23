package edu.gatech.seclass.wordfind6300;

import android.content.Context;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class Board extends BaseAdapter {

    private Context mContext;
    private String letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    // 1
    public Board(Context context, String letters) {
        this.mContext = context;
        this.letters = letters;
    }

    @Override
    public int getCount() {
        return 0;
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
        TextView dummyTextView = new TextView(mContext);
        dummyTextView.setText(String.valueOf(position));
        return dummyTextView;
    }
}
