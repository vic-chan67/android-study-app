package com.example.tester.ui.todo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.tester.HomeActivity;
import com.example.tester.R;

public class TodoActivity extends AppCompatActivity {
    private LinearLayout listLayout;
    private TextView selectedTodoItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo);
        listLayout = findViewById(R.id.todo_list);

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
                Intent backIntent = new Intent(TodoActivity.this, HomeActivity.class);
                startActivity(backIntent);
            }
        });
    }

    private void addTodoItem() {
        final TextView textView = new TextView(this);
        textView.setText("New Todo Item");
        textView.setTextSize(20);
        textView.setPadding(0, 10, 0, 10);
        textView.setBackground(null);

        listLayout.addView(textView);

        // Set click listener to allow editing
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedTodoItem = textView; // Set the selected todo item
                editTodoItem(textView);
            }
        });
    }

    private void editTodoItem(final TextView textView) {
        // Replace the text with an editable EditText
        EditText editText = new EditText(this);
        editText.setText(textView.getText());
        editText.setTextSize(20);
        editText.setPadding(0, 10, 0, 10);
        int index = listLayout.indexOfChild(textView);
        listLayout.removeView(textView); // Remove TextView
        listLayout.addView(editText, index); // Add EditText at the same position

        // Set focus and show keyboard
        editText.requestFocus();
    }
}
