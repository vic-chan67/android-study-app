package com.example.tester.ui.todo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.tester.databinding.FragmentTodoBinding;
import java.util.List;

public class TodoFragment extends Fragment {

    private FragmentTodoBinding binding;
    private TodoViewModel todoViewModel;
    private TodoListAdapter todoListAdapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentTodoBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        todoViewModel = new ViewModelProvider(this).get(TodoViewModel.class);
        todoListAdapter = new TodoListAdapter();

        RecyclerView recyclerView = binding.recyclerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        recyclerView.setAdapter(todoListAdapter);

        // Observe the LiveData from the ViewModel and update the list when it changes
        todoViewModel.todoItems.observe(getViewLifecycleOwner(), this::updateTodoList);

        return root;
    }

    private void updateTodoList(List<String> items) {
        todoListAdapter.submitList(items);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
