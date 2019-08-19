package com.example.uros.projekat3novo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class FavoriteFragment extends Fragment {

    View v;
    DatabaseHelper dbHandler;
    RecyclerView recyclerView;
    List<Contact> contactLista;

    public FavoriteFragment(){
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.favorite_fragment,container,false);

        recyclerView = (RecyclerView) v.findViewById(R.id.recyclerView);
        FavoriteRecyclerViewAdapter recyclerAdapter = new FavoriteRecyclerViewAdapter(getContext(), contactLista);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        recyclerView.setAdapter(recyclerAdapter);

        return v;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        contactLista = new ArrayList<>();
        dbHandler = new DatabaseHelper(getActivity());
        if (dbHandler.getAllContacts().size()>0) {
            contactLista = dbHandler.getAllFavContacts();
        }
        dbHandler.close();
    }
}
