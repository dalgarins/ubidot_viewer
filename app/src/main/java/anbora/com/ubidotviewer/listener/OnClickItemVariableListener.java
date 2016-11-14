package anbora.com.ubidotviewer.listener;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;

import com.ubidots.DataSource;
import com.ubidots.Variable;

import anbora.com.ubidotviewer.ListVariableActivity;
import anbora.com.ubidotviewer.R;
import anbora.com.ubidotviewer.StatActivity;

/**
 * Created by NoaD on 28/09/2016.
 */

public class OnClickItemVariableListener implements AdapterView.OnItemClickListener {

    private ListVariableActivity listVariableActivity;
    private Variable variable;

    public OnClickItemVariableListener(ListVariableActivity listVariableActivity){
        this.listVariableActivity = listVariableActivity;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

        variable = (Variable) adapterView.getItemAtPosition(i);

        Intent intent = new Intent(listVariableActivity.getApplicationContext(), StatActivity.class);
        intent.putExtra(listVariableActivity.getString(R.string.key_variable), variable.getId());
        intent.putExtra(listVariableActivity.getString(R.string.name_variable), variable.getName());
        listVariableActivity.startActivity(intent);
    }
}
