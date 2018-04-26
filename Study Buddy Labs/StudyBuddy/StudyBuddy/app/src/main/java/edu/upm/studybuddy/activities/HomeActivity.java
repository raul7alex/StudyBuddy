package edu.upm.studybuddy.activities;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.PermissionChecker;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.security.Permission;
import java.util.ArrayList;
import java.util.List;

import edu.upm.studybuddy.R;
import edu.upm.studybuddy.adapter.UserRVAdapter;
import edu.upm.studybuddy.fragments.EditProfileFragment;
import edu.upm.studybuddy.helper.FirebaseHelper;
import edu.upm.studybuddy.listeners.MatchListener;
import edu.upm.studybuddy.models.UserModel;

public class HomeActivity extends BaseActivity implements MatchListener {

    private RecyclerView mRecyclerView;
    private ArrayList<UserModel> mUsers;
    private ProgressDialog progressDialog;

    private static final int CALL_PHONE_PERMISSION = 123;

    String phoneNumberToCall;


    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String[] permissions,
                                           int[] grantResults){

        switch (requestCode) {

            case CALL_PHONE_PERMISSION:

                if (grantResults[0] == PackageManager.PERMISSION_GRANTED &&
                        phoneNumberToCall != null){

                    callPhoneNumber(phoneNumberToCall);

                }

        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        getSupportActionBar().show();

        progressDialog = ProgressDialog.show(this, "Please wait...", "Processing...", true);

        initializeWidgets();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.action_settings) {
            getSupportActionBar().hide();
            EditProfileFragment fragment = EditProfileFragment.newInstance();
            addFragmentOnTop(fragment, R.id.fragment_on_top_container, EditProfileFragment.TAG, true, true, true);
        }
        return super.onOptionsItemSelected(item);
    }

    private void initializeWidgets() {

        SnapHelper snap = new PagerSnapHelper();

        mRecyclerView = (RecyclerView) findViewById(R.id.users_recycler_view);

        snap.attachToRecyclerView(mRecyclerView);

        DatabaseReference databaseReference = this.getDatabase().getReference("users").child((this).getFirebaseAuth().getCurrentUser().getUid());
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                progressDialog.dismiss();
                setmCurrentUser( dataSnapshot.getValue(UserModel.class) );

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        databaseReference = this.getDatabase().getReference("users");
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                Iterable<DataSnapshot> list = dataSnapshot.getChildren();

                // Getting current user Id
                String uid = HomeActivity.this.getmCurrentUser().getUid();

                // Filter User
                ArrayList<UserModel> userList = new ArrayList<>();
                for (DataSnapshot dataSnapshot1 : list) {
                    if (!dataSnapshot1.getKey().equals(uid)) {
                        UserModel user = dataSnapshot1.getValue(UserModel.class);

                        boolean found = false;

                        if (getmCurrentUser().getmMatchedIds()!= null && getmCurrentUser().getmMatchedIds().size()>0){

                            for (String s : getmCurrentUser().getmMatchedIds()) {

                                if (s.equalsIgnoreCase(user.getUid())){

                                    userList.add(0, user);

                                    found = true;

                                }

                            }

                        }

                        if (!found) {

                            userList.add(user);

                        }
                    }
                }

                mUsers = userList;

                UserRVAdapter rvAdapter = new UserRVAdapter(mUsers, HomeActivity.this, getmCurrentUser());
                mRecyclerView.setAdapter(rvAdapter);

                LinearLayoutManager lm = new LinearLayoutManager(HomeActivity.this);
                lm.setOrientation(LinearLayoutManager.HORIZONTAL);
                mRecyclerView.setLayoutManager(lm);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }

    @Override
    public void matchPressed(UserModel user, boolean shouldCall) {

        if (shouldCall){

            checkCallPermissionAndCallNumber(user.getPhone());

        }else {

            DatabaseReference databaseReference = getDatabase().getReference("users").child(user.getUid());
            databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {

                    UserModel otherUser = dataSnapshot.getValue(UserModel.class);

                    if (otherUser.getmMatchAttempt() != null && otherUser.getmMatchAttempt().size() > 0) {

                        boolean found = false;

                        for (String s : otherUser.getmMatchAttempt()){

                            if (s.equalsIgnoreCase(HomeActivity.this.getmCurrentUser().getUid())) {

                                otherUser.removeMatchAttempt(HomeActivity.this.getmCurrentUser().getUid());
                                otherUser.addMatchedUser(HomeActivity.this.getmCurrentUser().getUid());

                                FirebaseHelper.saveUser(otherUser, HomeActivity.this);

                                found = true;

                            }

                        }

                        if (found) {

                            HomeActivity.this.getmCurrentUser().addMatchedUser(otherUser.getUid());

                        }else {
                            HomeActivity.this.getmCurrentUser().addMatchAttempt(otherUser.getUid());
                        }

                    }else {

                        getmCurrentUser().addMatchAttempt(otherUser.getUid());

                    }


                    FirebaseHelper.saveUser(getmCurrentUser(), HomeActivity.this);

                    ((UserRVAdapter)mRecyclerView.getAdapter()).updatePosition(otherUser.getUid());

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

        }

    }


    private void checkCallPermissionAndCallNumber(String number) {

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {

            callPhoneNumber(number);

        }else {

            phoneNumberToCall = number;

            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.CALL_PHONE}, 123);

        }


    }

    private void callPhoneNumber(String number) {

        Intent intent = new Intent(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel:" + number));
        try {
            this.startActivity(intent);
        }catch(SecurityException e){
            Toast.makeText(this, "Cannot initiate call to number" + e.getMessage(), Toast.LENGTH_SHORT).show();
        }catch (Exception e) {
            Toast.makeText(this, "Cannot initiate call to number" + e.getMessage(), Toast.LENGTH_SHORT).show();
        }

    }

}
