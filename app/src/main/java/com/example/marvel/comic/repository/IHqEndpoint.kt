package com.example.marvel.comic.repository

import com.example.marvel.comic.model.HqModel
import com.example.marvel.data.api.NetworkUtils
import com.example.marvel.data.model.ResponseModel
import retrofit2.http.GET
import retrofit2.http.Query

interface IHqEndpoint {
    @GET("/v1/public/comics")
    suspend fun getHqs(@Query("offset") offset: Int? = 0, @Query("orderBy") orderBy:String = "title"): ResponseModel<HqModel>

    companion object {
        val endpoint: IHqEndpoint by lazy {
            NetworkUtils.getRetrofitInstance().create(IHqEndpoint::class.java)
        }
    }
}