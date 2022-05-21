package com.hattler.lab1_3.utils;

import android.content.Context;

import com.hattler.lab1_3.Todo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class MyJsonReader {
    public static List<Todo> read(String fileName) throws IOException, JSONException {
        String jsonText = readText(fileName);

        JSONArray arr = new JSONArray(jsonText);
        ArrayList<Todo> result = new ArrayList<Todo>(arr.length());

        for(int i=0;i < arr.length();i++) {
            JSONObject jsonRoot = arr.getJSONObject(i);

            int id= jsonRoot.getInt("id");
            String name = jsonRoot.getString("name");
            Timestamp date_start = new Timestamp(jsonRoot.getLong("date_start"));
            Timestamp date_finish = new Timestamp(jsonRoot.getLong("date_finish"));
            String description = jsonRoot.getString("description");

            Todo todo = new Todo();
            todo.setId(id);
            todo.setName(name);
            todo.setDate_start(date_start);
            todo.setDate_finish(date_finish);
            todo.setDescription(description);

            result.add(todo);
        }

        return result;
    }

    public static String readText(String fileName) throws IOException {
        try(BufferedReader br = new BufferedReader(new FileReader(fileName))){
            StringBuilder sb= new StringBuilder();
            String s = null;
            while((s = br.readLine())!=null) {
                sb.append(s);
                sb.append("\n");
            }
            return sb.toString();
        }
    }
}
