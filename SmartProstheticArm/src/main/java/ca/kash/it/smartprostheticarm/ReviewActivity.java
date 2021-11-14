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
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ReviewActivity extends AppCompatActivity {

    public EditText editname,editphone,editemail;
    Button submit;

    FirebaseDatabase database;
    DatabaseReference ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);
        //getDeviceName()
        submit = (Button) findViewById(R.id.reviewsubmit);

         editname = (EditText) findViewById(R.id.editName);
        editphone = (EditText) findViewById(R.id.editPhone);
        editemail = (EditText) findViewById(R.id.editEmail);


        ref = FirebaseDatabase.getInstance().getReference().child("Students");

        submit = (Button)findViewById(R.id.reviewsubmit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = editname.getText().toString();
                String email = editemail.getText().toString();
                Review review = new Review(name,email);

                ref.push().setValue(review);

            }
        });



    }






    public static String getDeviceName() {
        String manufacturer = Build.MANUFACTURER;
        String model = Build.MODEL;

        if (model.startsWith(manufacturer)) {
            return capitalize(model);
        }
        return capitalize(manufacturer) + " " + model;
    }

    private static String capitalize(String text) {
        if (TextUtils.isEmpty(text)) {
            return text;
        }
        char[] arr1 = text.toCharArray();
        boolean capitalize = true;

        StringBuilder phrase = new StringBuilder();

        for (char c:arr1) {
            if (capitalize && Character.isLetter(c)) {
                phrase.append(Character.toUpperCase(c));
                capitalize = false;
                continue;
            } else if (Character.isWhitespace(c)) {
                capitalize = true;
            }

            phrase.append(c);
        }

        return phrase.toString();
    }

}