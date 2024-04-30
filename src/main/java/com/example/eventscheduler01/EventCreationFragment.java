package com.example.eventscheduler01;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.example.eventscheduler01.ui.home.HomeFragment;

public class EventCreationFragment extends Fragment {

    private EditText eventTitleInput;

    // It's common to use a no-arg constructor instead of a factory method unless additional setup is required.
    public EventCreationFragment() {}

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_eventcreation, container, false);

        // Initialize views
        eventTitleInput = view.findViewById(R.id.eventTitle);
        Button createEventButton = view.findViewById(R.id.add_event_button);

        // Set a click listener on the button
        createEventButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String eventTitle = eventTitleInput.getText().toString().trim();
                if (!eventTitle.isEmpty()) {
                    // Add the event title to the list of events
                    HomeFragment.addEvent(eventTitle);
                    Navigation.findNavController(v).navigate(R.id.navigation_home);
                }
            }
        });
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // ViewModel providers are used to retain data across configuration changes like screen rotations
        ViewModelProvider viewModelProvider = new ViewModelProvider(requireActivity()); // Changed to requireActivity for scope beyond just this fragment
        EventCreationViewModel mViewModel = viewModelProvider.get(EventCreationViewModel.class);
    }
}
