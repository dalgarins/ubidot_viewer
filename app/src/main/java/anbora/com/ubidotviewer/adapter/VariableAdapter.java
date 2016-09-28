package anbora.com.ubidotviewer.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ubidots.Variable;

import java.util.ArrayList;
import java.util.List;

import anbora.com.ubidotviewer.R;

/**
 * Created by Usuario on 27/09/2016.
 */

public class VariableAdapter extends ArrayAdapter<Variable>{

    public VariableAdapter(Context context, ArrayList<Variable> arrayList){
        super(context, 0, arrayList);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) getContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        //Salvando la referencia del View de la fila
        View listItemView = convertView;

        //Comprobando si el View no existe
        if (null == convertView) {
            //Si no existe, entonces inflarlo con two_line_list_item.xml
            listItemView = inflater.inflate(
                    R.layout.list_view_item_variables,
                    parent,
                    false);
        }

        Variable variable = getItem(position);

        TextView txtname_data_source = (TextView) listItemView.findViewById(R.id.text_view_name_variable);
        txtname_data_source.setText(variable.getName());

        TextView txtid_data_source = (TextView)listItemView.findViewById(R.id.text_view_id_variable);
        txtid_data_source.setText(variable.getId());

        ImageView imageViewPhoto = (ImageView) listItemView.findViewById(R.id.img_variable);

        //Agrega el icono si el prosesor ha sido evaluado o no
        imageViewPhoto.setImageDrawable(getContext().getResources().getDrawable(R.drawable.variable_icon));

        return listItemView;
    }
}
