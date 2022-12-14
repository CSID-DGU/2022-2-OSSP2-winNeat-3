package com.example.winneat.PostData

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class PostLogin(
    @Expose
    @SerializedName("userId")
    var userId: String? = null,
    @Expose
    @SerializedName("userPassword")
    var userPassword: String? = null,
)
