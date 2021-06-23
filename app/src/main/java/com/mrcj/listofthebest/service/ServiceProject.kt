package com.mrcj.listofthebest.service

import com.mrcj.listofthebest.RetrofitUtils
import com.mrcj.listofthebest.rest.Repositories

class ServiceProject {
    private val urlBase : String = "https://api.github.com"

    fun getListProjects(page: Int = 1, q: String = "language:kotlin", sort: String = "stars") =
        RetrofitUtils.getRetrofitInstance(urlBase)
            .create(Repositories::class.java)
            .getList(q, sort, page)
}