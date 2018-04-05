package edu.upm.studybuddylab1.activities;

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
import com.google.firebase.auth.FirebaseAuth;

import edu.upm.studybuddylab1.R;
import edu.upm.studybuddylab1.helper.ValidationHelper;

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

        FirebaseAuth.getInstance();

        initializeWidgets();
    }

    private void initializeWidgets() {


        //TODO a se lega elementele din UI cu obiectele declarate la inceputul clasei

        //TODO initializarea comportamentului butonului de login
        //TODO - verificarea validitatii campurilor
        //TODO      - invalid -> afisarea mesajului de eroare
        //TODO      - valid -> chemarea metodei login()

         mUserNameEditText = findViewById(R.id.login_user);
         mPasswordEditText = findViewById(R.id.login_password);
         mLoginButton = findViewById(R.id.login_login_button);
         mGoToRegistration = findViewById(R.id.login_register);
         mErrorMessage = findViewById(R.id.login_error_message);

         mLoginButton.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {

                 mErrorMessage.setVisibility(View.GONE);

                 if (ValidationHelper.isValidEmail(mUserNameEditText.getText().toString()) &&
                         ValidationHelper.isValidPassword(mPasswordEditText.getText().toString())){

                     login();

                 }else {

                     mErrorMessage.setVisibility(View.VISIBLE);

                 }

             }
         });

         mGoToRegistration.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {

                 Intent intent = new Intent(LoginActivity.this, RegistrationActivity.class);
                 startActivity(intent);

             }
         });


        //TODO initializarea comportamentul butonului de inregistrare

        //TODO acesta trebuie sa porneasca RegisterActivity

    }

    private void login(){

        final ProgressDialog progressDialog = ProgressDialog.show(LoginActivity.this, "Please wait...", "Processing...", true);
        (FirebaseAuth.getInstance().signInWithEmailAndPassword(mUserNameEditText.getText().toString(), mPasswordEditText.getText().toString())).
                addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog.dismiss();
                        if(task.isSuccessful()) {
                            Toast.makeText(LoginActivity.this, "Login successful", Toast.LENGTH_LONG ).show();

                            Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                            startActivity(intent);


                        } else {
                            Log.e("Login error", task.getException().getMessage());
                            Toast.makeText(LoginActivity.this, "Login unsuccessful", Toast.LENGTH_LONG ).show();

                            mErrorMessage.setText("Invalid credentials");

                            mErrorMessage.setVisibility(View.VISIBLE);

                        }
                    }
                });


    }


    private boolean credentialsAreValid() {

        //TODO a se returna validitatea informatiilor din campurile in formular

        return false;

    }
}
