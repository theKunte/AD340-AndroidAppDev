package com.example.helloworld.viewmodel;

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

    public void updateMatchItem(Match match) {
        matchesDataModel.updateMatchById(match);
    }

    public void clear() {
        matchesDataModel.clear();
    }
}
