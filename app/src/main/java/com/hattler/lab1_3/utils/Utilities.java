package com.hattler.lab1_3.utils;

import com.hattler.lab1_3.domain.Todo;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Utilities {
    public static List<Todo> getDefaultList(){
        ArrayList<Todo> result = new ArrayList<>();

        for (int i = 1; i < 30; i += 2){
            Todo todo = new Todo();
            todo.setId(i);
            todo.setName("Todo" + i);
            todo.setDescription("Описание дела" + i);
            todo.setDate_start(Timestamp.valueOf(String.format("%d-%d-%d %d:00:00", 2022, 5, i, 1 + i % 10)));
            todo.setDate_finish(Timestamp.valueOf(String.format("%d-%d-%d %d:00:00", 2022, 5, i, 1 + i % 10)));
            result.add(todo);
        }
        return result;
    }

    public static int getDayOfMonth(Timestamp timestamp){
        if (timestamp == null)
            return 0;

        java.util.Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(timestamp.getTime());
        return cal.get(Calendar.DAY_OF_MONTH);
    }

    public static int getHour(Timestamp timestamp){
        if (timestamp == null)
            return 0;

        java.util.Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(timestamp.getTime());
        return cal.get(Calendar.HOUR_OF_DAY);
    }

    public static int getMonth(Timestamp timestamp){
        if (timestamp == null)
            return 0;

        java.util.Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(timestamp.getTime());
        return cal.get(Calendar.MONTH);
    }

    public static int getYear(Timestamp timestamp){
        if (timestamp == null)
            return 0;

        java.util.Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(timestamp.getTime());
        return cal.get(Calendar.YEAR);
    }

    public static ArrayList<String> toStringList(List<Todo> todos) {
        ArrayList<String> todosText = new ArrayList<>();
        for (Todo todo: todos) {
            todosText.add(todo.toString());
        }
        return todosText;
    }
}
