package org.sopt24.dshyun0226.androidseminar.Activity

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import kotlinx.android.synthetic.main.activity_signup.*
import org.jetbrains.anko.toast
import org.json.JSONObject
import org.sopt24.dshyun0226.androidseminar.Network.ApplicationController
import org.sopt24.dshyun0226.androidseminar.Network.NetworkService
import org.sopt24.dshyun0226.androidseminar.Network.Post.PostLoginResponse
import org.sopt24.dshyun0226.androidseminar.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*

class SignupActivity : AppCompatActivity() {

    val networkService: NetworkService by lazy{
        ApplicationController.instance.networkService
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        val edtOnFocusChangeListener: View.OnFocusChangeListener = View.OnFocusChangeListener { v, hasFocus ->
            if(hasFocus) v.setBackgroundResource(R.drawable.primary_border)
            else v.setBackgroundResource(R.drawable.gray_border)
        }

        edtSignupName.setOnFocusChangeListener(edtOnFocusChangeListener)
        edtSignupID.setOnFocusChangeListener(edtOnFocusChangeListener)
        edtSignupPW.setOnFocusChangeListener(edtOnFocusChangeListener)

        btnSignupSubmit.setOnClickListener {
            val signup_u_name: String = edtSignupName.text.toString()
            val signup_u_id = edtSignupID.text.toString()
            val signup_u_pw: String = edtSignupPW.text.toString()

            if(isValid(signup_u_id, signup_u_pw, signup_u_name)){
                postSignupResponse(signup_u_id, signup_u_pw, signup_u_name)
            }
            /*if(signup_u_name == "") edtSignupName.requestFocus()
            else if(signup_u_id == "") edtSignupID.requestFocus()
            else if(signup_u_pw == "") edtSignupPW.requestFocus()
            else{
                postSignupResponse(signup_u_id, signup_u_pw, signup_u_name)
                finish()
            }*/
        }
    }
    fun isValid(u_id: String, u_pw: String, u_name: String): Boolean{
        if(u_name == "") edtSignupName.requestFocus()
        else if(u_id == "") edtSignupID.requestFocus()
        else if(u_pw == "") edtSignupPW.requestFocus()
        else return true
        return false
    }
    fun postSignupResponse(u_id:String, u_pw: String, u_name: String){
       /* // Request Signup
        val simpleDateFormat = SimpleDateFormat("dd/M/yyyy hh:mm:ss")
        val e_time = simpleDateFormat.format(Date())

        val intent: Intent = Intent()
        intent.putExtra("end_time",e_time)
        setResult(Activity.RESULT_OK, intent)

        finish()*/

        var jsonObject = JSONObject()
        jsonObject.put("id",u_id)
        jsonObject.put("password",u_pw)
        jsonObject.put("name",u_name)

        val gsonObject = JsonParser().parse(jsonObject.toString())as JsonObject
        val postLoginResponse: Call<PostLoginResponse> =
                networkService.postLoginResponse("application/json", gsonObject)
        postLoginResponse.enqueue(object : Callback<PostLoginResponse> {
            override fun onFailure(call: Call<PostLoginResponse>, t: Throwable) {
                Log.e("Login failed", t.toString())
            }

            override fun onResponse(call: Call<PostLoginResponse>, response: Response<PostLoginResponse>) {
                if(response.isSuccessful){
                    toast(response.body()!!.message)
                    if(response.body()!!.status == 201){
                        // Request Login
                        val simpleDateFormat = SimpleDateFormat("dd/M/yyyy hh:mm:ss")
                        val e_time = simpleDateFormat.format(Date())

                        val intent: Intent = Intent()
                        intent.putExtra("end_time",e_time)
                        setResult(Activity.RESULT_OK, intent)
                        finish()
                    }
                }
            }
        })
    }
}
