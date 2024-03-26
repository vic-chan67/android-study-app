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
    private TextView selectedItem;
    private Set<String> itemList;
    private SharedPreferences sharedPreferences;        // use SharedPreferences to save the to-do list

    /**
     * onCreate
     * Called when starting the activity to create the ui and its components
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo);
        listLayout = findViewById(R.id.todo_list);

        // Get the SharedPreferences
        sharedPreferences = getSharedPreferences("TodoList", Context.MODE_PRIVATE);

        // Get saved items from SharedPreferences
        // Create new set if no SharedPreferences found
        itemList = sharedPreferences.getStringSet("items", new HashSet<String>());

        // Populate to-do list with the saved items
        populateList();

        // Setup addButton listener
        Button addButton = findViewById(R.id.todo_add);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addItem();
            }
        });

        // Setup backButton listener
        Button backButton = findViewById(R.id.back_button);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Save the items to the SharedPreferences
                saveItems();
                // Take the user to the HomeActivity page
                Intent backIntent = new Intent(TodoActivity.this, HomeActivity.class);
                startActivity(backIntent);
            }
        });
    }

    /**
     * populateList()
     * Add the items to the list
     */
    private void populateList() {
        for (String itemText : itemList) {
            addItemToLayout(itemText);
        }
    }

    /**
     * addItemToLayout()
     * @param itemText      item text to add
     * Add an item to the layout view
     */
    private void addItemToLayout(String itemText) {
        // Set the textView view choices
        TextView textView = new TextView(this);
        textView.setText(itemText);
        textView.setTextSize(16);
        textView.setPadding(0, 10, 0, 10);
        textView.setBackground(null);

        listLayout.addView(textView);

        // Setup textView listener to allow editing of the item
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedItem = textView;
                editItem(itemText);
            }
        });
    }

    /**
     * addItem()
     * Add new item to the list
     */
    private void addItem() {
        String itemText = getString(R.string.todo_new);
        addItemToLayout(itemText);
        itemList.add(itemText);
    }

    /**
     * editTodoItem()
     * @param itemText      item text to edit
     * Allow editing of an item in the list
     */
    private void editItem(String itemText) {
        // Replace the text with EditText
        EditText editText = new EditText(this);
        editText.setText(itemText);
        editText.setTextSize(16);
        editText.setPadding(0, 10, 0, 10);
        editText.setBackground(null);
        int index = listLayout.indexOfChild(selectedItem);
        listLayout.removeView(selectedItem);        // remove the TextView
        listLayout.addView(editText, index);        // add the EditText at the same position in the layout

        // Show the keyboard for the user
        editText.requestFocus();

        // Setup focus change listener to save the text changes when the EditText is no longer focused on
        editText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    String updatedItem = editText.getText().toString().trim();
                    // Update the item in the set
                    itemList.remove(itemText);      // remove old text
                    itemList.add(updatedItem);      // add the changed text
                }
            }
        });
    }

    /**
     * saveItems()
     * Save the items to SharedPreferences
     */
    private void saveItems() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putStringSet("items", itemList);
        editor.apply();
    }
}
