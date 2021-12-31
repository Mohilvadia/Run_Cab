package com.runcab

import androidx.appcompat.app.AppCompatActivity
import android.app.DatePickerDialog.OnDateSetListener
import android.app.TimePickerDialog.OnTimeSetListener
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.runcab.R
import android.widget.ArrayAdapter
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import android.view.View
import android.widget.Toast
import android.widget.DatePicker
import android.widget.TimePicker
import com.runcab.databinding.ActivityAddNewBinding
import com.runcab.model.DriverListResponse
import com.runcab.model.InsertResponse
import com.runcab.model.InsetReqest
import com.runcab.model.Name
import com.runcab.webapi.RemoteCallback
import com.runcab.webapi.WebAPIManager
import java.text.SimpleDateFormat
import java.util.*

class AddNewActivity : AppCompatActivity(), OnDateSetListener, OnTimeSetListener {
    val myCalendar = Calendar.getInstance()
    var binding: ActivityAddNewBinding? = null
    var driverList : List<Name> = arrayListOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_new)
        initView()
    }

    private fun initView() {
        getDriverData()
        val strings: MutableList<String> = ArrayList()
        strings.add("one Way")
        strings.add("Round Trip")
        val adapter = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, strings)
        binding!!.spSelectWay.adapter = adapter
        val stringCar: MutableList<String> = ArrayList()
        stringCar.add("Sedan")
        stringCar.add("SUV")
        stringCar.add("Hatchback")
        val adapterCar = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, stringCar)
        binding!!.spCarType.adapter = adapterCar
        binding!!.tvDate.setOnClickListener { v: View? ->
            DatePickerDialog(
                this,
                R.style.Theme_MaterialComponents_Dialog_Alert,
                this,
                Calendar.getInstance()[Calendar.YEAR],
                Calendar.getInstance()[Calendar.MONTH],
                Calendar.getInstance()[Calendar.DAY_OF_MONTH]
            ).show()
        }
        val stringPay: MutableList<String> = ArrayList()
        stringPay.add("Online")
        stringPay.add("Cash")
        stringPay.add("other")
        val adapterPay = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, stringPay)
        binding!!.spPayType.adapter = adapterPay
        binding!!.tvDate.setOnClickListener { v: View? ->
            DatePickerDialog(
                this,
                R.style.Theme_MaterialComponents_Dialog_Alert,
                this,
                Calendar.getInstance()[Calendar.YEAR],
                Calendar.getInstance()[Calendar.MONTH],
                Calendar.getInstance()[Calendar.DAY_OF_MONTH]
            ).show()
        }
        binding!!.tvTime.setOnClickListener { v: View? ->
            TimePickerDialog(
                this,
                R.style.Theme_MaterialComponents_Dialog_Alert,
                this,
                Calendar.getInstance()[Calendar.HOUR_OF_DAY],
                Calendar.getInstance()[Calendar.MINUTE],
                true
            ).show()
        }
        binding!!.btnSave.setOnClickListener { v: View? ->
            if (checkOkay()) {
                callApi()
            }
        }
    }

    private fun callApi() {
//        var insetReqest = InsetReqest()
        WebAPIManager.instance.insertData(binding!!.tvDate.text.toString(),binding!!.tvTime.text.toString(),
            binding!!.spSelectWay.selectedItem.toString(),binding!!.spCarType.selectedItem.toString(),
            binding!!.etSource.text.toString() + "-" + binding!!.etDestination.text.toString(),
            binding!!.spSelectWay.selectedItem.toString(),binding!!.etAmout.text.toString(),binding!!.etAmout.text.toString(),
            binding!!.etAdvance.text.toString(),binding!!.etAvg.text!!.toString(),binding!!.spPayType.selectedItem.toString(),
            binding!!.etCustomerName.text.toString(),binding!!.etTotalAmount.text.toString(),
            binding!!.etDriverName.text.toString(),binding!!.etDriverNumber.text.toString()).enqueue(object : RemoteCallback<InsertResponse>(){
            override fun onSuccess(response: InsertResponse?) {
                if (response!!.success){
                    Toast.makeText(this@AddNewActivity,response.msg,Toast.LENGTH_LONG).show()
                    onBackPressed()
                }
            }

            override fun onUnauthorized(throwable: Throwable) {
                Toast.makeText(this@AddNewActivity,throwable.localizedMessage,Toast.LENGTH_LONG).show()
            }

            override fun onFailed(throwable: Throwable) {
                Toast.makeText(this@AddNewActivity,throwable.localizedMessage,Toast.LENGTH_LONG).show()
            }

            override fun onInternetFailed() {
                Toast.makeText(this@AddNewActivity,"no internet!!",Toast.LENGTH_LONG).show()
            }

            override fun onEmptyResponse(message: String) {
                Toast.makeText(this@AddNewActivity,message,Toast.LENGTH_LONG).show()
            }
        })
    }

    private fun checkOkay(): Boolean {
        return if (binding!!.etDriverName.text.toString()
                .isEmpty() && binding!!.etTotalAmount.text.toString()
                .isEmpty() && binding!!.etDriverNumber.text.toString().isEmpty()
                && binding!!.etAmout.text.toString().isEmpty()
        ) {
            Toast.makeText(this, "please enter all data", Toast.LENGTH_SHORT).show()
            false
        } else {
            true
        }
    }

    private fun updateLabel() {
        val myFormat = "MM/dd/yy" //In which you need put here
        val sdf = SimpleDateFormat(myFormat, Locale.US)
        binding!!.tvDate.text = sdf.format(myCalendar.time)
    }

    override fun onDateSet(datePicker: DatePicker, i: Int, i1: Int, i2: Int) {
        myCalendar[Calendar.YEAR] = i
        myCalendar[Calendar.MONTH] = i1
        myCalendar[Calendar.DAY_OF_MONTH] = i2
        updateLabel()
    }

    override fun onTimeSet(timePicker: TimePicker, i: Int, i1: Int) {
        binding!!.tvTime.text = "$i:$i1"
    }

    private fun getDriverData() {
        WebAPIManager.instance.getDriverList().enqueue(object  : RemoteCallback<DriverListResponse>(){
            override fun onSuccess(response: DriverListResponse?) {
                if (response!!.name.isNotEmpty()){
                    driverList = (response.name)
                }else{

                }
            }

            override fun onUnauthorized(throwable: Throwable) {
                Toast.makeText(this@AddNewActivity,throwable.localizedMessage,Toast.LENGTH_LONG).show()
            }

            override fun onFailed(throwable: Throwable) {
                Toast.makeText(this@AddNewActivity,throwable.localizedMessage,Toast.LENGTH_LONG).show()
            }

            override fun onInternetFailed() {
                Toast.makeText(this@AddNewActivity,"no internet connection!!",Toast.LENGTH_LONG).show()
            }

            override fun onEmptyResponse(message: String) {
                Toast.makeText(this@AddNewActivity,message,Toast.LENGTH_LONG).show()
            }
        })
    }
}