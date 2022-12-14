package com.example.winneat.PostData

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class storeResult(
    @Expose
    @SerializedName("store")
    var storeList:List<PostStore> = arrayListOf()
)

data class PostStore(
    @Expose
    @SerializedName("storeName")
    var storeName: String?,
    @Expose
    @SerializedName("storeLoc")
    var storeLoc: String?,
    @Expose
    @SerializedName("phoneNum")
    var phoneNum: Int?,
    @Expose
    @SerializedName("stadiumName")
    var stadiumName: String?,
    @Expose
    @SerializedName("storeCategory")
    var storeCategory: String?,
) : Serializable
