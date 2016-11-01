package com.tgirard12.kotlintips.retrofit

import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * A retrofit interface sample
 */
interface GithubService {

    @GET("/search/repositories")
    fun search(@Query("q") query: String): Call<Search>
}

fun retrofitTips() {

    // Retrofit initialization
    val gson = Gson()
    val retrofit: Retrofit = Retrofit
            .Builder()
            .baseUrl("https://api.github.com")
            .addConverterFactory(GsonConverterFactory.create(gson)).build()
    val githubService: GithubService = retrofit.create(GithubService::class.java)

    // Retrofit Call
    githubService.search("kotlin").enqueue {
        onResponse { call, response ->
            val search = response.body()
            println("Found ${search.total_count} repositories")
            // ...
        }
        onFailure { call, throwable ->
            println("onFailure : ${throwable.message}")
            // ...
        }
    }
}

// Result from Github API

data class Search(
        val total_count: Int,
        val isIncomplete_results: Boolean,
        val items: List<SearchItem>?
)

class SearchItem(
        val id: Int,
        val name: String,
        val description: String?,
        val full_name: String?,
        val html_url: String,
        val owner: Owner
)

data class Owner(
        val login: String,
        val id: Long,
        val avatar_url: String?
)