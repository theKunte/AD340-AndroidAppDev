package com.example.helloworld.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.annotation.NonNull;

@Entity
public class Settings {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private Integer id;

    @ColumnInfo(name = "private_account")
    private boolean isPrivateAccount;

    @ColumnInfo(name = "reminder_hour")
    private Integer MatchReminderHour;

    @ColumnInfo(name = "reminder_min")
    private Integer MatchReminderMin;

    @ColumnInfo(name = "max_distance")
    private Integer MaxDistance;

    @ColumnInfo(name = "min_age")
    private Integer MinAge;

    @ColumnInfo(name = "max_age")
    private Integer MaxAge;

    @ColumnInfo(name = "gender_preference")
    private Integer GenderPreference;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public boolean isPrivateAccount() {
        return isPrivateAccount;
    }

    public void setPrivateAccount(boolean privateAccount) {
        isPrivateAccount = privateAccount;
    }

    public Integer getMaxDistance() {
        return MaxDistance;
    }

    public void setMaxDistance(Integer maxDistance) {
        MaxDistance = maxDistance;
    }

    public Integer getMinAge() {
        return MinAge;
    }

    public void setMinAge(Integer minAge) {
        MinAge = minAge;
    }

    public Integer getMaxAge() {
        return MaxAge;
    }

    public void setMaxAge(Integer maxAge) {
        MaxAge = maxAge;
    }

    public Integer getMatchReminderHour() {
        return MatchReminderHour;
    }

    public void setMatchReminderHour(Integer matchReminderHour) {
        MatchReminderHour = matchReminderHour;
    }

    public Integer getMatchReminderMin() {
        return MatchReminderMin;
    }

    public void setMatchReminderMin(Integer matchReminderMin) {
        MatchReminderMin = matchReminderMin;

    }
    public Integer getGenderPreference() {
        return GenderPreference;
    }

    public void setGenderPreference(Integer genderPreference) {
        GenderPreference = genderPreference;
    }

}
