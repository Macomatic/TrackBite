package com.example.diabeticmealtracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserInfo;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;

import java.util.HashMap;
import java.util.Map;

public class UpdateProfilePage extends AppCompatActivity {

    private FirebaseAuth mAuth;
    Spinner spinner;
    ArrayAdapter<CharSequence> adapter;
    Map<String, Object> userInfo = new HashMap<>(); //Creates a new map, contains data that will be placed in the user's extraData file


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_profile_page);

        spinner = (Spinner) findViewById(R.id.sex);
        adapter = ArrayAdapter.createFromResource(this,R.array.sex, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Object sexSubmit = parent.getItemAtPosition(position);
                userInfo.put("Sex",String.valueOf(sexSubmit));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public void finishUpdateMyProfile (View view){
        finish();
    }

    public void onUpdate(View view){
        FirebaseAuth mAuth = FirebaseAuth.getInstance(); //Grabs current instance of database
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        FirebaseUser user = mAuth.getCurrentUser(); //Grabs current user

        EditText age = (EditText) findViewById(R.id.editTextNumberSigned);
        EditText height = (EditText) findViewById(R.id.editTextNumberSigned2);
        EditText weight = (EditText) findViewById(R.id.editTextNumberSigned4);
        //RadioButton male = (RadioButton) findViewById(R.id.radioButtonMale);
        //RadioButton female = (RadioButton) findViewById(R.id.radioButtonFemale);


        userInfo.put("Age", age.getText().toString());
        userInfo.put("Height", height.getText().toString());
        userInfo.put("Weight", weight.getText().toString());
        //userInfo.put((String) sexSubmit )

//        DocumentReference docRef = db.collection("users").document(user.getUid().toString()).collection("userData").document("profile");
//        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() { //Does the .get() command with a custom onComplete
//            @Override
//            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
//                if (task.isSuccessful()) {
//                    DocumentSnapshot document = task.getResult(); //Grab snapshot of requirements
//
//                    String name = document.getString("name");
//                    String email = document.getString("email");
//                    userInfo.put("name",name);
//                    userInfo.put("email",email);
//                }
//            }
//        });
        db.collection("users").document(user.getUid().toString()).collection("userData").document("profile").set(userInfo,SetOptions.merge());
        finish();
    }
}