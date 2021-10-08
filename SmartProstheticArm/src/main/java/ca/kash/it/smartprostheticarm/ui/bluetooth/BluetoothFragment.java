/*
    Kevin Santizo n01382533 Section B
    (Alex) Narendre Ramdhan n01240746 Section B
    Samad Agha n01364908 Section B
    Henry To n01365792 Section B
*/
package ca.kash.it.smartprostheticarm.ui.bluetooth;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.snackbar.Snackbar;

import ca.kash.it.smartprostheticarm.R;

public class BluetoothFragment extends Fragment {

    private static final int PERMISSIONS_REQUEST_BLUETOOTH = 100;

    private BluetoothViewModel bluetoothViewModel;
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        bluetoothViewModel = new ViewModelProvider(this).get(BluetoothViewModel.class);
        View root = inflater.inflate(R.layout.fragment_bluetooth, container, false);
        final TextView textView = root.findViewById(R.id.text_notifications);
        bluetoothViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }

        });
        if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, PERMISSIONS_REQUEST_BLUETOOTH);
        }
        else {
            Snackbar snackBar = Snackbar.make(getActivity().findViewById(android.R.id.content), R.string.BTGranted, Snackbar.LENGTH_LONG);
            snackBar.show();
        }
        return root;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case PERMISSIONS_REQUEST_BLUETOOTH: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Snackbar snackBar = Snackbar.make(getActivity().findViewById(android.R.id.content), R.string.BTGranted, Snackbar.LENGTH_LONG);
                    snackBar.show();
                } else {
                    return;
                }
                return;
            }
        }
    }
}