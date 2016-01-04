# AspectForAndroid
Some aspects to use on android

##Setup


Import library, current version is `0.1`

```groovy
apply plugin: 'com.uphyca.android-aspectj'

repositories{
	...
   	maven { url "https://jitpack.io" }
}

compile 'com.github.aardouin:AspectForAndroid:$version'
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

