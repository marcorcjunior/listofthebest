package com.mrcj.listofthebest.rest

import com.mrcj.listofthebest.model.Projects
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface Repositories {
    @GET("search/repositories")
    fun getList(
        @Query("q") q: String?,
        @Query("sort") sort: String?,
        @Query("page") page: Int?
    ) : Call<Projects>
}