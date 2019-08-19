package com.example.uros.projekat3novo;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class EditFragment extends Fragment {

    EditText nameContact;
    EditText numberContact;
    DatabaseHelper dbHandler;
    Button editBtn;
    String id;
    Contact contact;
    String name;
    String number;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.current_fragment, container, false);

        dbHandler = new DatabaseHelper(getActivity().getApplicationContext());
        final Bundle value = getActivity().getIntent().getExtras();

        nameContact = (EditText) v.findViewById(R.id.nameEdit);
        numberContact = (EditText) v.findViewById(R.id.numberEdit);

        editBtn = (Button) v.findViewById(R.id.editButton);

        nameContact.setText(value.getString("ime"));
        numberContact.setText(value.getString("broj"));
        id = value.getString("id");

        editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                contact = new Contact();
                contact.setId(Long.parseLong(String.valueOf(id)));
                contact.setNameContact(nameContact.getText().toString());
                contact.setNumberContact(numberContact.getText().toString());

                dbHandler.updateContact(contact);
                dbHandler.close();

                Intent startIntent = new Intent(getActivity().getApplicationContext(), CurrentContactActivity.class);
                startIntent.putExtra("ime", contact.getNameContact());
                startIntent.putExtra("broj", contact.getNumberContact());
                startIntent.putExtra("id",id);
                startActivity(startIntent);
            }
        });

        return v;
    }
}

