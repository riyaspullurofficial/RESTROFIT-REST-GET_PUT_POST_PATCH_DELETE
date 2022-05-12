package com.riyaspullur.learningrestapimethods

import retrofit2.Call
import retrofit2.http.*

interface RetroService {

    //access Token :  93e0d54a7328dabcbed3f20c726427178bba5eef1ce095251dcf1d44c573a16c


    // https://gorest.co.in/public/v2/users/

    @GET("users")
    @Headers("Accept:application/json","Content-Type:application/json")
    fun getUserList():Call<UserList>


    //https://gorest.co.in/public/v2/users?name=a
    @GET("users")
    @Headers("Accept:application/json","Content-Type:application/json")
    fun searchUsers(@Query("name") searchText:String):Call<UserList>


    //https://gorest.co.in/public/v2/users/3838
    @GET("users/{user_id}")
    @Headers("Accept:application/json","Content-Type:application/json")
    fun getUsers(@Path("user_id") user_id:String):Call<UserResponse>


    @POST("users")
    @Headers("Accept:application/json","Content-Type:application/json",
        "Authorization: Bearer 93e0d54a7328dabcbed3f20c726427178bba5eef1ce095251dcf1d44c573a16c")
    fun createUser(@Body params:User):Call<UserResponse>


    @PATCH("users/{user_id}")
    @Headers("Accept:application/json","Content-Type:application/json",
        "Authorization: Bearer 93e0d54a7328dabcbed3f20c726427178bba5eef1ce095251dcf1d44c573a16c")
    fun updateUser(@Path("user_id") user_id:String,@Body params:User):Call<UserResponse>


    @DELETE("users/{user_id}")
    @Headers("Accept:application/json","Content-Type:application/json",
        "Authorization: Bearer 93e0d54a7328dabcbed3f20c726427178bba5eef1ce095251dcf1d44c573a16c")
    fun deleteUser(@Path("user_id") user_id:String):Call<UserResponse>

}