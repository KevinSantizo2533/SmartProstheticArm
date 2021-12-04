/*
    Kevin Santizo n01382533 Section B
    (Alex) Narendre Ramdhan n01240746 Section B
    Samad Agha n01364908 Section B
    Henry To n01365792 Section B
*/
package ca.kash.it.smartprostheticarm;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.Toast;


import com.google.android.material.snackbar.Snackbar;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ReviewActivity extends AppCompatActivity {

    EditText editname,editphone,editemail,editcomment;
    Button submit;
    RatingBar ratingbar;

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


                if (editname.getText().toString().matches("^[A-Za-z]+$+")) {
                } else {
                    editname.setError("Invalid name format");

                }

                if (editemail.getText().toString().matches("[a-zA-Z0-9]+@[a-z].+[a-z]+")) {
                } else {
                    editemail.setError("Invalid email format");
                }

                if (editphone.length() != 10) {

                    editphone.setError("Phone number must be 10 digits");
                }

                if (editcomment.length() < 1) {
                    editcomment.setError("Please add a comment");

                }
                if (ratingbar.getRating() == 0)

                {
                    Toast toast = Toast.makeText(ReviewActivity.this, "Enter a rating", Toast.LENGTH_SHORT);
                    toast.show();
                }

                if (editname.getText().toString().matches("^[A-Za-z]+$+")
                        && editemail.getText().toString().matches("[a-zA-Z0-9]+@[a-z].+[a-z]+") && editphone.length() == 10 && editcomment.length() > 0 && ratingbar.getRating() != 0) {
                    submit.setEnabled(false);
                    progbar.setVisibility(View.VISIBLE);

                    submit.postDelayed(new Runnable() {

                        @Override
                        public void run() {
                            String name = editname.getText().toString();
                            String email = editemail.getText().toString();
                            String comment = editcomment.getText().toString();
                            String phone = editphone.getText().toString();

                            long phone2 = Long.parseLong(phone);
                            float rating = ratingbar.getRating();
                            String model = getModel();
                            Review review = new Review(name, email, phone2, comment, rating, model);

                            ref.push().setValue(review);

                            Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content), R.string.Reviewsent, Snackbar.LENGTH_LONG);
                            snackbar.show();
                            progbar.setVisibility(View.INVISIBLE);

                            submit.setEnabled(true);
                        }

                    }, 2000);
                }

            }
        });

    }
    public static String getModel() {
        String model = Build.MODEL;
            return model;
    }
}