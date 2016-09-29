package anbora.com.ubidotviewer.listener;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;

import com.ubidots.DataSource;

import anbora.com.ubidotviewer.ListVariableActivity;
import anbora.com.ubidotviewer.MainActivity;
import anbora.com.ubidotviewer.R;

/**
 * Created by NoaD on 28/09/2016.
 */

public class OnClickItemDataSourceListener implements AdapterView.OnItemClickListener {

    private MainActivity listDataSource;
    private DataSource dataSource;

    public OnClickItemDataSourceListener(MainActivity listDataSource){
        this.listDataSource = listDataSource;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

        dataSource = (DataSource) adapterView.getItemAtPosition(i);

        Intent intent = new Intent(listDataSource.getApplicationContext(), ListVariableActivity.class);
        intent.putExtra(listDataSource.getString(R.string.key_datasource), dataSource.getId());
        listDataSource.startActivity(intent);
    }
}
