package com.example.winneat.PostData

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class stadiumResult(
    @Expose
    @SerializedName("stadium")
    var stadiumList:List<PostStadium> = arrayListOf()
)

data class PostStadium(
    @Expose
    @SerializedName("stadiumName")
    var stadiumName: String?,
)
