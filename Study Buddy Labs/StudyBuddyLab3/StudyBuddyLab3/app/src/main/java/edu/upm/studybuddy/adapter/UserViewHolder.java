package edu.upm.studybuddy.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

import edu.upm.studybuddy.models.UserModel;

public class UserViewHolder extends RecyclerView.ViewHolder {

    private TextView mUserName;
    private TextView mEmail;
    private RatingBar mMathRatingBar;
    private RatingBar mAndroidRatingBar;
    private RatingBar mJavaRatingBar;
    private RatingBar mIosRatingBar;
    private Button mMatchButton;

    public UserViewHolder(View itemView) {
        super(itemView);

        //TODO initializarea elementelor de UI din itemView
    }

    public void bind(UserModel model) {


        //TODO legarea elementelor de UI initializare de informatiile din model

    }
}
