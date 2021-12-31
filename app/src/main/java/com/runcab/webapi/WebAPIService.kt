package com.runcab.webapi

import com.runcab.model.*
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*


interface WebAPIService {
//
    @GET("display_all.php")
    fun allData(): Call<DisplayAlResponse>

    @FormUrlEncoded
    @POST("ren_cab_insert.php")
    fun setApi(
        @Field("r_date") r_date : String,
        @Field("r_time") r_time : String,
        @Field("select_one") select_one : String,
        @Field("car_type") car_type : String,
        @Field("only_drop") only_drop : String,
        @Field("raund_triap") raund_triap : String,
        @Field("amount") amount : String,
        @Field("da") da : String,
        @Field("advance") advance : String,
        @Field("per_day_avg_km") per_day_avg_km : String,
        @Field("pay_mode") pay_mode : String,
        @Field("cust_detail") cust_detail : String,
        @Field("t_amt") t_amt : String,
        @Field("driver_name") driver_name : String,
        @Field("driver_mob") driver_mob : String,
    ) : Call<InsertResponse>

    @GET("driver_get_list.php")
    fun getDriverList() : Call<DriverListResponse>
}