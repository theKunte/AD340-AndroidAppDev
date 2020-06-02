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
import android.widget.TextView;

import com.example.helloworld.entity.Settings;
import com.example.helloworld.viewmodel.SettingsViewModel;

import java.util.Objects;

public class SettingsFragment extends Fragment {

    private Integer settingsId = 1;
    private SettingsViewModel settingsViewModel;
    private TextView tv_minAge;

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

        //set layout views w/ data retrieved
            this.tv_minAge.setText(newSettings.getMinAge());
        };

        settingsViewModel.loadSettingsById(this.getContext(), 1).observe(this, getSettingsObserver);
    }

    public void updateSettings(View view, Settings settings) {

        settingsViewModel.updateSettings(this.getContext(), settings);

        this.tv_minAge.setText(settings.getMinAge());
//      add other fields
    }

    public void deleteSettings(View view) {
        Settings currentSettings = new Settings();
        currentSettings.setMinAge(Integer.parseInt(this.tv_minAge.getText().toString()));
        //add other fields

        settingsViewModel.deleteSettings(this.getContext(), currentSettings);

        this.tv_minAge.setText("");
        //add other fields

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        this.tv_minAge = view.findViewById(R.id.min_age_value);

        this.tv_minAge.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void afterTextChanged(Editable editable) {
                String newMinAge = String.valueOf(this.minAge.getText());

                settingsViewModel.updateMinAge(newMinAge);
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
