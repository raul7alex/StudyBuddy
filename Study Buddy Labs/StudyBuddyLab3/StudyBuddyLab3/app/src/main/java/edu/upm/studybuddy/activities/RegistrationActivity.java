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
import com.google.firebase.database.DatabaseReference;
import com.google.gson.Gson;

import java.util.ArrayList;

import edu.upm.studybuddy.R;
import edu.upm.studybuddy.helper.ValidationHelper;
import edu.upm.studybuddy.models.UserModel;

public class RegistrationActivity extends BaseActivity {


    // fiecare field necesar din layout

    EditText mNameEditText;
    EditText mEmailEditText;
    EditText mPhoneEditText;
    EditText mPasswordEditText;
    EditText mPassword2EditText;
    Button mRegisterButton;
    TextView mErrorMessage;

    private DatabaseReference databaseUsers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        initializeWidgets();
    }

    private void initializeWidgets() {


        mNameEditText = findViewById(R.id.register_user);
        mEmailEditText = findViewById(R.id.register_email);
        mPhoneEditText = findViewById(R.id.register_phone);
        mPasswordEditText = findViewById(R.id.register_password);
        mPassword2EditText = findViewById(R.id.register_password2);
        mRegisterButton = findViewById(R.id.register_register_button);
        mErrorMessage = findViewById(R.id.register_error_message);

        mRegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(validData()) {
                    registerUser();
                } else {
                    mErrorMessage.setVisibility(View.VISIBLE);
                }
            }
        });
    }


    private void registerUser() {

        final ProgressDialog progressDialog = ProgressDialog.show(RegistrationActivity.this, "Please wait...", "Processing...", true);

        (getFirebaseAuth().createUserWithEmailAndPassword(mEmailEditText.getText().toString(), mPasswordEditText.getText().toString())).
                addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog.dismiss();
                        if (task.isSuccessful()) {

                            Toast.makeText(RegistrationActivity.this, "Registration successful", Toast.LENGTH_LONG).show();

                            databaseUsers = getDatabase().getReference("users");

                            String uid = getFirebaseAuth().getCurrentUser().getUid();

                            UserModel user = new UserModel(uid, mNameEditText.getText().toString(),
                                    mEmailEditText.getText().toString(), mPhoneEditText.getText().toString(), 0, 0, 0, 0, new ArrayList<String>(), new ArrayList<String>());

                            databaseUsers.child(uid).setValue(user);

                            Intent intent = new Intent(RegistrationActivity.this, LoginActivity.class);
                            startActivity(intent);
                        } else {
                            Log.e("Registration error", task.getException().getMessage());
                            Toast.makeText(RegistrationActivity.this, "Registration unsuccessful", Toast.LENGTH_LONG).show();
                        }
                    }
                });


    }

    private boolean validData() {
        String name = mNameEditText.getText().toString();
        String email = mEmailEditText.getText().toString();
        String phoneNumber = mPhoneEditText.getText().toString();
        String password1 = mPasswordEditText.getText().toString();
        String password2 = mPassword2EditText.getText().toString();

        if(ValidationHelper.isValidName(name) && ValidationHelper.isValidEmail(email)
                && ValidationHelper.isValidPhone(phoneNumber) && ValidationHelper.isValidPassword(password1)
                && ValidationHelper.isValidPassword(password2) && ValidationHelper.arePasswordsSame(password1, password2)) {
            return  true;
        }

        return false;

    }
}
