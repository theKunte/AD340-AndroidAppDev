package com.example.helloworld;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileFragment extends Fragment {

    private static final String ARG_OCCUPATION = "ARG_OCCUPATION";
    private static final String ARG_DESCRIPTION = "ARG_DESCRIPTION";

    private String moccupation;
    private String mdescription;

    public ProfileFragment() {
        // Required empty public constructor
    }


    public static ProfileFragment newInstance(String occupation, String description) {
        ProfileFragment fragment = new ProfileFragment();
        Bundle args = new Bundle();
        args.putString(ARG_OCCUPATION, occupation);
        args.putString(ARG_DESCRIPTION, description);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        moccupation = getArguments().getString(ARG_OCCUPATION);
        mdescription = getArguments().getString(ARG_DESCRIPTION);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        TextView tv_occupation = view.findViewById(R.id.tv_occupation);;
        TextView tv_description = view.findViewById(R.id.tv_description);;

        tv_occupation.setText(moccupation);
        tv_description.setText(mdescription);

        // sign Out Button
        Button buttonSecondView = view.findViewById(R.id.buttonSecondView);
        buttonSecondView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent intent = new Intent(Main2Activity.this, MainActivity.class);
                getActivity().finish();
            }
        });
        return view;

    }
}
