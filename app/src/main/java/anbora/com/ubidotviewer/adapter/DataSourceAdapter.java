package anbora.com.ubidotviewer.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

import anbora.com.ubidotviewer.R;

/**
 * Created by Usuario on 27/09/2016.
 */

public class DataSourceAdapter extends ArrayAdapter {

    public DataSourceAdapter(Context context, ArrayList list){
        super(context, R.layout.list_view_item_datasource, list);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return super.getView(position, convertView, parent);
    }
}
