package anbora.com.ubidotviewer;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ListView;

import com.ubidots.ApiClient;
import com.ubidots.Variable;

import java.util.ArrayList;

import anbora.com.ubidotviewer.adapter.VariableAdapter;
import anbora.com.ubidotviewer.listener.OnClickItemVariableListener;


public class ListVariableActivity extends AbstractActivity {

    private String idDataSource;
    private ListView listVariable;
    private VariableAdapter variableAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_list_variable);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (getIntent() != null && getIntent().getExtras() != null){
            idDataSource = getIntent().getExtras().getString(getString(R.string.key_datasource));
            setOnSharedPreferences(getString(R.string.key_datasource), idDataSource);
        } else {
            idDataSource = getOnSharedPreferences(getString(R.string.key_datasource));
        }

        listVariable = (ListView) findViewById(R.id.list_view_variable);
        variableAdapter = new VariableAdapter(this, new ArrayList<Variable>());
        listVariable.setAdapter(variableAdapter);

        listVariable.setOnItemClickListener(new OnClickItemVariableListener(this));

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

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
