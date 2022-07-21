package com.ist.onlinemovieapplication.fragment

import android.location.Location
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.ist.onlinemovieapplication.databinding.FragmentDigitalBinding
import com.ist.onlinemovieapplication.interfaces.locationCallback
import kotlin.math.roundToInt

class DigitalFragment : Fragment(),locationCallback {

    private var maxSpeed = 0F
    private var speeds: ArrayList<Float> = ArrayList()
    private var minSpeed: Float = 0F



    // UI Widgets.
    private val mStartUpdatesButton: Button? = null
    private val mStopUpdatesButton: Button? = null
    ////////////////////////////////////////////////////////////////////////////////////////////////////////
    var digitalBinding: FragmentDigitalBinding? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        digitalBinding = FragmentDigitalBinding.inflate(inflater, container, false)
        return digitalBinding!!.root
    }



    override fun locationCallback(location: Location) {
        Log.e("Digital","Digital<============>${location.latitude}/${location.longitude}")
        try {
            if (location != null) {
                val speed = (location!!.speed * 3.6).toFloat()
                digitalBinding?.currentSpeed!!.setText("${speed.roundToInt()} km/h")
                speeds.add(speed)
                var avgSpeed = (speeds.sum() / (speeds.size - 1))
                digitalBinding!!.averageSpeed.setText("${avgSpeed.roundToInt()} km/h")
                digitalBinding!!.maxSpeed.setText("${speeds.maxOrNull()} km/h")
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }


}