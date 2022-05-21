package com.hattler.lab1_3.services;

import com.hattler.lab1_3.domain.Todo;
import com.hattler.lab1_3.utils.MyJsonReader;
import com.hattler.lab1_3.utils.MyJsonWriter;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TodoService {
    public static List<Todo> getTodos(String fileName){
        try {
            return MyJsonReader.read(fileName);
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    public static void setTodos(String fileName, List<Todo> todos){
        try {
            MyJsonWriter.writeText(fileName, todos);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void addTodo(String fileName, Todo todo) {
        try {
            List<Todo> todos = MyJsonReader.read(fileName);
            todos.add(todo);
            MyJsonWriter.writeText(fileName, todos);
        } catch(Exception e)  {
            e.printStackTrace();
        }
    }

    public static String getJsonText(String fileName){
        try {
            return MyJsonReader.readText(fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static void clearJson(String fileName){
        try {
            MyJsonWriter.writeText(fileName, new ArrayList<>());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
