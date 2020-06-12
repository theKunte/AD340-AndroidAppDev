package com.example.helloworld;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.NumberPicker;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.helloworld.entity.Settings;
import com.example.helloworld.viewmodel.SettingsViewModel;

import java.util.ArrayList;
import java.util.Objects;

public class SettingsFragment extends Fragment {

    private Integer settingsId = 1;
    private SettingsViewModel settingsViewModel;
    private Settings loadedSettings;

    private Switch accountIsPrivateSwitch;
    private TimePicker reminderTimePicker;
    private Button setReminderTimeButton;
    private NumberPicker maxDistancePicker;
    private NumberPicker minAgePicker;
    private NumberPicker maxAgePicker;
    private Spinner genderPreferenceSpinner;

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
                loadedSettings = new Settings();
                loadedSettings.setDefaultValues();
                updateSettings(loadedSettings);
            }

            this.accountIsPrivateSwitch.setChecked(loadedSettings.isPrivateAccount());
            this.reminderTimePicker.setHour(loadedSettings.getMatchReminderHour());
            this.reminderTimePicker.setMinute(loadedSettings.getMatchReminderMin());
            this.minAgePicker.setValue(loadedSettings.getMinAge());
            this.maxAgePicker.setValue(loadedSettings.getMaxAge());
            this.maxDistancePicker.setValue(loadedSettings.getMaxDistance());
            this.genderPreferenceSpinner.setSelection(loadedSettings.getGenderPreference());

        };

        settingsViewModel.loadSettingsById(this.getContext(), 1).observe(this, getSettingsObserver);
        settingsViewModel.loadSettingsById(this.getContext(), 1).getValue();
    }

    public void updateSettings(Settings settings) {

        settingsViewModel.updateSettings(this.getContext(), settings);
        Toast.makeText(this.getContext(), R.string.Updated, Toast.LENGTH_SHORT).show();
    }

    public void deleteSettings(View view) {
        Settings currentSettings = new Settings();
        currentSettings.setMinAge(this.minAgePicker.getValue());
        //add other fields

        settingsViewModel.deleteSettings(this.getContext(), currentSettings);

        this.minAgePicker.setValue(Constants.NP_minAgeLimit);
        this.maxAgePicker.setValue(Constants.NP_maxAgeLimit);
        //add other fields

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_settings, container, false);

        this.accountIsPrivateSwitch = view.findViewById(R.id.account_type_switch);
        this.reminderTimePicker = view.findViewById(R.id.match_reminder_setting_Id);
        this.setReminderTimeButton = view.findViewById(R.id.setReminderTimeButton);
        this.minAgePicker = view.findViewById(R.id.min_age_value);
        this.maxAgePicker = view.findViewById(R.id.max_age_value);
        this.maxDistancePicker = view.findViewById(R.id.distance_value);
        this.genderPreferenceSpinner = view.findViewById(R.id.gender_spinner_ID);

        this.accountIsPrivateSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                loadedSettings.setPrivateAccount(isChecked);
                updateSettings(loadedSettings);
            }
        });

        this.setReminderTimeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadedSettings.setMatchReminderHour(reminderTimePicker.getHour());
                loadedSettings.setMatchReminderMin(reminderTimePicker.getMinute());
                updateSettings(loadedSettings);
            }
        });

        this.minAgePicker.setMinValue(Constants.NP_minAgeLimit);
        this.minAgePicker.setMaxValue(Constants.NP_maxAgeLimit);
        this.minAgePicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker numberPicker, int i, int i1) {
                loadedSettings.setMinAge(minAgePicker.getValue());
                updateSettings(loadedSettings);
            }
        });

        this.maxAgePicker.setMinValue(Constants.NP_minAgeLimit);
        this.maxAgePicker.setMaxValue(Constants.NP_maxAgeLimit);
        this.maxAgePicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker numberPicker, int i, int i1) {
                loadedSettings.setMaxAge(maxAgePicker.getValue());
                updateSettings(loadedSettings);
            }
        });

        this.maxDistancePicker.setMinValue(Constants.NP_minDistanceLimit);
        this.maxDistancePicker.setMaxValue(Constants.NP_maxDistanceLimit);
        this.maxDistancePicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker numberPicker, int i, int i1) {
                loadedSettings.setMaxDistance(numberPicker.getValue());
                updateSettings(loadedSettings);
            }
        });

        ArrayAdapter<CharSequence> genderAdapter = ArrayAdapter.createFromResource(view.getContext(),
                R.array.genderOptions, android.R.layout.simple_spinner_item);
        genderAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        this.genderPreferenceSpinner.setAdapter(genderAdapter);

        this.genderPreferenceSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                if (loadedSettings != null) {
                    loadedSettings.setGenderPreference(position);
                    updateSettings(loadedSettings);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Does nothing
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
