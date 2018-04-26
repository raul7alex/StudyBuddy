package edu.upm.studybuddy.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

import edu.upm.studybuddy.R;
import edu.upm.studybuddy.listeners.MatchListener;
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

        mUserName = itemView.findViewById(R.id.username);
        mEmail = itemView.findViewById(R.id.email);

        mMathRatingBar = (RatingBar)itemView.findViewById(R.id.math_rating_bar);
        mAndroidRatingBar = itemView.findViewById(R.id.android_rating_bar);
        mJavaRatingBar = itemView.findViewById(R.id.java_rating_bar);
        mIosRatingBar = itemView.findViewById(R.id.ios_rating_bar);

        mMatchButton = itemView.findViewById(R.id.match_button);
    }


    void bind(final UserModel user, final MatchListener listener, UserModel currentUser) {

        mUserName.setText(user.getmName());
        mEmail.setText(user.getEmail());

        mMathRatingBar.setRating(user.getmMathRating());
        mAndroidRatingBar.setRating(user.getmAndroidRating());
        mJavaRatingBar.setRating(user.getmJavaRating());
        mIosRatingBar.setRating(user.getmIosRating());

        boolean partialMatch = false;
        boolean totalMatch = false;

        if (currentUser.getmMatchedIds() != null && currentUser.getmMatchedIds().size() > 0){

            for (String s : currentUser.getmMatchedIds()){

                if (user.getUid().equalsIgnoreCase(s)){

                    totalMatch = true;

                    break;

                }

            }

        }


        if (totalMatch) {

            mMatchButton.setText("Call " + user.getmName());

        }else {

            if (currentUser.getmMatchAttempt()!= null && currentUser.getmMatchAttempt().size() > 0) {

                for (String s : currentUser.getmMatchAttempt()){

                    if (s.equalsIgnoreCase(user.getUid())) {

                        partialMatch = true;

                        break;

                    }

                }

            }

            if (partialMatch) {

                mMatchButton.setEnabled(false);
                mMatchButton.setOnClickListener(null);
            }

        }


        if (!partialMatch) {


            final boolean finalTotalMatch = totalMatch;
            mMatchButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    listener.matchPressed( user , finalTotalMatch);

                }
            });

        }

    }
}
