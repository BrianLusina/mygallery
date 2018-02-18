# MyGallery

[![Build Status](https://www.bitrise.io/app/e240ce32d7bab980/status.svg?token=WyjkV9jju1NVA4QL8LSTvQ)](https://www.bitrise.io/app/e240ce32d7bab980)

Simple Android application that displays Gallery Albums from user's device and allows them to navigate through their albums pictures.

## Getting Started

Getting started with the project is quite simple. You will only need to clone the project:

```bash
# if using ssh
$ git clone git@github.com:BrianLusina/mygallery.git
# if using https
$ git clone https://github.com:BrianLusina/mygallery.git
```
> Gets a copy of the project onto development environment

## Pre-requisites

A couple of things you will need to setup:

1. *[Android Studio](https://developer.android.com/studio/index.html)*
    Ensure you already have the latest version of AS before setup. Also have the latest SDK version.
 
2. *[gradle.properties](https://docs.gradle.org/current/userguide/build_environment.html)* file.
    
    Set this at the root of the project with your build environment variables as shown in the [gradle properties file example](./gradle.example.properties)

3. *keystore.properties* file set up in the keystores directory
    
    This is setup in the [keystores](./keystores) directory. An example is defined in the [example keystore file](./keystores/keystore.example.properties).
    This is used for signing the application for release builds.
    
4. *publish_key.json* file in the keystore directory file.

    This is also setup in the keystores directory and can be generated from the [Google API Console](https://console.cloud.google.com/apis?pli=1). This is the key used for automated publishing to PlayStore.
 
## Running Tests

Running tests is as simple as 

```bash
$ ./gradlew test
```
> This will run all unit tests.

```bash
$ ./gradlew connectedAndroidTest
```
> Will run Instrumented tests, ensure you have an Android Device connected or an Emulator running.

## Code Styling

Code styling has been configured using [Detekt](https://github.com/arturbosch/detekt) and can be run using:

```bash
$ ./gradlew detektCheck
```

## Deployment

Deployment has been configured for PlayStore and the [Play Publisher](https://github.com/Triple-T/gradle-play-publisher) plugin has been used for this process. Read more about this [here](https://github.com/codepath/android_guides/wiki/Automating-Publishing-to-the-Play-Store).

## Versioning

[SemVer](https://semver.org/) is used for versioning. For the versions available, see the [tags](https://github.com/BrianLusina/mygallery/tags) on this repository.

## License
This project is licensed under the MIT License - see the [LICENSE.md](./LICENSE) file for details

[![forthebadge](https://forthebadge.com/images/badges/built-for-android.svg)](https://forthebadge.com)
[![forthebadge](https://forthebadge.com/images/badges/built-by-developers.svg)](https://forthebadge.com)
[![forthebadge](https://forthebadge.com/images/badges/built-with-love.svg)](https://forthebadge.com)