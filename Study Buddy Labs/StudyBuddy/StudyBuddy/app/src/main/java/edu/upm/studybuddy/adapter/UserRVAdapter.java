package edu.upm.studybuddy.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import edu.upm.studybuddy.R;
import edu.upm.studybuddy.listeners.MatchListener;
import edu.upm.studybuddy.models.UserModel;

public class UserRVAdapter extends RecyclerView.Adapter {

    private ArrayList<UserModel> mUsers;
    private MatchListener mListener;
    private UserModel mUser;

    public UserRVAdapter(ArrayList<UserModel> users, MatchListener listener, UserModel user){

        mUsers = users;
        mListener = listener;
        mUser = user;

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user_rv, parent, false);

        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        ((UserViewHolder)holder).bind(mUsers.get(position), mListener, mUser);

    }

    @Override
    public int getItemCount() {
        return mUsers.size();
    }

    public void updatePosition(String id) {

        int i = -1;

        for (int j = 0; j < mUsers.size(); j++){

            if (id.equalsIgnoreCase(mUsers.get(j).getUid())){

                i = j;

                break;

            }

        }

        if (i > -1) {
            notifyItemChanged(i);
        }

    }
}
