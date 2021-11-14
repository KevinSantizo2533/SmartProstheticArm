package ca.kash.it.smartprostheticarm;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;


public class SelectionsActivity extends AppCompatActivity {
    Button settingsBtn;
    TextView settingsTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selections);

        settingsBtn = (Button) findViewById(R.id.getSettingsBtn);
        settingsTxt = findViewById(R.id.usrSettings);
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        settingsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StringBuilder info = new StringBuilder();
                info.append("\nScreen Orientation Mode: " + sharedPreferences.getString("ORIENTATION", ""));
                info.append("\nAlerts: " + sharedPreferences.getBoolean("ALERTS", false));
                info.append("\nUser Profile: " + sharedPreferences.getString("userProfile", ""));

                settingsTxt.setText(info);
            }
        });
    }
}
