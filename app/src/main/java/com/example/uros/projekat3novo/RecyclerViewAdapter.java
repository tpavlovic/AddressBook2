package com.example.uros.projekat3novo;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>{

    private Context mContext;
    private List<Contact> listOfContacts;
    private DatabaseHelper dbHandlers;
    private Contact contact = new Contact();

    public RecyclerViewAdapter(Context mContext, List<Contact> listOfContacts) {
        this.mContext = mContext;
        this.listOfContacts = listOfContacts;
        dbHandlers = new DatabaseHelper(mContext);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v;
        v = LayoutInflater.from(mContext).inflate(R.layout.contact_item,parent , false);
        final MyViewHolder vHolder = new MyViewHolder(v);

        vHolder.kontaktLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), CurrentContactActivity.class);
                intent.putExtra("ime", vHolder.imeTextView.getText());
                intent.putExtra("broj", vHolder.brojTextView.getText());
                intent.putExtra("id", vHolder.kontaktId.getText());

                mContext.startActivity(intent);
            }
        });


        vHolder.omiljeniBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(mContext, MainActivity.class);
                Long kontaktID = Long.parseLong(String.valueOf(vHolder.kontaktId.getText()));
                contact = dbHandlers.getContactID(kontaktID);
                dbHandlers.close();

                if (contact.getFav() == 0) {
                    contact.setFav(1);
                    dbHandlers.setContactFavorite(contact.getId(), true);
                    dbHandlers.close();
                    vHolder.omiljeniBtn.getDrawable().setColorFilter(Color.YELLOW, PorterDuff.Mode.MULTIPLY);
                    mContext.startActivity(intent);
                }else{
                    contact.setFav(0);
                    dbHandlers.setContactFavorite(contact.getId(), false);
                    dbHandlers.close();
                    vHolder.omiljeniBtn.getDrawable().setColorFilter(Color.WHITE, PorterDuff.Mode.MULTIPLY);
                    mContext.startActivity(intent);
                }
                ((Activity) mContext).overridePendingTransition(0, 0);//ne resetuje ekran!
            }
        });
        return vHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
            holder.imeTextView.setText(listOfContacts.get(position).getNameContact());
            holder.brojTextView.setText(listOfContacts.get(position).getNumberContact());
            holder.kontaktId.setText(String.valueOf(listOfContacts.get(position).getId()));

            contact = dbHandlers.getContactID(Long.parseLong(String.valueOf(listOfContacts.get(position).getId())));
            dbHandlers.close();
            if(contact.getFav()==0){
                holder.omiljeniBtn.getDrawable().setColorFilter(Color.WHITE, PorterDuff.Mode.MULTIPLY);
            }else{
                holder.omiljeniBtn.getDrawable().setColorFilter(Color.YELLOW, PorterDuff.Mode.MULTIPLY);
            }
    }

    @Override
    public int getItemCount() {
        return listOfContacts.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        private LinearLayout kontaktLayout;
        private TextView imeTextView;
        private TextView brojTextView;
        private TextView kontaktId;
        private ImageView omiljeniBtn;


        public MyViewHolder(View itemView){
            super(itemView);

            omiljeniBtn = (ImageView)itemView.findViewById(R.id.favButton);
            kontaktLayout = (LinearLayout)itemView.findViewById(R.id.kontaktLayout);
            kontaktId = (TextView)itemView.findViewById(R.id.kontaktId);
            imeTextView = (TextView)itemView.findViewById(R.id.imeTextView);
            brojTextView = (TextView)itemView.findViewById(R.id.brojTextView);
        }
    }
}
