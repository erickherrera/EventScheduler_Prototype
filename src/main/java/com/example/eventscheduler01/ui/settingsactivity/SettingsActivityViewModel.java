package com.example.eventscheduler01.ui.settingsactivity;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;


public class SettingsActivityViewModel extends ViewModel {
    private final MutableLiveData<String> mText;

    public SettingsActivityViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is notifications fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}