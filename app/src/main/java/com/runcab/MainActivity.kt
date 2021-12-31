package com.runcab

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.content.Intent




class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Handler().postDelayed({
            val i = Intent(this, HomeActivity::class.java)
            startActivity(i)

            // close this activity
            finish()
        }, (5 * 1000).toLong()) // wait for 5 seconds

    }
}