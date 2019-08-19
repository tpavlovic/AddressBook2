package com.example.uros.projekat3novo;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailsFragment extends Fragment {

    TextView nameDetail;
    TextView numberDetail;
    TextView favDetail;
    ImageView imageView;
    Long contactID;
    Contact contact = new Contact();
    DatabaseHelper dbHandler;
    Button allContacts;
    View v;
    int fav;

    public DetailsFragment(){
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.details_fragment,container,false);

        Bundle value = getActivity().getIntent().getExtras();

        dbHandler = new DatabaseHelper(getActivity().getApplicationContext());

        contactID = Long.parseLong(String.valueOf(value.getString("id")));

        nameDetail = (TextView)v.findViewById(R.id.imeInfo);
        numberDetail = (TextView)v.findViewById(R.id.brojInfo);
        favDetail = (TextView)v.findViewById(R.id.favDetail);
        imageView = (ImageView)v.findViewById(R.id.imageView);
        allContacts = (Button)v.findViewById(R.id.allButton);

        nameDetail.setText(value.getString("ime"));
        numberDetail.setText(value.getString("broj"));


        fav = dbHandler.getAllFavContacts().size();

        favDetail.setText(String.valueOf(fav));


        contact = dbHandler.getContactID(contactID);

        if(contact.getFav()==1){
            imageView.getDrawable().setColorFilter(Color.YELLOW, PorterDuff.Mode.MULTIPLY);
        }else{
            imageView.getDrawable().setColorFilter(Color.WHITE, PorterDuff.Mode.MULTIPLY);
        }

        allContacts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(),MainActivity.class);
                startActivity(intent);
            }
        });

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(contact.getFav()==1){
                    favDetail.setText(String.valueOf(--fav));
                    contact.setFav(0);

                    dbHandler.setContactFavorite(contact.getId(), false);
                    imageView.getDrawable().setColorFilter(Color.WHITE, PorterDuff.Mode.MULTIPLY);
                }else{
                    favDetail.setText(String.valueOf(++fav));
                    contact.setFav(1);

                    dbHandler.setContactFavorite(contact.getId(), true);
                    imageView.getDrawable().setColorFilter(Color.YELLOW, PorterDuff.Mode.MULTIPLY);
                }
            }
        });



        return v;
    }

}