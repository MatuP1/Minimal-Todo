package com.example.avjindersinghsekhon.minimaltodo.Main;

import com.example.avjindersinghsekhon.minimaltodo.Utility.ToDoItem;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ToDoTab<T extends  ToDoItem> {
    private ArrayList<T> mItems;
    private static final String TABARRAY = "items";
    private static final String TABTITLE = "titulo";
    private String mTitulo;

    public ToDoTab(){
        mItems = new ArrayList<T>();
        mTitulo = "";
    }

    public ToDoTab(ArrayList<T> a, String t){
        mItems = a;
        mTitulo = t;
    }


    public String getTitulo() {
        return mTitulo;
    }

    public void setTitulo(String mTitulo) {
        this.mTitulo = mTitulo;
    }

    public ArrayList<T> getItems() {
        return mItems;
    }

    public void setItems(ArrayList<T> mItems) {
        this.mItems = mItems;
    }

    public JSONObject toJSON() throws JSONException {
        JSONObject jsonObject = new JSONObject();
        JSONArray items = new JSONArray();
        jsonObject.put(TABTITLE, mTitulo);
        for (T item : mItems) {
            JSONObject itemJson = item.toJSON();
            items.put(itemJson);
        }
        jsonObject.put(TABARRAY, items);
        return jsonObject;
    }
}
