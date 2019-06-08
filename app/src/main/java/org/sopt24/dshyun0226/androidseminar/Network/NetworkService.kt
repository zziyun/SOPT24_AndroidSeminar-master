package org.sopt24.dshyun0226.androidseminar.Network

import com.google.gson.JsonObject
import org.sopt24.dshyun0226.androidseminar.Network.Get.GetMainProductListResponse
import org.sopt24.dshyun0226.androidseminar.Network.Post.PostLoginResponse
import retrofit2.Call
import retrofit2.http.*

interface NetworkService {
    @POST("/api/auth/signin") //HTTP Method (POST), API URL (/api/auth/login)
    fun postLoginResponse(
            @Header("Content-Type")content_type: String, // HTTP Request Header
            @Body() body:JsonObject // POST 방식은 HTTP Request의 Body에 Json 포맷으로 데이터를 담아서 전달
    ): Call<PostLoginResponse> //Postloginreponse라는 객체에 내용 담아 보낸다

    @POST("/api/auth/signup")
    fun postSignupResponse(
            @Header("Content-Type")content_type: String,
            @Body() body:JsonObject
    ): Call<PostLoginResponse>

    @GET("/api/webtoons/main/{flag}")
    fun getMainProductListResponse(
            @Header("Content-Type")content_type: String,
            //@Query("flag")flag: Int
            @Path("flag") flag: Int
    ): Call<GetMainProductListResponse>
}