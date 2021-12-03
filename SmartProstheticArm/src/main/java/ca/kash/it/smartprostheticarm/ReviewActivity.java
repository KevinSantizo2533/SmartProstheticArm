/*
    Kevin Santizo n01382533 Section B
    (Alex) Narendre Ramdhan n01240746 Section B
    Samad Agha n01364908 Section B
    Henry To n01365792 Section B
*/
package ca.kash.it.smartprostheticarm;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ReviewActivity extends AppCompatActivity {

    EditText editname,editphone,editemail,editcomment;
    Button submit;
    RatingBar ratingbar;

    FirebaseDatabase database;
    DatabaseReference ref;
    ProgressBar progbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);
        //getDeviceName()
        submit = (Button) findViewById(R.id.reviewsubmit);
        progbar = (ProgressBar) findViewById(R.id.progressBar);

        editname = (EditText) findViewById(R.id.editName);
        editphone = (EditText) findViewById(R.id.editPhone);
        editemail = (EditText) findViewById(R.id.editEmail);
        editcomment = (EditText) findViewById(R.id.editComment);
        ratingbar = findViewById(R.id.ratingBar);
        ref = FirebaseDatabase.getInstance().getReference().child("Feedback");
        submit = (Button)findViewById(R.id.reviewsubmit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                submit.setEnabled(false);
                progbar.setVisibility(View.VISIBLE);

                submit.postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        String name = editname.getText().toString();
                        String email = editemail.getText().toString();
                        String comment = editcomment.getText().toString();
                        String phone = editphone.getText().toString();
                        int phone2 = Integer.parseInt(phone);
                        float rating = ratingbar.getRating();
                        String model = getModel();
                        Review review = new Review(name,email, phone2,comment,rating, model);

                        ref.push().setValue(review);

                        Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content), R.string.Reviewsent, Snackbar.LENGTH_LONG);
                        snackbar.show();
                        progbar.setVisibility(View.INVISIBLE);

                        submit.setEnabled(true);
                    }

                }, 2000);


            }
        });
    }
    public static String getModel() {
        String model = Build.MODEL;
            return model;
    }
}