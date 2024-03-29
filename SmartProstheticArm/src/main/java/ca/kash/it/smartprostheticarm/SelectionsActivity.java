/*
    Kevin Santizo n01382533 Section B
    (Alex) Narendre Ramdhan n01240746 Section B
    Samad Agha n01364908 Section B
    Henry To n01365792 Section B
*/
package ca.kash.it.smartprostheticarm;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;



public class SelectionsActivity extends AppCompatActivity {
    Button settingsBtn,sendBtn;
    TextView settingsTxt;
    DatabaseReference ref;

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

                settingsTxt.setText(info);
            }
        });




    }
}
