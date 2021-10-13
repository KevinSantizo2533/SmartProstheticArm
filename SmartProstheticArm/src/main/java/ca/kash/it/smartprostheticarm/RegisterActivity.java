/*
    Kevin Santizo n01382533 Section B
    (Alex) Narendre Ramdhan n01240746 Section B
    Samad Agha n01364908 Section B
    Henry To n01365792 Section B
*/
package ca.kash.it.smartprostheticarm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private FirebaseAuth mAuth;
    private TextView registerUser;
    private EditText editTextFullName, editTextAge, editTextEmail, editTextPassword;

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

        if (password.isEmpty()){
            editTextPassword.setError(getString(R.string.passReq));
        }
    }
}