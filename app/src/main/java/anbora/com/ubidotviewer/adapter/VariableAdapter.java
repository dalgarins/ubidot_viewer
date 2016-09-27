package anbora.com.ubidotviewer.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.List;

import anbora.com.ubidotviewer.R;

/**
 * Created by Usuario on 27/09/2016.
 */

public class VariableAdapter extends ArrayAdapter{

    public VariableAdapter(Context context, ArrayList arrayList){
        super(context, R.layout.list_view_item_variables, arrayList);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return super.getView(position, convertView, parent);
    }
}
