package com.demo.phoneotp;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText name, age, dob, email;
    Button save, showDetails;
    DatabaseReference database;
    List<Member> memberList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name = findViewById(R.id.name);
        age = findViewById(R.id.age);
        dob = findViewById(R.id.dob);
        email = findViewById(R.id.email);
        save = findViewById(R.id.save);
        showDetails = findViewById(R.id.showDetails);

        database = FirebaseDatabase.getInstance().getReference();

        //data saving
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!name.getText().toString().equalsIgnoreCase("")
                        && !age.getText().toString().equalsIgnoreCase("")
                        && !dob.getText().toString().equalsIgnoreCase("")
                        && !email.getText().toString().equalsIgnoreCase("")) {

                    Member member = new Member();
                    member.setUserId(database.child("Member").push().getKey());
                    member.setName(name.getText().toString());
                    member.setAge(age.getText().toString());
                    member.setDob(dob.getText().toString());
                    member.setEmail(email.getText().toString());
                    database.child("Member").child(member.getUserId()).setValue(member);
                    // finish();
                    Toast.makeText(MainActivity.this, "Saved Successfully!!", Toast.LENGTH_SHORT).show();

                    name.setText("");
                    age.setText("");
                    dob.setText("");
                    email.setText("");
                } else {
                    Toast.makeText(MainActivity.this, "Enter all the fields!!", Toast.LENGTH_SHORT).show();
                }


            }
        });

        //data fetching
        database.child("Member").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot noteDataSnapshot : dataSnapshot.getChildren()) {
                    Member note = noteDataSnapshot.getValue(Member.class);
                    memberList.add(note);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(MainActivity.this, "Some Error Occured in Fetching!!", Toast.LENGTH_SHORT).show();
            }

        });
        //data showing in UI
        showDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(MainActivity.this, "Data Has Been Fetched!!", Toast.LENGTH_SHORT).show();

                if (memberList.size() > 0) {
                    name.setText(memberList.get(0).getName());
                    age.setText(memberList.get(0).getAge());
                    dob.setText(memberList.get(0).getDob());
                    email.setText(memberList.get(0).getEmail());
                }


            }
        });


    }
}
