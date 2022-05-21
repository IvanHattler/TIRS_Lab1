package com.hattler.lab1_3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.CalendarView;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.hattler.lab1_3.services.TodoService;
import com.hattler.lab1_3.utils.MyJsonReader;
import com.hattler.lab1_3.utils.MyJsonWriter;
import com.hattler.lab1_3.utils.Utilities;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final String MAIN_FILE_PATH = getFilesDir().getAbsolutePath() + "/todos.json";
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //String[]  list = fileList();
        //deleteFile("todos.json");
        //deleteFile("todos");
        //list = fileList();

        TodoService.clearJson(MAIN_FILE_PATH);
        TodoService.setTodos(MAIN_FILE_PATH, Utilities.getDefaultList());

        String a = TodoService.getJsonText(MAIN_FILE_PATH);

        CalendarView calendarView = findViewById(R.id.calendarView);
        calendarView.setOnDateChangeListener((view, year, month, dayOfMonth) -> {
            month++;
            String dateString = String.format("%d-%d-%d 00:00:00", year, month, dayOfMonth);
            String dateString2 = String.format("%d-%d-%d 01:00:00", year, month, dayOfMonth);

            Todo newTodo = new Todo();
            newTodo.setName("asf");
            newTodo.setDescription("ewq");
            newTodo.setDate_start(Timestamp.valueOf(dateString));
            newTodo.setDate_finish(Timestamp.valueOf(dateString2));

            TodoService.addTodo(MAIN_FILE_PATH, newTodo);

            setListView(MAIN_FILE_PATH);

            List<Todo> a1 = TodoService.getTodos(MAIN_FILE_PATH);
        });

        int BOOKSHELF_ROWS = 5;
        int BOOKSHELF_COLUMNS = 5;

        TableLayout tableLayout = (TableLayout) findViewById(R.id.tableLayout);

        int hour = 0;
        for (int i = 0; i < BOOKSHELF_ROWS; i++) {
            TableRow tableRow = new TableRow(this);
            //tableRow.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,
            //        TableRow.LayoutParams.WRAP_CONTENT));
            //tableRow.setBackgroundResource(R.drawable.shelf);

            for (int j = 0; j < BOOKSHELF_COLUMNS; j++) {
                TextView textView = new TextView(this);
                textView.setText(Integer.toString(hour));

                tableRow.addView(textView, j);
                hour++;
            }

            tableLayout.addView(tableRow, i);
        }

        setListView(MAIN_FILE_PATH);
    }

    private void setListView(String MAIN_FILE_PATH) {
        ListView listView = findViewById(R.id.mainListView);
        try {
            List<Todo> todos = TodoService.getTodos(MAIN_FILE_PATH);
            ArrayList<String> todosText = toStringList(todos);
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                    android.R.layout.simple_list_item_1, todosText);
            listView.setAdapter(adapter);
        } catch(Exception e)  {
            e.printStackTrace();
        }
    }

    @NonNull
    private ArrayList<String> toStringList(List<Todo> todos) {
        ArrayList<String> todosText = new ArrayList<String>();
        for (Todo todo: todos) {
            todosText.add(todo.toString());
        }
        return todosText;
    }
}