package ca.kash.it.smartprostheticarm.ui.settings;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;

import androidx.preference.ListPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.PreferenceManager;

import ca.kash.it.smartprostheticarm.R;
import ca.kash.it.smartprostheticarm.ReviewActivity;

public class SettingsFragment extends PreferenceFragmentCompat {

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey);

        Preference p = (Preference) findPreference("review");
        p.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            public boolean onPreferenceClick(Preference preference) {

                Intent intent = new Intent(getActivity(), ReviewActivity.class);
                startActivity(intent);
                return true;
            }
        });

    }

    private void LoadPreferences() {
        SharedPreferences SP = PreferenceManager.getDefaultSharedPreferences(getContext());
        ListPreference LP = (ListPreference) findPreference("ORIENTATION");
        String usrOrient = SP.getString("ORIENTATION", "false");

        if ("1".equals(usrOrient)) {
            getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_BEHIND);
        } else if ("2".equals(usrOrient)) {
            getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }

        LP.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object obj) {
                String items = (String) obj;
                if (preference.equals("ORIENTATION")) {
                    switch (items) {
                        case "1":
                            getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_BEHIND);
                            break;
                        case "2":
                            getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                            break;
                    }
                }
                return true;
            }
        });
    }


    @Override
    public void onResume() {
        super.onResume();
        // Set up a listener whenever a key changes
        LoadPreferences();
    }


}