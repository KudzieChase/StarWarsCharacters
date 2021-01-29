<p align="center">
  <a href="https://android-arsenal.com/api?level=21"><img alt="API" src="https://img.shields.io/badge/API-21%2B-brightgreen.svg?style=flat"/></a>
</p>

# Star Wars Characters

## Case Study Project for Trivago Android Engineer Role

Hello! 😬 - This is a repository for the Star Wars Case Study Project for the Trivago Android Engineer role.

The app is written 100% in Kotlin with Unit Tests and Instrumentation tests.

In this repository I attempt to demonstrate the following concepts:

* Clean Architecture with MVVM (Model View ViewModel) on the presentation layer
* Usage of Jetpack libraries
* Usage of Kotlin's Coroutines and Flow for background execution
* Dependency Injection using Dagger Hilt

## Prerequisites

In order to run this project you need the following:
- Android Studio 4.1.1 or better
- Gradle 6.5
- JDK 1.8
- [Android SDK](https://developer.android.com/studio/index.html)

## My Thought Process

The application has been split into 3 modules - Domain, Data and App.

### Domain

The domain layer contains UseCase instances we use to collect data from the Data Layer and then in turn pass it to our Presentation Layer (App).
I have defined two Use Case Base classes. A general Use case and a FlowUseCase.
The use cases are referenced from an outer later without directly referencing the specific implementation.
The layer also has no mapper because it is not supposed to know abything of the outer layers using Dependency inversion principle.

I have defined two interfaces namely SearchRepository and CharacterRepository which provide a set of methods for an external layer to implement.

### Data

The data layer acts as our access point to fetch data from a remote source i.e. network.
It contains the implementations of our two repositories defined in the Domain layer.
We also perform mapping in this later to map the Data Layer entities to our Domain.

The networking here is performed by Retrofit. A popular Http client for andrioid and OkHttp.
Retrofit also has builtin support for coroutines hence my choice in the library.

### App (The presentation)

This layer has our Android Framework User Interface (UI) and also the ViewModels which connect to the UI components hence the MVVM.
I chose MVVM in this layer because of the lifecycle aware ViewModel component found in the Jetpack libraries.
The ViewModels contain a reference to our use cases and which are used to request data.

I use LiveData to observe the data and state changes.
I have also defined a neat little Sealed class to manage network specific state like Success,Loading,Error and Idle.
Layouts for Activities and Fragments were using XML. And Navigation was done using the navigation component in the Jetpack Library.
I managed all Dependency Injection via Dagger Hilt.

I used Lottie Android for the lovely animation that you see when you are searching. Lottie is a library by AirBnB that makes complex animation very easy.

I also chose Dagger Hilt for Dependency Injection simply because it does away with a lot of boiler plate that Dagger needs to work on android.
I am also more comfortable using Dagger. So Hilt felt like a right choice.

Data Binding was used in this application. I feel like the use of databinding helps make Activity, Adapter and Fragment Code much much cleaner.

## Why Flow over Rx?

I chose Flow over RxJava simply because it is easier than RxJava to setup and use. The fact that it is supported by the Kotlin language makes them feel more natural
as compared to RxJava.

## Tests

I wrote unit tests for each Layer and integration tests in the presentation layer in the form of Instrumentation tests that were targeting the ViewModel instances.
The data layer tests mocked the server responses using Okhttp's MockServer.

I tried to write as many tests as I could think of following a Test Driven approach esp in the Data and Domain Layers.
I did not write UI tests.

## Opportunities for improvement?

I noticed that the app could have been improved by adding a Room Database for persistence. Maybe allowing a user to save searches and revisit them later.
I also thought the application could have used themes and styles for dark mode as well as adding a different fancy font to delight users.
These additions were not made due to the time I had to implement.

## Other things to note

I used Flow to reduce the number of requests made by the performSearch function. Namely I used the debounce operator to wait for 500ms before making a request as a means to listen whether or not a user had completed typing.

This strategy saves a lot of bandwidth because we reduce number of requests that end up going to the server.


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

<img src="https://user-images.githubusercontent.com/16834730/106307733-607f2c00-6268-11eb-8ff7-f9d89767a30e.png"/> <img src="https://user-images.githubusercontent.com/16834730/106307752-67a63a00-6268-11eb-96f2-805ed60d7fa2.png"/>

<br>
## P.S.

I hope you enjoy evaluating my solution and I hope its enough to get me to practice in your Jedi Temple.
May the code be with you!

