package com.example.diabeticmealtracker;

import androidx.annotation.NonNull;
<<<<<<< HEAD
=======
import androidx.annotation.Nullable;
>>>>>>> 9331affd327ff74cb2398e6a45e59f3cb8617977
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.renderscript.Sampler;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.DataSnapshot;

public class ProfilePage extends AppCompatActivity {

    private double weight; // kg
    private double height; // cm
    private int age; // yrs
    private String sex; // male or female

    Profile profile;
    DatabaseReference databaseProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_page);
<<<<<<< HEAD

        FirebaseAuth mAuth = FirebaseAuth.getInstance(); //Grabs current instance of database
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        FirebaseUser user = mAuth.getCurrentUser(); //Grabs current user

        DocumentReference docRef = db.collection("users").document(user.getUid().toString()).collection("userData").document("profile");
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() { //Does the .get() command with a custom onComplete
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult(); //Grab snapshot of requirements

                    TextView age = (TextView) findViewById(R.id.ageTextView);
                    TextView height = (TextView) findViewById(R.id.heightTextView);
                    TextView sex = (TextView) findViewById(R.id.sexTextView);
                    TextView weight = (TextView) findViewById(R.id.weightTextView);
                    age.setText(document.getString("Age"));
                    height.setText(document.getString("Height"));
                    sex.setText(document.getString("Sex"));
                    weight.setText(document.getString("Weight"));
                }
            }
        });
    }

    @Override
    public void onResume(){
        super.onResume();
        FirebaseAuth mAuth = FirebaseAuth.getInstance(); //Grabs current instance of database
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        FirebaseUser user = mAuth.getCurrentUser(); //Grabs current user
        DocumentReference docRef = db.collection("users").document(user.getUid().toString()).collection("userData").document("profile");
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() { //Does the .get() command with a custom onComplete
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult(); //Grab snapshot of requirements

                    TextView age = (TextView) findViewById(R.id.ageTextView);
                    TextView height = (TextView) findViewById(R.id.heightTextView);
                    TextView sex = (TextView) findViewById(R.id.sexTextView);
                    TextView weight = (TextView) findViewById(R.id.weightTextView);
                    age.setText(document.getString("Age"));
                    height.setText(document.getString("Height"));
                    sex.setText(document.getString("Sex"));
                    weight.setText(document.getString("Weight"));
                }
            }
        });
=======
        profile = new Profile();
        databaseProfile = FirebaseDatabase.getInstance().getReference("Profile");
>>>>>>> 9331affd327ff74cb2398e6a45e59f3cb8617977
    }

    public void backMyProfileClick (View view){
        Intent intent = new Intent (getApplicationContext(), MainScreenPage.class);
        startActivity(intent);
    }
    public void changeMyProfile (View view){
        Intent intent = new Intent (getApplicationContext(), UpdateProfilePage.class);
        startActivity(intent);
    }



}