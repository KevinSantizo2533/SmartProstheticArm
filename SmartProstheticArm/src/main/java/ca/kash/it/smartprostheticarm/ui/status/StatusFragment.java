/*
    Kevin Santizo n01382533 Section B
    (Alex) Narendre Ramdhan n01240746 Section B
    Samad Agha n01364908 Section B
    Henry To n01365792 Section B
*/
package ca.kash.it.smartprostheticarm.ui.status;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import ca.kash.it.smartprostheticarm.EmailActivity;
import ca.kash.it.smartprostheticarm.R;
import ca.kash.it.smartprostheticarm.User;

public class StatusFragment extends Fragment {
    private FirebaseUser user;
    private DatabaseReference reference;
    private String userID;
    private static final int PERMISSIONS_REQUEST_BLUETOOTH = 100;
    private StatusViewModel statusViewModel;



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        statusViewModel = new ViewModelProvider(this).get(StatusViewModel.class);
        View root = inflater.inflate(R.layout.fragment_status, container, false);
        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Users");
        userID = user.getUid();
        final TextView name = root.findViewById(R.id.greetingName);


        reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User userProfile = snapshot.getValue(User.class);

                if (userProfile != null) {
                    String fullName = userProfile.fullName;
                    name.setText(fullName);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
        Button btbutton;
         TextView textBattery;
         TextView textBluetooth;
        textBattery = (TextView) root.findViewById(R.id.textviewbattery);
        textBluetooth = (TextView) root.findViewById(R.id.txtview_bt);
        btbutton = root.findViewById(R.id.bluetoothbtn);
        btbutton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                textBattery.setText(R.string.fragmentstatus_battery_connected);
                textBluetooth.setText(R.string.fragmentstatus_battery_connected);
                textBluetooth.setTextColor(Color.GREEN);
                textBattery.setTextColor(Color.GREEN);
                if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, PERMISSIONS_REQUEST_BLUETOOTH
                    );
                } else {
                    Snackbar snackBar = Snackbar.make(getActivity().findViewById(android.R.id.content), R.string.BTGranted, Snackbar.LENGTH_LONG);
                    snackBar.show();
                }
            }
        });
        FloatingActionButton kashFab;
        kashFab = root.findViewById(R.id.myFab);
        kashFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(getActivity(), EmailActivity.class);
                startActivity(myIntent);
            }
        });

        return root;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case PERMISSIONS_REQUEST_BLUETOOTH: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) { // If request is cancelled, the result arrays are empty.
                    Snackbar snackBar = Snackbar.make(getActivity().findViewById(android.R.id.content), R.string.BTGranted, Snackbar.LENGTH_LONG);
                    snackBar.show();
                } else {
                    Snackbar snackBar = Snackbar.make(getActivity().findViewById(android.R.id.content), R.string.BTDenied, Snackbar.LENGTH_LONG);
                    snackBar.show();
                }
                return;
            }
        }
    }
}
