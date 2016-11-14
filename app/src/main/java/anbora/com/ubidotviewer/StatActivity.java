package anbora.com.ubidotviewer;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.ubidots.ApiClient;
import com.ubidots.Value;



public class StatActivity extends AbstractActivity {

    String idVariable;
    String nameVariable;
    private TextView txtName;
    private TextView txtValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_stat);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        txtName = (TextView) findViewById(R.id.txtNameVariable);
        txtValue = (TextView) findViewById(R.id.txtValueVariable);



        if (getIntent() != null && getIntent().getExtras() != null){
            idVariable = getIntent().getExtras().getString(getString(R.string.key_variable));
            nameVariable = getIntent().getExtras().getString(getString(R.string.name_variable));
            setOnSharedPreferences(getString(R.string.key_variable), idVariable);
            setOnSharedPreferences(getString(R.string.name_variable), idVariable);
        } else {
            idVariable = getOnSharedPreferences(getString(R.string.key_variable));
            nameVariable = getIntent().getExtras().getString(getString(R.string.name_variable));
        }

        txtName.setText(nameVariable);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    @Override
    protected void onStart() {
        super.onStart();
        new ApiUbidots().execute();
    }

    public class ApiUbidots extends AsyncTask<Integer, Void, Value[]> {
        private final String API_KEY = "945a36d71fde0e6514770b5f27bb0c4f6553086c";

        @Override
        protected Value[] doInBackground(Integer... params) {
            ApiClient apiClient = new ApiClient(API_KEY);

            return apiClient.getVariable(idVariable).getValues();
        }

        @Override
        protected void onPostExecute(Value[] values) {
            if (values != null && values.length > 0) {

                txtValue.setText(String.valueOf(values[0].getValue()));
            }
        }
    }

}
