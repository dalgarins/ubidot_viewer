package anbora.com.ubidotviewer;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;

import com.ubidots.ApiClient;
import com.ubidots.DataSource;
import com.ubidots.Variable;

import java.util.ArrayList;

import anbora.com.ubidotviewer.adapter.DataSourceAdapter;
import anbora.com.ubidotviewer.listener.OnClickItemDataSourceListener;

public class MainActivity extends AppCompatActivity {

    private DataSourceAdapter dataSourceAdapter;
    private ListView listDataSource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        listDataSource = (ListView) findViewById(R.id.list_view_data_source);
        dataSourceAdapter = new DataSourceAdapter(this, new ArrayList<DataSource>());
        listDataSource.setAdapter(dataSourceAdapter);

        listDataSource.setOnItemClickListener(new OnClickItemDataSourceListener(this));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    protected void onStart() {
        super.onStart();
        new ApiUbidots().execute();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    public class ApiUbidots extends AsyncTask<Integer, Void, DataSource[]> {
        private final String API_KEY = "945a36d71fde0e6514770b5f27bb0c4f6553086c";

        @Override
        protected DataSource[] doInBackground(Integer... params) {
            ApiClient apiClient = new ApiClient(API_KEY);

            return apiClient.getDataSources();
        }

        @Override
        protected void onPostExecute(DataSource[] dataSources) {
            if (dataSources != null){
                dataSourceAdapter.clear();
                for (DataSource data : dataSources){
                    dataSourceAdapter.add(data);
                }
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
