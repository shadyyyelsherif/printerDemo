package com.example.shadi.printerdemo;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ForgetPassword extends AppCompatActivity {

    EditText forget;
    Button bt_send;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);

        forget = findViewById(R.id.ed_forget);
        bt_send = findViewById(R.id.send);
        progressDialog = new ProgressDialog(this);

        bt_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog.setMessage("Sending confirmation code ...");
                progressDialog.show();
                Toast.makeText(ForgetPassword.this, "Done", Toast.LENGTH_SHORT).show();
            }
        });


    }

}
