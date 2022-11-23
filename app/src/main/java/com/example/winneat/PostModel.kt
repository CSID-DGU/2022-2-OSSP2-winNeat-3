package com.example.winneat

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class PostModel(
    @Expose
    @SerializedName("userId")
    var userId: String? = null,
    @Expose
    @SerializedName("userPassword")
    var userPassword: String? = null,
    @Expose
    @SerializedName("status")
    var status: String? = null,
)