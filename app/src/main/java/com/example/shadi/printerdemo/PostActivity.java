package com.example.shadi.printerdemo;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;

public class PostActivity extends AppCompatActivity {

    private ImageButton mSelectImage;
    private EditText mPostTitle, mPostDesc;
    private static int GALLARY_RQUEST =1 ;
    private Button mSubmitBtn;
    private Uri imageuri;
    String value="0";
    int count =0;
    private StorageReference mStorage;
    private ProgressDialog mProgress ;
    private DatabaseReference mDatabase;
    private DatabaseReference Database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

      //  Toast.makeText(PostActivity.this, "2", Toast.LENGTH_SHORT).show();
        mProgress=new ProgressDialog(this);
        mDatabase= FirebaseDatabase.getInstance().getReference().child("order");
        Database= FirebaseDatabase.getInstance().getReference().child("count");

        mStorage= FirebaseStorage.getInstance().getReference();
        mPostTitle=(EditText)findViewById(R.id.edt);
        mPostDesc=(EditText)findViewById(R.id.desEdt);
        mSubmitBtn =(Button)findViewById(R.id.btn);
        mSelectImage=(ImageButton)findViewById(R.id.imageButton);
        mSelectImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent GallaryIntent = new Intent(Intent.ACTION_GET_CONTENT);
                GallaryIntent.setType("image/*");
                startActivityForResult(GallaryIntent,GALLARY_RQUEST);

            }
        });

        mSubmitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Database.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        // This method is called once with the initial value and again
                        // whenever data at this location is updated.
                        value = dataSnapshot.getValue(String.class);

                    }
                    @Override
                    public void onCancelled(DatabaseError error) {
                    }
                });

                     startPosting();
            }
        });


    }

    private void startPosting() {
        mProgress.setMessage("Posting To Blog ...");
        mProgress.show();
        final String title_val = mPostTitle.getText().toString().trim();
        final String desc_val = mPostDesc.getText().toString().trim();

        if(!TextUtils.isEmpty(title_val) && !TextUtils.isEmpty(desc_val) && imageuri != null ) {

            StorageReference filePath = mStorage.child("Bloge Images").child(imageuri.getLastPathSegment());

            StorageTask<UploadTask.TaskSnapshot> taskSnapshotStorageTask = filePath.putFile(imageuri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override

                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                    //Uri downloadUrl = taskSnapshot.getDownloadUrl();
                    Task<Uri> downloadUrl = taskSnapshot.getMetadata().getReference().getDownloadUrl();
                    count = Integer.parseInt(value);

                    Toast.makeText(PostActivity.this, count-- + " ", Toast.LENGTH_SHORT).show();


                    Database.setValue(String.valueOf(count));
                    //     String id= LoginActivity.user.getUid();
                    String id = "asdasdadsasdad";

                    DatabaseReference newPost = mDatabase.child(String.valueOf(count) + " - " + id);
                    newPost.child("time").setValue(title_val);
                    newPost.child("layer").setValue(desc_val);
                    newPost.child("image").setValue(downloadUrl.toString());
                    mProgress.dismiss();
                    finish();

                }
            });
            filePath.putFile(imageuri).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception exception) {
                    // Handle unsuccessful uploads
                    Toast.makeText(PostActivity.this, "error", Toast.LENGTH_SHORT).show();
                    // ...
                    mProgress.dismiss();
                    finish();
                }
            });
        }

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if( requestCode==GALLARY_RQUEST&& resultCode==RESULT_OK )
        {
             imageuri = data.getData();
            mSelectImage.setImageURI(imageuri);

        }


    }
}
