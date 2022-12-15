package com.example.winneat.PostData

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import retrofit2.http.Field

data class PostOrder(
    @Expose
    @SerializedName("userId")
    var userId: String?,
    @Expose
    @SerializedName("orderNum")
    var orderNum: String?,
    @Expose
    @SerializedName("orderStatus")
    var orderStatus: String?,
    @Expose
    @SerializedName("menuName")
    var menuName: String?,
    @Expose
    @SerializedName("orderDetailNum")
    var orderDetailNum: String?,
    @Expose
    @SerializedName("menuQuantity")
    var menuQuantity: Int?,
    @Expose
    @SerializedName("stadiumName")
    var stadiumName: String?,
    @Expose
    @SerializedName("status")
    var status: String?
)
