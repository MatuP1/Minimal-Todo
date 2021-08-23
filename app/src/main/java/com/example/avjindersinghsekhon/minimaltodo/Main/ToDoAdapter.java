package com.example.avjindersinghsekhon.minimaltodo.Main;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.example.avjindersinghsekhon.minimaltodo.R;

import java.util.ArrayList;

public class ToDoAdapter<ToDoItem> extends ArrayAdapter<ToDoItem> {
    public ToDoAdapter(Activity c, ArrayList<ToDoItem> todos){
        super(c,0, todos);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_circle_try, parent, false);
        }



        return listItemView;
    }
}
