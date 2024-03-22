package com.example.tester.ui.todo;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import java.util.ArrayList;
import java.util.List;

public class TodoViewModel extends ViewModel {

    private MutableLiveData<List<String>> _todoItems = new MutableLiveData<>();
    public LiveData<List<String>> todoItems = _todoItems;

    public TodoViewModel() {
        // Initialize the list of todo items (replace this with your actual data)
        List<String> items = new ArrayList<>();
        items.add("Task 1");
        items.add("Task 2");
        items.add("Task 3");
        _todoItems.setValue(items);
    }
}
