package org.sopt24.dshyun0226.androidseminar.Network

import android.app.Application
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApplicationController:Application(){

    private val baseURL = "http://hyunjkluz.ml:2424/" //api 서버 주소, 2424는 포트번호
    lateinit var networkService: NetworkService //초기화, 객체 만들기

    companion object {
        lateinit var instance: ApplicationController
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        buildNetwork()
    }

    fun buildNetwork(){
        val retrofit: Retrofit = Retrofit.Builder()
                .baseUrl(baseURL) //baseURl 지정
                .addConverterFactory(GsonConverterFactory.create())
                .build() // 적용해준 내용대로 빌드

        // networkservice라는 객체에 저장
        networkService = retrofit.create(NetworkService::class.java)
    }
}