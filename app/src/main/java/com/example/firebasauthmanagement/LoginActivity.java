package com.example.firebasauthmanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity {
    EditText email,pass;
    private FirebaseAuth auth;
    ArrayList<User> users= new ArrayList<User>();
    User newUser, userFinded = null;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        email = findViewById(R.id.email);
        pass = findViewById(R.id.pass);
        auth = FirebaseAuth.getInstance();
        sharedPreferences = getSharedPreferences("dataLogin", MODE_PRIVATE);
        email.setText(sharedPreferences.getString("taikhoan", ""));
        pass.setText(sharedPreferences.getString("matkhau", ""));
    }
    public void toRegister(View view) {
        Intent intent = new Intent();
        intent.setClass(this, RegisterActivity.class);
        startActivityForResult(intent, RegisterActivity.RESULT);
    }
    public void handleLogin(View view) {
        String email = this.email.getText().toString();
        String pass = this.pass.getText().toString();
        if(email.isEmpty() || pass.isEmpty()) {
            Toast.makeText(this, "Please fill all the fields", Toast.LENGTH_SHORT).show();
        } else {
            auth.signInWithEmailAndPassword(email, pass).addOnCompleteListener(this, task -> {
                if (task.isSuccessful()) {
                    Toast.makeText(this, "Login successfully", Toast.LENGTH_SHORT).show();
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("taikhoan", email);
                    editor.putString("matkhau", pass);
                    editor.commit();
                    startActivity(new Intent(this, MainActivity.class));
                } else {
                    Toast.makeText(this, "Error: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == RegisterActivity.RESULT && resultCode == RESULT_OK) {
            newUser = (User) data.getSerializableExtra("user");
            users.add(newUser);
        }
    }
}