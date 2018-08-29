OpenCV for Android library
==========================
English version is [here](https://github.com/seesaa/opencv-android/blob/master/README.md).

# これは何？

OpenCVは便利なライブラリですが、検索して見つかる導入方法は面倒であったりします。
また、ソースコードとバイナリの分離の観点からも、ひとつあたり数メガバイトの`libopencv_java3.so`を複数コミットするということは回避したいところです。

本ライブラリは、OpenCVをGradleでお馴染みの`dependencies`ブロックに記述するだけで利用できるようになるライブラリです。

## オリジナルSDKとの差異

[OpenCVのDownloadsページ](https://sourceforge.net/projects/opencvlibrary/files/)より入手できるAndroid版SDKとは以下の点が異なります

- 本ライブラリはdependenciesに記述するだけで使用できます
- 本ライブラリでは、オリジナルSDKに含まれている一部のマイナーなABIは省かれています
 - 具体的には： mips, mips64, x86, x86_64 が**含まれません**
 - arm64-v8a, armeabi, armeabi-v7a は**含まれます**

# 使用方法

## 1. dependenciesブロックに以下を付け加える

```groovy
dependencies {
    implementation "jp.seesaa.android:opencv:3.4.2.0"
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

## versionCode, versionNameの付け方について

versionCode及びversionNameは、Projectレベルのbuild.gradle(`./build.gradle`)にて管理されています。

(build.gradleにも書いてありますが)versionCode, versionNameには記入ルールを設けてあります。

**versionName**は、OpenCVのバージョン名の末尾に、該当バージョンでの本ライブラリのリリース回数を付与した、4区切りのバージョンで管理します。
（例：OpenCVが2.4.11で、本ライブラリが2.4.11における初回リリースであるなら、`2.4.11.0`とします。3度アップデートを行った場合は、`2.4.11.3`とします。
OpenCV3.0.0にバージョンアップしたなら、リリース回数部分はリセットされ、`3.0.0.0`とします。）

**versionCode**は、上記ルールに則ったversionNameより生成します。
まず、versionNameの4区切りをそれぞれ、`[major].[minor].[hotfix].[revision]`とします。

- [revision]部は、0埋め2桁とします (例:01)
- [hotfix]部は、0埋め2桁とします (例:11)
- [minor]部は、0埋め2桁とします (例:04)
- [major]部は、0埋めをしない桁数制限なし(高々3桁程度まで)とします (例:2)

その後、これを[major]部から[minor],[hotfix],[revision]部と順番に結合します。上記例で考えると、「2041101」となります。


# Licenses

本ライブラリは三条項BSDライセンスの下で配布されているOpenCVの[Downloadsページ](http://opencv.org/downloads.html)より入手したSDKソースコード・バイナリを調整の上、AARライブラリ化しております。
三条項BSDライセンスに則り、OpenCVのライセンス条項をアプリ内に明示することでお使いいただけます。

- [https://github.com/opencv/opencv/blob/master/LICENSE](https://github.com/opencv/opencv/blob/master/LICENSE)

社内用に作成したものなので特に何も考えてないものの、ロジック類の特別な追記は行っておりませんのでライセンスにおける弊社の表記は不要です。