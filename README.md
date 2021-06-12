# Introduction to LeakCanary
LeakCanary is a memory leak detection library for Android.
LeakCanary’s knowledge of the internals of the Android Framework gives it a unique ability to narrow down the cause of each leak, helping developers dramatically reduce OutOfMemoryError crashes.

## What is a memory leak?
In a Java based runtime, a memory leak is a programming error that causes an application to keep a reference to an object that is no longer needed. As a result, the memory allocated for that object cannot be reclaimed, eventually leading to an OutOfMemoryError (OOM) crash.

For example shown in this repo, an Android `Activity` instance is no longer needed after its `onDestroy()` method is called, and storing a reference to that instance in a static field prevents it from being garbage collected.

## Common causes for memory leaks
Most memory leaks are caused by bugs related to the lifecycle of objects. Here are a few common Android mistakes:

* Adding a `Fragment` instance to the backstack without clearing that Fragment’s view fields in `Fragment.onDestroyView()`.
* Storing an `Activity` instance as a Context field in an object that survives activity recreation due to configuration changes.
* Registering a listener, broadcast receiver or RxJava subscription which references an object with lifecycle, and forgetting to unregister when the lifecycle reaches its end.

## Why should I use LeakCanary?
Memory leaks are very common in Android apps and the accumulation of small memory leaks causes apps to run out of memory and crash with an OOM. LeakCanary will help you find and fix these memory leaks during development. When Square engineers first enabled LeakCanary in the Square Point Of Sale app, they were able to fix several leaks and reduced the OOM crash rate by 94%.

