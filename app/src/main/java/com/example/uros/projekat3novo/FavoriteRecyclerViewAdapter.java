package com.example.uros.projekat3novo;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class FavoriteRecyclerViewAdapter extends RecyclerView.Adapter<FavoriteRecyclerViewAdapter.MyViewHolder>{

    private Context mContext;
    private List<Contact> listOfContacts;
    private DatabaseHelper dbHandlers;
    private Contact contact = new Contact();


    public FavoriteRecyclerViewAdapter(Context mContext, List<Contact> kontaktiLista) {
        this.mContext = mContext;
        this.listOfContacts = kontaktiLista;
        dbHandlers = new DatabaseHelper(mContext);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v;
        v = LayoutInflater.from(mContext).inflate(R.layout.fav_item,parent ,false);
        final MyViewHolder vHolder = new MyViewHolder(v, mContext, listOfContacts);

        return vHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
            holder.nameCardView.setText(listOfContacts.get(position).getNameContact());
            holder.numberCardView.setText(listOfContacts.get(position).getNumberContact());
            holder.kontaktCardVIewId.setText(String.valueOf(listOfContacts.get(position).getId()));
    }

    @Override
    public int getItemCount() {
        return listOfContacts.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, PopupMenu.OnMenuItemClickListener{

        private TextView nameCardView;
        private TextView numberCardView;
        private TextView kontaktCardVIewId;
        private Button detailBtn;

        private List<Contact> con = new ArrayList<>();
        Context contex;
        DatabaseHelper helper;

        public MyViewHolder(View itemView,  Context context, List<Contact> con){
            super(itemView);

            helper = new DatabaseHelper(context);
            itemView.setOnClickListener(this);

            this.con = con;
            this.contex = context;

            kontaktCardVIewId = (TextView)itemView.findViewById(R.id.kontaktCardVIewId);
            nameCardView = (TextView)itemView.findViewById(R.id.nameCardView);
            numberCardView = (TextView)itemView.findViewById(R.id.numberCardView);
        }

        @Override
        public void onClick(View v) {
            showPopup(v);
        }

        @Override
        public boolean onMenuItemClick(MenuItem item) {
            int position = getAdapterPosition();
            Contact contact = this.con.get(position);
            switch (item.getItemId()){
                case R.id.edit:
                    Intent startIntent = new Intent(contex, CurrentContactActivity.class);//otavra drugi prozor
                    startIntent.putExtra("ime", nameCardView.getText());
                    startIntent.putExtra("broj", numberCardView.getText());
                    startIntent.putExtra("id",kontaktCardVIewId.getText());
                    startIntent.putExtra("pos", 2);
                    contex.startActivity(startIntent);
                    return true;

                case R.id.delete:
                    Intent intent2 = new Intent(this.contex, MainActivity.class);
                    helper = new DatabaseHelper(contex);
                    contact.setFav(0);
                    helper.setContactFavorite(contact.getId(), false);
                    if(helper.getAllFavContacts().size()>0) {
                        con = helper.getAllFavContacts();
                    }
                    this.contex.startActivity(intent2);
                    return  true;
                default: return false;
            }
        }

        public void showPopup(View v){
            PopupMenu popup = new PopupMenu(contex, v);
            popup.setOnMenuItemClickListener(this);
            popup.inflate(R.menu.popup_menu);
            popup.show();
        }
    }
}
