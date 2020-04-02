//package com.example.news.data.service
//
//import retrofit2.Call
//import retrofit2.Callback
//import retrofit2.Response
//
//internal object ApiResponse {
//
//    fun <T> callRetrofit(call: Call<T>, resultCallBack: RetrofitService.ResultCallBack) {
//
//        //    val result = Result<T>()
//
//        call.enqueue(object : Callback<T> {
//            override fun onResponse(call: Call<T>, response: Response<T>) {
////                if (response.body() != null) {
////                    result.data = response.body()
////                } else {
////                    result.errorMessage = response.message()
////                }
////
////                resultCallBack.getResult(result)
//            }
//
//            override fun onFailure(call: Call<T>, t: Throwable) {
//                println(t.localizedMessage)
//            }
//        })
//    }
//}
//
