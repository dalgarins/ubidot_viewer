package anbora.com.ubidotviewer;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by NoaD on 01/10/2016.
 */

public abstract class AbstractActivity extends AppCompatActivity{

    public static final String PREFERENCES_FILE = "info_data";


    /**
     * Guarda la informacion dentro de las shared preferences de android
     * @param key String con la key
     * @param value String con el valor a guardar
     */
    protected void setOnSharedPreferences(String key, String value){
        SharedPreferences sharedPref = getSharedPreferences(PREFERENCES_FILE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(key, value);
        editor.commit();
    }

    /**
     * Obtiene la informacion de las shared preferences
     * @param key String con la key de la informacion
     * @return String con el valor guardado
     */
    protected String getOnSharedPreferences(String key){
        SharedPreferences sharedPref = getSharedPreferences(PREFERENCES_FILE, Context.MODE_PRIVATE);
        return sharedPref.getString(key, null);
    }
}
