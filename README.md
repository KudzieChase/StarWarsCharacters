<p align="center">
  <a href="https://android-arsenal.com/api?level=21"><img alt="API" src="https://img.shields.io/badge/API-21%2B-brightgreen.svg?style=flat"/></a>
</p>

# Star Wars Characters

The app is written 100% in Kotlin with Unit Tests and Instrumentation tests.

In this repository I attempt to demonstrate the following concepts:

* Clean Architecture with MVVM (Model View ViewModel) on the presentation layer
* Usage of Jetpack libraries
* Usage of Kotlin's Coroutines and Flow for background execution
* Dependency Injection using Dagger Hilt

## Prerequisites

In order to run this project you need the following:
- Android Studio 4.1.1 or better
- Gradle 6.5 or better
- JDK 1.8
- [Android SDK](https://developer.android.com/studio/index.html)

## My Thought Process

<img src="https://blog.cleancoder.com/uncle-bob/images/2012-08-13-the-clean-architecture/CleanArchitecture.jpg"/>
<br>

The application has been split into 3 modules - Domain, Data and App.


## Libraries I chose to use

* [Kotlin](https://kotlinlang.org/)
* [Kotlin Coroutines](https://kotlinlang.org/docs/reference/coroutines-overview.html)
* [Flow](https://kotlinlang.org/docs/reference/coroutines/flow.html)
* [Retrofit](http://square.github.io/retrofit/) - An http client for android
* [Okhttp](http://square.github.io/okhttp/) - For networking requests
* [Mockito](http://site.mockito.org/) - For mocking instances
* [Moshi](https://github.com/square/moshi) - For parsing JSON into Objects
* [Dagger Hilt](https://dagger.dev/hilt/) - For dependency injection
* [Lottie Android](https://github.com/airbnb/lottie-android) - For animation
* [Truth](https://truth.dev/) - For assertions during testing
* Jetpack Libraries


## Screenshots

<img src="https://user-images.githubusercontent.com/16834730/106307733-607f2c00-6268-11eb-8ff7-f9d89767a30e.png" width="250px"/> <img src="https://user-images.githubusercontent.com/16834730/106307752-67a63a00-6268-11eb-96f2-805ed60d7fa2.png" width="250px"/>

<br>
