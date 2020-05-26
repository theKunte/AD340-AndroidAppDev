package com.example.helloworld.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import java.util.HashMap;
import java.util.Map;

@IgnoreExtraProperties
public class Match implements Parcelable {
    public String imageUrl;
    public boolean liked;
    public String name;
    public String uid;
    public String lat;
    public String longitude;

    public Match() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public Match(String imageUrl, boolean liked, String name, String uid, String lat, String longitude) {
        this.imageUrl = imageUrl;
        this.liked = liked;
        this.name = name;
        this.uid = uid;
        this.lat = lat;
        this.longitude = longitude;
    }

    public Match(Parcel in) {
        imageUrl = in.readString();
        liked = in.readByte() != 0;
        name = in.readString();
        uid = in.readString();
        lat = in.readString();
        longitude = in.readString();
    }

    public static final Creator<Match> CREATOR = new Creator<Match>() {
        @Override
        public Match createFromParcel(Parcel in) {
            return new Match(in);
        }

        @Override
        public Match[] newArray(int size) {
            return new Match[size];
        }
    };

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("imageUrl", imageUrl);
        result.put("liked", liked);
        result.put("name", name);
        result.put("uid", uid);
        result.put("lat", lat);
        result.put("longitude", longitude);

        return result;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(imageUrl);
        dest.writeByte((byte) (liked ? 1 : 0));
        dest.writeString(name);
        dest.writeString(uid);
        dest.writeString(lat);
        dest.writeString(longitude);
    }
}
