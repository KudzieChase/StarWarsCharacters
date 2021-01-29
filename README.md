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

The application has been split into 3 modules.

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


## Libraries I chose to use

* [Kotlin](https://kotlinlang.org/)
* [Kotlin Coroutines]()
* [Flow]()
* [Retrofit](http://square.github.io/retrofit/)
* [Okhttp](http://square.github.io/okhttp/)
* [Mockito](http://site.mockito.org/)
* [Moshi]()
* [Dagger Hilt]()
* [Lottie]()
* [Truth]()
* Jetpack Libraries


## Screenshots

