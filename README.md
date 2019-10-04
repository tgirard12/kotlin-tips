# kotlin-tips
Some Kotlin tips I keep here for :
- Retrofit
- Lazy Loader

## Retrofit 2.x

Retrofit Callback with a better DSL :

````kotlin
githubService.search("kotlin")
    .enqueue {
        onResponse { call, response ->           
            // ...
        }
        onFailure { call, throwable ->
            // ...
        }
    }
````

## Lazy Loader

Lazy loading can result in faster startup time, since loading is deferred to when the variable is accessed. This is particularly useful in using Kotlin for an Android app as opposed to a server app. For Android apps, we want to reduce app startup time so that the user sees the app content faster, rather than sitting at an initial loading screen.

````
val purchasingApi: PurchasingApi by lazy {
    val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(API_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    retrofit.create(PurchasingApi::class.java)
}
````
