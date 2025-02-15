package com.example.splashscreen

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class Pg6_BookRide : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_pg6_book_ride)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        replaceWithFragment(Bike_Fragment())
        val btnNavBar=findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        btnNavBar.setOnItemSelectedListener {
            when(it.itemId){
                R.id.BikeNavBar-> replaceWithFragment(Bike_Fragment())
                R.id.CarNavBar-> replaceWithFragment(Car_Fragment())
                R.id.RickNavBar-> replaceWithFragment(Rick_Fragment())
                else->{

                }
            }
            true
        }
    }

    private fun replaceWithFragment(frag: Fragment) {
        val manager=supportFragmentManager
        val fragtransaction=manager.beginTransaction()
        fragtransaction.replace(R.id.layout,frag)
        fragtransaction.commit()
    }
}