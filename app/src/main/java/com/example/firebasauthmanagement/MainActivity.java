package com.example.firebasauthmanagement;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ListView listView;
    ArrayList<Nam> namArrayList;
    NamAdapter namAdapter;
    DatabaseReference database ;
    FirebaseFirestore db;
    CardView btnAdd , btnDelete ;
    EditText tenthuongoi,tenkhoahoc,congdung;
    Nam nam ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = FirebaseFirestore.getInstance();
        listView = findViewById(R.id.listOrder);
        namArrayList = new ArrayList<>();
        Nam newNam = new Nam("Nam Doc","Nam Doc","khong co",1);
        namArrayList.add(newNam);
        namArrayList.add(new Nam("Nam Doc","Nam Doc","khong co",1));
        database = FirebaseDatabase.getInstance().getReference("Users");
        tenkhoahoc = findViewById(R.id.tenkhoahocinput);
        tenthuongoi = findViewById(R.id.tenthuongoiinput);
        congdung = findViewById(R.id.congdunginput);
        getListItems();
        /*database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Nam nam = dataSnapshot.getValue(Nam.class);
                    namArrayList.add((nam));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });*/
        namAdapter = new NamAdapter(this, R.layout.row_item, namArrayList);
        listView.setAdapter(namAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Nam listNam = namArrayList.get(i);

            }
        });

    }
    private void getListItems() {
        db.collection("nam").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot documentSnapshots) {
                if (documentSnapshots.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "List is empty", Toast.LENGTH_LONG).show();
                    return;
                } else {
                    List<Nam> types = documentSnapshots.toObjects(Nam.class);

                    namArrayList.addAll(types);
                }
            }
        });
    }
    public void handleAdd(View view){
        nam = new Nam(tenthuongoi.getText().toString(),tenkhoahoc.getText().toString(),congdung.getText().toString(),2);
        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                database.setValue(nam);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    public void handleDelete(View view){
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
        Query cayQuery = ref.child("name").orderByChild("tenkhoahoc").equalTo(tenkhoahoc.getText().toString());

        cayQuery.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot appleSnapshot: dataSnapshot.getChildren()) {
                    appleSnapshot.getRef().removeValue();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }
}