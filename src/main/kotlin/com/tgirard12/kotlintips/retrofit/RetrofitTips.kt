package com.tgirard12.kotlintips.retrofit

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * enqueue extension function with DSL
 */
inline fun <reified T> Call<T>.enqueue(init: RetrofitTipsCallback<T>.() -> Unit) {
    enqueue(RetrofitTipsCallback<T>().apply(init))
}

/**
 * Custom Retrofit Callback
 */
class RetrofitTipsCallback<T> : Callback<T> {

    // Backing fields
    private lateinit var onResponse: (acll: Call<T>?, response: Response<T>) -> Unit
    private lateinit var onFailure: (call: Call<T>, t: Throwable) -> Unit

    // DSL function
    fun onResponse(block: (call: Call<T>?, response: Response<T>) -> Unit) {
        onResponse = block
    }

    fun onFailure(block: (call: Call<T>, t: Throwable) -> Unit) {
        onFailure = block
    }

    // Retrofit ethod
    override fun onResponse(call: Call<T>?, response: Response<T>) {
        onResponse.invoke(call, response)
    }

    override fun onFailure(call: Call<T>, t: Throwable) {
        onFailure.invoke(call, t)
    }

}