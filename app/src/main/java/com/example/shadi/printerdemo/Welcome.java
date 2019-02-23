package com.example.shadi.printerdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Welcome extends AppCompatActivity implements View.OnClickListener {

    Button bt_wregister, bt_wLogin;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome);


        bt_wregister = findViewById(R.id.wregister);
        bt_wLogin = findViewById(R.id.wlogin);

        bt_wregister.setOnClickListener(this);
        bt_wLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.wlogin:
//                loGin();
                startActivity(new Intent(Welcome.this, LoginRegisterActivity.class));
                break;


            case R.id.wregister:
                startActivity(new Intent(Welcome.this, Register.class));
                break;
        }

        }
}
