package com.example.uros.projekat3novo;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class DeleteFragment extends Fragment {

    TextView nameDelete;
    TextView numberDelete;
    Button deleteBtn;
    DatabaseHelper dbHandler;

    public DeleteFragment(){
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.delete_fragment,container,false);

        final Bundle value = getActivity().getIntent().getExtras();
        dbHandler = new DatabaseHelper(getActivity().getApplicationContext());

        nameDelete = (TextView)v.findViewById(R.id.nameDelete);
        numberDelete = (TextView)v.findViewById(R.id.numberDelete);

        nameDelete.setText(value.getString("ime"));
        numberDelete.setText(value.getString("broj"));

        deleteBtn = (Button)v.findViewById(R.id.deleteButton);

        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

                builder.setTitle("Confirm close");
                builder.setMessage("Are you sure?");

                builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {
                        Intent startIntent = new Intent(getContext(), MainActivity.class);
                        long r = Long.parseLong(value.getString("id"), 10);
                        dbHandler.deleteContact(r);
                        startActivity(startIntent);
                        dialog.dismiss();
                    }
                });

                builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                AlertDialog alert = builder.create();
                alert.show();
            }
        });

        return  v;
    }
}
