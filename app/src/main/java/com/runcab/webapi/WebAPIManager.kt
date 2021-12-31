package com.runcab.webapi

import com.runcab.model.*
import retrofit2.Call


class WebAPIManager private constructor() {

    private val mApiService: WebAPIService = WebAPIServiceFactory.newInstance().makeServiceFactory()

    companion object {

        private var INSTANCE: WebAPIManager? = null

        val instance: WebAPIManager
            get() {
                if (INSTANCE == null) {
                    INSTANCE = WebAPIManager()
                }
                return INSTANCE as WebAPIManager
            }
    }

    fun displayAllData():Call<DisplayAlResponse>{
        return mApiService.allData()
    }

    fun insertData(r_date : String,r_time : String,select_one : String,car_type : String,only_drop : String,raund_triap : String,
                   amount : String,da : String,advance : String,per_day_avg_km : String,pay_mode : String,cust_detail : String,
                   t_amt : String,driver_name : String,driver_mob : String,) :Call<InsertResponse>{
        return mApiService.setApi(r_date,r_time,select_one,car_type,only_drop,raund_triap,amount,
            da,advance,per_day_avg_km,pay_mode,cust_detail,t_amt,driver_name,driver_mob)
    }

    fun getDriverList() : Call<DriverListResponse>{
        return mApiService.getDriverList()
    }
    
}
