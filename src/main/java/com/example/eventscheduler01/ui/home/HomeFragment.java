package com.example.eventscheduler01.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.eventscheduler01.R;
import com.example.eventscheduler01.Recycler_Event_Adapter;
import com.example.eventscheduler01.SessionManager;
import com.example.eventscheduler01.databinding.FragmentHomeBinding;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private SessionManager sessionManager;
    // List to hold events created from the FAB
    static List<String> listOfEvents = new ArrayList<>();

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        sessionManager = new SessionManager(getContext());

        // Setting up the RecyclerView with a LinearLayoutManager
        RecyclerView recyclerView = binding.recyclerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        final Recycler_Event_Adapter[] adapter = new Recycler_Event_Adapter[1]; // Declare final array to hold adapter

        adapter[0] = new Recycler_Event_Adapter(listOfEvents, new Recycler_Event_Adapter.OnRemoveEventListener() {
            @Override
            public void onRemoveEvent(int position) {
                // Handle the remove event here
                // This method will be called when an event is removed from the adapter
                // Ensure position is valid
                if (position >= 0 && position < listOfEvents.size()) {
                    listOfEvents.remove(position);
                    adapter[0].notifyItemRemoved(position); // Access adapter through the array
                }
            }
        });

        FloatingActionButton fab = binding.floatingActionButton;
        fab.setOnClickListener(view -> {
            NavController navController = NavHostFragment.findNavController(this);
            navController.navigate(R.id.action_create_event); // Correct navigation action ID needed
        });

        // Handle back press to log out
        requireActivity().getOnBackPressedDispatcher().addCallback(getViewLifecycleOwner(), new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                // Log out the user
                sessionManager.logoutUser();
            }
        });
        recyclerView.setAdapter(adapter[0]);
        return root;
    }

    // This method will be called from EventCreationFragment to add events to the list
    public static void addEvent(String eventTitle) {
        listOfEvents.add(eventTitle);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
