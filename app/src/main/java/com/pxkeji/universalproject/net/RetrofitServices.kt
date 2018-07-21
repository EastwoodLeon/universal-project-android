package com.pxkeji.universalproject.net

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface LoginService {
    @GET("user/login")
    fun uploadPosition(
            @Query("username") username: String,
            @Query("password") password: String
    ) : Call<LoginResponse>
}