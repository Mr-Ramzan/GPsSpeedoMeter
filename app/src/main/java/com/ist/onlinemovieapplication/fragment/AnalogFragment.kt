package com.ist.onlinemovieapplication.fragment

import android.content.Intent
import android.location.Location
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.ist.onlinemovieapplication.activity.MapActivity
import com.ist.onlinemovieapplication.databinding.FragmentAnalogBinding
import com.ist.onlinemovieapplication.interfaces.locationCallback

class AnalogFragment : Fragment(),locationCallback {

    var binding: FragmentAnalogBinding? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAnalogBinding.inflate(inflater, container, false)
        binding!!.mapBtn.setOnClickListener { view: View? ->
            startActivity(
                Intent(
                    activity, MapActivity::class.java
                )
            )
        }
        return binding!!.root
    }

    override fun locationCallback(location: Location) {
        Log.e("Analog","Analog<============>${location.latitude}/${location.longitude}")
        val speed = (location!!.speed * 3.6).toFloat()
        binding!!.speedometer.setSpeed(speed.toInt(), 700) {}
    }
}