package com.example.helloworld;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.Toast;

import com.example.helloworld.entity.Settings;
import com.example.helloworld.viewmodel.SettingsViewModel;

import java.util.Objects;

public class SettingsFragment extends Fragment {

    private Integer settingsId = 1;
    private SettingsViewModel settingsViewModel;
    private Settings loadedSettings;
    private NumberPicker minAge;
    private NumberPicker maxAge;
    private Integer minAgeLimit = 18;
    private Integer maxAgeLimit = 120;
    private Integer defaultMinAge = 18;
    private Integer defaultMaxAge = 120;

    public SettingsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        settingsViewModel = new ViewModelProvider(this).get(SettingsViewModel.class);

        // Create the observer which updates the UI.
        final Observer<Settings> getSettingsObserver = dbSettings -> {
            loadedSettings = dbSettings;

            if (loadedSettings == null) {
                //use default settings
                loadedSettings = new Settings();
                loadedSettings.setId(1);
                loadedSettings.setMatchReminderHour(14);
                loadedSettings.setMatchReminderMin(0);
                loadedSettings.setPrivateAccount(false);
                loadedSettings.setMaxDistance(5);
                loadedSettings.setMinAge(18);
                loadedSettings.setMaxAge(120);
//                return;
            }

            this.minAge.setValue(loadedSettings.getMinAge());
            this.maxAge.setValue(loadedSettings.getMaxAge());
        };

        settingsViewModel.loadSettingsById(this.getContext(), 1).observe(this, getSettingsObserver);
        settingsViewModel.loadSettingsById(this.getContext(), 1).getValue();
    }

    public void updateSettings(View view, Settings settings) {

        settingsViewModel.updateSettings(this.getContext(), settings);
        Toast.makeText(view.getContext(), R.string.Updated, Toast.LENGTH_SHORT).show();
    }

    public void deleteSettings(View view) {
        Settings currentSettings = new Settings();
        currentSettings.setMinAge(this.minAge.getValue());
        //add other fields

        settingsViewModel.deleteSettings(this.getContext(), currentSettings);

        this.minAge.setValue(this.minAgeLimit);
        this.maxAge.setValue(this.maxAgeLimit);
        //add other fields

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_settings, container, false);

        this.minAge = view.findViewById(R.id.min_age_value);
        this.minAge.setMinValue(minAgeLimit);
        this.minAge.setMaxValue(maxAgeLimit);

        this.maxAge = view.findViewById(R.id.max_age_value);
        this.maxAge.setMinValue(minAgeLimit);
        this.maxAge.setMaxValue(maxAgeLimit);

        this.minAge.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker numberPicker, int i, int i1) {
                loadedSettings.setMinAge(minAge.getValue());
                updateSettings(view, loadedSettings);
            }
        });

        this.maxAge.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker numberPicker, int i, int i1) {
                loadedSettings.setMaxAge(maxAge.getValue());
                updateSettings(view, loadedSettings);
            }
        });

        // Back Button
        Button buttonSecondView = view.findViewById(R.id.buttonSecondView);
        buttonSecondView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Objects.requireNonNull(getActivity()).finish();
            }
        });
        return view;
    }
}
