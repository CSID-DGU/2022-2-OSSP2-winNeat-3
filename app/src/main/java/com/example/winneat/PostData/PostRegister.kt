package com.example.winneat.PostData

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class PostRegister(
    @Expose
    @SerializedName("userId")
    var userId: String? = null,
    @Expose
    @SerializedName("userPassword")
    var userPassword: String? = null,
    @Expose
    @SerializedName("userName")
    var userName: String? =null,
    @Expose
    @SerializedName("status")
    var status: String? =null,
    @Expose
    @SerializedName("phoneNum")
    var phoneNum: String? =null
)
