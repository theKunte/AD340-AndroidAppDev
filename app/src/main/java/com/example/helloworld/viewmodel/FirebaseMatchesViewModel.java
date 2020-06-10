package com.example.helloworld.viewmodel;

import android.location.Location;

import androidx.core.util.Consumer;
import com.example.helloworld.datamodel.FirebaseMatchesDataModel;
import com.example.helloworld.model.Match;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class FirebaseMatchesViewModel {

    private FirebaseMatchesDataModel matchesDataModel;

    public FirebaseMatchesViewModel() {
        matchesDataModel = new FirebaseMatchesDataModel();
    }

    public void addMatch(Match item) {
        matchesDataModel.addMatch(item);
    }

    public void getMatches(Consumer<ArrayList<Match>> responseCallback) {
        matchesDataModel.getMatches(
                (QuerySnapshot querySnapshot) -> {
                    if (querySnapshot != null) {
                        ArrayList<Match> matches = new ArrayList<>();
                        for (DocumentSnapshot matchesSnapshot : querySnapshot.getDocuments()) {
                            Match match = matchesSnapshot.toObject(Match.class);
                            assert match != null;
                            match.setUid(matchesSnapshot.getId());
                            matches.add(match);
                        }
                        responseCallback.accept(matches);
                    }
                },
                (databaseError -> System.out.println("Error reading Matches: " + databaseError))
        );
    }

    public void getMatchesInProximity(Integer proximity, Location userLocation, Consumer<ArrayList<Match>> responseCallback) {
        matchesDataModel.getMatches(
                (QuerySnapshot querySnapshot) -> {
                    if (querySnapshot != null) {
                        ArrayList<Match> matches = new ArrayList<>();
                        for (DocumentSnapshot matchesSnapshot : querySnapshot.getDocuments()) {
                            Match match = matchesSnapshot.toObject(Match.class);
                            Location matchLocation = new Location("");
                            matchLocation.setLatitude(Double.parseDouble(match.getLat()));
                            matchLocation.setLongitude(Double.parseDouble(match.getLongitude()));

                            assert match != null;
                            match.setUid(matchesSnapshot.getId());

//                            double distance = distance(userLocation, matchLocation);
                            double distance = userLocation.distanceTo(matchLocation) / 160009.34;
                            if (distance <= proximity) {
                                matches.add(match);
                            }
                        }
                        responseCallback.accept(matches);
                    }
                },
                (databaseError -> System.out.println("Error reading Matches: " + databaseError))
        );
    }

    private double distance(Location userLocation, Location matchLocation) {
            double lon1 = userLocation.getLongitude();
            double lon2 = matchLocation.getLongitude();
            double lat1 = userLocation.getLatitude();
            double lat2 = matchLocation.getLatitude();

            double theta = lon1 - lon2;
            double dist = Math.sin(Math.toRadians(lat1)) * Math.sin(Math.toRadians(lat2)) + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) * Math.cos(Math.toRadians(theta));
            dist = Math.acos(dist);
            dist = Math.toDegrees(dist);
            dist = dist * 60 * 1.1515;
            return (dist);
    }

    public void updateMatchItem(Match match) {
        matchesDataModel.updateMatchById(match);
    }

    public void clear() {
        matchesDataModel.clear();
    }
}
