package com.runcab.model

data class DisplayAlResponse(
    val `data`: ArrayList<Data>,
    val error: Boolean,
    val message: String
)

data class Data(
    val `0`: String,
    val `1`: String,
    val `10`: String,
    val `11`: String,
    val `12`: String,
    val `13`: String,
    val `14`: String,
    val `15`: String,
    val `16`: String,
    val `17`: Any,
    val `2`: String,
    val `3`: String,
    val `4`: String,
    val `5`: String,
    val `6`: String,
    val `7`: String,
    val `8`: String,
    val `9`: String,
    val add_date: Any,
    val advance: String,
    val amount: String,
    val car_type: String,
    val cust_detail: String,
    val da: String,
    val driver_mob: String,
    val driver_name: String,
    val id: String,
    val only_drop: String,
    val pay_mode: String,
    val per_day_avg_km: String,
    val r_date: String,
    val r_time: String,
    val raund_triap: String,
    val select_one: String,
    val t_amt: String,
    val vendor_name: String
)