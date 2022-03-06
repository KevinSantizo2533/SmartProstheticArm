/*
    Kevin Santizo n01382533 Section B
    (Alex) Narendre Ramdhan n01240746 Section B
    Samad Agha n01364908 Section B
    Henry To n01365792 Section B
*/
package ca.kash.it.smartprostheticarm.ui.sensors;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import ca.kash.it.smartprostheticarm.BluetoothActivity;
import ca.kash.it.smartprostheticarm.R;
import ca.kash.it.smartprostheticarm.TemperatureActivity;
import ca.kash.it.smartprostheticarm.UltrasonicActivity;

public class SensorFragment extends Fragment {

    private SensorViewModel sensorViewModel;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        sensorViewModel =
                new ViewModelProvider(this).get(SensorViewModel.class);
        View root = inflater.inflate(R.layout.fragment_sensors, container, false);
        final TextView textView = root.findViewById(R.id.text_dashboard);
        ImageButton ultraButton = (ImageButton) root.findViewById(R.id.ultraBtn);
        ultraButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getActivity(), UltrasonicActivity.class);
                startActivity(in);
            }
        });
        ImageButton tempButton = (ImageButton) root.findViewById(R.id.tempBtn);
        tempButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getActivity(), TemperatureActivity.class);
                startActivity(in);
            }
        });
        ImageButton bluetoothBtn = (ImageButton) root.findViewById(R.id.bluetoothBtn);
        bluetoothBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getActivity(), BluetoothActivity.class);
                startActivity(in);
            }
        });
        sensorViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}