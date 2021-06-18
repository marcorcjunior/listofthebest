package com.mrcj.listofthebest.model

import com.google.gson.annotations.SerializedName

data class Author (
    @SerializedName("login")
    var name : String,

    @SerializedName("avatar_url")
    var url_photo : String,
)