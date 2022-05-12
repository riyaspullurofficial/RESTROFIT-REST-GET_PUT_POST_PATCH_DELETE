package com.riyaspullur.learningrestapimethods

data class UserList(
   val data:List<User>
)


data class User(
    var id:String?,
    var name:String?,
    var email:String?,
    var gender:String?,
    var status:String?
)

data class UserResponse(val code:Int?, val meta:String?, val data:User?)
