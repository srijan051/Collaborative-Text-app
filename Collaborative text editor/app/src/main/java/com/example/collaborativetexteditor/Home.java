package com.example.collaborativetexteditor;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Home extends AppCompatActivity {

    public static final String EXTRA_MESSAGE ="com.example.android.twoactivities.extra.MESSAGE";

    private FirebaseDatabase mFirebaseDatabase;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private DatabaseReference myRef;
    private ListView mListView;

    public ArrayList<FilesAS> filesASlist;
    FilesAS filesAS;

    private Button btaddTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        myRef = FirebaseDatabase.getInstance().getReference("Files");
        mListView =findViewById(R.id.lv_files);
        filesASlist =new ArrayList<>();

        btaddTitle = findViewById(R.id.button_add);
        btaddTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openadd();
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                filesASlist.clear();
                for (final DataSnapshot ds: dataSnapshot.getChildren()){

                    filesAS = ds.getValue(FilesAS.class);
                    filesASlist.add(filesAS);
                    mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                            FilesAS item = filesASlist.get(position);
                            Intent intent = new Intent(Home.this, CollabAddText.class);
                            Bundle bundle = new Bundle();
                            bundle.putSerializable("OBJECT", item);
                            intent.putExtra(EXTRA_MESSAGE, bundle);
                            startActivity(intent);
                        }
                    });

                }
                FileList adapter = new FileList(Home.this, filesASlist);
                mListView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void openadd() {
        Intent intent = new Intent(this, CollabAddTitle.class);
        startActivity(intent);
        Log.d("open","openadd clicked5");

    }

}
