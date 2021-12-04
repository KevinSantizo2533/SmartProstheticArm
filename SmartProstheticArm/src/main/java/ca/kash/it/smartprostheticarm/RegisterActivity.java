/*
    Kevin Santizo n01382533 Section B
    (Alex) Narendre Ramdhan n01240746 Section B
    Samad Agha n01364908 Section B
    Henry To n01365792 Section B
*/
package ca.kash.it.smartprostheticarm;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private FirebaseAuth mAuth;
    private TextView registerUser;
    private EditText editTextFullName, editTextAge, editTextEmail, editTextPassword, editConfirmTextPassword;
    private ProgressBar progressbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance();

        registerUser = (Button) findViewById(R.id.registerUser);
        registerUser.setOnClickListener(this);
        editTextFullName = (EditText)  findViewById(R.id.name);
        editTextAge = (EditText)  findViewById(R.id.age);
        editTextEmail = (EditText)  findViewById(R.id.email);
        editTextPassword = (EditText)  findViewById(R.id.password);
        editConfirmTextPassword = (EditText)  findViewById(R.id.password2);

        progressbar = (ProgressBar) findViewById(R.id.loginprogressbar);

    }

    public static boolean isValidPassword(final String password) {

        Pattern pattern;
        Matcher matcher;
        final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{4,}$";
        pattern = Pattern.compile(PASSWORD_PATTERN);
        matcher = pattern.matcher(password);

        return matcher.matches();

    }



    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.registerUser:
                registerUser();
                break;

        }

    }

    private void registerUser() {
        String name = editTextFullName.getText().toString();
        String email = editTextEmail.getText().toString();
        String password = editTextPassword.getText().toString();
        String confirmpassword = editConfirmTextPassword.getText().toString();

        String age = editTextAge.getText().toString();

        if (name.isEmpty()){
            editTextFullName.setError(getString(R.string.nameRequired));
        }

        if (age.isEmpty()){
            editTextAge.setError(getString(R.string.ageReq));
        }

        if (email.isEmpty()){
            editTextEmail.setError(getString(R.string.emailReq));
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            editTextEmail.setError(getString(R.string.emailInvalid));

        }

        if (password.isEmpty()){
            editTextPassword.setError(getString(R.string.passReq));
            editTextPassword.requestFocus();
            return;
        }

        if (password.length()<8){
            editTextPassword.setError(getString(R.string.passMin));
            editTextPassword.requestFocus();
            return;
        }
        if(!confirmpassword.equals(password))
        {
            editConfirmTextPassword.setError("Password not the same");
            return;
        }


        if(password.length()<8 &&!isValidPassword(password)){
            editTextPassword.setError(getString(R.string.passMin));

//       # a digit must occur at least once
//       # a lower case letter must occur at least once
//       # an upper case letter must occur at least once
//       # a special character must occur at least once you can replace with your special characters
//       # no whitespace allowed in the entire string
//       # anything, at least eight places though
        }

        progressbar.setVisibility(View.VISIBLE);
        mAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(task.isSuccessful()){
                            User user = new User(name, age, email);
                            FirebaseDatabase.getInstance().getReference(getString(R.string.userspath))
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful()){
                                        Toast.makeText(RegisterActivity.this, "Registered Succesfully", Toast.LENGTH_LONG).show();
                                        progressbar.setVisibility(View.GONE);
                                    }else{
                                        Toast.makeText(RegisterActivity.this, "Failed to Register", Toast.LENGTH_LONG).show();
                                        progressbar.setVisibility(View.GONE);
                                    }

                                }
                            });

                        }else{
                            Toast.makeText(RegisterActivity.this, "Failed to Register", Toast.LENGTH_LONG).show();
                            progressbar.setVisibility(View.GONE);
                        }

                    }
                });

    }
}