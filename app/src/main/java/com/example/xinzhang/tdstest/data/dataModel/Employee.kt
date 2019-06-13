package com.example.xinzhang.tdstest.data.dataModel

import com.google.gson.annotations.SerializedName

data class Employee  constructor(
    @SerializedName("id") val id: String,
    @SerializedName("employee_name") val name: String,
    @SerializedName("employee_age") val age: Int,
    @SerializedName("profile_image") val image: String
)