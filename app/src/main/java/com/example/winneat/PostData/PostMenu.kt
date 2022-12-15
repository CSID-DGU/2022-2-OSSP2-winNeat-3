package com.example.winneat.PostData

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class menuResult (
    @Expose
    @SerializedName("menu")
    var MenuList:List<PostMenu> = arrayListOf()
)

data class PostMenu(
    @Expose
    @SerializedName("menuId")
    var menuId: Int?,
    @Expose
    @SerializedName("menuName")
    var menuName: String?,
    @Expose
    @SerializedName("menuPrice")
    var menuPrice: Int?,
    @Expose
    @SerializedName("category")
    var category: String?,
    @Expose
    @SerializedName("menuIntro")
    var menuIntro: String?,
    @Expose
    @SerializedName("storeName")
    var storeName: String?,
    @Expose
    @SerializedName("stadiumName")
    var stadiumName: String?,
):Serializable
