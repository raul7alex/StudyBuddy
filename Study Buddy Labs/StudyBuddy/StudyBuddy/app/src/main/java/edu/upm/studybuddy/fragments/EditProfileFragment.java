package edu.upm.studybuddy.fragments;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import edu.upm.studybuddy.R;
import edu.upm.studybuddy.activities.BaseActivity;
import edu.upm.studybuddy.activities.LoginActivity;
import edu.upm.studybuddy.activities.RegistrationActivity;
import edu.upm.studybuddy.helper.FirebaseHelper;
import edu.upm.studybuddy.helper.ValidationHelper;
import edu.upm.studybuddy.models.UserModel;

public class EditProfileFragment extends Fragment {

    EditText mName;
    EditText mEmail;
    EditText mPhone;
    RatingBar mMath;
    RatingBar mAndroid;
    RatingBar mJava;
    RatingBar mIos;
    Button mSaveData;
    Button mLogout;


    public static final String TAG = EditProfileFragment.class.getSimpleName();

    public EditProfileFragment() {
    }

    public static EditProfileFragment newInstance() {
        return new EditProfileFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_edit_profile, null);
        return root;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initializeWidgets(view);
    }

    private void initializeWidgets(View view) {
        mName = (EditText) view.findViewById(R.id.profile_user);
        mEmail = (EditText) view.findViewById(R.id.profile_email);
        mPhone = (EditText) view.findViewById(R.id.profile_phone);
        mMath = (RatingBar) view.findViewById(R.id.rating_bar_math);
        mAndroid = (RatingBar) view.findViewById(R.id.rating_bar_android);
        mJava = (RatingBar) view.findViewById(R.id.rating_bar_java);
        mIos = (RatingBar) view.findViewById(R.id.rating_bar_ios);
        mSaveData = (Button) view.findViewById(R.id.profile_save_data);
        mLogout = (Button) view.findViewById(R.id.profile_logout);

        Log.e(TAG,mMath.getStepSize() +"  step size is");

        mLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((BaseActivity)getActivity()).getFirebaseAuth().signOut();
                getActivity().finish();

                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
            }
        });

        mSaveData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Log.e(TAG, mMath.getRating()+"math rating");

                String uid = ((BaseActivity)getActivity()).getFirebaseAuth().getCurrentUser().getUid();
                UserModel user = new UserModel( uid, mName.getText().toString(), mEmail.getText().toString(), mPhone.getText().toString(),
                        (int)mMath.getRating(), (int)mAndroid.getRating(), (int)mJava.getRating(), (int)mIos.getRating(),
                        ((BaseActivity)getActivity()).getmCurrentUser().getmMatchedIds(), ((BaseActivity)getActivity()).getmCurrentUser().getmMatchAttempt());

                FirebaseHelper.saveUser(user, (BaseActivity)getActivity());

                Toast.makeText(getActivity(), "The data has been updated in the database", Toast.LENGTH_LONG ).show();
            }
        });

        final ProgressDialog progressDialog = ProgressDialog.show(getActivity(), "Please wait...", "Processing...", true);

        populateWidgets(((BaseActivity)getActivity()).getmCurrentUser(), progressDialog);


    }

    private void populateWidgets(UserModel value, ProgressDialog progress) {
        mName.setText(value.getmName());
        mEmail.setText(value.getEmail());
        mPhone.setText(value.getPhone());
        mMath.setRating(value.getmMathRating());
        mAndroid.setRating(value.getmAndroidRating());
        mJava.setRating(value.getmJavaRating());
        mIos.setRating(value.getmIosRating());

        progress.dismiss();
    }
}
