package com.example.swr2d2.agendacel.app;

import android.content.Context;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

/**
 * Created by swr2d2 on 09/06/2016.
 */

public class ViewHelper {


    public static ArrayAdapter<String> createArrayAdapter(Context ctx, Spinner spinner)
    {
        ArrayAdapter arrayAdapter = new ArrayAdapter<String>(ctx, android.R.layout.simple_spinner_item);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(arrayAdapter);

        return arrayAdapter;

    }


}
