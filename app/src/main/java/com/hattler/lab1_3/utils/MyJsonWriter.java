package com.hattler.lab1_3.utils;

import android.content.Context;
import android.util.JsonWriter;

import com.hattler.lab1_3.Todo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Writer;
import java.sql.Timestamp;
import java.util.List;

public class MyJsonWriter {
    public static void writeJsonStream(Writer output, List<Todo> todos) throws IOException {
        JsonWriter jsonWriter = new JsonWriter(output);

        jsonWriter.beginArray();
        for (int i = 0; i < todos.size(); i++){
            Todo todo = todos.get(i);
            jsonWriter.beginObject();

            long date_start = 0;
            long date_finish = 0;
            if (todo.getDate_start() != null){
                date_start = todo.getDate_start().getTime();
            }
            if (todo.getDate_finish() != null){
                date_finish = todo.getDate_finish().getTime();
            }

            jsonWriter.name("id").value(todo.getId());
            jsonWriter.name("date_start").value(date_start);
            jsonWriter.name("date_finish").value(date_finish);
            jsonWriter.name("name").value(todo.getName());
            jsonWriter.name("description").value(todo.getDescription());

            jsonWriter.endObject();
        }
        jsonWriter.endArray();
    }

    public static void writeText(String fileName, List<Todo> todos) throws IOException {
        try(BufferedWriter reader = new BufferedWriter(new FileWriter(fileName))){
            writeJsonStream(reader, todos);
        }
    }
}
