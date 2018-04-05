package edu.upm.studybuddylab1.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import edu.upm.studybuddylab1.models.UserModel;

public class BaseActivity extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;

    private FirebaseDatabase database;

    private UserModel mCurrentUser;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        firebaseAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
    }

    public FirebaseAuth getFirebaseAuth() {
        return this.firebaseAuth;
    }

    public FirebaseDatabase getDatabase() {
        return this.database;
    }

    public UserModel getmCurrentUser() {
        return mCurrentUser;
    }

    public void setmCurrentUser(UserModel mCurrentUser) {
        this.mCurrentUser = mCurrentUser;
    }

}
