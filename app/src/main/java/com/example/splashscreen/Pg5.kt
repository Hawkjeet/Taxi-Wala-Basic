package com.example.splashscreen

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class Pg5 : AppCompatActivity() {
    val channel_id="Channel_Id"
    val channel_name="Channel_Name"
    val notification_id=0
    lateinit var rv:RecyclerView
    lateinit var profile:ArrayList<profiles> // Profiles is a data class which takes name,img and number of rides
    @SuppressLint("MissingPermission")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_pg5)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val text=findViewById<TextView>(R.id.welcometext)
        val te=intent.getStringExtra(Pg3_login.KEY).toString()
        text.text="Hi $te"
        rv=findViewById<RecyclerView>(R.id.recycleView)
        var names= arrayOf<String>("Jeet","Harsh","Pyus","Gian","Devansh","Jeet","Harsh","Pyus","Gian","Devansh")
        var nrides= arrayOf<Int>(100,200,300,400,500,100,200,300,400,500)
        var images= arrayOf(
           R.drawable.person,
           R.drawable.person,
           R.drawable.person,
           R.drawable.person,
           R.drawable.person,
           R.drawable.person,
           R.drawable.person,
           R.drawable.person,
           R.drawable.person,
           R.drawable.person
            )
        profile= arrayListOf<profiles>()
        for(i in names.indices){
            var toadd=profiles(names[i],nrides[i],images[i])
            profile.add(toadd)
        }
        rv.layoutManager=LinearLayoutManager(this)
        var myadapter=MyAdapter(this,profile) // Now we create an adapter for recycler view
        rv.adapter=myadapter
        myadapter.setItem(object:MyAdapter.ItemClickListener{
            override fun onItemClick(position: Int) {
                val intent= Intent(this@Pg5,eachProfile::class.java)
                intent.putExtra("intentName",profile[position].name)
                intent.putExtra("intentImage",profile[position].img)
                intent.putExtra("intentRides",profile[position].rides.toString())
                startActivity(intent)
            }

        })

        bookRide()
        val btnBookRide=findViewById<AppCompatButton>(R.id.bookRide)
        val builder=NotificationCompat.Builder(this,channel_id)
            .setContentTitle("Your Ride is Booked")
            .setContentText("Your Ride is few minutes away from your doorstep")
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setSmallIcon(R.drawable.person)
            .build()

        val notificationManager=NotificationManagerCompat.from(this)
        btnBookRide.setOnClickListener {
            notificationManager.notify(notification_id,builder)
            val intent=Intent(this,Pg6_BookRide::class.java)
            startActivity(intent)
        }
        val navComp=findViewById<AppCompatButton>(R.id.toNavComp)
        navComp.setOnClickListener {
            val intent=Intent(this,Nav_Components::class.java)
            startActivity(intent)
        }

    }
    fun bookRide(){
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
            val channel=NotificationChannel(channel_id,channel_name,NotificationManager.IMPORTANCE_DEFAULT).apply {
                description="This is a channel"
            }
            val manager=getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            manager.createNotificationChannel(channel)
        }

    }
}