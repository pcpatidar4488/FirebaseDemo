package com.example.abc.firebaseproject.activity;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.test.mock.MockApplication;
import android.widget.Toast;

import com.example.abc.firebaseproject.R;
import com.example.abc.firebaseproject.adapter.FireBaseDataListAdapter;
import com.example.abc.firebaseproject.utils.ParameterConstant;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Timer;

public class FirbaseDataListActivity extends AppCompatActivity {

    RecyclerView mRecyclerView;
    FireBaseDataListAdapter mAdapter;
    RecyclerView.LayoutManager layoutManager;
    ProgressDialog progressDialog;
    List<Map<String,String>> listMap = new ArrayList<>();;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firbase_data_list);
        mRecyclerView = findViewById(R.id.list_recyclerView);
        progressDialog = new ProgressDialog(getApplicationContext());
        progressDialog.show();
        getAllRecord();
    }


    void getAllRecord() {
//use  addValueEventListener for every hit

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child(ParameterConstant.ROOT_KEY).child(ParameterConstant.NOTICE);
        ref.addListenerForSingleValueEvent(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        collectPhoneNumbers((Map<String, Object>) dataSnapshot.getValue());
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        //handle databaseError
                    }
                });
    }

    private void collectPhoneNumbers(Map<String, Object> users) {

        System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAA    " + users);

        //iterate through each user, ignoring their UID
        if (users == null) {
            Toast.makeText(this, "No record found", Toast.LENGTH_SHORT).show();
            progressDialog.dismiss();
            finish();
        } else {
            for (Map.Entry<String, Object> entry : users.entrySet()) {
                //Get user map

                Map map = (Map) entry.getValue();
                Set<String> keyset = map.keySet();
                listMap.add(map);
               /* for (String key : keyset) {

                    if (key.equals("username")) {
                        Toast.makeText(this, ""+map.get(key).toString(), Toast.LENGTH_SHORT).show();

                    }
                    if (key.equals("password")) {

                    }
                }*/
            }
            progressDialog.dismiss();
            mRecyclerView.setHasFixedSize(true);
            layoutManager = new LinearLayoutManager(getApplicationContext());
            mRecyclerView.setLayoutManager(layoutManager);
            mAdapter = new FireBaseDataListAdapter(this,listMap);
            mRecyclerView.setAdapter(mAdapter);
        }
    }
}
