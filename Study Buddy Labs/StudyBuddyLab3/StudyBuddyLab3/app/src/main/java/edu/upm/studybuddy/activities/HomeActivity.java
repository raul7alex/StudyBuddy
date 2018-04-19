package edu.upm.studybuddy.activities;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import edu.upm.studybuddy.R;
import edu.upm.studybuddy.fragments.EditProfileFragment;
import edu.upm.studybuddy.models.UserModel;

public class HomeActivity extends BaseActivity{

    private RecyclerView mRecyclerView;
    private ArrayList<UserModel> mUsers;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        getSupportActionBar().show();

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

    @Override
    public void onBackPressed(){
        getSupportActionBar().show();
    }


    public void initializeWidgets() {

        //TODO initializare recyclerView din layout aici

        DatabaseReference databaseReference = this.getDatabase().getReference("users").child((this).getFirebaseAuth().getCurrentUser().getUid());
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
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
                // lista de useri din baza de date
                mUsers = userList;


                //TODO 1.o noua instanta de UserRvAdapter aici

                //TODO 2.atasare Adapter de RV aici


                /* TODO 3.o noua instanta de LinearLayoutManager aici
                    TODO 4. atasare LinearLayoutManager de RV aici */



            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



    }
}
