package com.example.imagesearch.retrofit

import com.example.imagesearch.BuildConfig.KAKAO_API_KEY
import com.google.gson.internal.GsonBuildConfig
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Query

interface KakaoInterface {
    @Headers("Authorization: KakaoAK ${KAKAO_API_KEY}")
    @GET("v2/search/image")
    suspend fun imgSearch(
        @Query("query") query: String?,
        @Query("sort") sort: String?,
        @Query("page") page: Int,
        @Query("size") size: Int
    ): Response<ImageModel>

}