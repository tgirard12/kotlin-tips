# kotlin-tips
Some Kotlin tips I keep here for :
- Retrofit


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