package edu.upm.studybuddy.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;

import edu.upm.studybuddy.R;
import edu.upm.studybuddy.helper.ValidationHelper;

public class LoginActivity extends BaseActivity {

    EditText mUserNameEditText;
    EditText mPasswordEditText;
    Button mLoginButton;
    TextView mGoToRegistration;
    TextView mErrorMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initializeWidgets();
    }

    private void initializeWidgets() {

         mUserNameEditText = findViewById(R.id.login_user);
         mPasswordEditText = findViewById(R.id.login_password);
         mLoginButton = findViewById(R.id.login_login_button);
         mGoToRegistration = findViewById(R.id.login_register);
         mErrorMessage = findViewById(R.id.login_error_message);


         mLoginButton.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {

                 if (credentialsAreValid()) {

                     login();

                 }else {

                     mErrorMessage.setVisibility(View.VISIBLE);

                 }


             }
         });

         mGoToRegistration.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {

                 Intent registrationIntent = new Intent(LoginActivity.this,
                         RegistrationActivity.class);

                 startActivity(registrationIntent);

             }
         });


    }

    private void login(){

        final ProgressDialog progressDialog = ProgressDialog.show(LoginActivity.this, "Please wait...", "Processing...", true);
        (getFirebaseAuth().signInWithEmailAndPassword(mUserNameEditText.getText().toString(), mPasswordEditText.getText().toString())).
                addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog.dismiss();
                        if(task.isSuccessful()) {
                            Toast.makeText(LoginActivity.this, "Login successful", Toast.LENGTH_LONG ).show();

                            Intent homeIntent = new Intent(LoginActivity.this, HomeActivity.class);

                            startActivity(homeIntent);

                        } else {
                            Log.e("Login error", task.getException().getMessage());
                            Toast.makeText(LoginActivity.this, "Login unsuccessful", Toast.LENGTH_LONG ).show();
                        }
                    }
                });


    }


    private boolean credentialsAreValid() {

        if (ValidationHelper.isValidEmail(mUserNameEditText.getText().toString())
                && ValidationHelper.isValidPassword(mPasswordEditText.getText().toString())){

            return true;

        }

        return false;

    }
}
