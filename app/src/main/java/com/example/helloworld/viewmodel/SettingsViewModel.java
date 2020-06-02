package com.example.helloworld.viewmodel;

import android.content.Context;

import com.example.helloworld.AppDatabase;
import com.example.helloworld.AppDatabaseSingleton;
import com.example.helloworld.entity.Settings;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

public class SettingsViewModel extends ViewModel {

    public LiveData<Settings> loadSettingsById(Context context, Integer id) {
        AppDatabase db = AppDatabaseSingleton.getDatabase(context);
        return db.settingsDao().loadById(id);
    }

    public void updateSettings(Context context, Settings settings) {
        AppDatabase db = AppDatabaseSingleton.getDatabase(context);
        db.getTransactionExecutor().execute(() -> {
            db.settingsDao().updateSettings(settings);
        });
    }

    public void deleteSettings(Context context, Settings settings) {
        AppDatabase db = AppDatabaseSingleton.getDatabase(context);
        db.getTransactionExecutor().execute(() -> {
            db.settingsDao().delete(settings);
        });
    }
}
