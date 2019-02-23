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

    // custom ListView
    int [] img = {R.drawable.a, R.drawable.b, R.drawable.c, R.drawable.d };

    String [] names = {"Letter a", "Letter b", "Letter c", "Letter d"};
    String [] descrption = {"Description a", "Description b", "Description c", "Description d"};

    @Override


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //ActionButton
       /* fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               fab.setRotation(45);
               startActivity(new Intent(MainActivity.this, PostActivity.class));
            }
        });*/

        //custom listView
        ListView lv = (ListView)findViewById(R.id.listView);
        CustomAdapter customAdapter = new CustomAdapter();

        lv.setAdapter(customAdapter);

      //  Toast.makeText(MainActivity.this, "1", Toast.LENGTH_SHORT).show();

        mDatabase= FirebaseDatabase.getInstance().getReference().child("order");
       mBlogList = (RecyclerView)findViewById(R.id.blog_list );
       mBlogList.setHasFixedSize(true);
       mBlogList.setLayoutManager(new LinearLayoutManager(this));
        mBlogList.setLayoutManager(new LinearLayoutManager(this));



    //    Toast.makeText(MainActivity.this, "2", Toast.LENGTH_SHORT).show();
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


       // Toast.makeText(MainActivity.this, "3", Toast.LENGTH_SHORT).show();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


    }


    class CustomAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return img.length ;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View view, ViewGroup parent) {

            view = getLayoutInflater().inflate(R.layout.custom_list, null);
            ImageView imageView = view.findViewById(R.id.imageView1);
            TextView textView = view.findViewById(R.id.textView_name);
            TextView textView1 = view.findViewById(R.id.textView_description);

            imageView.setImageResource(img[position]);
            textView.setText(names[position]);
            textView1.setText(descrption[position]);

            return view;
        }
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
        getMenuInflater().inflate(R.menu.main, menu);
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
        if(item.getItemId()== R.id.action_add ) {

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


        } else if (id == R.id.nav_upload) {
            //upload

        } else if (id == R.id.nav_designs) {


        } else if (id == R.id.nav_about) {


        }else if (id == R.id.nav_account) {


        }else if (id == R.id.nav_contact) {


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

        FirebaseRecyclerAdapter<Blog,BlogViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Blog, BlogViewHolder>(

                Blog.class,
                R.layout.blog_row,
                BlogViewHolder.class,
                mDatabase

        ) {
            @Override
            protected void populateViewHolder(BlogViewHolder viewHolder, Blog model, int position) {

                viewHolder.setTitle(model.gettime());
                viewHolder.setDesc(model.getlayer());
                viewHolder.setImage(getApplicationContext(),model.getImage());
            }
        };
        mBlogList.setAdapter(firebaseRecyclerAdapter);
        auth.addAuthStateListener(authListener);

    }
    public static class BlogViewHolder extends RecyclerView.ViewHolder {

        View mView;

        public BlogViewHolder(View itemView) {
            super(itemView);
            mView=itemView;
        }

        public void setTitle(String title)
        {
            TextView post_title=(TextView) mView.findViewById(R.id.Post_title);
            post_title.setText(title);
        }

        public void setDesc(String desc)
        {
            TextView post_desc=(TextView) mView.findViewById(R.id.Post_desc);
            post_desc.setText(desc);
        }

        public  void setImage(Context ctx, String Image)
        {

            ImageView imageView = (ImageView) mView.findViewById(R.id.Post_image);

            Picasso.with(ctx).load(Image).into(imageView);

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
