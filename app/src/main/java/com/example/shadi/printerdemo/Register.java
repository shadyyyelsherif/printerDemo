package com.example.shadi.printerdemo;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Register extends AppCompatActivity implements View.OnClickListener  {

    FirebaseAuth auth;

    EditText edemail, edpassword, edconpassword;

    Button bt_register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_activity);

        auth = FirebaseAuth.getInstance();

        edemail = findViewById(R.id.email);
        edpassword = findViewById(R.id.password);
        edconpassword = findViewById(R.id.conpassword);
        bt_register = findViewById(R.id.register);

        /*animation4 = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.undertoup);
        register_btn_register_page.setAnimation(animation4);*/

        bt_register.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.register:

                Register();
                break;

        }

    }

    private void Register() {

        String email = edemail.getText().toString();
        String password = edpassword.getText().toString();
        String repassword = edconpassword.getText().toString();

        if (email.length() > 0 && password.length() > 0) {
            auth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if (task.isSuccessful()) {
                                Toast.makeText(getApplicationContext(), "Successful", Toast.LENGTH_LONG).show();
                                sendEmailVerification();

                            } else {
                                Toast.makeText(getApplicationContext(), "Invalid Email", Toast.LENGTH_LONG).show();

                            }
                        }
                    });
        } else {
            edemail.setError("please check Email");
            edpassword.setError("please check password");
            edconpassword.setError("please check password");
            edpassword.setHintTextColor(Color.RED);
            edemail.setHintTextColor(Color.RED);
            edconpassword.setHintTextColor(Color.RED);


        }

        if (repassword.length() != password.length()) {
            Toast.makeText(getApplicationContext(), "The password is not matching", Toast.LENGTH_LONG).show();
            edconpassword.setTextColor(Color.RED);
            edpassword.setTextColor(Color.RED);
            edpassword.setError("please check password");
            edconpassword.setError("please check password");

        }

        if (edpassword.length() < 6) {
            edpassword.setError("Password must be more than 6 char.");
            edconpassword.setError("Password must be more than 6 char.");
            edconpassword.setTextColor(Color.RED);
            edpassword.setTextColor(Color.RED);

        }

    }

    private void sendEmailVerification() {
    }
}
