package com.example.avjindersinghsekhon.minimaltodo.Utility;

import android.content.Context;

import com.example.avjindersinghsekhon.minimaltodo.Main.ToDoTab;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.UUID;

public class StoreRetrieveData {
    private Context mContext;
    private String mFileName;
    private int mListaActual;

    public StoreRetrieveData(Context context, String filename) {
        mContext = context;
        mFileName = filename;
        mListaActual = 0;
    }

    public static JSONArray toJSONArray(ArrayList<ToDoItem> items) throws JSONException {
        JSONArray jsonArray = new JSONArray();
        for (ToDoItem item : items) {
            JSONObject jsonObject = item.toJSON();
            jsonArray.put(jsonObject);
        }
        return jsonArray;
    }
    public static JSONArray toJSONArrayArray(ArrayList<ToDoTab<ToDoItem>> items) throws JSONException {
        JSONArray jsonArrayArray = new JSONArray();

        for(ToDoTab<ToDoItem> toDoList : items){
            JSONObject tabJson = toDoList.toJSON();
            jsonArrayArray.put(tabJson);
        }
        return jsonArrayArray;
    }

    public void saveArrayToFile(ArrayList<ToDoTab<ToDoItem>> items) throws JSONException, IOException {
        FileOutputStream fileOutputStream;
        OutputStreamWriter outputStreamWriter;
        fileOutputStream = mContext.openFileOutput(mFileName, Context.MODE_PRIVATE);
        outputStreamWriter = new OutputStreamWriter(fileOutputStream);
        outputStreamWriter.write(toJSONArrayArray(items).toString());
        outputStreamWriter.close();
        fileOutputStream.close();
    }

    public void saveToFile(ArrayList<ToDoItem> items, UUID id) throws JSONException, IOException {
       ArrayList<ToDoTab<ToDoItem>> arregloDeArreglo = loadArrayFromFile();
       int i = 0, j = 0;
       boolean encontre = false;
       while(i<arregloDeArreglo.size() && !encontre){
           ToDoTab<ToDoItem> tabActual = arregloDeArreglo.get(i);
           while (j<tabActual.getItems().size() && !encontre){
               if(tabActual.getItems().get(j).getIdentifier() == id){
                   encontre = true;
                   arregloDeArreglo.get(i).setItems(items);
               }
               j++;
           }
           i++;
       }
       if(encontre){
           //mListaActual = i-1;
           saveArrayToFile(arregloDeArreglo);
       }

    }
    public void saveToFile(ArrayList<ToDoItem> items, int p) throws IOException, JSONException {
        ArrayList<ToDoTab<ToDoItem>> arregloDeArreglo = loadArrayFromFile();
        arregloDeArreglo.get(p).setItems(items);
        saveArrayToFile(arregloDeArreglo);
    }

    public ArrayList<ToDoItem> loadFromFile() throws IOException, JSONException {
        ArrayList<ToDoItem> items = new ArrayList<>();
        BufferedReader bufferedReader = null;
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = mContext.openFileInput(mFileName);
            StringBuilder builder = new StringBuilder();
            String line;
            bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
            while ((line = bufferedReader.readLine()) != null) {
                builder.append(line);
            }

            JSONArray jsonArray = (JSONArray) new JSONTokener(builder.toString()).nextValue();
            for (int i = 0; i < jsonArray.length(); i++) {
                ToDoItem item = new ToDoItem(jsonArray.getJSONObject(i));
                items.add(item);
            }


        } catch (FileNotFoundException fnfe) {
            //do nothing about it
            //file won't exist first time app is run
        } finally {
            if (bufferedReader != null) {
                bufferedReader.close();
            }
            if (fileInputStream != null) {
                fileInputStream.close();
            }

        }
        return items;
    }
    public ArrayList<ToDoTab<ToDoItem>> loadArrayFromFile()  {
        ArrayList<ToDoTab<ToDoItem>> arrayItems = new ArrayList<>();
        BufferedReader bufferedReader = null;
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = mContext.openFileInput(mFileName);
            StringBuilder builder = new StringBuilder();
            String line;
            bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
            while ((line = bufferedReader.readLine()) != null) {
                builder.append(line);
            }
            JSONArray jsonArrayArray = (JSONArray) new JSONTokener(builder.toString()).nextValue();
            JSONObject jsonObject;
            for (int i = 0; i < jsonArrayArray.length(); i++) {

                jsonObject = jsonArrayArray.getJSONObject(i);
                arrayItems.add(convertToJsonObject(jsonObject));
            }


        } catch (FileNotFoundException fnfe) {
            //do nothing about it
            //file won't exist first time app is run
        } catch (JSONException | IOException e){
            e.printStackTrace();
        }
         finally {
            try{
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
            }
            catch (IOException e){
                e.printStackTrace();
            }

        }
        return arrayItems;
    }

    private ToDoTab<ToDoItem> convertToJsonObject(JSONObject jo){
        ToDoTab<ToDoItem> ret = new ToDoTab<ToDoItem>();
        ArrayList<ToDoItem> items = new ArrayList<>();
        JSONArray jsonArray = null;
        try {
            jsonArray = jo.getJSONArray("items");

        for (int i = 0; i < jsonArray.length(); i++) {
            ToDoItem item = new ToDoItem(jsonArray.getJSONObject(i));
            items.add(item);
        }
        ret.setItems(items);
        ret.setTitulo(jo.getString("titulo"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return ret;
    }
    public ArrayList<ToDoItem> loadFromFile(int position) throws IOException, JSONException {
       ArrayList<ToDoTab<ToDoItem>> arr = loadArrayFromFile();
       if(arr.size()==0)
           return new ArrayList<>();

       return arr.get(position).getItems();
    }

    public ArrayList<ToDoItem> getArrayListFromId(UUID id) {
        ArrayList<ToDoItem> ret = null;
        ArrayList<ToDoTab<ToDoItem>> a = loadArrayFromFile();
        int i =0,j=0;
        boolean encontre = false;
        while(i<a.size() && !encontre){
            ToDoTab<ToDoItem> tabActual = a.get(i);
            while (j<tabActual.getItems().size() && !encontre){
                if(tabActual.getItems().get(j).getIdentifier() == id){
                    encontre = true;
                    ret = a.get(i).getItems();
                }
                j++;
            }
            i++;
        }
        return ret;
    }
}
