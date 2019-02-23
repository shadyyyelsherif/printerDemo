package com.example.shadi.printerdemo;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shadi.printerdemo.Nav_Fragments.HomeFragment;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginRegisterActivity extends AppCompatActivity implements View.OnClickListener {
    Button bt_Login, bt_register;

    TextView forget;
    ImageView img;
    EditText ed_Username, ed_Password;
    ProgressDialog progressDialog ;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_register);

        firebaseAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this);


        bt_register = findViewById(R.id.register);
        bt_Login = findViewById(R.id.login);
 //       bt_Learn = findViewById(R.id.learn);
        forget = findViewById(R.id.forgot);

        ed_Username = findViewById(R.id.user);
        ed_Password = findViewById(R.id.pass);

        forget = findViewById(R.id.forgot);

        bt_register.setOnClickListener(this);
        bt_Login.setOnClickListener(this);
    //    bt_Learn.setOnClickListener(this);
        forget.setOnClickListener(this);

        ed_Username.setOnClickListener(this);
        ed_Password.setOnClickListener(this);

        img = findViewById(R.id.imglogin);

        ed_Password.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus)
                    img.setImageResource(R.drawable.facepalm);
                else
                    img.setImageResource(R.drawable.user);
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.login:
//                loGin();
                startActivity(new Intent(LoginRegisterActivity.this, HomeFragment.class));
                break;


            case R.id.forgot:

                startActivity(new Intent(LoginRegisterActivity.this,ForgetPassword.class));
                break;


            case R.id.register:
                startActivity(new Intent(LoginRegisterActivity.this, Register.class));
                break;



               /*forgot.setTextColor(Color.rgb(192, 192, 192));

               startActivity(new Intent(LoginRegisterActivity.this, Forget_Password.class));*/


           /* case R.id.learn :
                Toast.makeText(this, "Learn more about 3dsss", Toast.LENGTH_SHORT).show();
                break;
*/
        }
    }


    private  void  loGin()
    {
        String mail = ed_Username.getText().toString().trim();
        String password = ed_Password.getText().toString().trim();


        if(TextUtils.isEmpty(mail))
        {
            // email empty
            Toast.makeText(this,"Enter Your E-mail ",Toast.LENGTH_LONG).show();
            return;
        }
        if(TextUtils.isEmpty(password))
        {
            // password empty
            Toast.makeText(this,"Enter Your Password ",Toast.LENGTH_LONG).show();
            return;
        }

        // if validation right
        progressDialog.setMessage("Sign in ...");
        progressDialog.show();

        final Task<AuthResult> authResultTask = firebaseAuth.signInWithEmailAndPassword(mail, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog.dismiss();
                        if(task.isSuccessful())
                        {
                            Toast.makeText(LoginRegisterActivity.this," Login Successfully ", Toast.LENGTH_LONG).show();
                            finish();
                            Intent profile = new Intent(getApplicationContext(),MainActivity.class);
                            startActivity(profile);

                        }
                        else
                        {
                            Toast.makeText(LoginRegisterActivity.this," Login Failed ",Toast.LENGTH_LONG).show();
                        }

                    }
                });


    }
}
