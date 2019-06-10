package com.example.iteradmin.kotlin_devicelocation10june

import android.Manifest
import android.content.pm.PackageManager
import android.location.Location
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val txt = findViewById<TextView>(R.id.text)
        val bt = findViewById<Button>(R.id.button)

        val f: FusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)

        bt.setOnClickListener {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                f.lastLocation.addOnSuccessListener { location: Location? ->
                    if(location==null) {
                        Toast.makeText(this, "location not found", Toast.LENGTH_LONG).show()
                    }else{
                        val s:String="LONGITUDE"+location.longitude+"\n latitude :"+ location.latitude
                        txt.text=s
                    }
                    }

            } else {
                ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION),100)
            }

        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        if(requestCode==100){
            if(grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Toast.makeText(this,"permission has been granted",Toast.LENGTH_LONG).show()
            }
            else{
                Toast.makeText(this,"permission denied",Toast.LENGTH_LONG).show()
            }
        }
    }
}

