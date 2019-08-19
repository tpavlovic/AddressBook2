package com.example.uros.projekat3novo;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class ContactFragment extends Fragment {

    View v;
    private RecyclerView myRecycleView;
    private List<Contact> listContact;
    DatabaseHelper helper;
    Button addBtn;

    public ContactFragment(){
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
       v = inflater.inflate(R.layout.contacts_fragment,container,false);
       myRecycleView = (RecyclerView) v.findViewById(R.id.recyclerView);
       RecyclerViewAdapter recyclerAdapter = new RecyclerViewAdapter(getContext(), listContact);
       myRecycleView.setLayoutManager(new LinearLayoutManager(getActivity()));
       myRecycleView.setAdapter(recyclerAdapter);

       addBtn = (Button)v.findViewById(R.id.addButton);
       addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent startIntent = new Intent(getActivity(),AddActivity.class);
                startActivity(startIntent);
            }
        });
       return v;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        helper = new DatabaseHelper(getActivity());
        listContact = new ArrayList<>();
        if(helper.getAllContacts().size()>0) {
            listContact = helper.getAllContacts();
        }
    }
}
