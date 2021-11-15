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
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
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
    private EditText loginEmail, loginPass;
    private Button loginBtn;
    private FirebaseAuth mAuth;
    private ProgressBar progressbar;
    private CheckBox remember;
    private SharedPreferences pref;
    private static final String PREF = "preffile";

    /** google auth **/
    private GoogleSignInClient mGoogleSignInClient;
    private final static int RC_SIGN_IN = 100;

    @Override
    protected void onStart() { /* if the user is already logged in via firebase, then login automatically when app is opened*/
        super.onStart();
        FirebaseUser user = mAuth.getCurrentUser();
        if(user!=null){
            Intent intent = new Intent(getApplicationContext(),MainActivity.class);
            startActivity(intent);
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        createRequest();
        findViewById(R.id.alternative_google).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn();
            }
        });
        mAuth = FirebaseAuth.getInstance();
        register1 = (TextView) findViewById(R.id.register);
        register1.setOnClickListener(this);
        forgotpass = (TextView) findViewById(R.id.forgotpass);
        forgotpass.setOnClickListener(this);
        loginBtn = (Button)findViewById(R.id.loginBtn);
        loginBtn.setOnClickListener(this);
        loginEmail = (EditText)  findViewById(R.id.loginEmail);
        loginPass = (EditText) findViewById(R.id.loginPass);
        progressbar = (ProgressBar) findViewById(R.id.loginprogressbar);
        remember = (CheckBox) findViewById(R.id.rememberme);

        pref = getSharedPreferences(PREF,MODE_PRIVATE);

        getPreferences();

    }

    private void createRequest() {
        // Configure Google Sign In
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
    }

    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
                firebaseAuthWithGoogle(account.getIdToken());
            } catch (ApiException e) {
                // Google Sign In failed, update UI appropriately
                Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void firebaseAuthWithGoogle(String idToken) {
        AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            FirebaseUser user = mAuth.getCurrentUser();
                            startActivity(new Intent(LoginActivity.this, MainActivity.class));
                        } else {
                            Toast.makeText(LoginActivity.this, "Authentication failed!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }


    private void getPreferences(){
        SharedPreferences p = getSharedPreferences(PREF, MODE_PRIVATE);
        if (p.contains("prefname")){
            String name = p.getString("prefname","notfound");
            loginEmail.setText(name.toString());
        }
        if(p.contains("prefpass")){
            String pass = p.getString("prefpass","notfound");
            loginPass.setText(pass.toString());
        }
        if(p.contains("prefremember")){
            Boolean check = p.getBoolean("prefremember",false);
            remember.setChecked(check);
        }
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

                if(remember.isChecked()){
                    Boolean isChecked = remember.isChecked();
                    SharedPreferences.Editor editor = pref.edit();
                    editor.putString("prefname", loginEmail.getText().toString());
                    editor.putString("prefpass", loginPass.getText().toString());
                    editor.putBoolean("prefremember",isChecked);
                    editor.apply();

                }else{

                    pref.edit().clear().apply();
                }

                loginUser();
                break;
        }

    }

    private void loginUser() {
        String logPassword = loginPass.getText().toString().trim();
        String logEmail = loginEmail.getText().toString().trim();

        if(logEmail.isEmpty()){
            loginEmail.setError("Email is required");
            loginEmail.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(logEmail).matches()){
            loginEmail.setError("Email invalid");
            loginEmail.requestFocus();
            return;
        }

        if(logPassword.isEmpty()){
            loginPass.setError("Password is required");
            loginPass.requestFocus();
            return;
        }

        if (logPassword.length()<6){
            loginPass.setError(getString(R.string.passMin));
            loginPass.requestFocus();
            return;
        }

        progressbar.setVisibility(View.VISIBLE);
        mAuth.signInWithEmailAndPassword(logEmail, logPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                    if(user.isEmailVerified()){
                        startActivity(new Intent(LoginActivity.this,MainActivity.class));
                    }else{
                        user.sendEmailVerification();
                        Toast.makeText(LoginActivity.this,"An email has been sent for verification",Toast.LENGTH_LONG).show();
                    }
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    }else{
                    Toast.makeText(LoginActivity.this,"Failed to login",Toast.LENGTH_LONG).show();
                }
            }
        });
    }

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