package com.mrcj.listofthebest.model

import com.google.gson.annotations.SerializedName

data class Project(
    @SerializedName("name")
    var name : String,

    @SerializedName("full_name")
    var full_name : String,

    @SerializedName("description")
    var description : String,

    @SerializedName("forks_count")
    var forks_count : Int,

    @SerializedName("stargazers_count")
    var stargazers_count : Int,

    @SerializedName("owner")
    var owner : Author,

)
