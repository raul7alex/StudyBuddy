package edu.upm.studybuddy.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;

import edu.upm.studybuddy.R;

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

        //todo instantiere elemente UI



    }


}
