package com.hattler.lab1_3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.fragment.app.FragmentManager;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CalendarView;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.hattler.lab1_3.domain.Todo;
import com.hattler.lab1_3.services.TodoService;
import com.hattler.lab1_3.utils.Utilities;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<Todo> todos;
    private int selectedIndex;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final String MAIN_FILE_PATH = getFilesDir().getAbsolutePath() + "/todos.json";

        initTodos(MAIN_FILE_PATH);

        initHourTable();

        initCalendarView(MAIN_FILE_PATH);

        initListView(MAIN_FILE_PATH);
    }

    private void initTodos(String MAIN_FILE_PATH) {
        TodoService.clearJson(MAIN_FILE_PATH);
        TodoService.setTodos(MAIN_FILE_PATH, Utilities.getDefaultList());
        todos = TodoService.getTodos(MAIN_FILE_PATH);
    }

    private void initCalendarView(String fileName) {
        CalendarView calendarView = findViewById(R.id.calendarView);
        calendarView.setOnDateChangeListener((view, year, month, dayOfMonth) -> {
            clearHourTable();

            List<Todo> todos = TodoService.getTodos(fileName);
            for (Todo todo : todos) {
                Timestamp timestamp = todo.getDate_start();
                if (timestamp != null
                        && Utilities.getYear(timestamp) == year
                        && Utilities.getMonth(timestamp) == month
                        && Utilities.getDayOfMonth(timestamp) == dayOfMonth){
                    int hour = Utilities.getHour(todo.getDate_start());
                    TextView textView = findViewById(1488 + hour - 1);
                    textView.setBackgroundResource(R.color.purple_200);
                    textView.setText(String.format("[%s]", textView.getText()));
                    selectedIndex = todos.indexOf(todo);
                }
            }
        });
    }

    private void clearHourTable(){
        for (int i = 1; i < 25; i++){
            TextView textView = findViewById(1488 + i);
            textView.setBackgroundResource(R.color.white);
            CharSequence text = textView.getText();
            if (text.charAt(0) == '['){
                text = substring(text);
                textView.setText(text);
            }

        }
    }

    @NonNull
    private CharSequence substring(CharSequence text) {
        text = text.subSequence(1, text.length());
        text = text.subSequence(0, text.length() - 1);
        return text;
    }

    private void initHourTable() {
        final int HOUR_ROWS = 5;
        final int HOUR_COLUMNS = 5;
        TableLayout tableLayout = (TableLayout) findViewById(R.id.tableLayout);
        int hour = 1;
        for (int i = 0; i < HOUR_ROWS; i++) {
            TableRow tableRow = new TableRow(this);
            for (int j = 0; j < HOUR_COLUMNS && hour < 25; j++) {
                TextView textView = new TextView(this);
                String t = String.format(getString(R.string.hourTableFormat), hour - 1, hour);
                textView.setText(t);
                textView.setId(1488 + hour);
                textView.setTextSize(12);
                textView.setClickable(true);
                textView.setPadding(10,50,10,50);
                textView.setOnClickListener(v -> {
                    TextView tv = (TextView)v;
                    if (tv.getText().charAt(0) == '['){
                        onCreateDialog(tv).show();
                    }
                });
                tableRow.addView(textView, j);
                hour++;
            }

            tableLayout.addView(tableRow, i);
        }
    }

    private void initListView(String fileName) {
        ListView listView = findViewById(R.id.mainListView);
        try {
            List<Todo> todos = TodoService.getTodos(fileName);
            ArrayList<String> todosText = Utilities.toStringList(todos);
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                    android.R.layout.simple_list_item_1, todosText);
            listView.setAdapter(adapter);
        } catch(Exception e)  {
            e.printStackTrace();
        }
    }

    public Dialog onCreateDialog(TextView textView) {
        Todo todo = todos.get(selectedIndex);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Дело " + substring(textView.getText()))
                .setMessage(todo.getDescription())
                .setPositiveButton("Ок", (dialog, id) -> {
                    dialog.cancel();
                });
        return builder.create();
    }
}