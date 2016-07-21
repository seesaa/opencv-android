OpenCV for Android library
==========================

# これは何？

OpenCVは便利なライブラリですが、検索して見つかる導入方法は面倒であったりします。
また、ソースコードとバイナリの分離の観点からも、ひとつあたり数メガバイトの`libopencv_java3.so`を複数コミットするということは回避したいところです。

本ライブラリは、OpenCVをGradleでお馴染みの`dependencies`ブロックに記述するだけで利用できるようになるライブラリです。

## オリジナルSDKとの差異

[OpenCVのDownloadsページ](http://opencv.org/downloads.html)より入手できるAndroid版SDKとは以下の点が異なります

- 本ライブラリはdependenciesに記述するだけで使用できます
- 本ライブラリでは、オリジナルSDKに含まれている一部のマイナーなABIは省かれています
 - 具体的には： mips, mips64, x86, x86_64 が**含まれません**
 - arm64-v8a, armeabi, armeabi-v7a は**含まれます**

# 使用方法

## 1. dependenciesブロックに以下を付け加える

```groovy
dependencies {
    compile "jp.seesaa.android:opencv:3.1.0.0"
}
```

NOTE: `Sync Project with Gradle File`することを忘れずに。

## 2. `Application`クラスを継承したクラスに以下を付け加える

```java
public class MyApplication extends Application {
    static {
        OpenCVLoader.initDebug();
    }
}
```

NOTE: `OpenCVLoader`の代わりに`System.loadLibrary("opencv_java3");`と記述しても良い。

## 3. あとは普通に使うだけ。

# ライブラリのメンテナンスについて

OpenCVのバージョンが上がったなどの理由で本リポジトリを修正する必要がある場合、以下の点に留意してください。

## `*.so`ファイルはGit LFSで管理されています

Gitリポジトリはテキストの差分管理には有用ですが、バイナリは不向きです。
そのため、サイズの大きなバイナリファイル（本リポジトリにおいては`*.so`ファイル）は[Git Large File Storage](https://git-lfs.github.com/)で管理しています。

とは言えあまり特別なことはなく、以下コマンドなどでgit-lfsをインストールしてから git clone すれば良いだけです。

```
$ brew install git-lfs
```

また、`git pull`などをするだけだと、ファイルのポインターファイルだけが取得された状態になるので、`git lfs pull`も併せて行うと良いでしょう。

## bintrayへの配信について

以下のようなシェルスクリプトを用意するのが良いでしょう。

```sh
#!/bin/sh

BINTRAY_USERNAME=*****
BINTRAY_KEY=**********

./gradlew clean library:build library:bintrayUpload -PbintrayUser=$BINTRAY_USERNAME -PbintrayKey=$BINTRAY_KEY -PdryRun=false
```

# Licenses

本ライブラリは三条項BSDライセンスの下で配布されているOpenCVの[Downloadsページ](http://opencv.org/downloads.html)より入手したSDKソースコード・バイナリを調整の上、AARライブラリ化しております。
三条項BSDライセンスに則り、OpenCVのライセンス条項をアプリ内に明示することでお使いいただけます。

- [https://github.com/opencv/opencv/blob/master/LICENSE](https://github.com/opencv/opencv/blob/master/LICENSE)

社内用に作成したものなので特に何も考えてないものの、ロジック類の特別な追記は行っておりませんのでライセンスにおける弊社の表記は不要です。