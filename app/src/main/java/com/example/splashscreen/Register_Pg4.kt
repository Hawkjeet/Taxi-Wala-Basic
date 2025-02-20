package com.example.splashscreen

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatEditText
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class Register_Pg4 : AppCompatActivity() {
    lateinit var database: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_register_pg4)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val back=findViewById<ImageView>(R.id.back)
        back.setOnClickListener{
            val intent= Intent(this,Pg2_Register::class.java)
            startActivity(intent)
        }
        val reg=findViewById<AppCompatButton>(R.id.register)
        val User=findViewById<AppCompatEditText>(R.id.userReg)
        val Mail=findViewById<AppCompatEditText>(R.id.mailReg)
        val Pass=findViewById<AppCompatEditText>(R.id.passReg)
        reg.setOnClickListener {
            val userid=User.text.toString()
            val email=Mail.text.toString()
            val password=Pass.text.toString()
            database=FirebaseDatabase.getInstance().getReference("Users")
            // user is a data class which takes userid,email,pw
            database.child(userid).setValue(user(userid,email,password)).addOnSuccessListener {
                Toast.makeText(applicationContext,"Registered",Toast.LENGTH_SHORT).show()
                val intentwel=Intent(this,Pg3_login::class.java)
                startActivity(intentwel)
            }.addOnFailureListener {
                Toast.makeText(this,"Failed!Try Again",Toast.LENGTH_SHORT).show()
            }
        }
//        finishAffinity()
    }
}