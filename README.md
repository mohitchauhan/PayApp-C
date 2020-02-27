# Weather Application
 Weather app to show next four days forecast based on current location


Architecture pattern used
------------------------------------
* Clean architecture with MVVM 


Core Layer
------------------------------------
Entities
RxSchedulers
Coroutine Dispatchers


Data Layer
------------------------------------
* Repository to provider data from data source
* Data Source to interact with API layer
* Used location provider to provide user current location

Domain
------------------------------------
* Used Xu weather api to get weather forecast


Libraries used on the sample project
------------------------------------
 * Entirely written in [Kotlin](https://kotlinlang.org/) 
 * Uses coroutines [Coroutine](https://kotlinlang.org/docs/reference/coroutines-overview.html)
 * Uses [RxJava](https://github.com/ReactiveX/RxJava) 2
 * Uses all of the [Architecture Components](https://developer.android.com/topic/libraries/architecture/): Room, LiveData and Lifecycle-components
 * Uses [dagger-android](https://google.github.io/dagger/android.html) for dependency injection
 * OkHttp and Retrofit for networking
 * Mockito and MockWebServer for mocking objects and network requests respectively in tests


Min Sdk version 16
------------------------------------

By Mohit Chauhan
------------


