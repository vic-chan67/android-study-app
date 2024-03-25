package com.example.tester.ui.todo;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.tester.HomeActivity;
import com.example.tester.R;
import java.util.HashSet;
import java.util.Set;

public class TodoActivity extends AppCompatActivity {
    private LinearLayout listLayout;
    private TextView selectedTodoItem;
    private Set<String> todoItemsSet;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo);
        listLayout = findViewById(R.id.todo_list);

        // Initialize SharedPreferences
        sharedPreferences = getSharedPreferences("TodoList", Context.MODE_PRIVATE);

        // Retrieve saved todo items from SharedPreferences, or create a new set if not found
        todoItemsSet = sharedPreferences.getStringSet("todoItems", new HashSet<String>());

        // Populate the todo list with saved items
        populateTodoList();

        Button addButton = findViewById(R.id.todo_add);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addTodoItem();
            }
        });

        // back button
        Button backButton = findViewById(R.id.back_button);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Save the current todo items to SharedPreferences
                saveTodoItemsToSharedPreferences();
                Intent backIntent = new Intent(TodoActivity.this, HomeActivity.class);
                startActivity(backIntent);
            }
        });
    }

    private void populateTodoList() {
        for (String todoItemText : todoItemsSet) {
            addTodoItemToLayout(todoItemText);
        }
    }

    private void addTodoItemToLayout(final String todoItemText) {
        final TextView textView = new TextView(this);
        textView.setText(todoItemText);
        textView.setTextSize(20);
        textView.setPadding(0, 10, 0, 10);
        textView.setBackground(null);

        listLayout.addView(textView);

        // Set click listener to allow editing
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedTodoItem = textView; // Set the selected todo item
                editTodoItem(todoItemText);
            }
        });
    }

    private void addTodoItem() {
        final String newTodoItemText = getString(R.string.todo_new);
        addTodoItemToLayout(newTodoItemText);
        // Add the new todo item to the set
        todoItemsSet.add(newTodoItemText);
    }

    private void editTodoItem(final String todoItemText) {
        // Replace the text with an editable EditText
        EditText editText = new EditText(this);
        editText.setText(todoItemText);
        editText.setTextSize(20);
        editText.setPadding(0, 10, 0, 10);
        editText.setBackground(null);
        int index = listLayout.indexOfChild(selectedTodoItem);
        listLayout.removeView(selectedTodoItem); // Remove TextView
        listLayout.addView(editText, index); // Add EditText at the same position

        // Set focus and show keyboard
        editText.requestFocus();

        // Set focus change listener to save changes when EditText loses focus
        editText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    String updatedTodoItemText = editText.getText().toString().trim();
                    // Update the todo item in the set
                    todoItemsSet.remove(todoItemText); // Remove old text
                    todoItemsSet.add(updatedTodoItemText); // Add updated text
                }
            }
        });
    }

    private void saveTodoItemsToSharedPreferences() {
        // Save the current todo items to SharedPreferences
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putStringSet("todoItems", todoItemsSet);
        editor.apply();
    }
}
