package edu.upm.studybuddy.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;

import edu.upm.studybuddy.models.UserModel;

public class UserRVAdapter extends RecyclerView.Adapter {

    private ArrayList<UserModel> mUsers;

    public UserRVAdapter (ArrayList<UserModel> mUsers){

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        //TODO crearea unui obiect View nou aici - cu layoutInflater si layout-ul din item_user_rv.xml

        //TODO crearea unui obiect nou de UserViewHolder cu view-ul creat mai sus

        //TODO return obiectul UserViewHolder

        return null;
    }


    //todo metoda are 2 param. : holder = UserViewHolder unde se va afisa informatia
    //// TODO:                   position = pozitia in lista cu useri a elementului care urmeaza sa fie afisat
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        UserViewHolder userViewHolder = (UserViewHolder)holder;

        //todo folosirea metodei bind din UserViewHolder pentru a lega userul de la pozitia position de view holder


    }

    @Override
    public int getItemCount() {

        //TODO return nr. de elemente pe care le vom afisa

        return 0;
    }
}
