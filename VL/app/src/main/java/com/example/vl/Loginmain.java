package com.example.vl;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Loginmain extends AppCompatActivity {

    EditText username, password, repassword;
    Button signup, signin;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginmain);

        username=findViewById(R.id.username);
        password=findViewById(R.id.password);
        repassword = findViewById(R.id.repassword);
        signup=findViewById(R.id.signup);
        signin=findViewById(R.id.signin);
        DB = new DBHelper(this);

       signup.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               String user = username.getText().toString();
               String pass = password.getText().toString();
               String repass = repassword.getText().toString();

               if(TextUtils.isEmpty(user) || TextUtils.isEmpty(pass) || TextUtils.isEmpty(repass))
                   Toast.makeText(Loginmain.this, "All fields Required", Toast.LENGTH_SHORT).show();
               else{
                   if(pass.equals(repass)) {
                       Boolean checkuser = DB.checkusername(user);
                       if (checkuser == false) {
                           Boolean insert = DB.insertData(user, pass);
                           if (insert == true) {
                               Toast.makeText(Loginmain.this, "Registersed Successfully", Toast.LENGTH_SHORT).show();
                               Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                               startActivity(intent);

                           } else {
                               Toast.makeText(Loginmain.this, "Registration Failed", Toast.LENGTH_SHORT).show();
                           }
                       } else {
                           Toast.makeText(Loginmain.this, "User already Exists", Toast.LENGTH_SHORT).show();
                       }
                   }else{
                       Toast.makeText(Loginmain.this,"Passwords are not matching",Toast.LENGTH_SHORT).show();

                   }
               }
           }
       });

       signin.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
               startActivity(intent);


           }
       });




    }
}