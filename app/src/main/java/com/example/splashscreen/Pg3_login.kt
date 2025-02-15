package com.example.splashscreen

import android.annotation.SuppressLint
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatEditText
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.getSystemService
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class Pg3_login : AppCompatActivity() {
    lateinit var database:DatabaseReference
    val channel_Id="Channel_id"
    val channel_Name="Channel_Name"
    val notification_Id=0
    companion object{
        const val KEY="com.example.splashscreen.Pg3_login.KEY"
        var KEY1=false
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_pg3_login)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val back1=findViewById<ImageView>(R.id.back)
        back1.setOnClickListener{
            intent= Intent(this,Pg2_Register::class.java)
            startActivity(intent)
        }
        val lgnbtn=findViewById<AppCompatButton>(R.id.login1)

        val pwlgn=findViewById<AppCompatEditText>(R.id.passlgn)
        lgnbtn.setOnClickListener {
            val userlgn=findViewById<AppCompatEditText>(R.id.namelgn)
            val userid=userlgn.text.toString()
            val pw=pwlgn.text.toString()

        val reg=findViewById<TextView>(R.id.regis)
        reg.setOnClickListener {
            val intentreg=Intent(this,Register_Pg4::class.java)
            startActivity(intentreg)
        }

        Notify()
        val builder=NotificationCompat.Builder(this,channel_Id)
            .setContentTitle("Login Successfully")
            .setContentText("Ready to Book your Ride")
            .setSmallIcon(R.drawable.person)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .build()
        val manager=NotificationManagerCompat.from(this)
            if(userid.isNotEmpty() && pw.isNotEmpty()){
                readata(userid,pw,manager,builder)
                pwlgn.text?.clear()
                userlgn.text?.clear()
            }else{
                Toast.makeText(this,"Add your details",Toast.LENGTH_SHORT).show()
            }
        }
        }

    @SuppressLint("MissingPermission")
    private fun readata(userid:String, pw:String, manager: NotificationManagerCompat, builder:Notification ){
        database=FirebaseDatabase.getInstance().getReference("Users")
        database.child(userid).get().addOnSuccessListener {
            if(it.exists()){
                val password=it.child("pass").value
                if(password.toString()==pw){
                    val intentwelcome=Intent(this,Pg5::class.java)
                    intentwelcome.putExtra(KEY,userid)
                    try {
                    manager.notify(notification_Id,builder)
                    }catch (e:Exception){
                        Toast.makeText(this,e.toString(),Toast.LENGTH_SHORT).show()
                        Log.d("Error", e.toString())
                    }
                    startActivity(intentwelcome)
                }else{
                    Toast.makeText(this,"Incorrect password",Toast.LENGTH_SHORT).show()
                }
            }else{
                Toast.makeText(this,"Please Register First",Toast.LENGTH_SHORT).show()
            }
        }.addOnFailureListener{
            Toast.makeText(this,"Failed! Try Again",Toast.LENGTH_SHORT).show()
        }
//        finishAffinity()
    }
    fun Notify(){
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
            val channel=NotificationChannel(channel_Id,channel_Name,NotificationManager.IMPORTANCE_DEFAULT).apply {
                description="This is my notification Channel"
            }
            val manager=getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            manager.createNotificationChannel(channel)
        }
    }
}