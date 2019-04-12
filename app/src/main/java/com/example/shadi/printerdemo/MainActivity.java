package com.example.shadi.printerdemo;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shadi.printerdemo.Nav_Fragments.AboutFragment;
import com.example.shadi.printerdemo.Nav_Fragments.AccountFragment;
import com.example.shadi.printerdemo.Nav_Fragments.ContactFragment;
import com.example.shadi.printerdemo.Nav_Fragments.DesignsFragment;
import com.example.shadi.printerdemo.Nav_Fragments.HomeFragment;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private FirebaseAuth.AuthStateListener authListener;
    private FirebaseAuth auth;


    private RecyclerView mBlogList ;
    private DatabaseReference mDatabase;

    //Action Button
    FloatingActionButton fab;

    @Override


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    fab= (FloatingActionButton)findViewById(R.id.fab);
    fab.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            startActivity(new Intent(MainActivity.this,PostActivity.class));

        }
    });
      //  Toast.makeText(Proofile.this, "1", Toast.LENGTH_SHORT).show();

        mDatabase = FirebaseDatabase.getInstance().getReference().child("order");
        mBlogList = (RecyclerView) findViewById(R.id.blog_list);
        mBlogList.setHasFixedSize(true);
        mBlogList.setLayoutManager(new LinearLayoutManager(this));
        mBlogList.setLayoutManager(new LinearLayoutManager(this));


    //    Toast.makeText(Proofile.this, "2", Toast.LENGTH_SHORT).show();
        auth = FirebaseAuth.getInstance();

        authListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user == null) {
                    // user auth state is changed - user is null
                    // launch login activity
                    //   startActivity(new Intent(MainActivity.this, LoginActivity.class));
                    //  finish();
                }


            }
        };


       // Toast.makeText(Proofile.this, "3", Toast.LENGTH_SHORT).show();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        auth = FirebaseAuth.getInstance();

        authListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user == null) {
                    //  user auth state is changed - user is null
                      // launch login activity
                         startActivity(new Intent(MainActivity.this, SplashScreen.class));
                        finish();
                }


            }
        };

    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_main_drawer, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement

        if (id == R.id.action_setting) {
            return true;
        }
        if (item.getItemId() == R.id.action_add) {



            startActivity(new Intent(MainActivity.this,PostActivity.class));

        }

        return super.onOptionsItemSelected(item);
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();



        if (id == R.id.nav_upload) {

            Intent intent = new Intent(this,Uploadfiletoserver.class);
            startActivity(intent);

            // Handle the camera action
        } else if (id == R.id.nav_home) {

          // getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();


        } else if (id == R.id.nav_designs) {

             getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new DesignsFragment()).commit();

        } else if (id == R.id.nav_about) {

              getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new AboutFragment()).commit();

        }else if (id == R.id.nav_account) {

               getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new AccountFragment()).commit();

        }else if (id == R.id.nav_contact) {

              getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ContactFragment()).commit();


        } else if (id == R.id.nav_logout) {

            signOut();
            finish();
        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    public void signOut() {
        auth.signOut();
    }

    protected void onStart() {
        super.onStart();

        FirebaseRecyclerAdapter<Blog, BlogViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Blog, BlogViewHolder>(

                Blog.class,
                R.layout.blog_row,
                BlogViewHolder.class,
                mDatabase

        ) {
            @Override
            protected void populateViewHolder(BlogViewHolder viewHolder, Blog model, int position) {

                viewHolder.setTitle(model.gettime());
                viewHolder.setname(model.getname());
                viewHolder.setFilament(model.getFilament());
                viewHolder.setImage(getApplicationContext(), model.getImage());
            }
        };
        mBlogList.setAdapter(firebaseRecyclerAdapter);
        auth.addAuthStateListener(authListener);

    }

    public static class BlogViewHolder extends RecyclerView.ViewHolder {

        View mView;

        public BlogViewHolder(View itemView) {
            super(itemView);
            mView = itemView;
        }

        public void setTitle(String title) {
            TextView post_title = (TextView) mView.findViewById(R.id.Post_title);
            post_title.setText("Time : "+title);
        }

        public void setname(String desc) {
            TextView post_desc = (TextView) mView.findViewById(R.id.design_name);
            post_desc.setText(desc);
        }

        public void setImage(Context ctx, String Image) {

            ImageView imageView = (ImageView) mView.findViewById(R.id.Post_image);

            Picasso.with(ctx).load(Image).into(imageView);

        }

        public void setFilament(String Image) {


            TextView post_desc = (TextView) mView.findViewById(R.id.Post_desc);
             post_desc.setText("Filament : "+Image);

        }
    }


    @Override
    public void onStop() {
        super.onStop();
        if (authListener != null) {
            auth.removeAuthStateListener(authListener);
        }
    }
}
