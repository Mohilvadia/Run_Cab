package com.runcab

import com.runcab.webapi.WebAPIManager.Companion.instance
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.runcab.R
import android.content.Intent
import android.view.View
import android.widget.Toast
import com.runcab.adapter.DisplayAllAdapter
import com.runcab.databinding.ActivityHomeBinding
import com.runcab.model.DisplayAlResponse
import com.runcab.model.DriverListResponse
import com.runcab.model.Name
import com.runcab.webapi.RemoteCallback
import com.runcab.webapi.WebAPIManager

class HomeActivity : AppCompatActivity() {
    var homeBinding: ActivityHomeBinding? = null
    var displayAllAdapter : DisplayAllAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        homeBinding = DataBindingUtil.setContentView(this, R.layout.activity_home)
        initView()
    }

    private fun initView() {
        homeBinding!!.addNew.setOnClickListener { v: View? ->
            startActivity(
                Intent(
                    this,
                    AddNewActivity::class.java
                )
            )
        }
        displayAllData()
    }



    private fun displayAllData() {
        instance.displayAllData().enqueue(object : RemoteCallback<DisplayAlResponse>(){
            override fun onSuccess(response: DisplayAlResponse?) {
                if (!response!!.error){
                    displayAllAdapter  = DisplayAllAdapter(response.data)
                    homeBinding!!.recAllData.adapter = displayAllAdapter
                }
            }

            override fun onUnauthorized(throwable: Throwable) {
                Toast.makeText(this@HomeActivity,throwable.message,Toast.LENGTH_LONG).show()
            }

            override fun onFailed(throwable: Throwable) {
                Toast.makeText(this@HomeActivity,throwable.message,Toast.LENGTH_LONG).show()
            }

            override fun onInternetFailed() {
                Toast.makeText(this@HomeActivity,"No internet!",Toast.LENGTH_LONG).show()
            }

            override fun onEmptyResponse(message: String) {
                Toast.makeText(this@HomeActivity,message,Toast.LENGTH_LONG).show()
            }
        })
    }

    override fun onResume() {
        super.onResume()
        displayAllData()
    }
}