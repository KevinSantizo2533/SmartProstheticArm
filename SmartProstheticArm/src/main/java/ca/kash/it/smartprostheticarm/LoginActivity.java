/*
    Kevin Santizo n01382533 Section B
    (Alex) Narendre Ramdhan n01240746 Section B
    Samad Agha n01364908 Section B
    Henry To n01365792 Section B
*/
package ca.kash.it.smartprostheticarm;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView register1;
    private TextView forgotpass;
    private EditText editTextEmail, editTextPassword;
    private Button loginBtn;
    private FirebaseAuth mAuth;
    private ProgressBar progressbar;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        register1 = (TextView) findViewById(R.id.register);
        register1.setOnClickListener(this);
        forgotpass = (TextView) findViewById(R.id.forgotpass);
        forgotpass.setOnClickListener(this);
        loginBtn = (Button)findViewById(R.id.loginBtn);
        loginBtn.setOnClickListener(this);
        editTextEmail = (EditText)  findViewById(R.id.email);
        editTextPassword = (EditText) findViewById(R.id.password);
        progressbar = (ProgressBar) findViewById(R.id.progressbar);

    }



    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.register:
                startActivity(new Intent(this, RegisterActivity.class));
                break;
            case R.id.forgotpass:
                startActivity(new Intent(this, ForgotPasswordActivity.class));
                break;
            case R.id.loginBtn:
                startActivity(new Intent(this, MainActivity.class));
                break;
        }

    }

//    private void loginUser() {
//        String password = editTextPassword.getText().toString().trim();
//        String email = editTextEmail.getText().toString().trim();
//
//        if(email.isEmpty()){
//            editTextEmail.setError("Email is required");
//            editTextEmail.requestFocus();
//            return;
//        }
//        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
//            editTextEmail.setError("Email invalid");
//            editTextEmail.requestFocus();
//            return;
//        }
//
//        if(password.isEmpty()){
//            editTextPassword.setError("Password is required");
//            editTextPassword.requestFocus();
//            return;
//        }
//
//        if (password.length()<6){
//            editTextPassword.setError(getString(R.string.passMin));
//            editTextPassword.requestFocus();
//            return;
//        }
//
//        progressbar.setVisibility(View.VISIBLE);
//        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
//            @Override
//            public void onComplete(@NonNull Task<AuthResult> task) {
//                if(task.isSuccessful()){
//                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
//                }else{
//                    Toast.makeText(LoginActivity.this,"Failed to login",Toast.LENGTH_LONG).show();
//                }
//            }
//        });
//    }

    @Override
    public void onBackPressed(){
        final AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
        builder.setMessage(R.string.Dialog_Exit);
        builder.setCancelable(true);
        builder.setNegativeButton(R.string.onBack_No, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.setPositiveButton(R.string.OnBack_Close, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }


}