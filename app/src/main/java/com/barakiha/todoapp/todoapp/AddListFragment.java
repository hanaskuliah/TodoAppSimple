package com.barakiha.todoapp.todoapp;


import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddListFragment extends DialogFragment implements View.OnClickListener {

    Button addButton, cancelButton;
    EditText contentEdittext;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View rootView = inflater.inflate(R.layout.fragment_add_list, container, false);
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);

        addButton = (Button) rootView.findViewById(R.id.button_add);
        addButton.setOnClickListener(this);

        cancelButton = (Button) rootView.findViewById(R.id.button_cancel);
        cancelButton.setOnClickListener(this);

        contentEdittext = (EditText) rootView.findViewById(R.id.editText_content);


        return rootView;
    }


    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == addButton.getId()) {
            getDialog().dismiss();
            ((TodoActivity) getActivity()).saveToDB(String.valueOf(contentEdittext.getText()));
            ((TodoActivity) getActivity()).updateUI();
            contentEdittext.setText("");
            Toast.makeText(getContext(), "Tersimpan", Toast.LENGTH_SHORT).show();
        } else if (id == cancelButton.getId()) {
            getDialog().dismiss();
        }
    }
}
