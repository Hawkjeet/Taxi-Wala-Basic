package com.example.splashscreen

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.imageview.ShapeableImageView

class eachProfile : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_each_profile)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        var intentname=intent.getStringExtra("intentName")
        var intentRides=intent.getStringExtra("intentRides")
        var intentImage=intent.getIntExtra("intentImage",R.drawable.person)
        var eachName=findViewById<TextView>(R.id.eachProfileName)
        var eachRide=findViewById<TextView>(R.id.eachProfileRides)
        var eachImage=findViewById<ShapeableImageView>(R.id.eachProfileImage)
        eachName.text=intentname
        eachImage.setImageResource(intentImage)
        eachRide.text=intentRides.toString()
    }
}