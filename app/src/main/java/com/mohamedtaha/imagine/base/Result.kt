package com.mohamedtaha.imagine.base

sealed class Result<T>( val code:Int?= null,
                        val message:String? = null,
                        val data: T? = null){

    class Loading<T>:Result<T>()
    class Success<T>(data:T):Result<T>(code =null,message = null, data)
    class Error<T>(message: String):Result<T>(code = null,message)
}
