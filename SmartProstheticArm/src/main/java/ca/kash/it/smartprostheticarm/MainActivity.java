/*
    Kevin Santizo n01382533 Section B
    (Alex) Narendre Ramdhan n01240746 Section B
    Samad Agha n01364908 Section B
    Henry To n01365792 Section B
*/
package ca.kash.it.smartprostheticarm;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final int PERMISSIONS_REQUEST_BLUETOOTH = 100;
    TextView greeting;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        greeting = findViewById(R.id.greetingName);
        GoogleSignInAccount signInAccount = GoogleSignIn.getLastSignedInAccount(this);
        if(signInAccount!=null) {
            greeting.setText(signInAccount.getDisplayName());
        }

        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications, R.id.navigation_settings)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel("notification", "notification", NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);


        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public void onBackPressed() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

        builder.setTitle("Warning");
        builder.setIcon(R.drawable.ic_baseline_warning_24);

        builder.setMessage(R.string.Dialog_Exit);
        builder.setCancelable(true);
        builder.setNegativeButton(R.string.onBack_No, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.setPositiveButton(R.string.OnBack_Close, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                FirebaseAuth.getInstance().signOut();
                finish();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.setIcon(R.drawable.ic_baseline_warning_24);
        alertDialog.show();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.signout:
                final AlertDialog.Builder builder2 = new AlertDialog.Builder(MainActivity.this);
                builder2.setIcon(R.drawable.ic_baseline_warning_24);
                builder2.setMessage(R.string.Dialog_Exit2);
                builder2.setCancelable(true);
                builder2.setNegativeButton(R.string.onBack_No, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                builder2.setPositiveButton(R.string.OnBack_Close, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        FirebaseAuth.getInstance().signOut();
                        startActivity(new Intent(MainActivity.this, LoginActivity.class));
                        NotificationCompat.Builder builder = new NotificationCompat.Builder(MainActivity.this,"notification");
                        builder.setContentTitle("Alert");
                        builder.setContentText("You have signed out from the app");
                        builder.setSmallIcon(R.drawable.ic_baseline_warning_24);
                        builder.setAutoCancel(true);

                        NotificationManagerCompat manager2 = NotificationManagerCompat.from(MainActivity.this);
                        manager2.notify(1,builder.build());



                    }
                });
                AlertDialog alertDialog = builder2.create();
                alertDialog.setIcon(R.drawable.ic_baseline_warning_24);
                alertDialog.show();
                return true;
            case R.id.about:
                startActivity(new Intent(MainActivity.this, AboutActivity.class));
                return true;
            case R.id.help:
                startActivity(new Intent(MainActivity.this, HelpActivity.class));
                return true;
            case R.id.feedback:
                startActivity(new Intent(MainActivity.this, ReviewActivity.class));
                return true;
            case R.id.emergency:
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:911"));
                startActivity(intent);
                return true;
        }
        return true;
    }

}