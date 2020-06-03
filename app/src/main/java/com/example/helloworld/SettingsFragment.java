package com.example.helloworld;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.text.Editable;
import android.text.TextWatcher;
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
    private NumberPicker minAge;
    private NumberPicker maxAge;
    private int defaultMinAge = 18;
    private int defaultMaxAge = 120;

    public SettingsFragment() {
        // Required empty public constructor
    }

    public static SettingsFragment newInstance() {
        SettingsFragment fragment = new SettingsFragment();
        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        settingsViewModel = new ViewModelProvider(this).get(SettingsViewModel.class);

        // Create the observer which updates the UI.
        final Observer<Settings> getSettingsObserver = newSettings -> {
            if (newSettings == null) {
                return;
            }

            //TODO set layout views w/ data retrieved
            this.minAge.setValue(newSettings.getMinAge());
            this.maxAge.setValue(newSettings.getMaxAge());
        };

        settingsViewModel.loadSettingsById(this.getContext(), 1).observe(this, getSettingsObserver);
    }

    public void updateSettings(View view, Settings settings) {

        settingsViewModel.updateSettings(this.getContext(), settings);

        this.minAge.setValue(settings.getMinAge());
        this.minAge.setValue(settings.getMaxAge());
    }

    public void deleteSettings(View view) {
        Settings currentSettings = new Settings();
        currentSettings.setMinAge(this.minAge.getValue());
        //add other fields

        settingsViewModel.deleteSettings(this.getContext(), currentSettings);

        this.minAge.setValue(this.defaultMinAge);
        this.maxAge.setValue(this.defaultMaxAge);
        //add other fields

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        this.minAge = view.findViewById(R.id.min_age_value);
        this.minAge.setMinValue(defaultMinAge);
        this.minAge.setMaxValue(defaultMaxAge);

        this.maxAge = view.findViewById(R.id.max_age_value);
        this.maxAge.setMinValue(defaultMinAge);
        this.maxAge.setMaxValue(defaultMaxAge);

        this.minAge.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker numberPicker, int i, int i1) {
                Integer newMinAge = minAge.getValue();
                settingsViewModel.updateMinAge(view.getContext(), newMinAge);
                Toast.makeText(view.getContext(), R.string.Updated, Toast.LENGTH_SHORT).show();
            }
        });

        this.maxAge.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker numberPicker, int i, int i1) {
                Integer newMaxAge = maxAge.getValue();
                settingsViewModel.updateMaxAge(view.getContext(), newMaxAge);
                Toast.makeText(view.getContext(), R.string.Updated, Toast.LENGTH_SHORT).show();
            }
        });

        // sign Out Button
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
