package anbora.com.ubidotviewer;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.ubidots.ApiClient;
import com.ubidots.Value;
import com.ubidots.Variable;

import java.util.ArrayList;
import java.util.List;

public class StatActivity extends AppCompatActivity {

    private String idVariable;
    private LineChart barCharGraphics;
    private List<Entry> entries = new ArrayList<Entry>();
    private ArrayList<String> labels = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stat);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        idVariable = getIntent().getExtras().getString(getString(R.string.key_variable));

        barCharGraphics = (LineChart) findViewById(R.id.bar_char_graphics);

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
            if (values != null) {
                entries.clear();
                labels.clear();

                for (int i = 0; i < values.length; i++) {
                    entries.add(new BarEntry(i, Float.parseFloat(String.valueOf(values[i].getValue()))));
                    labels.add(String.valueOf(values[i].getValue()));
                }
                LineDataSet dataset = new LineDataSet(entries, " ");
                LineData data = new LineData(dataset);
                barCharGraphics.setData(data);
                barCharGraphics.invalidate();
            }
        }
    }

}
