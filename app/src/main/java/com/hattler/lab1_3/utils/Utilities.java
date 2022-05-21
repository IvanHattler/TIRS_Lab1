package com.hattler.lab1_3.utils;

import com.hattler.lab1_3.domain.Todo;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class Utilities {
    public static List<Todo> getDefaultList(){
        ArrayList<Todo> result = new ArrayList<>();

        for (int i = 1; i < 30; i += 2){
            Todo todo = new Todo();
            todo.setId(i);
            todo.setName("Todo" + i);
            todo.setDescription("Описание дела" + i);
            todo.setDate_start(Timestamp.valueOf(String.format("%d-%d-%d 14:00:00", 2022, 5, i)));
            todo.setDate_finish(Timestamp.valueOf(String.format("%d-%d-%d 15:00:00", 2022, 5, i)));
            result.add(todo);
        }
        return result;
    }
}
