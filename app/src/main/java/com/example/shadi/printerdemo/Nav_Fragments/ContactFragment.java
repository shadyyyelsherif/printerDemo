package com.example.shadi.printerdemo.Nav_Fragments;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.shadi.printerdemo.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class ContactFragment extends Fragment {

    EditText ed_name, ed_phone, ed_email, ed_msg;
    Button bt_submit;
    ProgressDialog progressDialog;
    FloatingActionButton fab;

    public ContactFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_contact, container, false);

        ed_name = view.findViewById(R.id.ed_name);
        ed_phone = view.findViewById(R.id.ed_phone);
        ed_email = view.findViewById(R.id.ed_email);
        ed_msg = view.findViewById(R.id.ed_message);
        bt_submit = view.findViewById(R.id.bt_submit);
        fab = view.findViewById(R.id.fab);


        bt_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                progressDialog.setMessage("Sending");
                progressDialog.show();
            }
        });

        return view;
    }

}
