package com.egco428.practice

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_signup.*
import java.util.*

class SignupActivity : AppCompatActivity() , SensorEventListener {
    private var sensorManager: SensorManager? = null
    private  var lastUpdate: Long = 0
    var latitude: Double = 0.0
    var longitude: Double = 0.0
    val r = Random()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        lastUpdate = System.currentTimeMillis()

        ran.setOnClickListener {

            //val latitude = Random().nextDouble(90 + 1 + 90) - 90
            latitude = -90 + (90 + 90) * r.nextDouble()
            longitude = -180 + (180 + 180) * r.nextDouble()
            lat.setText(latitude.toString())
            longi.setText(longitude.toString())

        }

        add.setOnClickListener {
            if(signup_user.text.toString().isEmpty() || signup_pass.text.toString().isEmpty() || lat.text.isEmpty() || longi.text.isEmpty()){
                //
            }else{
                //add to database
                finish()
            }
        }
    }
    override fun onSensorChanged(event: SensorEvent?) {
        if (event!!.sensor.type == Sensor.TYPE_ACCELEROMETER){
            getAccelerometer(event)
        }
    }

    private fun getAccelerometer(event: SensorEvent) {
        val values = event.values
        // Movement
        val x = values[0]
        val y = values[1]
        val z = values[2]

        val accel = (x * x + y * y + z * z) / (SensorManager.GRAVITY_EARTH * SensorManager.GRAVITY_EARTH)
        val actualTime = System.currentTimeMillis()
        if (accel >= 2)
        //
        {
            if (actualTime - lastUpdate < 200) {
                return
            }
            lastUpdate = actualTime

            latitude = -90 + (90 + 90) * r.nextDouble()
            longitude = -180 + (180 + 180) * r.nextDouble()
            lat.setText(latitude.toString())
            longi.setText(longitude.toString())

            //Toast.makeText(this, "Device was shuffled", Toast.LENGTH_SHORT).show()

        } //else {
//            Toast.makeText(this, "Device was not shuffled", Toast.LENGTH_SHORT)
//                    .show()
//        }
    }

    override fun onAccuracyChanged(p0: Sensor?, p1: Int) {

    }

    override fun onResume() {
        super.onResume()
        sensorManager!!.registerListener(this, sensorManager!!.getDefaultSensor(Sensor.TYPE_ACCELEROMETER) , SensorManager.SENSOR_DELAY_NORMAL)
    }

    override fun onPause() {
        super.onPause()
        sensorManager!!.unregisterListener(this)
    }
}
