package com.egco428.practice

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        signin.setOnClickListener {
            if(username.text.toString().isEmpty() || pass.text.toString().isEmpty()){
                //
            }else{
                val intent = Intent(this, Main2Activity::class.java)
                intent.putExtra("user",username.text.toString())
                intent.putExtra("pass",pass.text.toString())
                startActivity(intent)
            }

        }
        signup.setOnClickListener {
            val intent = Intent(this, SignupActivity::class.java)
            startActivity(intent)
        }
        cancel.setOnClickListener {
            username.setText("")
            pass.setText("")
        }
    }
}
