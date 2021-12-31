package com.runcab.model

data class DriverListResponse(
    val name: List<Name>
)

data class Name(
    val driver_mo: String,
    val driver_name: String,
    val id: String
)