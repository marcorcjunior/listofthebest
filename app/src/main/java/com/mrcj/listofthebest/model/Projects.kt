package com.mrcj.listofthebest.model

import com.google.gson.annotations.SerializedName

data class Projects(
    @SerializedName("items")
    var items : List<Project>,
)
