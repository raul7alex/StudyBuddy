package edu.upm.studybuddy.helper;

import com.google.firebase.database.DatabaseReference;

import edu.upm.studybuddy.activities.BaseActivity;
import edu.upm.studybuddy.models.UserModel;

public class FirebaseHelper {


    public static void saveUser(UserModel user, BaseActivity act) {

        DatabaseReference databaseReference = act.getDatabase().getReference("users").child(user.getUid());
        databaseReference.setValue(user);

    }

}
