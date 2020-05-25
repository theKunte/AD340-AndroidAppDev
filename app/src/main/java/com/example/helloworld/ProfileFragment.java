package com.example.helloworld;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.Objects;

public class ProfileFragment extends Fragment {
    private String occupation;
    private String description;
    private String name;
    private String age;

    public ProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null)
        {
            occupation = getArguments().getString(Constants.KEY_OCCUPATION);
            description = getArguments().getString(Constants.KEY_DESCRIPTION);
            name = getArguments().getString(Constants.KEY_NAME);
            age = getArguments().getString(Constants.KEY_AGE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        TextView tv_occupation = view.findViewById(R.id.tv_occupation);
        TextView tv_description = view.findViewById(R.id.tv_description);
        TextView tv_name = view.findViewById(R.id.tv_name);
        TextView tv_age = view.findViewById(R.id.tv_age);

        tv_occupation.setText(occupation);
        tv_description.setText(description);
        tv_name.setText(name);
        tv_age.setText(age);

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
