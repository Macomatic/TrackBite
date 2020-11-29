package com.example.diabeticmealtracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;


public class MainActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void registerButtonClick (View view){
        Intent intent = new Intent (getApplicationContext(), RegisterPage.class);
        startActivity(intent);
    }

    public void loginButtonClick (View view){
        mAuth = FirebaseAuth.getInstance();
        EditText email = (EditText) findViewById(R.id.textEmail);
        EditText password = (EditText) findViewById(R.id.textPassword);

//        if (email.getText().toString() == "" || password.getText().toString()==""){
//            Toast.makeText(getApplicationContext(), "Please Fill In All Fields", Toast.LENGTH_SHORT).show();
//        }

        if (!email.getText().toString().equals("") && !password.getText().toString().equals("")) {
            mAuth.signInWithEmailAndPassword(email.getText().toString(), password.getText().toString())
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                FirebaseUser currentUser = mAuth.getCurrentUser();
                                FirebaseFirestore db = FirebaseFirestore.getInstance();
                                DocumentReference docRef = db.collection("users").document(currentUser.getUid().toString()).collection("userData").document("profile"); //Grabs extraData doc for current user
                                docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() { //Does the .get() command with a custom onComplete
                                    @Override
                                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                        if (task.isSuccessful()) {
                                            DocumentSnapshot document = task.getResult(); //Grab snapshot of extraData
                                            String flag = document.getString("setup");
                                            Intent intent;
                                            if(flag.equals("false")){
                                                intent = new Intent(getApplicationContext(), MainScreenPage.class);
                                                startActivity(intent);
                                            }
                                            intent = new Intent(getApplicationContext(), UpdateProfilePage.class);
                                            startActivity(intent);
                                        }
                                    }
                                });

                            } else {
                                // If sign in fails, display a message to the user.
                                Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();
                                // ...
                            }

                            // ...
                        }
                    });
        }

        else {
            Toast.makeText(getApplicationContext(), "Please enter all valid fields", Toast.LENGTH_SHORT).show();
        }


    }
}