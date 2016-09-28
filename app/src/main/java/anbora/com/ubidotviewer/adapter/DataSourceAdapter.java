package anbora.com.ubidotviewer.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ubidots.DataSource;

import java.util.ArrayList;

import anbora.com.ubidotviewer.R;

/**
 * Created by Usuario on 27/09/2016.
 */

public class DataSourceAdapter extends ArrayAdapter<DataSource> {

    public DataSourceAdapter(Context context, ArrayList<DataSource> list){
        super(context, 0, list);
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
                    R.layout.list_view_item_datasource,
                    parent,
                    false);
        }

        DataSource dataSource = getItem(position);

        TextView txtname_data_source = (TextView) listItemView.findViewById(R.id.text_view_name_data_source);
        txtname_data_source.setText(dataSource.getName());

        TextView txtid_data_source = (TextView)listItemView.findViewById(R.id.text_view_id_data_source);
        txtid_data_source.setText(dataSource.getId());

        ImageView imageViewPhoto = (ImageView) listItemView.findViewById(R.id.img_data_source);

        //Agrega el icono si el prosesor ha sido evaluado o no
        imageViewPhoto.setImageDrawable(getContext().getResources().getDrawable(R.drawable.datasource_icon));

        return listItemView;
    }
}
