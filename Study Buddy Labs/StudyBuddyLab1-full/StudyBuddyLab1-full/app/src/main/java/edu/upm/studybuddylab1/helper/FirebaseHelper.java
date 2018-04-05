package edu.upm.studybuddylab1.helper;

import com.google.firebase.database.DatabaseReference;

import edu.upm.studybuddylab1.activities.BaseActivity;
import edu.upm.studybuddylab1.models.UserModel;

public class FirebaseHelper {


    public static void saveUser(UserModel user, BaseActivity act) {

        DatabaseReference databaseReference = act.getDatabase().getReference("users").child(user.getUid());
        databaseReference.setValue(user);

    }

}
