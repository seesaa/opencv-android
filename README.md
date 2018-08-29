OpenCV for Android library
==========================
日本語のREADMEは[こちら](https://github.com/seesaa/opencv-android/blob/master/README_JP.md)。

# What is this library?

OpenCV is a very useful library, but the installation to Android Project is troublesome.
Also, from the viewpoint of separation Android Project source code and OpenCV bintary, you don't want to commit multiple megabytes of ".*so" file per one.

This library is a can be used by writing OpenCV in `dependencies` block familiar with Gradle!

## Difference from original OpenCV SDK

The following points differ from the OpenCV Android version SDK available from [OpenCV Download Page](https://sourceforge.net/projects/opencvlibrary/files/).

- This library can be used only by describing it in `dependencies` block.
- In this library, some minor ABIs included in the original SDK are omitted.
    - Specifically: mips, mips64, x86, x86_64 are **not** included.
    - arm64-v8a, armeabi, armeabi-v7a are included.

# Usage

## 1. Add the following code to your project's `build.gradle`

```groovy
dependencies {
    implementation "jp.seesaa.android:opencv:3.4.2.0"
}
```

NOTE: Don't forget to do `Sync Project with Gradle File`.

## 2. Add the following to the class that extends `Application` class

```java
public class MyApplication extends Application {
    static {
        OpenCVLoader.initDebug();
    }
}
```

NOTE: You can write `System.loadLibrary("opencv_java3);` instead of `OpenCVLoader`.

## 3. Enjoy OpenCV in your Android Project!

# About library maintenance

If you need to modify this repository for reasons such as the version of OpenCV has been raised, keep the following points in your mind.


## The `*.so` file is managed by Git LFS

Git repositories are useful for text difference management, but binaries are not sutiable.
Therefore, a large binary file (`.*so` files in this repository) is managed by [Git Large File Storage](https://git-lfs.github.com/).

but, it's not that special, just install git-lfs with the command below, and then git clone it.

```
$ brew install git-lfs
```

Also, if you do just `git pull` only the pointer file of the binaries will be acquired, so you should also do `git lfs pull` together.

## Naming rules of versionCode, versionName

versionCode and versionName are managed at the project level build.gradle.(`./build.gradle`)
(It's also written in build.gradle) versionCode and versionname has an naming rules.

**versionName** is managed as 4-delimited version, with the number of releases of this library in the corresponding version appended to the end of OpenCV version.
(e.g. If OpenCV is 2.4.11, this library is the initial release in 2.4.11, it is set to `2.4.11.0`. If you updated 3 times, it should be `2.4.11.3`. If you upgrade OpenCV to 3.0.0, the number of releases will be reset and set to `3.0.0.0`.)

**versionCode** is generated from versionName conforming to the below rules.
Firs of all, we divide the versionName 4 breaks into `[major].[minor].[hotfix].[revision]`.

- The [`revision`] part is 2-digits with **zero** padding. (e.g. 01)
- The [`hotfix`] part is 2-digits with **zero** padding. (e.g. 11)
- The [`minor`] part is 2-digits with **zero** padding. (e.g. 04)
- The [`major`] part is no digit limit and **not** zero padding.(up to about 3-digits at most.) (e.g. 2)

After that, it combines this from the [`major`]part in the order of [`minor`], [`hotfix`], and [`revision`] part. In the above example, It will be "2041101"

# Licenses

This library adjust the SDK source code binary obtained from the [OpenCV Download Page](https://sourceforge.net/projects/opencvlibrary/files/) distributed under the three clause BSD license, converted it to the AAR library we will.
In accordance with the three clause BSD license, you can use OpenCV by clarifying the license terms in the application.

- [https://github.com/opencv/opencv/blob/master/LICENSE](https://github.com/opencv/opencv/blob/master/LICENSE)

Although We have not thought about anything particularly because it was created for internal use, our special notation of logic is not done so our notation in license is unnecessary.Although We have not thought about anything particularly because it was created for internal use, our special notation of logic is not done so our notation in license is unnecessary.