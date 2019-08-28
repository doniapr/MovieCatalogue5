package com.doniapr.moviecatalogue5.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.CompoundButton;
import android.widget.Switch;

import com.doniapr.moviecatalogue5.R;
import com.doniapr.moviecatalogue5.reminder.DailyReminder;
import com.doniapr.moviecatalogue5.reminder.ReleaseReminder;

public class SettingActivity extends AppCompatActivity {
    public static final String SHARED_PREF_NAME = "sharedpref";
    public static final String KEY_DAILY = "key_daily";
    public static final String KEY_RELEASE = "key_release";
    Switch switchRelease, switchDaily;
    DailyReminder dailyReminder;
    ReleaseReminder releaseReminder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        Toolbar toolbar = findViewById(R.id.toolbar_setting);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(R.string.title_setting);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        switchRelease = findViewById(R.id.switch_release);
        switchDaily = findViewById(R.id.switch_daily);
        dailyReminder = new DailyReminder();
        releaseReminder = new ReleaseReminder();

        final SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);

        if (sharedPreferences.getString(KEY_DAILY, null) != null) {
            switchDaily.setChecked(true);
        } else {
            switchDaily.setChecked(false);
        }
        if (sharedPreferences.getString(KEY_RELEASE, null) != null) {
            switchRelease.setChecked(true);
        } else {
            switchRelease.setChecked(false);
        }

        switchDaily.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (buttonView.isChecked()) {
                    dailyReminder.setRepeatingAlarm(SettingActivity.this);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString(KEY_DAILY, "Reminder Daily");
                    editor.apply();
                } else {
                    dailyReminder.cancelAlarm(SettingActivity.this);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.remove(KEY_DAILY);
                    editor.apply();
                }
            }
        });

        switchRelease.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (buttonView.isChecked()) {
                    releaseReminder.setAlarmRelease(SettingActivity.this);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString(KEY_RELEASE, "Release");
                    editor.apply();
                } else {
                    releaseReminder.cancelAlarm(SettingActivity.this);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.remove(KEY_RELEASE);
                    editor.apply();
                }
            }
        });
    }


}
