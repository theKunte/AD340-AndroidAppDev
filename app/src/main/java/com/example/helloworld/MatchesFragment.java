package com.example.helloworld;

import com.example.helloworld.entity.Settings;
import com.example.helloworld.model.Match;
import com.example.helloworld.viewmodel.FirebaseMatchesViewModel;
import com.example.helloworld.viewmodel.SettingsViewModel;
import com.squareup.picasso.Picasso;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;

import java.util.ArrayList;

public class MatchesFragment extends Fragment {
FirebaseMatchesViewModel viewModel;
    LocationManager locationManager;
    Location userLocation;
    ContentAdapter adapter;
    RecyclerView recyclerView;
    private Settings loadedSettings;

    private final LocationListener locationListenerNetwork = new LocationListener() {
        public void onLocationChanged(Location location) {
            userLocation = location;

            Integer proximity = loadedSettings.getMaxDistance();

            adapter = new ContentAdapter(userLocation, recyclerView.getContext(), viewModel, proximity);
            recyclerView.setAdapter(adapter);
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

            Toast.makeText(getContext(), "Location changed", Toast.LENGTH_SHORT).show();
        }

        private boolean isLocationEnabled() {
            return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        }

        @Override
        public void onStatusChanged(String s, int i, Bundle bundle) {}

        @Override
        public void onProviderEnabled(String s) {}

        @Override
        public void onProviderDisabled(String s) {}
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        locationManager = (LocationManager)getActivity().getSystemService(Context.LOCATION_SERVICE);

        SettingsViewModel settingsViewModel = new ViewModelProvider(this).get(SettingsViewModel.class);

        final Observer<Settings> getSettingsObserver = dbSettings -> {
            loadedSettings = dbSettings;

            if (loadedSettings == null) {
                loadedSettings = new Settings();
                loadedSettings.setDefaultValues();
                Toast.makeText(getContext(), "Use default settings", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(getContext(), "Settings Loaded", Toast.LENGTH_LONG).show();
            }

            if (ActivityCompat.checkSelfPermission(getContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED ||
                    ActivityCompat.checkSelfPermission(getContext(), android.Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {

                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 60 * 1000, 10, locationListenerNetwork);
                Toast.makeText(getContext(), "Location set", Toast.LENGTH_LONG).show();
            }
        };

        settingsViewModel.loadSettingsById(this.getContext(), 1).observe(getViewLifecycleOwner(), getSettingsObserver);
        settingsViewModel.loadSettingsById(this.getContext(), 1).getValue();

        viewModel = new FirebaseMatchesViewModel();
        recyclerView = (RecyclerView) inflater.inflate(R.layout.recycler_view, container, false);

        return recyclerView;
    }

    @Override
    public void onPause() {
        viewModel.clear();
        super.onPause();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView picture;
        public TextView name;
        public TextView description;
        public Button like_btn;
        public Context context;

        @SuppressLint("SetTextI18n")
        public ViewHolder(LayoutInflater inflater, ViewGroup parent, FirebaseMatchesViewModel viewModel) {
            super(inflater.inflate(R.layout.fragment_matches, parent, false));
            picture = (ImageView) itemView.findViewById(R.id.card_image);
            name = (TextView) itemView.findViewById(R.id.card_title);
            description = (TextView) itemView.findViewById(R.id.card_text);
            like_btn = itemView.findViewById(R.id.like_btn);
            context = itemView.getContext();
        }
    }

    /**
     * Adapter to display recycler view.
     */
    public static class ContentAdapter extends RecyclerView.Adapter<ViewHolder> {
        // Set numbers of List in RecyclerView.
        private int length;
        private ArrayList<Match> matches;
        private FirebaseMatchesViewModel viewModel;

        ContentAdapter(Location userLocation, Context context, FirebaseMatchesViewModel viewModel, Integer proximity) {
            this.viewModel = viewModel;
            context.getResources();
            viewModel = new FirebaseMatchesViewModel();
            viewModel.getMatchesInProximity(proximity, userLocation, (fb_matches -> {
                this.matches = fb_matches;
                length = matches.size();
                notifyDataSetChanged();
            }));
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ViewHolder(LayoutInflater.from(parent.getContext()), parent, viewModel);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            if (this.matches != null) {
                Match match = this.matches.get(position % this.matches.size());
                Picasso.get().load(match.getImageUrl()).into(holder.picture);
                holder.name.setText(match.getName());

                holder.like_btn.setText(match.isLiked() ? R.string.UNLIKE : R.string.LIKE);
                holder.like_btn.setOnClickListener(view -> {
                    if (!match.isLiked()) {
                        //Liked someone
                        match.setLiked(true);
                        holder.like_btn.setText(R.string.UNLIKE);
                        Toast.makeText(holder.itemView.getContext(),holder.context.getString(R.string.LikeMsg) + holder.name.getText() , Toast.LENGTH_SHORT).show();
                    } else {
                        //Unliked someone
                        match.setLiked(false);
                        holder.like_btn.setText(holder.context.getString(R.string.LIKE));
                        Toast.makeText(holder.itemView.getContext(), holder.context.getString(R.string.UnlikedMsg) + holder.name.getText(), Toast.LENGTH_SHORT).show();
                    }
                    viewModel.updateMatchItem(match);
                });
            }
        }

        @Override
        public int getItemCount() {
            return length;
        }
    }
}