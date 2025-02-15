package com.example.splashscreen

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Pg2_Register : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_pg2_register)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val loginbtn=findViewById<AppCompatButton>(R.id.login)
        loginbtn.setOnClickListener {
            intent= Intent(this,Pg3_login::class.java)
            startActivity(intent)
        }
        val regbtn=findViewById<AppCompatButton>(R.id.register)
        regbtn.setOnClickListener {
            intent= Intent(this,Register_Pg4::class.java)
            startActivity(intent)
        }
//        finishAffinity()
    }
}