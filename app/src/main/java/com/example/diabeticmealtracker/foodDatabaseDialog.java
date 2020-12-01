package com.example.diabeticmealtracker;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

public class foodDatabaseDialog extends AppCompatDialogFragment {

    // variables
    private foodDatabaseDialogListener listener;
    private EditText servingSize;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // MyDiaLogTheme is found in themes.xml
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(), R.style.MyDialogTheme);


        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.food_database_layout, null);

        builder.setView(view)
                .setTitle("Add food to your daily intake?")
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String serving = servingSize.getText().toString().trim();
                        boolean add = false;
                        listener.add(add, serving);
                    }
                })
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String serving = servingSize.getText().toString().trim();
                        boolean add = true;
                        listener.add(add, serving);
                    }
                });

        servingSize = (EditText) view.findViewById(R.id.foodDatabaseServing);

        return builder.create();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            listener = (foodDatabaseDialogListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + "must implement foodDatabaseDialogListener");
        }
    }

    public interface foodDatabaseDialogListener {
        void add(boolean add, String servingSize);
    }
}
