/*
    Kevin Santizo n01382533 Section B
    (Alex) Narendre Ramdhan n01240746 Section B
    Samad Agha n01364908 Section B
    Henry To n01365792 Section B
*/
package ca.kash.it.smartprostheticarm.ui.bluetooth;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;



import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import ca.kash.it.smartprostheticarm.R;

public class BluetoothFragment extends Fragment {
    private TextView textView;
    private ProgressBar progressBar;
    private SeekBar seekBar;
    private TextView textViewpercent;
    private ProgressBar progressBarpercent;
    private SeekBar seekBarpercent;
    TextView Servo;
    DatabaseReference refspeed,refmotion,refgrab;
    Switch grabswitch;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_bluetooth, container, false);
        textView = (TextView) root.findViewById(R.id.textviewdegrees);
        progressBar = (ProgressBar) root.findViewById(R.id.progressBarDegree);
        seekBar = (SeekBar) root.findViewById(R.id.seekBarDegrees);
        seekBar.setMax(180);
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
        refmotion = FirebaseDatabase.getInstance().getReference().child("Manual Controls").child("Servo Motion");
        refspeed = FirebaseDatabase.getInstance().getReference().child("Manual Controls").child("Servo speed");
        refgrab = FirebaseDatabase.getInstance().getReference().child("Manual Controls").child("Mode");
        grabswitch = root.findViewById(R.id.switch1);
        textViewpercent = (TextView) root.findViewById(R.id.textviewpercent);
        progressBarpercent = (ProgressBar) root.findViewById(R.id.progressBarpercent);
        seekBarpercent = (SeekBar) root.findViewById(R.id.seekBarpercent);
        seekBarpercent.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                progressBarpercent.setMax(100);
                progressBarpercent.setProgress(progress);
                textViewpercent.setText("" + progress + "%");
                refspeed.push().setValue("Percentage: "+progress +"%");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                progressBar.setMax(180);
                progressBar.setProgress(progress);
                textView.setText("" + progress + " °");
                refmotion.push().setValue("Degrees: "+progress+"°");

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        grabswitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(grabswitch.isChecked()){

                    refgrab.push().setValue("Firmly Grasp");

                }
                else{
                    refgrab.push().setValue("No Grab");

                }
            }

        });

        return root;
    }
}