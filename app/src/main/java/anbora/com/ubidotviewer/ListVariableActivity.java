package anbora.com.ubidotviewer;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;

import com.ubidots.ApiClient;
import com.ubidots.DataSource;
import com.ubidots.Variable;

import java.util.ArrayList;

import anbora.com.ubidotviewer.adapter.VariableAdapter;

public class ListVariableActivity extends AppCompatActivity {

    private String idDataSource;
    private ListView listVariable;
    private VariableAdapter variableAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_variable);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        idDataSource = getIntent().getExtras().getString(getString(R.string.key_datasource));

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        listVariable = (ListView) findViewById(R.id.list_view_variable);
        variableAdapter = new VariableAdapter(this, new ArrayList<Variable>());
        listVariable.setAdapter(variableAdapter);

    }

    @Override
    protected void onStart() {
        super.onStart();
        new ApiUbidots().execute();
    }

    public class ApiUbidots extends AsyncTask<Integer, Void, Variable[]> {
        private final String API_KEY = "945a36d71fde0e6514770b5f27bb0c4f6553086c";

        @Override
        protected Variable[] doInBackground(Integer... params) {
            ApiClient apiClient = new ApiClient(API_KEY);

            return apiClient.getDataSource(idDataSource).getVariables();
        }

        @Override
        protected void onPostExecute(Variable[] variables) {
            if (variables != null){
                variableAdapter.clear();
                for (Variable data : variables){
                    variableAdapter.add(data);
                }
            }
        }
    }

}
