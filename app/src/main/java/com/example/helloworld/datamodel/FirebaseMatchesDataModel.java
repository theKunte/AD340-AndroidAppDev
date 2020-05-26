package com.example.helloworld.datamodel;

import com.example.helloworld.model.Match;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.ListenerRegistration;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class FirebaseMatchesDataModel {

    private FirebaseFirestore db;
    private List<ListenerRegistration> listeners;

    public FirebaseMatchesDataModel() {
        db = FirebaseFirestore.getInstance();
        listeners = new ArrayList<>();
    }

    public void addMatch(Match match) {
        CollectionReference matchRef = db.collection("matches");
        matchRef.add(match);
    }

    public void getMatches(Consumer<QuerySnapshot> dataChangedCallback, Consumer<FirebaseFirestoreException> dataErrorCallback) {
        ListenerRegistration listener = db.collection("matches")
                .addSnapshotListener((queryDocumentSnapshots, e) -> {
                    if (e != null) {
                        dataErrorCallback.accept(e);
                    }

                    dataChangedCallback.accept(queryDocumentSnapshots);
                });
        listeners.add(listener);
    }

    public void updateMatchById(Match match) {
        DocumentReference matchRef = db.collection("matches").document(match.uid);
        Map<String, Object> data = new HashMap<>();
        data.put("imageUrl", match.imageUrl);
        data.put("liked", match.liked);
        data.put("name", match.name);
        data.put("uid", match.uid);
        data.put("lat", match.lat);
        data.put("longitude", match.longitude);

        matchRef.update(data);
    }

    public void clear() {
        // Clear all the listeners onPause
        listeners.forEach(ListenerRegistration::remove);
    }
}
