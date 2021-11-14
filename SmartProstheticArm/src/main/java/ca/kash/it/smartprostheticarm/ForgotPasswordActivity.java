/*
    Kevin Santizo n01382533 Section B
    (Alex) Narendre Ramdhan n01240746 Section B
    Samad Agha n01364908 Section B
    Henry To n01365792 Section B
*/
package ca.kash.it.smartprostheticarm;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotPasswordActivity extends AppCompatActivity  {

    private EditText email;
    private Button submit;
    private ProgressBar progressbar;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot);

        email = (EditText) findViewById(R.id.forgotEmail);
        submit = (Button) findViewById(R.id.forgotSubmit);
        progressbar = (ProgressBar) findViewById(R.id.forgotprogressbar);

        auth = FirebaseAuth.getInstance();

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetPassword();
            }
        });


        Button back = (Button) findViewById(R.id.forgotBack);
        back.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent= new Intent(ForgotPasswordActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }

    private void resetPassword() {
        String emailString = email.getText().toString().trim();

        if (emailString.isEmpty()){
            email.setError("Email is required");
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(emailString).matches()){
            email.setError("Please provide a valid email");
            return;
        }

        progressbar.setVisibility(View.VISIBLE);
        auth.sendPasswordResetEmail(emailString).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Toast.makeText(ForgotPasswordActivity.this,"An email has been sent for password reset",Toast.LENGTH_LONG).show();
                    progressbar.setVisibility(View.GONE);
                }else{
                    Toast.makeText(ForgotPasswordActivity.this,"Please Try Again",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}