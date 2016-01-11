# AspectForAndroid
Some aspects to use on android

##Setup

![Latest Release](https://img.shields.io/github/release/aardouin/AspectForAndroid.svg?label=latest%20release)

Import library, current version is `1.0`

```groovy
//apply this plugin after 'com.android.application'
apply plugin: 'com.uphyca.android-aspectj'

repositories{
	...
   	maven { url "https://jitpack.io" }
}

dependencies {
        classpath 'com.uphyca.gradle:gradle-android-aspectj-plugin:0.9.14'
}  

compile 'com.github.aardouin:AspectForAndroid:$version'
```

Add aspectj to your classpath in your root `build.gradle`
```
classpath 'https://github.com/uPhyca/gradle-android-aspectj-plugin'
```



##Thread management 

To ensure a method will run on UIThread, simply annotate you method like this : 
```java
@EnsureUiThread
public void methodToRunOnUiThread(){
	...
}    
```

Same principle to run a method in a background thread. Please note that all method anoted like this will run on the same thread : 
```java
@EnsureAsync
public void methodToRunOnBackgroundThread(){
	...
}    
```

##UI features
In order to use UI features, the library needs a context, which can be set up via :

```java
AspectContextManager.initWithContext(this);
```

####Confirmation Dialog
ConfirmDialog features allows you to add a dialog asking for user confirmation before executing a method. 

```java
@ConfirmDialog()
public void methodToConfirm(){
	...
}   
```

Strings can be changed via one of the following (please note `xxxRes` params have priority over string params)

```
titleRes = R.string.confirm_title
title = "Custom title"
positiveTextRes = R.string.confirm_positive_text
positiveText = "Positive text"
negativeTextRes = R.string.confirm_negative_text
negativeText = "Negative text"
```

