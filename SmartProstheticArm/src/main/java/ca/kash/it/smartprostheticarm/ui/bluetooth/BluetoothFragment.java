/*
    Kevin Santizo n01382533 Section B
    (Alex) Narendre Ramdhan n01240746 Section B
    Samad Agha n01364908 Section B
    Henry To n01365792 Section B
*/
package ca.kash.it.smartprostheticarm.ui.bluetooth;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.snackbar.Snackbar;

import ca.kash.it.smartprostheticarm.R;

public class BluetoothFragment extends Fragment {

    private static final int PERMISSIONS_REQUEST_BLUETOOTH = 100;
    Button btbutton;


    private BluetoothViewModel bluetoothViewModel;
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_bluetooth, container, false);
        btbutton = root.findViewById(R.id.bluetoothbtn);
        btbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, PERMISSIONS_REQUEST_BLUETOOTH
                    );
                } else {
                    Snackbar snackBar = Snackbar.make(getActivity().findViewById(android.R.id.content), R.string.BTGranted, Snackbar.LENGTH_LONG);
                    snackBar.show();
                }

            }
        });
        return root;


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case PERMISSIONS_REQUEST_BLUETOOTH: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
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
