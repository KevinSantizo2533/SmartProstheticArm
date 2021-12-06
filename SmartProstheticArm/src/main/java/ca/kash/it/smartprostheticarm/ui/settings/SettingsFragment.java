package ca.kash.it.smartprostheticarm.ui.settings;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.widget.Toast;

import androidx.preference.ListPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.PreferenceManager;

import ca.kash.it.smartprostheticarm.ForgotPasswordActivity;
import ca.kash.it.smartprostheticarm.R;
import ca.kash.it.smartprostheticarm.ReviewActivity;
import ca.kash.it.smartprostheticarm.SelectionsActivity;

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
        Preference p2 = (Preference) findPreference("resetpassword");
        p2.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            public boolean onPreferenceClick(Preference preference) {

                Intent intent = new Intent(getActivity(), ForgotPasswordActivity.class);
                startActivity(intent);
                return true;
            }
        });




        Preference prefs = (Preference) findPreference("usrSelections");
        prefs.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            public boolean onPreferenceClick(Preference preference) {

                Intent in = new Intent(getActivity(), SelectionsActivity.class);
                startActivity(in);
                return true;
            }
        });


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
            public boolean onPreferenceChange(Preference pref, Object obj) {
                String items = (String) obj;
                if (pref.getKey().equals("ORIENTATION")) {
                    switch (items) {
                        case "1":
                            getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_BEHIND);
                            Toast.makeText(getActivity(), "Screen Orientation Mode: Auto", Toast.LENGTH_LONG).show();
                            break;
                        case "2":
                            getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                            Toast.makeText(getActivity(), "Screen Orientation Mode: Portrait", Toast.LENGTH_LONG).show();
                            break;
                    }
                }
                return true;
            }
        });



    }



}